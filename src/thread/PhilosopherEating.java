package thread;

import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by lawsn on 2017-08-20.
 */
public class PhilosopherEating extends Thread {

    private boolean eating;
    private PhilosopherEating left;
    private PhilosopherEating right;
    private ReentrantLock table;
    private Condition condition;
    private Random random;

    public PhilosopherEating(ReentrantLock table) {
        eating = false;
        this.table = table;
        condition = table.newCondition();
        random = new Random();
    }

    public void setLeft(PhilosopherEating left) {
        this.left = left;
    }

    public void setRight(PhilosopherEating right) {
        this.right = right;
    }

    public void run() {
        try {
            while(true) {
                think();
                eat();
            }
        }catch(InterruptedException e) {

        }
    }

    private void think() throws InterruptedException {
        table.lock();

        try {
            eating = false;
            left.condition.signal();
            right.condition.signal();
        }finally {
            table.unlock();
        }
        Thread.sleep(1000);
    }

    private void eat() throws InterruptedException {
        table.lock();
        try {
            while(left.eating || right.eating) {
                condition.await();
            }
            eating = true;
        }finally {
            table.unlock();
        }
        Thread.sleep(1000);
    }
}