package com.xiaxinyu.service.impl;

import com.xiaxinyu.service.CategoryService;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class CategoryServiceImplTest extends TestCase {

    @Resource
    private CategoryService categoryService;

    @Test
    public void testSelectAll() {
        System.out.println(categoryService.selectAll());
    }
}