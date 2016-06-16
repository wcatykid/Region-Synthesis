import java.io.Serializable;
import java.util.Vector;

public class TopBottomBound implements Serializable
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 3742451116366675419L;
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
    	if (b.getBoundT() != Bound.BoundT.HORIZONTAL_LINE ||
    		!(!bounds.isEmpty() && bounds.lastElement().getBoundT() == Bound.BoundT.HORIZONTAL_LINE))
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
            while (c + dupCount < this.bounds.size() && this.bounds.elementAt(c) == this.bounds.elementAt(c + dupCount) )
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
    
    public int numberOfBounds() {
    	return bounds.size();
    }
    
    public TopBottomBound clone() {
    	return null;
    }
}