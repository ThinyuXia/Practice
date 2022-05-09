package com.xiaxinyu.OA.service;

import com.xiaxinyu.OA.entity.Node;
import junit.framework.TestCase;
import org.junit.Test;
import java.util.List;

public class UserServiceTest extends TestCase {

    private UserService userService = new UserService();

    @Test
    public void testCheckLogin1() {
        userService.checkLogin("uu","2131");
    }

    @Test
    public void testCheckLogin2() {
        userService.checkLogin("m8","2131");
    }

    @Test
    public void testCheckLogin3() {
        userService.checkLogin("m8","test");
    }

    @Test
    public void testSelectNodeByUserId() {
        List<Node> list = userService.selectNodeByUserId(3l);
        System.out.println();
    }
}