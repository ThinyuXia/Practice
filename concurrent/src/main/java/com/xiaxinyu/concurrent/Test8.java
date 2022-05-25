package com.xiaxinyu.concurrent;


import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "c.Test8")
public class Test8 {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            log.debug("洗水壶");
            try {
                Thread.sleep(1000);
                log.debug("烧开水");
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"老王");

       Thread t2 = new Thread(() -> {
            try {
                log.debug("洗茶壶");
                Thread.sleep(1000);
                log.debug("洗茶杯");
                Thread.sleep(2000);
                log.debug("拿茶叶");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

           try {
               t1.join();
               log.debug("泡茶");
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
       },"小王");

       t1.start();
       t2.start();

    }
}
