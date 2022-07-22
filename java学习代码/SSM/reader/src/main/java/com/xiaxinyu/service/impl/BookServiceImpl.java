package com.xiaxinyu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaxinyu.entity.Book;
import com.xiaxinyu.entity.Evaluation;
import com.xiaxinyu.entity.MemberReadState;
import com.xiaxinyu.mapper.BookMapper;
import com.xiaxinyu.mapper.EvaluationMapper;
import com.xiaxinyu.mapper.MemberMapper;
import com.xiaxinyu.mapper.MemberReadStateMapper;
import com.xiaxinyu.service.BookService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service("bookService")
@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
public class BookServiceImpl implements BookService {

    @Resource
    private BookMapper bookMapper;

    @Resource
    private MemberReadStateMapper memberReadStateMapper;

    @Resource
    private EvaluationMapper evaluationMapper;

    @Override
    public IPage<Book> paging(Long categoryId, String order, Integer page, Integer rows) {
        Page<Book> p = new Page<>(page, rows);
        QueryWrapper<Book> queryWrapper = new QueryWrapper<>();
        if(categoryId != null && categoryId != -1){
            queryWrapper.eq("category_id",categoryId);
        }
        if(order != null){
            if(order.equals("quantity")){
                queryWrapper.orderByDesc("evaluation_quantity");
            }else if (order.equals("score")){
                queryWrapper.orderByDesc("evaluation_score");
            }
        }
        IPage<Book> pageObject = bookMapper.selectPage(p, queryWrapper);
        return pageObject;
    }


    @Override
    public Book selectById(Long id) {
        return bookMapper.selectById(id);
    }

    @Override
    @Transactional
    public void updateEvaluation() {
        bookMapper.updateEvaluation();
    }


    @Override
    @Transactional
    public Book createBook(Book book) {
        bookMapper.insert(book); //MabatisPlus自动进行主键回填
        return book;
    }

    @Override
    @Transactional
    public Book updateBook(Book book) {
        bookMapper.updateById(book);
        return book;
    }

    @Override
    @Transactional
    public void deleteBook(Long bookId) {
        bookMapper.deleteById(bookId);
        memberReadStateMapper.delete(new QueryWrapper<MemberReadState>().eq("book_id",bookId));
        evaluationMapper.delete(new QueryWrapper<Evaluation>().eq("book_id",bookId));
    }
}
