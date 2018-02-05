public class RandomNumGen {
    private long x;

    public RandomNumGen(long seed) {
        if (seed == 0) {
            throw new IllegalArgumentException("seed == 0");
        }
        x = seed;
    }

    public long next() {  // Marsaglia's XorShift
        x ^= x >>> 12;
        x ^= x << 25;
        x ^= x >>> 27;
        return x * 2685821657736338717L;
    }

    public static void main(String[] args) {
        RandomNumGen rng = new RandomNumGen(1);
        for(int i = 0; i < 1000; i++) {
            System.out.println(rng.next());
        }
    }

}
