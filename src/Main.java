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

		// Create the resulting set of problems: areas, volumes, etc.

		// Emit the instantiations as problems

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
