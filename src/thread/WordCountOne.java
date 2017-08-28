package thread;

import java.util.HashMap;

/**
 * Created by lawsn on 2017-08-28.
 */
public class WordCountOne {
    private static final HashMap<String, Integer> counts = new HashMap<String, Integer>();

    public static void main(String[] args) throws Exception {
        Iterable<Page> pages = new Pages(100000, "enwike.xml");
        for(Page page: pages) {
            Iterable<String> words = new Words(page.getText());
            for(String word : words) {
                countWord(word);
            }
        }
    }

    private static void countWord(String word) {
        Integer currentCount = counts.get(word);
        if(currentCount == null) {
            counts.put(word, 1);
        }else {
            counts.put(word, currentCount + 1);
        }
    }
}
