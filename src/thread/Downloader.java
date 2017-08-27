package thread;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.net.URL;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by lawsn on 2017-08-20.
 */
public class Downloader extends Thread {

    private InputStream in;
    private OutputStream out;
//    private ArrayList<ProgressListener> listeners;
    private CopyOnWriteArrayList<ProgressListener> listeners;

    public Downloader(URL url, String outputFilename) throws IOException {
        in = url.openConnection().getInputStream();
        out = new FileOutputStream(outputFilename);
//        listeners = new ArrayList<ProgressListener>();
        listeners = new CopyOnWriteArrayList<ProgressListener>();
    }
//    public synchronized void addListener(ProgressListener listener) {
    public void addListener(ProgressListener listener) {
        listeners.add(listener);
    }
//    public synchronized void removeListener(ProgressListener listener) {
    public void removeListener(ProgressListener listener) {
        listeners.remove(listener);
    }

    private void updateProgress(int n) {
//        ArrayList<ProgressListener> listenersCopy;
//        synchronized (this) {
//            listenersCopy = (ArrayList<ProgressListener>) listeners.clone();
//        }
//        for(ProgressListener listener: listenersCopy) {
//            listener.onProgress(n);
//        }
        for(ProgressListener listener: listeners) {
            listener.onProgress(n);
        }
    }

    public void run() {
        int n = 0;
        int total = 0;
        byte[] buffer = new byte[1024];

        try {
            while((n = in.read(buffer)) != -1) {
                out.write(buffer, 0, n);
                total += n;
                updateProgress(total);
            }
            out.flush();
        }catch(IOException e) {

        }
    }

    public class ProgressListener {

        public void onProgress(int n) {
        }
    }
}
