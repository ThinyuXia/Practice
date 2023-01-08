package com.xiaxinyu.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

/**
 * @Description: 使用Curator来操作Zookeeper
 */

public class CuratorDemo {
    public static final String SERVER_PATH = "127.0.0.1:2181";

    public static void main(String[] args) {
        ExponentialBackoffRetry retry = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client = CuratorFrameworkFactory.newClient(SERVER_PATH,retry);
        client.start();
        client.getCuratorListenable().addListener((CuratorFramework c, CuratorEvent event) -> {
            switch (event.getType()){
                case WATCHED:
                    WatchedEvent watchedEvent = event.getWatchedEvent();
                    if(watchedEvent.getType() == Watcher.Event.EventType.NodeDataChanged){
                        System.out.println(new String(c.getData().forPath("/curator")));
                    }
            }
        });
        try {
            client.create().withMode(CreateMode.PERSISTENT).forPath("/curator","curator".getBytes());
            System.out.println(new String(client.getData().watched().forPath("/curator")));


            client.setData().forPath("/curator","curator2".getBytes());


            client.delete().forPath("/curator");

            Thread.sleep(2000);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}