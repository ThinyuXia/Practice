package com.xiaxinyu.zkjavaapi;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * @Description: 和节点相关的信息：是否存在，获取数据，加上watch
 */

public class ZKGetNode implements Watcher {
    public static final String SERVER_PATH = "127.0.0.1:2181";

    public static final Integer TIMEOUT = 5000;

    private static CountDownLatch countDownLatch = new CountDownLatch(1);

    public static void main(String[] args) {
        try {
            /**
             * 客户端与服务端之间是异步连接，连接成功后，客户端会收到watcher通知
             */
            ZooKeeper zooKeeper = new ZooKeeper(SERVER_PATH,TIMEOUT,new ZKGetNode());
            System.out.println("客户端开始连接ZK服务器了");
            System.out.println(zooKeeper.getState());
            Thread.sleep(2000);
            System.out.println(zooKeeper.getState());

//            Stat exists = zooKeeper.exists("/test", false);
//            if(exists != null){
//                System.out.println("节点的版本为：" + exists.getVersion());
//            }else{
//                System.out.println("该节点不存在");
//            }
            zooKeeper.getData("/test",true,null);
            countDownLatch.await();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (KeeperException e) {
            throw new RuntimeException(e);
        }
    }

    public void process(WatchedEvent event) {
        if(event.getType() == Event.EventType.NodeDataChanged){
            System.out.println("数据被改变");
            countDownLatch.countDown();
        }
    }
}