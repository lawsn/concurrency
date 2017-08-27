package thread;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by lawsn on 2017-08-20.
 */
public class HelloWorld {

    public static void main(String[] args) throws InterruptedException {
        Thread myThread = new Thread() {
            public void run() {
                System.out.println("Hello from new thread");
            }
        };

        System.out.println(StringUtils.isEmpty(null));

        myThread.start();
        Thread.yield();
        Thread.sleep(1);
        System.out.println("Hello from main thread");
        myThread.join();
    }
}
