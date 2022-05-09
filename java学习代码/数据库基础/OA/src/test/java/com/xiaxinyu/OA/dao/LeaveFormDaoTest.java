package com.xiaxinyu.OA.dao;

import com.xiaxinyu.OA.entity.LeaveForm;
import junit.framework.TestCase;
import com.xiaxinyu.OA.utils.MybatisUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.List;

public class LeaveFormDaoTest extends TestCase {

    public void testInsert() {
        MybatisUtils.executeUpdate(sqlSession -> {
            LeaveFormDao leaveFormDao = sqlSession.getMapper(LeaveFormDao.class);
            LeaveForm form = new LeaveForm();
            form.setEmployeeId(4l); //员工编号
            form.setFormType(1); //事假
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date startTime = null;//起始时间
            Date endTime = null;//结束时间
            try {
                startTime = sdf.parse("2020-03-25 08:00:00");
                endTime = sdf.parse("2020-04-01 18:00:00");
            } catch (ParseException e) {
                e.printStackTrace();
            }
            form.setStartTime(startTime);
            form.setEndTime(endTime);
            form.setReason("回家探亲");//请假事由
            form.setCreateTime(new Date());//创建时间
            form.setState("processing");//当前状态
            leaveFormDao.insert(form);
            return null;
        });
    }

    public void testSelectByParameters(){
        MybatisUtils.executeQuery(sqlSession -> {
           LeaveFormDao leaveFormDao = sqlSession.getMapper(LeaveFormDao.class);
           List<Map> result = leaveFormDao.selectByParameters("process",2l);
            System.out.println(result);
            return result;
        });
    }
}