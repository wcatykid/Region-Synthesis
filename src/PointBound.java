
public class PointBound extends Bound
{
	protected Point point;

	public PointBound()
	{
		super();
		point = null;
	}

    public PointBound(Point pt)
    {
    	point = pt;
    }

    public PointBound(PointBound that)
    {
        point = that.point;
    }

    public Object clone()
    {
        return new PointBound(this.point);
    }

    public String toString()
    {
        return "PointBound(" + this.point.toString() + ")";
    }

    public boolean equals(Object obj)
    {
    	if (obj == null) return false;
    	
    	if (!(obj instanceof PointBound)) return false;

    	PointBound that = (PointBound)obj;

    	return this.point.equals(that.point);
    }

    public boolean notEquals(Object obj)
    {
	    return !equals(obj);
    }
}
