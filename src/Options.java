import java.util.Vector;

//
// A aggregation class for options specified in the command-line
//
public class Options
{
	private String[] args;
	private Vector<String> templateFiles;
    public Vector<String> getFiles() { return templateFiles; }

    public static boolean DEBUG;
    
    public Options(String[] args)
    {
    	this.args = args;
    	this.templateFiles = new Vector<String>();
    }

    public boolean parseCommandLine()
    {
        for (int i = 0; i < args.length; i++)
        {
            if (args[i].charAt(0) == '-')
            {
                if (!handleOption(i)) return false;
            }
            else if (args[i].endsWith("template-specs.txt"))
            {
                templateFiles.add(args[i]);
            } else if (args[i].endsWith("options.txt")) {
            	OptionsFileParser optionsFileParser = new OptionsFileParser(args[i]);
            	optionsFileParser.parseFile();
            }
        }

        return true;
    }

    //
    // Deal with the actual options specified on the command-line.
    //
    private boolean handleOption(int index)
    {
        if (args[index].equalsIgnoreCase("-d") || args[index].equalsIgnoreCase("-debug"))
        {
            Options.DEBUG = true;
            return true;
        }
        // Forces contact to the Wolfram / Alpha Engine
        else if (args[index].equalsIgnoreCase("-f") || args[index].equalsIgnoreCase("-force-contact"))
        {
        	Constants.CONTACT_WA_ENGINE = true;
            return true;
        }

        return false;
    }
}