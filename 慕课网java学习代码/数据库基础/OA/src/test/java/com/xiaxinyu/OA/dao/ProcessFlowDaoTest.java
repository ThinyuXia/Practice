package com.xiaxinyu.OA.dao;

import com.xiaxinyu.OA.entity.ProcessFlow;
import com.xiaxinyu.OA.utils.MybatisUtils;
import junit.framework.TestCase;

import java.util.Date;

public class ProcessFlowDaoTest extends TestCase {

    public void testInsert() {
        MybatisUtils.executeUpdate(sqlSession -> {
            ProcessFlowDao processFlowDao = sqlSession.getMapper(ProcessFlowDao.class);
            ProcessFlow flow = new ProcessFlow();
            flow.setFormId(3l);
            flow.setOperatorId(2l);
            flow.setAction("audit");
            flow.setReason("approved");
            flow.setReason("同意");
            flow.setCreateTime(new Date());
            flow.setAuditTime(new Date());
            flow.setOrderNo(1);
            flow.setState("ready");
            flow.setIsLast(1);
            processFlowDao.insert(flow);
            return null;
        });
    }
}