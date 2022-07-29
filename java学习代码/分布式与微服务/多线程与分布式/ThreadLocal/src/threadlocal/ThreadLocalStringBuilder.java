package threadlocal;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadLocalStringBuilder {
    public static ExecutorService threadPool = Executors.newFixedThreadPool(10);
    public static String data(int num){
        StringBuilder stringBuilder = ThreadSafeStringBuilder.stringBuilderThreadLocal.get();
        stringBuilder.delete(0,stringBuilder.length());
        stringBuilder.append("数字" + num);
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        for(int i = 0;i < 20;i ++){
            int finalI = i;
            threadPool.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000);
                        ThreadLocalStringBuilder threadLocalStringBuilder = new ThreadLocalStringBuilder();
                        System.out.println(threadLocalStringBuilder.data(finalI));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}

class ThreadSafeStringBuilder{
    public static ThreadLocal<StringBuilder> stringBuilderThreadLocal = ThreadLocal.withInitial(() -> new StringBuilder());
}
