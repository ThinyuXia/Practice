package com.xiaxinyu;

import com.xiaxinyu.stream.Author;
import com.xiaxinyu.stream.Book;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Description:
 * @author: xiaxinyu
 * @Email: xiaxinyuxy@163.com
 * @date: 2022年11月14日 20:23
 * @Copyright: 个人版权所有
 * @version: 1.0.0
 */

public class MethodDemo {
    interface UseString{
        String use(String str,int start,int length);
    }

    public static String subAuthorName(String str, UseString useString){
        int start = 0;
        int length = 1;
        return useString.use(str,start,length);
    }
    public static void main(String[] args) {

        subAuthorName("三更草堂", new UseString() {
            @Override
            public String use(String str, int start, int length) {
                return str.substring(start,length);
            }
        });


        subAuthorName("三更草堂", String::substring);
    }

}