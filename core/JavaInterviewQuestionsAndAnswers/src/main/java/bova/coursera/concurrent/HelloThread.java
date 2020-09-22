package bova.coursera.concurrent;

public class HelloThread extends Thread {

    public void run() {
        System.out.println("Hello from a thread!");
    }

    public static void main(String args[]) {
        (new HelloThread()).start();
    }

}

/*

         PrimeThread(long minPrime) {
             this.minPrime = minPrime;
         }

         public void run() {
             // compute primes larger than minPrime
              . . .
         }



public void addName(String name) {
    synchronized(this) {
        lastName = name;
        nameCount++;
    }
    nameList.add(name);
}


public class MsLunch {
    private long c1 = 0;
    private long c2 = 0;
    private Object lock1 = new Object();
    private Object lock2 = new Object();

    public void inc1() {
        synchronized(lock1) {
            c1++;
        }
    }

    public void inc2() {
        synchronized(lock2) {
            c2++;
        }
    }
}

Reentrant Synchronization

Recall that a thread cannot acquire a lock owned by another thread.
But a thread can acquire a lock that it already owns. Allowing a thread to acquire the same
lock more than once enables reentrant synchronization. This describes a situation where synchronized code,
directly or indirectly, invokes a method that also contains synchronized code,
and both sets of code use the same lock. Without reentrant synchronization,
synchronized code would have to take many additional precautions to avoid having a thread
cause itself to block.

Note: Always invoke wait inside a loop that tests for the condition being waited for. Don't assume that the interrupt
was for the particular condition you were waiting for, or that the condition is still true.

Like many methods that suspend execution, wait can throw InterruptedException

Some time after the second thread has released the lock, the first thread reacquires the lock and resumes by
returning from the invocation of wait.


SynchronousQueue<E>
BlockingQueue

-- Lock

Lock objects work very much like the implicit locks used by synchronized code.
As with implicit locks, only one thread can own a Lock object at a time. Lock objects also support
a wait/notify mechanism, through their associated Condition objects.



 */


