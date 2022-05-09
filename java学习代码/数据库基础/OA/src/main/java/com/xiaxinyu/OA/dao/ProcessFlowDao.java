package com.xiaxinyu.OA.dao;

import com.xiaxinyu.OA.entity.LeaveForm;
import com.xiaxinyu.OA.entity.ProcessFlow;

import java.util.List;

public interface ProcessFlowDao {
    public void insert(ProcessFlow processFlow);

    public void update(ProcessFlow processFlow);

    public List<ProcessFlow> selectByFormId(Long formId);

}
