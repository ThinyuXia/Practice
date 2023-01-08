package com.xiaxinyu.zkjavaapi;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;

/**
 * @Description: 连接到ZK服务端，打印连接状态
 */

public class ZKConnect implements Watcher {
    public static final String SERVER_PATH = "127.0.0.1:2181";

    public static final Integer TIMEOUT = 5000;

    public static void main(String[] args) {
        try {
            /**
             * 客户端与服务端之间是异步连接，连接成功后，客户端会收到watcher通知
             */
            ZooKeeper zooKeeper = new ZooKeeper(SERVER_PATH,TIMEOUT,new ZKConnect());
            System.out.println("客户端开始连接ZK服务器了");
            System.out.println(zooKeeper.getState());
            Thread.sleep(2000);
            System.out.println(zooKeeper.getState());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void process(WatchedEvent watchedEvent) {
        System.out.println("收到了通知" + watchedEvent);
    }
}