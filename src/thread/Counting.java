package thread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by lawsn on 2017-08-20.
 */
public class Counting {
    public static void main(String[] args) throws InterruptedException {

//        class Counter {
//
//            private int count = 0;
//            public synchronized void increment() {
//                ++count;
//            }
//            public int getCount() {
//                return count;
//            }
//        }
//        final Counter counter = new Counter();

        final AtomicInteger counter = new AtomicInteger();
        class CountingThread extends Thread {
            public void run() {
                for(int x = 0; x < 10000; ++x) {
//                    counter.increment();
                    counter.incrementAndGet();
                }
            }
        }
        CountingThread t1 = new CountingThread();
        CountingThread t2 = new CountingThread();
        t1.start();
        t2.start();
        t1.join();
        t2.join();
//        System.out.println(counter.getCount());
        System.out.println(counter.get());
    }
}
