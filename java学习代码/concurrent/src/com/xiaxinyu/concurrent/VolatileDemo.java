package com.xiaxinyu.concurrent;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description: 学习Volatile关键字作用
 * @author: xiaxinyu
 * @Email: xiaxinyuxy@163.com
 * @date: 2022年11月12日 11:46
 * @Copyright: 个人版权所有
 * @version: 1.0.0
 */

class MyData1{
    int number = 0;
    public void add10(){
        this.number += 10;
    }
}

class MyData2{
    volatile int number = 0;
    public void add10(){
        this.number += 10;
    }
}

class MyData3{
    volatile int number = 0;
    public void add(){
        number ++;
    }
}

class MyData4{
    volatile int number = 0;
    public synchronized void add(){
        number ++;
    }
}

class MyData5{
    volatile AtomicInteger number = new AtomicInteger();
    public void add(){
        number.getAndIncrement();
    }
}

public class VolatileDemo {
    public static void main(String[] args) {
//        testNoVisibility();
//        testVisibility();
//        testNoAtomic();
//        testAtomic1();
//        testAtomic2();

        //单线程测试单例模式
//        System.out.println("单线程的情况测试开始");
//        System.out.println(SingletonDemo.getInstance() == SingletonDemo.getInstance());
//        System.out.println(SingletonDemo.getInstance() == SingletonDemo.getInstance());
//        System.out.println("单线程的情况测试结束");
        //多线程测试单例模式
        System.out.println("多线程的情况测试开始");
        for(int i = 1;i <= 10;i ++){
            new Thread(SingletonDemo::getInstance,String.valueOf(i)).start();
        }
    }

    /**
     * 证明另一个线程修改number的值后，主线程对改修改不具有可见性(number不加volatile修饰)
     */
    static void testNoVisibility(){
        MyData1 myData = new MyData1();
        new Thread(() -> {
            System.out.println("线程" + Thread.currentThread().getName() + "\t + 正在执行");
            try{
                TimeUnit.SECONDS.sleep(3);
            }catch (Exception e){
                e.printStackTrace();
            }
            myData.add10();
            System.out.println("线程" + Thread.currentThread().getName() + "\t 更新后，number的值为" + myData.number);
        }).start();

        while(myData.number == 0){

        }
        System.out.println("具有可见性！！！");
    }

    /**
     * 证明另一个线程修改number的值后，主线程对改修改具有可见性(number加volatile修饰)
     */
    static void testVisibility(){
        MyData2 myData = new MyData2();
        new Thread(() -> {
            System.out.println("线程" + Thread.currentThread().getName() + "\t + 正在执行");
            try{
                TimeUnit.SECONDS.sleep(3);
            }catch (Exception e){
                e.printStackTrace();
            }
            myData.add10();
            System.out.println("线程" + Thread.currentThread().getName() + "\t 更新后，number的值为" + myData.number);
        }).start();

        while(myData.number == 0){

        }
        System.out.println("具有可见性！！！");
    }

    /**
     * 证明volatile不保证原子性
     */
    static void testNoAtomic(){
        MyData3 myData = new MyData3();
        for(int i = 0;i < 20;i ++){
            new Thread(() -> {
                for(int j = 0;j < 1000;j ++) myData.add();
            }).start();
        }
        try{
            TimeUnit.SECONDS.sleep(5);
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("number值进行了20000次加1操作，但此时number的实际值是：" + myData.number);
    }

    /**
     * 给add()方法加上synchronized关键字，解决volatile不保证原子性的问题(这种做法不推荐)
     */
    static void testAtomic1(){
        MyData4 myData = new MyData4();
        for(int i = 0;i < 20;i ++){
            new Thread(() -> {
                for(int j = 0;j < 1000;j ++) myData.add();
            }).start();
        }
        try {
            TimeUnit.SECONDS.sleep(5);
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("number值进行了20000次加1操作，但此时number的实际值是：" + myData.number);
    }

    /**
     * 其实并没有必要给整个方法加锁，因为方法中的其他逻辑代码是支持多线程同步执行的
     * 所以可以使用原子整形类型的变量代替int类型来提高效率，
     */
    static void testAtomic2(){
        MyData5 myData = new MyData5();
        for(int i = 0;i < 20;i ++){
            new Thread(() -> {
                for(int j = 0;j < 1000;j ++) myData.add();
            }).start();
        }
        try {
            TimeUnit.SECONDS.sleep(5);
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("number值进行了20000次加1操作，但此时number的实际值是：" + myData.number);
    }
}

class SingletonDemo{
    private volatile static SingletonDemo instance = null;
    private SingletonDemo(){
        System.out.println(Thread.currentThread().getName() + "\t 执行构造方法SingletonDemo()");
    }
    public static SingletonDemo getInstance(){
//        if(instance == null){
//            instance = new SingletonDemo();
//        }
//        return instance;


        /**
         * DCL(Double Check Lock双检索机制)
         */
        if(instance == null){ //第一层检索，如果是空，不进入同步代码块，提升效率
            synchronized (SingletonDemo.class){ //加同步锁，防止多个线程实例化多个对象
                if(instance == null) //第二层检索，如果是空，则实例化对象
                    instance = new SingletonDemo();
            }
        }
        return instance;
    }
}
