package ex5;

import java.util.concurrent.Callable;

/*
 * CustomCallable class
 * Is able to return an Integer, here the result of the multiplication
 */
public class CustomCallable implements Callable<Integer> {

	private int number;
	
	public CustomCallable(int n) { number = n; }
	
	// returns nx10 instead of printing it
	@Override
	public Integer call() throws Exception {
		return number*10;
	}
}
