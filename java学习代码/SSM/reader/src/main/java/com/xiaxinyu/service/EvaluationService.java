package com.xiaxinyu.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xiaxinyu.entity.Book;
import com.xiaxinyu.entity.Evaluation;
import java.util.List;

public interface EvaluationService {

    /**
     * 按图书编号查询有效短评
     * @param bookId 图书编号
     * @return 评论列表
     */
    public List<Evaluation> selectByBookId(Long bookId);

    /**
     * 分页查询评论
     * @param state 评论状态
     * @param order 排列顺序
     * @param page 页号
     * @param rows  记录数
     * @return
     */
    public IPage<Evaluation> paging(String state , String order, Integer page, Integer rows);

    /**
     * 禁用短评
     * @param evaluationId 短评编号
     * @param reason 禁用原因
     * @return
     */
    public Evaluation disableEvaluation(Long evaluationId,String reason);
}
