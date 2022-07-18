package com.xiaxinyu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xiaxinyu.entity.Book;
import com.xiaxinyu.entity.Evaluation;
import com.xiaxinyu.entity.Member;
import com.xiaxinyu.mapper.BookMapper;
import com.xiaxinyu.mapper.EvaluationMapper;
import com.xiaxinyu.mapper.MemberMapper;
import com.xiaxinyu.service.EvaluationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("evaluationService")
@Transactional(propagation = Propagation.NOT_SUPPORTED,readOnly = true)
public class EvaluationServiceImpl implements EvaluationService {
    @Resource
    private EvaluationMapper evaluationMapper;

    @Resource
    private MemberMapper memberMapper;

    @Resource
    private BookMapper bookMapper;

    @Override
    public List<Evaluation> selectByBookId(Long bookId) {
        QueryWrapper<Evaluation> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("book_id",bookId);
        queryWrapper.eq("state","enable");
        queryWrapper.orderByDesc("create_time");
        List<Evaluation> evaluationList = evaluationMapper.selectList(queryWrapper);

        Book book = bookMapper.selectById(bookId);

        for(Evaluation eva : evaluationList){
           Member member = memberMapper.selectById(eva.getMemberId());
            eva.setMember(member);
            eva.setBook(book);
        }
        return evaluationList;
    }
}
