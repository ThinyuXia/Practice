package com.xiaxinyu.OA.dao;

import com.xiaxinyu.OA.entity.LeaveForm;
import org.apache.ibatis.annotations.Param;

import java.util.Map;
import java.util.List;

public interface LeaveFormDao {
    public void insert(LeaveForm leaveForm);

    public List<Map> selectByParameters(@Param("pf_state") String pfState,@Param("pf_operator_id") Long operatorId);

    public LeaveForm selectById(Long formId);

    public void update(LeaveForm leaveForm);
}
