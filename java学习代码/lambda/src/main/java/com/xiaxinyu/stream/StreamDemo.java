package com.xiaxinyu.stream;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * @Description:
 * @author: xiaxinyu
 * @Email: xiaxinyuxy@163.com
 * @date: 2022年11月12日 16:39
 * @Copyright: 个人版权所有
 * @version: 1.0.0
 */

public class StreamDemo {
    public static void main(String[] args) {
//        List<Author> authors = getAuthors();
//        test01(authors);
//        test02();
//        test03();
//        test04();
//        test05();
//        test06();
//        test07();
//        test08();
//        test09();
//        test10();
//        test11();

    }



    //flagMap2
    private static void test11() {
        //打印现有数据的所有分类。要求对分类进行去重。不能出现这种格式：哲学,爱情
        List<Author> authors = getAuthors();
        authors.stream()
                .flatMap(author -> author.getBooks().stream())
                .flatMap(book -> Arrays.stream(book.getCategory().split(",")))
                .distinct()
                .forEach(category -> System.out.println(category));

    }

    //flatMap1
    private static void test10() {
        //打印所有书籍的名字。要求对重复的元素进行去重
        List<Author> authors = getAuthors();
        authors.stream()
                .flatMap(author -> author.getBooks().stream())
                .distinct()
                .forEach(book -> System.out.println(book.getName()));

    }

    //skip
    private static void test09() {
        //打印除了年龄最大的作家外的其他作家，要求不能有重复元素，并且按照年龄降序排序
        List<Author> authors = getAuthors();
        authors.stream()
                .distinct()
                .sorted((a,b) -> b.getAge() - a.getAge())
                .skip(1)
                .forEach(author -> System.out.println(author.getName()));

    }

    //limit
    private static void test08() {
        //对流中的元素按照年龄进行降序排序，并且要求不能有重复的元素,然后打印其中年龄最大的两个作家的姓名
        List<Author> authors = getAuthors();
        authors.stream()
                .distinct()
                .sorted((a,b) -> b.getAge() - a.getAge())
                .limit(2)
                .forEach(author -> System.out.println(author.getName()));

    }

    private static void test07() {
        List<Author> authors = getAuthors();
        //对流中的元素按照年龄进行降序排序，并且要求不能有重复元素
//        authors.stream()
//                .distinct()
//                .sorted() //如果想要调用空参的sorted()方法，元素需要实现Comparable接口
//                .forEach(author -> System.out.println(author.getAge()));
        authors.stream()
                .distinct()
                .sorted((a,b) -> a.getAge() - b.getAge())
                .forEach(author -> System.out.println(author.getAge()));
    }

    //distinct(注意是否需要重写equals方法)
    private static void test06() {
        //打印所有的作家名称，并且要求其中不能有重复元素
        List<Author> authors = getAuthors();
        authors.stream()
                .distinct()
                .forEach(author -> System.out.println(author.getName()));
    }

    //map
    private static void test05() {
        //打印所有作家的姓名
        List<Author> authors = getAuthors();
        authors.stream()
                .map(author -> author.getName())
                .forEach(name -> System.out.println(name));

        authors.stream()
                .map(author -> author.getAge())
                .map(age -> age + 10)
                .forEach(age -> System.out.println(age));
    }

    //filter
    private static void test04() {
        List<Author> authors = getAuthors();
        //打印所有姓名长度大于1的作家的姓名
        authors.stream()
                .filter(author -> author.getName().length() > 1)
                .forEach(author -> System.out.println(author.getName()));
    }

    //双列集合先转化成单列集合再使用stream()方法
    private static void test03() {
        Map<String,Integer> map = new HashMap<>();
        map.put("蜡笔小新",19);
        map.put("黑子",17);
        map.put("日向翔阳",16);

        Stream<Map.Entry<String, Integer>> stream = map.entrySet().stream();
        stream.filter(entry -> entry.getValue() > 16)
                .forEach(entry -> System.out.println(entry.getKey() + "====" + entry.getValue()));
    }


    //数组创建流对象使用Arrays.stream()方法或者Stream.of()方法
    private static void test02() {
        Integer[] arr = {1,2,3,4,5};
//        Stream<Integer> stream = Arrays.stream(arr);
        Stream<Integer> stream = Stream.of(arr);
        stream.distinct()
                .filter(n -> n > 2)
                .forEach(n -> System.out.println(n));

    }

    //集合类创建流对象使用 stream() 方法
    private static void test01(List<Author> authors) {
        authors.stream() //把集合转换成流
                .distinct() 
                .filter(author -> author.getAge() < 18)
                .forEach(author -> System.out.println(author.getName()));
    }

    private static List<Author> getAuthors() {
        //数据初始化
        Author author = new Author(1L,"蒙多",33,"一个从菜刀中明悟哲理的祖安人",null);
        Author author2 = new Author(2L,"亚拉索",15,"狂风也追逐不上他的思考速度",null);
        Author author3 = new Author(3L,"易",14,"是这个世界在限制他的思维",null);
        Author author4 = new Author(3L,"易",14,"是这个世界在限制他的思维",null);

        //书籍列表
        List<Book> books1 = new ArrayList<>();
        List<Book> books2 = new ArrayList<>();
        List<Book> books3 = new ArrayList<>();

        books1.add(new Book(1L,"刀的两侧是光明与黑暗","哲学,爱情",88,"用一把刀划分了爱恨"));
        books1.add(new Book(2L,"一个人不能死在同一把刀下","个人成长,爱情",99,"讲述如何从失败中明悟真理"));

        books2.add(new Book(3L,"那风吹不到的地方","哲学",85,"带你用思维去领略世界的尽头"));
        books2.add(new Book(3L,"那风吹不到的地方","哲学",85,"带你用思维去领略世界的尽头"));
        books2.add(new Book(4L,"吹或不吹","爱情,个人传记",56,"一个哲学家的恋爱观注定很难把他所在的时代理解"));

        books3.add(new Book(5L,"你的剑就是我的剑","爱情",56,"无法想象一个武者能对他的伴侣这么的宽容"));
        books3.add(new Book(6L,"风与剑","个人传记",100,"两个哲学家灵魂和肉体的碰撞会激起怎么样的火花呢？"));
        books3.add(new Book(6L,"风与剑","个人传记",100,"两个哲学家灵魂和肉体的碰撞会激起怎么样的火花呢？"));

        author.setBooks(books1);
        author2.setBooks(books2);
        author3.setBooks(books3);
        author4.setBooks(books3);

        List<Author> authorList = new ArrayList<>(Arrays.asList(author,author2,author3,author4));
        return authorList;
    }
}