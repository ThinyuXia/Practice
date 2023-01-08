package com.xiaxinyu.zkjavaapi;

import com.xiaxinyu.zkjavaapi.callback.DeleteCallBack;
import org.apache.zookeeper.*;

import java.io.IOException;

/**
 * @Description: 演示对结点的操作，包括创建、读取、删除等
 */

public class ZKOperator implements Watcher {
    public static final String SERVER_PATH = "127.0.0.1:2181";

    public static final Integer TIMEOUT = 5000;

    public static void main(String[] args) {
        try {
            /**
             * 客户端与服务端之间是异步连接，连接成功后，客户端会收到watcher通知
             */
            ZooKeeper zooKeeper = new ZooKeeper(SERVER_PATH,TIMEOUT,new ZKOperator());
            System.out.println("客户端开始连接ZK服务器了");
            System.out.println(zooKeeper.getState());
            Thread.sleep(2000);

            /**
             * path:创建的路径
             * data:存储的数据
             * acl:权限，开放
             * createMode:永久、临时、顺序
             */
//            zooKeeper.create("/test","test".getBytes(), ZooDefs.Ids.OPEN;ACL_UNSAFE, CreateMode.PERSISTENT);
//            zooKeeper.setData("/test","test2".getBytes(),0);
            zooKeeper.delete("/test",2,new DeleteCallBack(),"删除成功");
            Thread.sleep(2000);
            byte[] data = zooKeeper.getData("/test",null,null);
//            System.out.println(new String(data));
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (KeeperException e) {
            throw new RuntimeException(e);
        }
    }

    public void process(WatchedEvent watchedEvent) {
    }
}