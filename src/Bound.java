import java.io.Serializable;

public abstract class Bound implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3073553057593456605L;

	public Bound() {type = BoundT.UNSPECIFIED;}
    
    public Bound(BoundT type) {this.type = type;}
    
    public abstract Object clone();
    
    private BoundT type;
    
    public BoundT getBoundT() { return type; }
    
    public abstract Point getLeftTopPoint();
    public abstract Point getLeftBottomPoint();
    
    public enum BoundT
    {
        POINT(0),
        VERTICAL_LINE(1),
        FUNCTION(2),
        HORIZONTAL_LINE(3),
        LINEAR(4),
        PARABOLA(5),
        CUBIC(6),
		QUARTIC(7),
		QUINTIC(8),
		EXPONENTIAL(9),
		LOGARITHMIC(10),
		SINE(11),
		COSINE(12),
		UNSPECIFIED(13);

        private final int value;
        private BoundT(int value) { this.value = value; }
        public int getValue() { return value; }
        public static BoundT convertToBound(char c) throws IllegalArgumentException
        {
            switch (c)
            {
                case 'p':
                case 'P':
                    return POINT;
                case 'v':
                case 'V':
                    return VERTICAL_LINE;
                case 'f':
                case 'F':
                    return FUNCTION;

                default:
                   throw new IllegalArgumentException(c + " not recognized " + "convertToBound");
            }
        }
        public static char convertFromBound(BoundT b) throws IllegalArgumentException
        {
            switch (b)
            {
                case POINT:
                    return 'P';

                case VERTICAL_LINE:
                    return 'V';

                case FUNCTION:
                    return 'F';
                    
                case HORIZONTAL_LINE:
                	return 'H';
                	
                case LINEAR:
                	return 'L';
                
                case PARABOLA:
                	return '2';

                default:
                   throw new IllegalArgumentException(b + " not recognized " + "convertFromBound");
            }
        }
        
        public FunctionT inFunctionTForm() {
        	switch (value) {
        	case 3: return FunctionT.HORIZONTAL_LINE;
        	case 4: return FunctionT.LINEAR;
        	case 5: return FunctionT.PARABOLA;
        	case 6: return FunctionT.CUBIC;
        	case 7: return FunctionT.QUARTIC;
        	case 8: return FunctionT.QUINTIC;
        	case 9: return FunctionT.EXPONENTIAL;
        	case 10: return FunctionT.LOGARITHMIC;
        	case 11: return FunctionT.SINE;
        	case 12: return FunctionT.COSINE;
        	default: return null;
        	}
        }
    }
}
