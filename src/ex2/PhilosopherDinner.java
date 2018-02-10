package ex2;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.Semaphore;

/*
With the given code, the program block : each philosopher lock one fork, and no philosopher can eat any more.
*/

public class PhilosopherDinner {

    private final ReentrantLock[] forks;
    private static int philosopherNumber = 5; // number of philosopher
    private Semaphore sem = new Semaphore((int)(philosopherNumber / 2)); // we initiate the semaphore, his value depend on the number of philisopher : the number maximum of philosopher who can eat at the same time is philosopherNumber/2

    public PhilosopherDinner(int forkCount) {
        ReentrantLock[] forks = new ReentrantLock[forkCount];
        for (int i = 0; i < forkCount; i++) {
            forks[i] = new ReentrantLock();
        }
        this.forks = forks;
    }

    public void eat(int index) {
        ReentrantLock fork1 = forks[index];
        ReentrantLock fork2 = forks[(index + 1) % forks.length];
        try{
            sem.acquire(); // We take a ressource reservation just before locking the first fork
            fork1.lock();
            try {
                fork2.lock();
                try {
                    System.out.println("philosopher " + index + " eat");
                } finally {
                    fork2.unlock();
                }
            }finally {
                fork1.unlock();
            }
        }
        catch(InterruptedException e){
                e.printStackTrace();
            }finally{
            sem.release(); // We release the ressource when the philosopher put down the fork
        }
    }

    public static void main(String[] args) {
        PhilosopherDinner dinner = new PhilosopherDinner(philosopherNumber);

        for (int i = 0; i < philosopherNumber; i++) {
            final int philosopher = i;
            new Thread(new Runnable() {

                @Override
                public void run() {
                    for(;;) {
                        dinner.eat(philosopher);
                    }
                }

            }).start();
        }
    }

}
