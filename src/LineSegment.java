import java.io.Serializable;

public class LineSegment extends Bound implements Serializable
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 7752196716541235809L;
	protected Point pt1;  //Bottom point
    protected Point pt2;  //Top point
		    
    public LineSegment() { super(Bound.BoundT.VERTICAL_LINE); }
    public LineSegment(Point e1,  Point e2)
    {
    	super(Bound.BoundT.VERTICAL_LINE);
        pt1 = e1;
        pt2 = e2;
    }

    public LineSegment(LineSegment that)
    {
    	super(Bound.BoundT.VERTICAL_LINE);
        pt1 = that.pt1;
        pt2 = that.pt2;
    }

    public Object clone() 
    {
        return new LineSegment(this.pt1, this.pt2);
    }

    public String toString() 
    {
        return "Segment(" + this.pt1 + ", " + this.pt2 + ")";
    }

    public boolean equals(Object obj) 
    {
    	if (obj == null) return false;
    	
    	if (!(obj instanceof LineSegment)) return false;

    	LineSegment that = (LineSegment)obj;
    	
        if (this.pt1 == that.pt1 && this.pt2 == that.pt2) return true;

        if (this.pt1 == that.pt2 && this.pt2 == that.pt1) return true;

        return false;
    }

    public boolean notEquals(Object that) 
    {
        return !equals(that);
    }
    
    public Point getLeftTopPoint() {return pt2; }
    public Point getLeftBottomPoint() {return pt1; }
}
