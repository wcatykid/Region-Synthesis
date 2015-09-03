public class RegionTemplate
{
    protected LeftRightTemplate left;
    protected TopBottomTemplate top;
    protected LeftRightTemplate right;
    protected TopBottomTemplate bottom;
    
    public RegionTemplate()
    {
        left = null;
        top = null;
        right = null;
        bottom = null;
    }

    // Left, Top, Right, Bottom
    RegionTemplate(LeftRightTemplate ell, TopBottomTemplate t,
    		       LeftRightTemplate r, TopBottomTemplate b)
    {
    	left = ell;
    	top = t;
    	right = r;
    	bottom = b;
    }

    public boolean leftIsPoint() { return left instanceof LeftRightPointTemplate; }
    public boolean leftIsVertical() { return left instanceof LeftRightVerticalTemplate; }
    public boolean rightIsPoint() { return right instanceof LeftRightPointTemplate; }
    public boolean rightIsVertical() { return right instanceof LeftRightVerticalTemplate; }

    //
    // A reasonable template defines:
    //     * a single template for left / right.
    //     * at least one function in the top / bottom.
    //
    public boolean verify()
    {
        if (left == null || top == null || right == null || bottom == null) return false;
        
        return left.verify() && top.verify() && right.verify() && bottom.verify();
    }
    
    public boolean equals(Object obj)
    {
    	if (obj == null) return false;
    	
    	if (!(obj instanceof RegionTemplate)) return false;

    	RegionTemplate that = (RegionTemplate)obj;
    	
        if (!this.left.equals(that.left)) return false;

        if (!this.top.equals(that.top)) return false;

        if (!this.right.equals(that.right)) return false;

        if (!this.bottom.equals(that.bottom)) return false;

        return true;
    }
    
    public String toString()
    {
	    String s = "";
	    s = "Left: " + this.left + "\n";
	    s += "Top: " + this.top + "\n";
	    s += "Right: " + this.right + "\n";
	    s += "Bottom: " + this.bottom + "\n";
        return s;
    }
    
    public enum SideT
    {
        LEFT(0),
        TOP(1),
        RIGHT(2),
        BOTTOM(3),
        UNRECOGNIZED(4);

        private final int value;
        private SideT(int value) { this.value = value; }
        public int getValue() { return value; }
        public static SideT convertToSide(char c) throws IllegalArgumentException
        {
            switch (c)
            {
                case 'l':
                case 'L':
                    return SideT.LEFT;
                case 't':
                case 'T':
                    return SideT.TOP;
                case 'r':
                case 'R':
                    return SideT.RIGHT;
                case 'b':
                case 'B':
                    return SideT.BOTTOM;

                default:
                   throw new IllegalArgumentException(c + " not recognized " + "convertToSide");
            }
        }
    }
}
