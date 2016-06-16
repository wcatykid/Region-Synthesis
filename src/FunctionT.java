public enum FunctionT
	{
		HORIZONTAL_LINE(0),
		LINEAR(1),
		PARABOLA(2),
		CUBIC(3),
		QUARTIC(4),
		QUINTIC(5),
		//POLYNOMIAL(6),

		EXPONENTIAL(6),
		LOGARITHMIC(7),

		SINE(8),
		COSINE(9);

		//UNSPECIFIED(11);

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

		   	    //case POLYNOMIAL:
				//	throw new IllegalArgumentException(POLYNOMIAL + " not recognized FunctionT.evaluate()");

			    case EXPONENTIAL:
                    return a * Math.pow(Math.E, b * (x - h)) + k;

			    case LOGARITHMIC:
                    return a * Math.log(b * (x - h)) + k;

			    case SINE:
                    return a * Math.sin(b * (x - h)) + k;
			
			    case COSINE:
                    return a * Math.cos(b * (x - h)) + k;

			    //case UNSPECIFIED:
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
                    return a + " * ( " + b + " * (x - " + h + ") ) + " + k;

			    case PARABOLA:
                    return a + " * ( " + b + " * (x - " + h + ")^2 ) + " + k;

			    case CUBIC:
                    return a + " * ( " + b + " * (x - " + h + ")^3 ) + " + k;
			    
		   	    case QUARTIC:
                    return a + " * ( " + b + " * (x - " + h + ")^4 ) + " + k;

		   	    case QUINTIC:
                    return a + " * ( " + b + " * (x - " + h + ")^5 ) + " + k;

		   	    //case POLYNOMIAL:
				//	throw new IllegalArgumentException(POLYNOMIAL + " not recognized FunctionT.toFunctionString()");

			    case EXPONENTIAL:
                    return a + " * e ^ ( " + b + " * (x - " + h + ") ) +" + k;

			    case LOGARITHMIC:
                    return a + " * ln ( " + b + " * (x - " + h + ") ) +" + k;

			    case SINE:
                    return a + " * sin ( " + b + " * (x - " + h + ") ) +" + k;
			
			    case COSINE:
                    return a + " * cos ( " + b + " * (x - " + h + ") ) +" + k;

			    //case UNSPECIFIED:
			    default:
				    throw new IllegalArgumentException(this.value + " not recognized FunctionT.toString()");
			}
		}
		public String toString()
		{
			switch (this)
			{
  			    case HORIZONTAL_LINE: return "horizontal line";
			    case LINEAR: return "linear";
			    case PARABOLA: return "parabola";
			    case CUBIC: return "cubic";
		   	    case QUARTIC: return "quartic";
			    case QUINTIC: return "quintic";
			    //case POLYNOMIAL: return "polynomial";
                    //return "";

			    case EXPONENTIAL:
				    return "e";

			    case LOGARITHMIC:
				    return "ln";

			    case SINE:
				    return "sin";
			
			    case COSINE:
				    return "cos";

			//case UNSPECIFIED:
			default:
				throw new IllegalArgumentException(this.value + " not recognized FunctionT.toString()");
			}
		}
	}