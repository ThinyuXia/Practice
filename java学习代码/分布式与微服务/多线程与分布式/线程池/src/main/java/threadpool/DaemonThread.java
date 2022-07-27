package threadpool;

/**
 * 测试守护线程停止时机
 */
public class DaemonThread {
    public static void main(String[] args) {
        ThreadA threadA = new ThreadA();
        ThreadB threadB = new ThreadB();
        ThreadC threadC = new ThreadC();
        threadC.setDaemon(true);
        threadA.start();
        threadB.start();
        threadC.start();
    }
}

class ThreadA extends Thread{
    @Override
    public void run() {
        for(int i = 0;i < 5;i ++)
        System.out.println("线程A正在执行");
    }
}
class ThreadB extends Thread{
    @Override
    public void run() {
        for(int i = 0;i < 5;i ++)
        System.out.println("线程B正在执行");
    }
}
class ThreadC extends Thread{
    @Override
    public void run() {
        for(int i = 0;i < 500;i ++)
        System.out.println("线程C正在执行");
    }
}
