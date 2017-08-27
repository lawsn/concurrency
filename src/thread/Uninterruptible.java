package thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by lawsn on 2017-08-20.
 */
public class Uninterruptible {
    public static void main(String[] args) throws InterruptedException {
        Lock lock = new ReentrantLock();
        lock.lock();
        try {
            System.out.println("sync...");
        }finally {
            lock.unlock();
        }

        final ReentrantLock l1 = new ReentrantLock();
        final ReentrantLock l2 = new ReentrantLock();

//        final Object o1 = new Object();
//        final Object o2 = new Object();

        Thread t1 = new Thread() {
            public void run() {
                try {
                    l1.lockInterruptibly();
                    Thread.sleep(1000);
                    l2.lockInterruptibly();
                }catch(InterruptedException e) {
                    System.out.println("t1 interrupted");
                }
            }
        };

        Thread t2 = new Thread() {
            public void run() {
                try {
                    l2.lockInterruptibly();
                    Thread.sleep(1000);
                    l1.lockInterruptibly();
                }catch(InterruptedException e) {
                    System.out.println("t2 interrupted");
                }
            }
        };

        t1.start();
        t2.start();
        Thread.sleep(2000);
        t1.interrupt();
        t2.interrupt();
        t1.join();
        t2.join();
    }
}
