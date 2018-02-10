package ex4;

import java.util.concurrent.atomic.AtomicLong;

public class RandomNumGen {
	
	// x is now an AtomicLong
    private AtomicLong x;

    public RandomNumGen(long seed) {
        if (seed == 0) {
            throw new IllegalArgumentException("seed == 0");
        }
        // not much conflict here, it is initialization
        x = new AtomicLong(seed);
    }

    // Dedicated (not mandatory) function performing the random transformation
    private long randomize(long value) {
        value ^= value >>> 12;
        value ^= value << 25;
        value ^= value >>> 27;
        return value * 2685821657736338717L;
    }
    
    // We want the next() operation to be thread-safe, saying that multiple threads could
    // call this method on a shared Generator, without having undefined behavior upon x.
    // -> We need to ensure that the value of x has not been modified during the call
    public long next() {  // Marsaglia's XorShift
    	
    	long prev, next;	
    	do {
    		// prev is the value of x starting the method
    		prev = x.get();
    		// next is prev randomize, and thus x leaving the method
            next = randomize(prev);
           
         // let x be set to next, only if it is still equal to prev before the operation
         // (and thus has not been modified) during the computations
         // -> Otherwise, restart
    	}while(!x.compareAndSet(prev, next)); 	
    	// Return the new value of x, which is next
    	return next; 
    }
    
    public static void main(String[] args) {
        RandomNumGen rng = new RandomNumGen(1);
        for(int i = 0; i < 1000; i++) {
            System.out.println(rng.next());
        }
    }
}
