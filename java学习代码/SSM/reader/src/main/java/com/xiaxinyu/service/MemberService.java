package com.xiaxinyu.service;

import com.xiaxinyu.entity.Member;

public interface MemberService {
    /**
     * 会员注册，创建新会员
     * @param username 用户名
     * @param password 密码
     * @param nickName 昵称
     * @return 新会员对象
     */
    public Member createMember(String username,String password,String nickName);
}
