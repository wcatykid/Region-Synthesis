import java.io.Serializable;

public class BoundedFunction extends Bound implements Serializable 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2883462033822784426L;
	//
	// g(x) = a f( b ( x - h )) + k
	//
	protected double a;
	protected double b;
	protected double h;
	protected double k;

	FunctionT theFunc;

	public double getA() { return a; }
	public double getB() { return b; }
	public double getH() { return h; }
	public double getK() { return k; }

	FunctionT getFunc() { return theFunc; }

	//
	// Restricted domain for this function: bounds placed on x 
	//
	protected double leftX;
	protected double rightX;
	
	protected String signOfLeftBound = "<=";
	protected String signOfRightBound = "<";

	public void signOfLeftBound(String replacement){ signOfRightBound = replacement; }
	
	public double getRightX() { return rightX; }
	public void setRightX(double rightX) { this.rightX = rightX; }

	public BoundedFunction() { super(Bound.BoundT.FUNCTION); }

	public BoundedFunction(BoundedFunction that)
	{
		super(Bound.BoundT.FUNCTION);

		this.a = that.a;
		this.b = that.b;
		this.h = that.h;
		this.k = that.k;
		this.theFunc = that.theFunc;

		this.leftX = that.leftX;
		this.rightX = that.rightX;
	}

	public BoundedFunction(FunctionT fType, double horiz, double vert)
	{
		super(Bound.BoundT.FUNCTION);

		this.a = 1;
		this.b = 1;
		this.h = horiz;
		this.k = vert;
		this.theFunc = fType;

		this.leftX = Double.MIN_VALUE;
		this.rightX = Double.MAX_VALUE;
	}

	public BoundedFunction(FunctionT fType, double vertStr, double horizStr, double horiz, double vert)
	{
		super(Bound.BoundT.FUNCTION);

		this.a = vertStr;
		this.b = horizStr;
		this.h = horiz;
		this.k = vert;
		this.theFunc = fType;

		this.leftX = Double.MIN_VALUE;
		this.rightX = Double.MAX_VALUE;
	}

	public BoundedFunction(FunctionT fType, double vertStr, double horizStr, double horiz,
			double vert, double leftBound, double rightBound)
	{
		super(Bound.BoundT.FUNCTION);

		this.a = vertStr;
		this.b = horizStr;
		this.h = horiz;
		this.k = vert;
		this.theFunc = fType;

		this.leftX = leftBound;
		this.rightX = rightBound;
	}

	public void assign(BoundedFunction that)
	{

		this.a = that.a;
		this.b = that.b;
		this.h = that.h;
		this.k = that.k;
		this.theFunc = that.theFunc;

		this.leftX = that.leftX;
		this.rightX = that.rightX;
	}

	public boolean withinBounds(Point pt)
	{
        return pt.getX() >= leftX && pt.getX() <= rightX;
	}
	
	public boolean equals(Object obj)
	{
		if (obj == null) return false;

		if (!(obj instanceof BoundedFunction)) return false;

		BoundedFunction that = (BoundedFunction)obj;

		if (this.theFunc != that.theFunc) return false;

		if (!Utilities.compareValues(this.a, that.a)) return false;

		if (!Utilities.compareValues(this.b, that.b)) return false;

		if (!Utilities.compareValues(this.h, that.h)) return false;

		if (!Utilities.compareValues(this.k, that.k)) return false;

		if (!Utilities.compareValues(this.leftX, that.leftX)) return false;

		if (!Utilities.compareValues(this.rightX, that.rightX)) return false;

		return true;
	}

	public boolean notEquals(Object obj)
	{
		return !this.equals(obj);
	}
	
	public String toMathematicaString() {
		return theFunc.toFunctionString(a, b, h, k);
	}

	public String toString()
	{
		return toMathematicaString() + " { " + leftX + " " + signOfLeftBound +  " x " + signOfRightBound + " " + rightX + "}" ;
	}
	
	public double evaluateAtPoint(double x)
	{
		return theFunc.evaluate(a, b, h, k, x);
	}
	
	public BoundedFunction clone() {
		return new BoundedFunction(this);
	}
	
	public Point getLeftTopPoint() { return new Point(rightX, evaluateAtPoint(rightX)); }    
	public Point getLeftBottomPoint() { return new Point(rightX, evaluateAtPoint(rightX));  } //Since it's a function, there should never be two different points for top and bottom.
}
