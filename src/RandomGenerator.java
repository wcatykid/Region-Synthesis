import java.util.Random;

//
// A static class implementing a consistent random number generator
//
public final class RandomGenerator
{
	private static Random rng;
	
	private RandomGenerator() { super(); }
	
    public static void initialize()
    {
        rng = new Random();
    }

    public static void initialize(long seed)
    {
        rng = new Random(seed);
    }

    // Generates a random integer from [0, RAND_MAX]
    public static int nextInt()
    {
        return rng.nextInt();
    }

    // Generates a random integer from [0, max]
    public static int nextInt(int max)
    {
        return rng.nextInt(max);
    }

    // Generates a random integer from [min, max]
    public static int nextInt(int min, int max)
    {
        return min + (rng.nextInt() % (int)(max - min + 1));
    }
}
