import java.util.Collections;
import java.util.Vector;

public class TopBottomTemplate
{
    protected Vector<Bound.BoundT> templateBounds;

    public TopBottomTemplate()
    {
        super(); 
        templateBounds = new Vector<Bound.BoundT>();
    }

    public TopBottomTemplate(Vector<Bound.BoundT> that)
    {
        templateBounds = new Vector<Bound.BoundT>(that);
    }

    public int length() { return templateBounds.size(); }

    public void addBound(Bound.BoundT bound)
    {
        templateBounds.addElement(bound);
    }
    
    public boolean verify()
    {
        return !consecutiveVerticals() && minimalFunctions();
    }

    private boolean consecutiveVerticals()
    {
        if (templateBounds.size() < 2) return false;

        for (int index = 0; index < templateBounds.size() - 1; index++)
        {
            if (templateBounds.elementAt(index) == Bound.BoundT.VERTICAL_LINE)
            {
                if (templateBounds.elementAt(index) == templateBounds.elementAt(index + 1)) return true;
            }
        }

        return false;
    }

    // A valid top / bottom sequence has at least one function
    private boolean minimalFunctions()
    {
        return templateBounds.contains(Bound.BoundT.FUNCTION);
    }
    
    public boolean equals(Object obj)
    {
        if (obj == null) return false;

        if (!(obj instanceof TopBottomTemplate)) return false;

        TopBottomTemplate that = (TopBottomTemplate)obj;

        if (this.length() != that.length()) return false;

        for (int b = 0; b < templateBounds.size(); b++)
        {
            if (!this.templateBounds.elementAt(b).equals(that.templateBounds.elementAt(b))) return false;
        }

        return true;
    }

    public boolean notEquals(Object obj)
    {
        return !this.equals(obj);
    }

    public String toString()
    {
        String s = "";
        s += "{ ";

        int c = 0;
        while (c < this.templateBounds.size())
        {
            int dupCount = 1;
            while (c + dupCount < this.templateBounds.size() && this.templateBounds.elementAt(c) == this.templateBounds.elementAt(c + dupCount)) dupCount++;
            
            s += Bound.BoundT.convertFromBound(this.templateBounds.elementAt(c));
            if (dupCount > 1) s += dupCount;
            s += " ";

            c += dupCount;
        }

        return s + "}";
    }
}
