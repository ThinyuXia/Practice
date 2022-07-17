package com.xiaxinyu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiaxinyu.entity.Test;

public interface TestMapper extends BaseMapper<Test> {
    public void insertSample();
}
