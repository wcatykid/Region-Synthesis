public class Region
{
    protected LeftRightBound left;
    protected TopBottomBound top;
    protected LeftRightBound right;
    protected TopBottomBound bottom;
        
    public Region() { super(); }

    // Left, Top, Right, Bottom
    Region(LeftRightBound ell, TopBottomBound t, LeftRightBound r, TopBottomBound b)
    {
    	left = ell;
    	top = t;
    	right = r;
    	bottom = b;
    }

    public Region(Region that)
    {
    	left = that.left;
    	top = that.top;
    	right = that.right;
    	bottom = that.bottom;
    }

    public void instantiateLeft(Point pt)
    {
        left = new LeftRightBound(new PointBound(pt));
    }
    
    public void instantiateLeft(LineSegment line)
    {
        left = new LeftRightBound(new LineSegment(line));
    }

    public boolean equals(Object obj)
    {
    	if (obj == null) return false;
    	
    	if (!(obj instanceof Region)) return false;

    	Region that = (Region)obj;
    	
    	if (!this.left.equals(that.left)) return false;

        if (!this.right.equals(that.right)) return false;

        if (!this.top.equals(that.top)) return false;

        if (!this.bottom.equals(that.bottom)) return false;

    	return true;
    }

    public String toString()
    {
        String s = "";

        s += "Left: " + this.left + "\n";
        s += "Top: " + this.top + "\n";
        s += "Right: " + this.right + "\n";
        s += "Bottom: " + this.bottom + "\n";

        return s;
    }
}
