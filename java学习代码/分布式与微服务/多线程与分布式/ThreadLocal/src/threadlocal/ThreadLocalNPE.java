package threadlocal;

public class ThreadLocalNPE {
    ThreadLocal<Long> threadLocal = new ThreadLocal<>();
    public void set(){
        threadLocal.set(Thread.currentThread().getId());
    }

    public long get(){
        return threadLocal.get();//返回值类型为基本类型long时会在装箱过程产生异常
    }

    public static void main(String[] args) {
        ThreadLocalNPE threadLocalNPE = new ThreadLocalNPE();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                threadLocalNPE.set();
                System.out.println(threadLocalNPE.get());
            }
        });
        thread.start();
    }
}
