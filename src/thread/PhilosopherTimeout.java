package thread;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by lawsn on 2017-08-20.
 */
public class PhilosopherTimeout extends Thread {
    private ReentrantLock leftChopstick, rightChopstick;
    private Random random;

    public PhilosopherTimeout(ReentrantLock leftChopstick, ReentrantLock rightChopstick) {
        this.leftChopstick = leftChopstick;
        this.rightChopstick = rightChopstick;
        random = new Random();
    }

    public void run() {
        try {
            while(true) {
                Thread.sleep(random.nextInt(1000));
                leftChopstick.lock();
                try {
                    if(rightChopstick.tryLock(1000, TimeUnit.MILLISECONDS)) {
                        try {
                            Thread.sleep(random.nextInt(1000));
                        }finally {
                            rightChopstick.unlock();
                        }
                    }else {

                    }
                }finally {
                    leftChopstick.unlock();
                }
            }
        }catch(InterruptedException e) {

        }
    }
}
