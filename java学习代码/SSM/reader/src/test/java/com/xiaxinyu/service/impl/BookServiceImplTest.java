package com.xiaxinyu.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xiaxinyu.entity.Book;
import com.xiaxinyu.service.BookService;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class )
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class BookServiceImplTest extends TestCase {
    @Resource
    private BookService bookService;

    @Test
    public void testPaging() {
        IPage<Book> pageObject = bookService.paging(2L,"quantity",2,10);
        List<Book> recodes = pageObject.getRecords();
        for(Book book : recodes){
            System.out.println(book.getBookName());
        }
        System.out.println("总页数 : " + pageObject.getPages());
        System.out.println("总记录数 : " + pageObject.getTotal());
    }
}