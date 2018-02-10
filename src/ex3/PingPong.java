package ex3;

import java.util.concurrent.locks.*;

public class PingPong {

	// we first declare a lock, which may represent the writing access to a common resource
	// -> the standard output stream here
    private final Lock consoleAccess = new ReentrantLock();
    // we then replace the sent flag to a pingSent condition, acting on the defined lock
    private final Condition pingSent = consoleAccess.newCondition();

    private void ping() {
    	// locks the access to the standard output stream
    	consoleAccess.lock();
    	try {
    		// frees the thread waiting for the pingSent condition (but keeps the lock)
        	pingSent.signal();
            System.out.println("ping");
    	}
    	finally {
    		// releases the lock
    		consoleAccess.unlock();
    	}
    }

    private void pong() throws InterruptedException {
    	// locks the access to the standard output stream
    	consoleAccess.lock();
    	try {
        	// waits for the pingSent condition to be signaled
    		// (and implicitly releases the lock)
        	pingSent.await();
            System.out.println("pong");
    	}
    	finally {
    		// releases the lock
    		consoleAccess.unlock();
    	}

    }
    /* Question 3.
     * Nothing to change here
     */
    public static void main(String[] args) throws InterruptedException {
    	
        PingPong pingPong = new PingPong();
        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    new AssertionError(e);
                }
                pingPong.ping();
            }

        }).start();
        pingPong.pong();
    }

}
