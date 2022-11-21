package com.xiaxinyu;

import com.xiaxinyu.domain.User;
import com.xiaxinyu.mapper.MenuMapper;
import com.xiaxinyu.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

/**
 * @Description:
 * @author: xiaxinyu
 * @Email: xiaxinyuxy@163.com
 * @date: 2022年11月18日 18:26
 * @Copyright: 个人版权所有
 * @version: 1.0.0
 */

@SpringBootTest
public class MapperTest {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MenuMapper menuMapper;

    public void testSelectPermsByUserId(){
        List<String> strings = menuMapper.selectPermsByUserId(2L);
        System.out.println(strings);
    }

    @Test
    public void testBCryptPasswordEncoder(){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encode = passwordEncoder.encode("1234");
        String encode1 = passwordEncoder.encode("1234");
        System.out.println(encode);
        System.out.println(encode1);
//        System.out.println(passwordEncoder.matches("123" +
//                "45", "$2a$10$aGFC5DhHG7xrdL4m5g8i1eUep/EMdp54WCx36nfzbJK0V7rM4qQBi"));
    }

    @Test
    public void testUserMapper(){
        List<User> users = userMapper.selectList(null);
        System.out.println(users);
    }
}