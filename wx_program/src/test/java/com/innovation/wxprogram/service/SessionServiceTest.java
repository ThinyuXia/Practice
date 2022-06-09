package com.innovation.wxprogram.service;

import com.innovation.wxprogram.entity.Session;
import com.innovation.wxprogram.utils.SessionIdUtil;
import junit.framework.TestCase;

public class SessionServiceTest extends TestCase {

    private SessionService sessionService = new SessionService();
    public void testSelectBySessionId() {
        System.out.println(sessionService.selectBySessionId("1654692247785748511"));
    }

    public void testInsert() {

        sessionService.insert(new Session(SessionIdUtil.genUniqueKey(),"1111"));

    }

    public void testUpdate() {
        sessionService.update(new Session("1654692247785748511","222"));
    }
}