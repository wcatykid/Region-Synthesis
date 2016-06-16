import java.io.Serializable;

public class LeftRightBound implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1748487694703940271L;
	protected Bound bound;

	public LeftRightBound() { super(); }
    
    public LeftRightBound(Bound b)
    {
    	this.bound = b;
    }

    public LeftRightBound(LeftRightBound that)
    {
    	this.bound = (Bound)that.bound.clone();
    }

    public boolean equals(Object obj)
    {
    	if (obj == null) return false;
    	
    	if (!(obj instanceof LeftRightBound)) return false;

    	LeftRightBound that = (LeftRightBound)obj;
    	
        return this.bound.equals(that.bound);
    }

    public boolean notEquals(Object obj)
    {
        return !this.equals(obj);
    }
    
    public String toString()
    {
    	return (bound != null) ? this.bound.toString() : "null";
    }
    
    @Override
    public Object clone() {
    	
    	return null;
    }
}
