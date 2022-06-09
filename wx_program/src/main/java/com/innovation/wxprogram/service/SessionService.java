package com.innovation.wxprogram.service;

import com.innovation.wxprogram.dao.SessionDao;
import com.innovation.wxprogram.dao.UserDao;
import com.innovation.wxprogram.entity.Session;
import com.innovation.wxprogram.entity.User;
import com.innovation.wxprogram.utils.MybatisUtils;

public class SessionService {
    /**
     * 按照session_id查询session_key
     * @param sessionId
     * @return session
     */
    public String selectBySessionId(String sessionId){
        return (String) MybatisUtils.executeQuery(sqlSession -> sqlSession.getMapper(SessionDao.class).selectBySessionId(sessionId));
    }

    public void insert(Session session){
        MybatisUtils.executeUpdate(sqlSession -> {
            sqlSession.getMapper(SessionDao.class).insert(session);
            return null;
        });
    }

    public void update(Session session){
        MybatisUtils.executeUpdate(sqlSession -> {
            sqlSession.getMapper(SessionDao.class).update(session);
            return null;
        });
    }


}
