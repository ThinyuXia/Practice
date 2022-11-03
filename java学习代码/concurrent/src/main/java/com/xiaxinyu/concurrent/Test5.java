package com.xiaxinyu.concurrent;


import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "c.Test5")
public class Test5 {
    static int r = 0;
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(){
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                r = 10;
            };
        };
        t1.setName("t1");
        t1.start();
        t1.join();
        System.out.println(r);
    }
}
