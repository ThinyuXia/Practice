package com.xiaxinyu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xiaxinyu.entity.Member;
import com.xiaxinyu.mapper.MemberMapper;
import com.xiaxinyu.service.MemberService;
import com.xiaxinyu.service.exception.BussinessException;
import com.xiaxinyu.utils.MD5Utils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service("memberService")
@Transactional
public class MemberServiceImpl implements MemberService {

    @Resource
    private MemberMapper memberMapper;

    @Override
    public Member createMember(String username, String password, String nickName) {
        QueryWrapper<Member> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",username);
        List<Member> memberList = memberMapper.selectList(queryWrapper);
        //判断用户名是否已存在
        if(memberList.size() > 0){
            throw new BussinessException("M01","用户名已存在");
        }
        Member member = new Member();
        member.setUsername(username);
        member.setNickname(nickName);
        int salt = new Random().nextInt(1000) + 1000;
        String md5Digest = MD5Utils.MD5Digest(password,salt);
        member.setPassword(md5Digest);
        member.setSalt(salt);
        member.setCreateTime(new Date());
        memberMapper.insert(member);
        return member;
    }
}
