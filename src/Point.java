import java.io.Serializable;

public class Point implements Serializable
{
    /**
	 * 
	 */
	private static final long serialVersionUID = -1459271016405991323L;
	protected double x;
    protected double y;
    
    public Point()
    {
        x = 0;
        y = 0;
    }

    public Point(double argX, double argY)
    {
        x = argX;
        y = argY;
    }
    
    public Point(Point that)
    {
        x = that.x;
        y = that.y;
    }
    
    public double getX() { return x; }
    public double getY() { return y; }

    //
    // Binary Subtraction
    //
    public Point minus(Point a, Point b)
    {
        return new Point(a.x - b.x, a.y - b.y);
    }

    public Point plus(Point that)
    {
        return new Point(this.x + that.x, this.y + that.y);
    }

    // Assignment Operator
    public void assign(Point that)
    {
        this.x = that.x;
        this.y = that.y;
    }

    //
    //
    // Relational Operators implementing Lexicographic Ordering
    //
    //
    public boolean equals(Object obj)
    {
    	if (obj == null) return false;
    	
    	if (!(obj instanceof Point)) return false;

    	Point that = (Point)obj;
    	
        return Utilities.compareValues(this.x, that.x) && Utilities.compareValues(this.y, that.y);
    }
    
    public Point clone() {
    	return new Point(this.x, this.y);
    }

    public boolean notEquals(Point that)
    {
        return !this.equals(that);
    }

    // Lexicographic Ordering
    public boolean lessThan(Point that)
    {
        // Equal x, Compare y
        if (Utilities.compareValues(this.x, that.x)) return this.y < that.y;

        // Compare x
        return this.x < that.x;
    }

    // Lexicographic Ordering
    public boolean lessThanOrEqual(Point that)
    {
        return this.equals(that) || this.lessThan(that);
    }

    // Lexicographic Ordering
    public boolean greaterThan(Point that)
    {
        // Equal x, Compare y
        if (Utilities.compareValues(this.x, that.x)) return this.y > that.y;

        // Compare x
        return this.x > that.x;
    }

    // Lexicographic Ordering
    public boolean greaterThanOrEqual(Point that)
    {
        return this.equals(that) || this.greaterThan(that);
    }

    public String toString()
    {
        return "(" + this.x + ", " + this.y + ")";
    }
    
}
