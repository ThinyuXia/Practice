package com.xiaxinyu.concurrent;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "c.Test1")
public class Test1 {
    public static void main(String[] args) {
        Thread t = new Thread() {
            public void run() {
                log.debug("starting");
            }
        };
        t.start();
        t.setName("t1");
        log.debug("starting");


    }
}
