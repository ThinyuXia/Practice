package threadlocal;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadLocalNormalUsage03 {

    public static ExecutorService threadPool = Executors.newFixedThreadPool(10);

    static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    public String date(int seconds) {
        Date date = new Date(1000 * seconds);
        return sdf.format(date);
    }

    public static void main(String[] args) throws InterruptedException {
        for(int i = 0; i < 1000;i ++) {
            int finalI = i;
            threadPool.submit(new Runnable() {
                @Override
                public void run() {
                        String date = new ThreadLocalNormalUsage03().date(finalI);
                        System.out.println(date);

                }
            });
        }
        threadPool.shutdown();
    }
}
