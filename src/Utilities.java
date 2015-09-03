public class Utilities
{
	// Handles negatives and positives.
	public static int modulus(int x, int m) { return (x % m + m) % m; }

	public static boolean isInteger(double x) { return Utilities.compareValues(x, (int)x); }
	public static int GCD(int a, int b) { return b == 0 ? a : GCD(b, a % b); }

	//
	// Comparing double within a threshold
	//
	private static double EPSILON = 0.000001;
	public static boolean compareValues(double a, double b) { return Math.abs(a - b) < EPSILON; }

	// -1 is an error
	public static int integerRatio(double x, double y)
	{
	    return Utilities.compareValues(x / y, Math.floor(x / y)) ? (int)Math.floor(x / y) : -1;
	}

	// -1 is an error
	// A reasonable value for geometry problems must be less than 10 for a ratio
	// This is arbitrarily chosen and can be modified
	private static int RATIO_MAX = 10;
	public static Pair<Integer, Integer> rationalRatio(double x, double y)
	{
	    for (int numer = 2; numer < RATIO_MAX; numer++)
	    {
	        for (int denom = 1; denom < RATIO_MAX; denom++)
	        {
	            if (numer != denom)
	            {
	                if (Utilities.compareValues(x / y, (double)(numer) / denom))
	                {
	                    int gcd = GCD(numer, denom);
	                    return numer > denom ? new Pair<Integer, Integer>(numer / gcd, denom / gcd)
	                                         : new Pair<Integer, Integer>(denom / gcd, numer / gcd);
	                }
	            }
	        }
	    }

	    return new Pair<Integer, Integer>(-1, -1);
	}

	//
	//
	//
	public static Pair<Integer, Integer> rationalRatio(double x)
	{
	    for (int val = 2; val < RATIO_MAX; val++)
	    {
	        // Do we acquire an integer?
	        if (Utilities.compareValues(x * val, Math.floor(x * val)))
	        {
	            int gcd = GCD(val, (int)Math.round(x * val));
	            return x < 1 ? new Pair<Integer, Integer>(val / gcd, (int)Math.round(x * val) / gcd) :
	                           new Pair<Integer, Integer>((int)Math.round(x * val) / gcd, val / gcd);
	        }
	    }

	    return new Pair<Integer, Integer>(-1, -1);
	}
}
