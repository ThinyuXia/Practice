package com.xiaxinyu.OA.dao;

import com.xiaxinyu.OA.entity.LeaveForm;
import com.xiaxinyu.OA.entity.Node;
import com.xiaxinyu.OA.entity.Notice;
import java.util.List;

public interface NoticeDao {
    public void insert(Notice notice);

    public List<Node> selectByReceiverId(Long receiverId);

}
