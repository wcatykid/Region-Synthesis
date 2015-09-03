public abstract class Bound
{
    public Bound() {}
    
    public abstract Object clone();
    
    public enum BoundT
    {
        POINT(0),
        VERTICAL_LINE(1),
        FUNCTION(2),
        UNSPECIFIED(3);

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

                default:
                   throw new IllegalArgumentException(b + " not recognized " + "convertFromBound");
            }
        }
    }
}
