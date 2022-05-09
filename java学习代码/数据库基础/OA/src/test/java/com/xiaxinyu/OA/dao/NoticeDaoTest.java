package com.xiaxinyu.OA.dao;

import com.xiaxinyu.OA.entity.Notice;
import com.xiaxinyu.OA.utils.MybatisUtils;
import junit.framework.TestCase;

import java.util.Date;

public class NoticeDaoTest extends TestCase {

    public void testInsert() {
        MybatisUtils.executeUpdate(sqlSession -> {
            NoticeDao dao = sqlSession.getMapper(NoticeDao.class);
            Notice notice = new Notice();
            notice.setReceiverId(2l);
            notice.setContent("测试消息");
            notice.setCreateTime(new Date());
            dao.insert(notice);
            return null;
        });
    }
}