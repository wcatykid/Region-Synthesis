public class BoundedFunction
{
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


	public BoundedFunction() { super(); }

	public BoundedFunction(BoundedFunction that)
	{
		super();

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
		super();

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
		super();

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
		super();

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

	public String toString()
	{
		return theFunc.toFunctionString(a, b, h, k);
	}
	
	public double evaluateAtPoint(double x)
	{
		return theFunc.evaluate(a, b, h, k, x);
	}
	
	public enum FunctionT
	{
		HORIZONTAL_LINE(0),
		LINEAR(1),
		PARABOLA(2),
		CUBIC(3),
		QUARTIC(4),
		QUINTIC(5),
		POLYNOMIAL(6),

		EXPONENTIAL(7),
		LOGARITHMIC(8),

		SINE(9),
		COSINE(10),

		UNSPECIFIED(11);

		private final int value;
		private FunctionT(int value) { this.value = value; }
		public int getValue() { return value; }

		public double evaluate(double a, double b, double h, double k, double x)
		{
			switch (this)
			{
  			    case HORIZONTAL_LINE:
                    return k;
  			    
			    case LINEAR:
                    return a * b * (x - h) + k;

			    case PARABOLA:
                    return a * Math.pow(b * (x - h), 2) + k;

			    case CUBIC:
                    return a * Math.pow(b * (x - h), 3) + k;
			    
		   	    case QUARTIC:
                    return a * Math.pow(b * (x - h), 4) + k;

		   	    case QUINTIC:
                    return a * Math.pow(b * (x - h), 5) + k;

		   	    case POLYNOMIAL:
					throw new IllegalArgumentException(POLYNOMIAL + " not recognized FunctionT.evaluate()");

			    case EXPONENTIAL:
                    return a * Math.pow(Math.E, b * (x - h)) + k;

			    case LOGARITHMIC:
                    return a * Math.log(b * (x - h)) + k;

			    case SINE:
                    return a * Math.sin(b * (x - h)) + k;
			
			    case COSINE:
                    return a * Math.cos(b * (x - h)) + k;

			    case UNSPECIFIED:
			    default:
				    throw new IllegalArgumentException(this.value + " not recognized FunctionT.evaluate()");
			}
		}
		public String toFunctionString(double a, double b, double h, double k)
		{
			switch (this)
			{
  			    case HORIZONTAL_LINE:
                    return Double.toString(k);
  			    
			    case LINEAR:
                    return a + " * ( " + b + " * (x - " + h + ") +" + k;

			    case PARABOLA:
                    return a + " * ( " + b + " * (x - " + h + ")^2 +" + k;

			    case CUBIC:
                    return a + " * ( " + b + " * (x - " + h + ")^3 +" + k;
			    
		   	    case QUARTIC:
                    return a + " * ( " + b + " * (x - " + h + ")^4 +" + k;

		   	    case QUINTIC:
                    return a + " * ( " + b + " * (x - " + h + ")^5 +" + k;

		   	    case POLYNOMIAL:
					throw new IllegalArgumentException(POLYNOMIAL + " not recognized FunctionT.toFunctionString()");

			    case EXPONENTIAL:
                    return a + " * e ^ ( " + b + " * (x - " + h + ") +" + k;

			    case LOGARITHMIC:
                    return a + " * ln ( " + b + " * (x - " + h + ") +" + k;

			    case SINE:
                    return a + " * sin ( " + b + " * (x - " + h + ") +" + k;
			
			    case COSINE:
                    return a + " * cos ( " + b + " * (x - " + h + ") +" + k;

			    case UNSPECIFIED:
			    default:
				    throw new IllegalArgumentException(this.value + " not recognized FunctionT.toString()");
			}
		}
		public String toString()
		{
			switch (this)
			{
  			    case HORIZONTAL_LINE:
			    case LINEAR:
			    case PARABOLA:
			    case CUBIC:
		   	    case QUARTIC:
			    case QUINTIC:
			    case POLYNOMIAL:
                    return "";

			    case EXPONENTIAL:
				    return "e";

			    case LOGARITHMIC:
				    return "ln";

			    case SINE:
				    return "sin";
			
			    case COSINE:
				    return "cos";

			case UNSPECIFIED:
			default:
				throw new IllegalArgumentException(this.value + " not recognized FunctionT.toString()");
			}
		}
	}
}
