package com.xiaxinyu.concurrent;


import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

@Slf4j(topic = "c.Test4")
public class Test4 {
    public static void main(String[] args) {
        new Thread(() -> {while(true){log.debug("running");}},"t1").start();
//        new Thread(() -> {while(true){log.debug("running");}},"t2").start();
//        new Thread(() -> {while(true){log.debug("running");}},"t3").start();
//        new Thread(() -> {while(true){log.debug("running");}},"t4").start();
    }
}
