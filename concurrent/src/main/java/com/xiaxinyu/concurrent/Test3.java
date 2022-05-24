package com.xiaxinyu.concurrent;


import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

@Slf4j(topic = "c.Test3")
public class Test3 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> futureTask = new FutureTask<>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                log.debug("running");
                Thread.sleep(1000);
                return 100;
            }
        });

        Thread t = new Thread(futureTask,"t1");
        t.start();

        int n = futureTask.get(); //主线程中该条语句会等待t1线程任务执行完成后获取返回结果
        log.debug("{}",n);
    }
}
