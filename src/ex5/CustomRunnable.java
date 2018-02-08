package ex5;

/*
 * CustomRunnable class
 * Enables to parameterize the number which will be multiplied by ten and displayed
 */
public class CustomRunnable implements Runnable {
	
	private int toMultiply;
	public CustomRunnable(int value) { toMultiply = value; }
	
	// Question 2.
	// Multiplies its number by 10 (and displays the result)
	@Override
	public void run() {
		System.out.println(toMultiply + " x 10 = " + toMultiply*10);
	}
	

}
