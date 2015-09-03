import java.util.Vector;

public class Instantiator
{
	public Instantiator() {}

	//
	// Main instantiation routine for region synthesis
	//
	public Vector<Region> instantiate(RegionTemplate theTemplate)
	{
	    Region workingRegion = new Region();

	    // Begin with the origin as the bottom-left point by default
	    Point bottomLeft = new Point(0, 0);
	    Point topLeft = new Point(0, 0);

	    // Handle vertical line
	    if (theTemplate.leftIsPoint())
	    {
	        workingRegion.instantiateLeft(bottomLeft);
	    }
	    else
	    {
	        int vertical = RandomGenerator.nextInt(1, Constants.MAX_VERTICAL_SHIFT);
	        topLeft = bottomLeft.plus(new Point(0, vertical));

	        workingRegion.instantiateLeft(new LineSegment(bottomLeft, topLeft));
	    }

	    Vector<Region> regions = new Vector<Region>();

	    constructTopBottom(theTemplate, regions, workingRegion);

	    return regions;
	}

	public boolean constructTopBottom(RegionTemplate theTemplate, Vector<Region> regions, Region workingRegion)
	{
	    return true;
	}

	//\Procedure{Construct}{$\region$, $\bottomHead :: \bottomTail$, $\topHead :: \topTail$, $\funcs$}
//	    \If { $\bottomTail = \emptyset$ and $\topTail = \emptyset$ }
//	    \ElsIf { $\bottomTail = \emptyset$ }
//	    \ElsIf { $\topTail = \emptyset$ }
//	    \EndIf
	//
//	    \Comment{Generate Sequence that has Lesser $x$ Coordinate}
//	    \State {$\func \gets \pick{\funcs \cup \set{ \segment } }$}
//	    \If {$\func = \segment$}
//	    \ElsIf { $\bottomLeftXOf{\region} \leq \topLeftXOf{\region}$ }
//	        \If { $\neg$\Call{Append}{$\func$, $\bottomLeftOf{\region}$, $\topOf{\region}$} }
//	            \State { \textbf{return} $false$ }
//	        \EndIf
	//
//	        \State \Call{Construct}{$\region$, $\bottomTail$, $\topHead :: \topTail$, $\funcs$}
//	    \Else
//	        \If { $\neg$\Call{Append}{$\func$, $\topLeftOf{\region}$, $\bottomOf{\region}$} }
//	            \State { \textbf{return} $false$ }
//	        \EndIf
	//
//	       \State  \Call{Construct}{$\region$, $\bottomHead :: \bottomTail$, $\topTail$, $\funcs$}
//	    \EndIf
	//\EndProcedure
	//
	//\State {}

	public boolean append(BoundedFunction.FunctionT func, Point point, Region region,
	                      long MAX_X, int MAX_ATTEMPTS, int MAX_SUCCESSES)
	{
	    boolean intersect = true;
	    int attempts = 0;

	    while (intersect && attempts < MAX_ATTEMPTS)
	    {
	        int a = RandomGenerator.nextInt(Constants.MAX_VERTICAL_STRETCH);
	        int b = RandomGenerator.nextInt(Constants.MAX_HORIZONTAL_STRETCH);

	        //
	        // Construct the basic function; does it work?
	        // If not, try:
	        //    1) all combinations of reflections with same stretching / shrinking
	        //    2) all combinations of reflections with horizontal shrinking / stretching

	        BoundedFunction f = new BoundedFunction(func, a, b, point.getX(), point.getY());

	        //
	        // Work backward from most recent to least recent
	        //
//	        for
//	        {
	//
//	        }
	    }
	    return true;
	}

	public boolean satisfies(BoundedFunction f, Vector<Bound> bounds)
	{
		// An empty set by default satisfies the function
		if (bounds.isEmpty()) return true;
		
		//
		// Check in reverse to ensure the function satisfies each function in the bounds 
		//
	    for (int index = bounds.size() - 1; index >= 0; index++)
	    {
//            if (bounds[index].intersects(f))
//            {
//                
//            }
	    }

	    return true;
	}

	//\Procedure{Append}{$\func$, $(h, k)$, $\sequence$, $X$, $MAX$}
//	    \State {$intersect \gets true$}
//	    \State {$attempt \gets 0$}
//	    \While {$intersect = true \wedge attempts < MAX$ }
//	        \State {$a \gets \pick{\R}$}
//	        \State {$b \gets \pick{\R}$}
//	        \State {$f \gets a \cdot \func( b \cdot (x - h) ) + k$}
//	        \ForAll {$s \in \sequence$}
//	            \If {$\neg \boundedIntersect{s}{f}{h}{X}$}
//	                \State {$intersect \gets false$}
//	            \EndIf
//	        \EndFor
//	        \State {$attempt \gets attempt + 1$}
//	    \EndWhile
//	    \State {\textbf{return} $true$}
	//\EndProcedure

}
