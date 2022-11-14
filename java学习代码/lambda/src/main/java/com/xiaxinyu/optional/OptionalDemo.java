package com.xiaxinyu.optional;

import com.xiaxinyu.stream.Author;

import java.util.Optional;

/**
 * @Description:
 * @author: xiaxinyu
 * @Email: xiaxinyuxy@163.com
 * @date: 2022年11月13日 21:05
 * @Copyright: 个人版权所有
 * @version: 1.0.0
 */

public class OptionalDemo {
    public static void main(String[] args) {
//        Author author = getAuthor();

//        Optional<Author> authorOptional = Optional.ofNullable(author);
//        authorOptional.ifPresent(author1 -> System.out.println(author1));
//        Optional<Author> authorOptional1 = getAuthorOptional();
//        authorOptional1.ifPresent(author1 -> System.out.println(author1.getName())); //安全消费值

//        Optional<Author> author1 = Optional.of(null); //使用of()方法要确定参数不能为null

//        Author author1 = (Author) Optional.ofNullable(null).get(); //如果Optional不存在值则抛出异常

//        Author author1 = (Author) Optional.ofNullable(null).orElseGet(() -> new Author()); //如果Optional不存在值则抛出异常

//        Author author1 = (Author) Optional.ofNullable(null).orElseThrow(() -> new RuntimeException("数据为null")); //如果Optional不存在值则抛出异常

//        Author author1 = (Author) Optional.ofNullable(null).orElse(new Author()); //如果Optional不存在值则抛出异常
//        testFilter();
//        testIsPresent();
        testMap();
    }

    private static void testMap() {
        Optional<Author> authorOptional = getAuthorOptional();
        authorOptional.map(author -> author.getBooks()).ifPresent(books -> System.out.println(books));
    }

    private static void testIsPresent() {
        Optional<Author> authorOptional = getAuthorOptional();
        if (authorOptional.isPresent()) {
            System.out.println(authorOptional.get().getName());
        }

    }

    public static void testFilter(){
        Optional<Author> authorOptional = getAuthorOptional();
        Optional<Author> author1 = authorOptional.filter((author -> author.getAge() > 18));

    }

    public static Optional<Author> getAuthorOptional() {
        Author author = new Author(1L, "蒙多", 33, "一个从菜刀中明悟哲理的祖安人", null);
        return Optional.ofNullable(author);
    }

    public static Author getAuthor() {
        Author author = new Author(1L, "蒙多", 33, "一个从菜刀中明悟哲理的祖安人", null);
        return null;
    }

}