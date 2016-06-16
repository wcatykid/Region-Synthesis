
public class PointBound extends Bound
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3190276295795816736L;
	protected Point point;

	public PointBound()
	{
		super(Bound.BoundT.POINT);
		point = null;
	}

    public PointBound(Point pt)
    {
    	super(Bound.BoundT.POINT);
    	point = pt;
    }

    public PointBound(PointBound that)
    {
    	super(Bound.BoundT.POINT);
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

    public Point getLeftTopPoint() {return point; }
    public Point getLeftBottomPoint() {return point; }
}
