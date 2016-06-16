import java.util.Vector;

public class Main
{
	public static void main(String[] args)
	{
		if (args.length < 2)
		{
			System.err.println("Usage: <program> -debug <template-files>");
			return;
		}

		//
		// Global options object.
		//
		Options options = new Options(args);
		if (!options.parseCommandLine())
		{
			System.err.println("Command-line parsing failed; exiting.");
			return;
		}

		// Acquire the set of template files to process
		Vector<String> files = options.getFiles();

		for (String file : files)
		{
			if (Options.DEBUG) System.out.println("Processing " + file + "...");

			if (!process(file))
			{
				System.err.println("Parse error in main: error in processing template file " + file);
			}
		}
	}

	//
	// Main processing routine
	//
	private static boolean process(String file)
	{
		Vector<RegionTemplate> templates;

		// Parse the template file
		templates = processTemplateFile(file);

		// Print the templates read from the file
		if (Options.DEBUG)
		{
			for (RegionTemplate template : templates)
			{
				System.out.println(template);
			}
		}

		// Instantiate those templates
		Instantiator instantiator = new Instantiator();
		
		Vector<Vector<Region>> instantiatedRegions = new Vector<>(); //A vector of all outputs from the instantiator.
		
		boolean DEBUG = Options.DEBUG;  //It's faster for me.
		
		if (DEBUG) 
		{
			System.out.println("Starting the instantiation process");
		}
		
		for (RegionTemplate template : templates) 
		{
			Vector<Region> newRegion = instantiator.instantiate(template);
			instantiatedRegions.add(newRegion);
			
			if (DEBUG) 
			{
				System.out.println("Instantiated: \n" + newRegion);
				System.out.println("Instanciated " + newRegion.size() + " regions.");
			}
		}

		// Create the resulting set of problems: areas, volumes, etc.

		// Emit the instantiations as problems

		/*
		CasInterface tester = new CasInterface();
		for (Region temp : instantiatedRegions.lastElement()) {
		//Region temp = instantiatedRegions.lastElement();
		
		
			BoundedFunction lastTop = (BoundedFunction) temp.top.bounds.lastElement();
			BoundedFunction lastBottom = (BoundedFunction) temp.bottom.bounds.lastElement();
			System.out.println("Intersect between f(x) = " + lastBottom.toMathematicaString() + " and g(x) = " + lastTop.toMathematicaString());
			System.out.print(tester.getInersection( lastBottom, lastTop ));
		}
		*/
		
		return true;
	}

	//
	// Parse the file and acquire templates
	//
	private static Vector<RegionTemplate> processTemplateFile(String theFileStr)
	{
		TemplateFileParser parser = new TemplateFileParser(theFileStr);

		if (!parser.parse())
		{
			throw new IllegalArgumentException("Parse error in main: error in processing template file " + theFileStr);
		}

		System.err.println("Number of templates: " + parser.getTemplates().size());

		return parser.getTemplates();
	}

}
