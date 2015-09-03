import java.util.Vector;

public class TopBottomBound
{
    protected Vector<Bound> bounds;
    
    public TopBottomBound()
    {
    	bounds = new Vector<Bound>();
    }

    public TopBottomBound(TopBottomBound that)
    {
    	bounds = new Vector<Bound>();
    	
    	for (Bound bound : that.bounds)
    	{
    		this.bounds.addElement((Bound)bound.clone());
    	}
    }
    
    public TopBottomBound(Vector<Bound> that)
    {
    	bounds = new Vector<Bound>();
    	
    	for (Bound bound : that)
    	{
    		this.bounds.addElement((Bound)bound.clone());
    	}
    }

    public void addBound(Bound b)
    {
    	this.bounds.addElement(b);
    }

    int length() { return bounds.size(); }

    public boolean equals(Object obj)
    {
    	if (obj == null) return false;
    	
    	if (!(obj instanceof TopBottomBound)) return false;

    	TopBottomBound that = (TopBottomBound)obj;
    	
        if (this.length() != that.length()) return false;

        for (int b = 0; b < bounds.size(); b++)
        {
            if (!this.bounds.elementAt(b).equals(that.bounds.elementAt(b))) return false;
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
        while (c < this.bounds.size())
        {
            int dupCount = 1;
            while (this.bounds.elementAt(c) == this.bounds.elementAt(c + dupCount) &&
                   c < this.bounds.size())
            {
                dupCount++;
            }
            s += this.bounds.elementAt(c).toString();
            if (dupCount > 1) s += dupCount;
            s += " ";

            c += dupCount;
        }

        return s + "}";
    }
}