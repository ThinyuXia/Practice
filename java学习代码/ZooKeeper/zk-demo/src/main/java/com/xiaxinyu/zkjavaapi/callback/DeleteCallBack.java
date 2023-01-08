package com.xiaxinyu.zkjavaapi.callback;

import org.apache.zookeeper.AsyncCallback;

/**
 * @Description: 删除后会运行的内容
 */

public class DeleteCallBack implements AsyncCallback.VoidCallback {
    public void processResult(int i, String s, Object o) {
        System.out.println("删除结点" + s);
        System.out.println((String)o);
    }
}