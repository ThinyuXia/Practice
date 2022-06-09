package com.innovation.wxprogram.dao;

import com.innovation.wxprogram.entity.Session;
import com.innovation.wxprogram.entity.User;

public interface SessionDao {
    public String selectBySessionId(String sessionId);

    public void insert(Session session);

    public void update(Session session);
}
