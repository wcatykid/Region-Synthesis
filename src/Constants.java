public final class Constants
{
	private Constants() {}

    public static int MAX_HORIZONTAL_SHIFT = 10;
    public static int MAX_VERTICAL_SHIFT = 10;
    public static int MAX_HORIZONTAL_STRETCH = 4;
    public static int MAX_VERTICAL_STRETCH = 4;
    
	public static String APPID = "V7VR6U-2PAEQALEU5";
	// region-synth: V7VR6U-YT5QEVGQAG
	// WolframTest:  V7VR6U-2PAEQALEU5
	
	public static boolean CONTACT_WA_ENGINE = false;
	
	public static boolean LIMITED_FUNCTIONS = true;       		//Use this to limit the algorithm to a few functions.  To be used with the following array.
	public static FunctionT[] ALLOWED_FUNCTIONS = { FunctionT.HORIZONTAL_LINE, FunctionT.LINEAR, FunctionT.PARABOLA };  //The one below works fine, but it's silly how large the numbers get.
	//static final FunctionT[] ALLOWED_FUNCTIONS = { FunctionT.HORIZONTAL_LINE, FunctionT.LINEAR, FunctionT.PARABOLA, FunctionT.CUBIC, FunctionT.QUARTIC, FunctionT.QUINTIC}; //Only to be used in the WIP version of a program.
	public static double MAX_RIGHT_X = 10.0;  				//The function can only be bound from n < x <= n + MAX_DOMAIN_SIZE where n is the starting bound.
	public static boolean INTS_ONLY = true;					//This is here to have the code able to limit the functions to only ints.  This does not guarantee int intersect points.
	public static boolean SAME_LENGTH_BOUNDS = false;         //If you want the bounds to be the same length between functions.  This also requires the top and bottom to have the same number of functions.
	public static int MAX_ATTEMPTS = 50;                     //Max number of attempts allowed to try and generate a top function.
	public static boolean SIMPLE_COEFFICIENTS = true;        //Limits b value to either 1 or pi.
	public static boolean ALLOW_INTERSECTIONS = false;       //top can intersect a bottom function.
}
