//import java.lang.*;
import java.io.FileNotFoundException;
import java.util.Vector;

//
// A parser for the template file specifications.
//
public class TemplateFileParser
{
    private static final String TEMPLATE_DELIMITER = "END";
    private String theFile;
    private Vector<RegionTemplate> templates;
    public Vector<RegionTemplate> getTemplates() { return templates; }

    TemplateFileParser(String f)
    {
        theFile = f;
        templates = new Vector<RegionTemplate>();
    }

    public boolean parse()
    {
        PeekableScanner input = null;

        try
        {
            input = new PeekableScanner(theFile);
        }
        catch (FileNotFoundException e)
        {
            System.err.println("File not found: " + theFile);
            return false;
        }

        while(input.hasNext())
        {
            RegionTemplate template = null;
            try
            {
                template = readTemplate(input);
            }
            catch (IllegalArgumentException e)
            {
                e.printStackTrace(System.err);
            }
            catch (Exception e)
            {
                //
                // Read until "END" or EOF
                //
                while (input.hasNext())
                {
                    if (input.peek().toUpperCase().equals(TEMPLATE_DELIMITER)) break;

                    // Read the next 
                    input.next();
                }
            }

            if (template != null) templates.add(template);
        }

        input.close();

        return true;
    }

    private RegionTemplate readTemplate(PeekableScanner input) throws IllegalArgumentException
    {
        LeftRightTemplate left = null;
        LeftRightTemplate right = null;
        TopBottomTemplate top = null;
        TopBottomTemplate bottom = null;

        boolean[] tracker = new boolean[4];

        // Loop until all parts of the template are acquired
        for (int count = 0; count < 4; count++)
        {
            String side = input.next();

            // Convert to enumerator value
            RegionTemplate.SideT sideType = RegionTemplate.SideT.UNRECOGNIZED;
            try
            {
                sideType = RegionTemplate.SideT.convertToSide(side.charAt(0));
            }
            catch (IllegalArgumentException e)
            {
                // Continue parsing the next template....
                System.err.println("Unexpected side values specified in template: " + side);
                throw new IllegalArgumentException();   
            }

            if (tracker[sideType.getValue()])
            {
                throw new IllegalArgumentException("Template side specified multiple times: " + side);
            }

            // Handle each side
            switch(side.charAt(0))
            {
                case 'L':
                    left = readLeftRight(input);
                    break;

                case 'R':
                    right = readLeftRight(input);
                    break;

                case 'T':
                    top = readTopBottom(input);
                    break;

                case 'B':
                    bottom = readTopBottom(input);
                    break;

                default:
                    throw new IllegalArgumentException("Unrecognized side in switch: " + side.charAt(0));
            }

            // Update the tracker to indicate completion
            tracker[sideType.getValue()] = true;
        }

        RegionTemplate template = new RegionTemplate(left, top, right, bottom);

        if (!template.verify())
        {
            throw new IllegalArgumentException("Template did not create a valid region: \n" + template);
        }
        
        return template;
    }

    LeftRightTemplate readLeftRight(PeekableScanner input)
    {
        //
        // Peek at the next character to determine if it is an integer character
        //
        if (Character.isDigit(input.peek().charAt(0)))
        {
            throw new IllegalArgumentException("Digit not expected in left / right template");
        }

        char code = input.next().charAt(0);

        System.err.println("|" + code + "|\n");

        switch(code)
        {
            case 'P':
                return new LeftRightPointTemplate();

            case 'V':
                return new LeftRightVerticalTemplate();

            case 'F':
                throw new IllegalArgumentException("A function cannot define a left / right template");

            default:
                throw new IllegalArgumentException("Unrecognized code: " + code);
        }
    }

    TopBottomTemplate readTopBottom(PeekableScanner input)
    {
        // Read the entire sequence, one element at a time
        Vector<Bound.BoundT> sequence = new Vector<Bound.BoundT>();

        final String ALLOWABLE = "0123456789PpVvFfHhLlSsCceGg";

        for (char code = input.peek().charAt(0); ALLOWABLE.indexOf(code) != -1; code = input.peek().charAt(0))
        {
            // Peek at the next character to determine if it is an integer character
            //if (Character.isDigit(code))
            //{
            //    throw new IllegalArgumentException("Digit may only follow 'F' (for functions)");
            //}

            code = input.next().charAt(0);

            System.err.println("|" + code + "|\n");

            switch(code)
            {
                case 'P':
                    throw new IllegalArgumentException("A point may not be specified for top / bottom template");

                case 'V':
                    if (!sequence.isEmpty() && sequence.lastElement() == Bound.BoundT.VERTICAL_LINE)
                    {
                        throw new IllegalArgumentException("A vertical line may not follow a vertical line");
                    }

                    if (input.hasNext() && Character.isDigit(input.peek().charAt(0)))
                    {
                        throw new IllegalArgumentException("A numeric value may not be repeated for vertical lines");
                    }

                    sequence.add(Bound.BoundT.VERTICAL_LINE);
                    break;

                case 'F':
                    // End of file single function
                    if (!input.hasNext())
                    {
                        sequence.add(Bound.BoundT.FUNCTION);
                        break;
                    }

                    // Multiple functions
                    if (Character.isDigit(input.peek().charAt(0)))
                    {
                        int repetitions = Integer.parseInt(input.next());

                        System.err.println("|" + repetitions + "|\n");

                        for (int r = 0; r < repetitions; r++)
                        {
                            sequence.add(Bound.BoundT.FUNCTION);
                        }
                        break;
                    }
                    // Single Function
                    else sequence.add(Bound.BoundT.FUNCTION);
                    break;
                    
                case 'H': sequence.add(Bound.BoundT.HORIZONTAL_LINE); break;  //horizontal line
                case 'L': sequence.add(Bound.BoundT.LINEAR); break;  //line/linear
                case '2': sequence.add(Bound.BoundT.PARABOLA); break;  //2nd degree
                case '3': sequence.add(Bound.BoundT.CUBIC);break;  //3rd degree
                case '4': sequence.add(Bound.BoundT.QUARTIC);break;  //4th degree
                case '5': sequence.add(Bound.BoundT.QUINTIC);break;  //5th degree
                case 'S': sequence.add(Bound.BoundT.SINE);break;  //Sine
                case 'C': sequence.add(Bound.BoundT.COSINE);break;  //Cosine
                case 'e': sequence.add(Bound.BoundT.EXPONENTIAL);break;  //Exponential
                case 'G': sequence.add(Bound.BoundT.LOGARITHMIC);break;  //Log

                default:
                    throw new IllegalArgumentException("Unrecognized code: |" + code);
            }

            if (!input.hasNext()) break;
        }

        return new TopBottomTemplate(sequence);
    }
}
