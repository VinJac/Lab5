package ex1;

import java.util.*;

/*
 * Question 1.
 * Here the 4 threads have to execute the same code, it is in this way 
 * simpler to pass an implementation of Runnable (that we only write once) to each thread,
 * than to have 4 classes extending Thread and overriding the run() method in the same way.
 * Remark: we here don't share the Runnable instance between the 4 threads, in order not to
 * share the counter (because each thread has to count separately).
 */
public class CounterThread {
	
	// Question 5.
	// In order to share the array, we create a custom implementation of Runnable that
	// contains an array, which will be set as the same one for all threads (whereas the
	// counter won't, because we are not sharing the entire runnable object)
	static private class CountAndAdd implements Runnable {
		
		private ArrayList<Integer> sharedArray;
		public void setSharedArray(ArrayList<Integer> a) { sharedArray = a; }
		
		@Override
		public void run() {
			for(int counter = 0; counter <= 10000; counter++)
				// Question 5. Adds the counter to the shared array
				// Question 6. exclude mutually the critical section
				// Note that here we can use the array itself as the token, because it is
				// shared among threads
				// => No more ArrayIndexOutOfBoundsException
				synchronized(sharedArray) {
					sharedArray.add(counter);
				}
		}
	}

	public static void main(String[] args) {

		// the 4 started threads, each counting from 0 to 10000 and printing their counter
		List<Thread> threads = new ArrayList<Thread>(4);
		
		// the shared counter list
		final ArrayList<Integer> counterList = new ArrayList<Integer>();
		
		for(int i = 1; i <= 4; i++) {
			// instantiates each runnable, using the same array
			CountAndAdd runnable = new CountAndAdd();
			runnable.setSharedArray(counterList);
			
			threads.add(new Thread(runnable));
			threads.get(threads.size() - 1).start();		// starts the newly inserted one
		}
		
		// Question 4.
		// the "main thread" waits until all 4 threads finish, before displaying the message
		try {
			for(Thread t : threads) {
				t.join();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Program finishes");
	}
}
