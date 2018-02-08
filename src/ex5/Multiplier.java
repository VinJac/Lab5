package ex5;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Multiplier {

	/* Question 2
	public static void main(String[] args) {
		// ExecutorService consisting in a fixed pool of 4 threads
		ExecutorService service = Executors.newFixedThreadPool(4);
		
		// Custom Runnable class, able to be parameterized
		CustomRunnable runnable = null;
		
		for(int n = 0; n <= 20; n++) {
			runnable = new CustomRunnable(n);
			service.execute(runnable);
		}
		
		// closes the service
		service.shutdown();
	}*/
	
	// Question 3
	public static void main(String[] args) {
		// ExecutorService consisting in a fixed pool of 4 threads
		ExecutorService service = Executors.newFixedThreadPool(4);
		
		// Custom Runnable class, able to be parameterized
		CustomRunnable runnable = null;
		
		for(int n = 0; n <= 20; n++) {
			runnable = new CustomRunnable(n);
			service.execute(runnable);
		}
		
		// closes the service
		service.shutdown();
	}
}


