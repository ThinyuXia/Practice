package com.xiaxinyu.OA.service;

import com.xiaxinyu.OA.dao.NoticeDao;
import com.xiaxinyu.OA.entity.Notice;
import com.xiaxinyu.OA.utils.MybatisUtils;

import java.util.List;

public class NoticeService {
    /**
     * 查询指定员工的系统通知消息
     * @param receiverId
     * @return 包含最近的100条消息的消息列表
     */
    public List<Notice> getNoticeList(Long receiverId){
        return (List<Notice>)MybatisUtils.executeQuery(sqlSession -> {
            NoticeDao noticeDao = sqlSession.getMapper(NoticeDao.class);
            return noticeDao.selectByReceiverId(receiverId);
        });
    }
}
