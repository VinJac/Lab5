package ex5;

import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/*
 * Question 3.
 */
public class Multiplier2 {

	public static void main(String[] args) {
		// ExecutorService consisting in a fixed pool of 4 threads
		ExecutorService service = Executors.newFixedThreadPool(4);
		
		// Custom Callable class, able to be parameterized
		CustomCallable callable = null;
		// maps a number to its corresponding future result (multiplied by 10)
		Map<Integer, Future<Integer>> results = new HashMap<Integer, Future<Integer>>(20);
		
		// only launches the callables here (because Future<V>::get() is blocking)
		// and we don't want to block the main execution at each loop
		for(int n = 0; n <= 20; n++) {
			callable = new CustomCallable(n);
			results.put(n, service.submit(callable));
		}
		
		// displays the results
		for(Map.Entry<Integer, Future<Integer>> entry : results.entrySet())
			try {
				System.out.println(entry.getKey() + " x 10 = " + entry.getValue().get());
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		
		// closes the service
		service.shutdown();

	}

}
