package com.innovation.wxprogram.dto;

import com.innovation.wxprogram.entity.User;
import junit.framework.TestCase;
import org.springframework.beans.BeanUtils;

public class UserDTOTest extends TestCase {

    public void test() {
        User user = new User("111","222","222","222",111,"21123");
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(user,userDTO);
        System.out.println(userDTO);
    }
}