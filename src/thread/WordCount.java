package thread;

import java.util.HashMap;

/**
 * Created by lawsn on 2017-08-28.
 */
public class WordCount {
    ArrayBlockingQueue<Page> queue = new ArrayBlockingQueue<Page>(100);
    HashMap<String, Integer> counts = new HashMap<String, Integer>();

    Thread counter = new Thread(new Counter(queue, counts));
    Thread parser = new Thread(new Parser(queue));

    counter.start();
    parser.start();
    parser.join();
    queue.put(new PoisonPill());
    counter.join();
}
