
public class LeftRightBound
{
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
    	return this.bound.toString();
    }
}
