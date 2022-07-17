package com.xiaxinyu;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xiaxinyu.entity.Test;
import com.xiaxinyu.mapper.TestMapper;
import com.xiaxinyu.service.TestService;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class MybatisPlusTest {
    @Resource
    private TestMapper testMapper;

    @org.junit.Test
    public void testInsert(){
        Test test = new Test();
        test.setContent("Mybatis-Plus 测试内容");
        testMapper.insert(test);
    }

    @org.junit.Test
    public void testUpdate(){
        Test test = testMapper.selectById(126);
        test.setContent("Mybatis-Plus 测试内容1");
        testMapper.updateById(test);
    }

    @org.junit.Test
    public void testDelete(){
        testMapper.deleteById(126);
    }

    @org.junit.Test
    public void testSelect(){
        QueryWrapper<Test> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",126);
        List<Test> tests = testMapper.selectList(queryWrapper);
        System.out.println(tests.size());
//        testMapper.selectById();
//        testMapper.selectOne();
    }
}
