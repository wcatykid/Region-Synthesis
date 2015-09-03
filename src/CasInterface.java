import java.util.ArrayList;
import java.util.Vector;

import com.wolfram.alpha.WAEngine;
import com.wolfram.alpha.WAException;
import com.wolfram.alpha.WAPlainText;
import com.wolfram.alpha.WAPod;
import com.wolfram.alpha.WAQuery;
import com.wolfram.alpha.WAQueryResult;
import com.wolfram.alpha.WASubpod;

public class CasInterface
{
	private CasInterface() {}

	public final Vector<Point> intersects(BoundedFunction first, BoundedFunction second)
	{
		//
		// Construct the query equation
		//
		String queryStr = "solve " + first.toString() + " = " + second.toString();

		if (Options.DEBUG)
		{
			System.out.println("Query: " + queryStr);
		}

		//
		// Acquire the results of the query
		//
		String[] result = CasQueryEngine.query(queryStr);

		//
		// Convert the solution back to x's
		//
		ArrayList<Double> xs = parseResult(result);

		//
		// Evaluate to acquire each (x, y) coordinate pair for the intersections
		//
		Vector<Point> points = evaluateAndFormPoints(xs, first, second);

		//
		// Refine the points to those in the bounds of the functions
		//
		return refinePoints(points, first, second);
	}

	private ArrayList<Double> parseResult(String[] args)
	{
		ArrayList<Double> xs = new ArrayList<Double>();

		for (String arg : args)
		{
			// Remove all whitespace from the String
			arg.replaceAll("\\s+","");

			// Extract the <digits> part in "x = <digits>" from the String
			int index = arg.indexOf('=');

			if (index == -1) throw new IllegalArgumentException("While parsing result |" + arg + "| '=' not detected.");

			String value = arg.substring(arg.indexOf('=') + 1);

			xs.add(Double.valueOf(value));
		}

		return xs;
	}

	//
	// Given a sequence of x-values for intersection points, evaluate the y = f(x) value for each and return the resultant points.
	//
	private Vector<Point> evaluateAndFormPoints(ArrayList<Double> xs, BoundedFunction first,
			BoundedFunction second)
	{
		Vector<Point> points = new Vector<Point>();

		for (Double x : xs)
		{
			double y1 = first.evaluateAtPoint(x.doubleValue());
			double y2 = second.evaluateAtPoint(x.doubleValue());

			if (!Utilities.compareValues(y1, y2))
			{
				String err = "f_1(x) = " + first.toString() + " and f_2(x) = " + second.toString();
				err += " evaluate to distinct values for x = " + x + " " + y1 + " != " + y2;
				throw new IllegalArgumentException(err);
			}

			points.add(new Point(x, y1));
		}

		return points;
	}

	//
	// Filter the points from unbounded x-values to those specified by the bounds of each function.
	//
	private Vector<Point> refinePoints(Vector<Point> points, BoundedFunction first,
			                                                 BoundedFunction second)
	{
		Vector<Point> refinedPts = new Vector<Point>();

		for (Point pt : points)
		{
			if (first.withinBounds(pt) && second.withinBounds(pt))
			{
				refinedPts.add(pt);
			}
		}

		return refinedPts;
	}

	private static class CasQueryEngine
	{
		public static String[] query(String arg)
		{
			// String input = "solve x^2 = x^3";
			System.out.println("Query: |" + arg + "|");

			// The WAEngine is a factory for creating WAQuery objects,
			// and it also used to perform those queries. You can set properties of
			// the WAEngine (such as the desired API output format types) that will
			// be inherited by all WAQuery objects created from it. Most applications
			// will only need to create one WAEngine object, which is used throughout
			// the life of the application.
			WAEngine engine = new WAEngine();

			// These properties will be set in all the WAQuery objects created from this WAEngine.
			engine.setAppID(Constants.APPID);
			engine.addFormat("plaintext");

			// Create the query.
			WAQuery query = engine.createQuery();

			// Set properties of the query.
			query.setInput(arg);

			try
			{
				// For educational purposes, print out the URL we are about to send:
				if (Options.DEBUG)
				{
					System.out.println("Query URL:");
					System.out.println(engine.toURL(query));
					System.out.println("");
				}

				// This sends the URL to the Wolfram|Alpha server, gets the XML result
				// and parses it into an object hierarchy held by the WAQueryResult object.
				WAQueryResult queryResult = null;

				if (Constants.CONTACT_WA_ENGINE) engine.performQuery(query);
				else
				{
					System.err.println("WARNING: External contact to Wolfram / Alpha is locally prohibited.");
					return (String[])new Vector<String>().toArray();
				}

				if (queryResult.isError())
				{
					System.out.println("Query error");
					System.out.println("  error code: " + queryResult.getErrorCode());
					System.out.println("  error message: " + queryResult.getErrorMessage());
				}
				else if (!queryResult.isSuccess())
				{
					System.out.println("Query was not understood; no results available.");
				}
				else   // Got a result.
				{
					return extractResultFromPods(queryResult);
				}
			}
			catch (WAException e)
			{
				e.printStackTrace();
			}

			throw new IllegalArgumentException("Wolfram / Alpha Query Problem");
		}

		private static String[] extractResultFromPods(WAQueryResult queryResult)
		{
			if (Options.DEBUG) System.out.println("Successful query. Pods follow:\n");

			Vector<String> result = new Vector<String>();

			for (WAPod pod : queryResult.getPods())
			{
				if (!pod.isError())
				{
					if (Options.DEBUG)
					{
						// We ignored many other types of Wolfram|Alpha output, such as warnings, assumptions, etc.
						// These can be obtained by methods of WAQueryResult or objects deeper in the hierarchy.
						System.out.println(pod.getTitle());
						System.out.println("------------");
						for (WASubpod subpod : pod.getSubpods())
						{
							for (Object element : subpod.getContents())
							{
								if (element instanceof WAPlainText)
								{
									System.out.println(((WAPlainText) element).getText());
									System.out.println("");
								}
							}
						}
						System.out.println("");
					}

					if (pod.getTitle().toUpperCase().contains("RESULT"))
					{
						for (WASubpod subpod : pod.getSubpods())
						{
							for (Object element : subpod.getContents())
							{
								if (element instanceof WAPlainText)
								{
									result.addElement(((WAPlainText) element).getText());
								}
							}
						}
						System.out.println("");
					}
				}
			}

			return (String[])result.toArray();
		}
	}
}