package com.xiaxinyu.stream;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;
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
//        test12();
//        test13();
//        test14();
//        test15();
//        test16();
//        test17();
//        test18();
//        test19();
//        test20();
//        test21();
//        test22();
//        test23();
//        test24();
//        test25();
//        test26();
//        Stream<Author> stream = getAuthors().stream();
//        stream.map(author -> author.getName())
//                .forEach(name -> System.out.println(name));
//        testAnd();
//        testOr();
        testNegate();
    }

    private static void testNegate() {
        //打印作家中年龄不大于17的作家
        List<Author> authors = getAuthors();
        authors.stream()
                .filter(((Predicate<Author>) author -> author.getAge() > 17).negate())
                .forEach(author -> System.out.println(author.getAge()));
    }

    private static void testOr() {
        //打印作家中年龄大于17或者姓名的长度小于2的作家
        List<Author> authors = getAuthors();
        authors.stream()
                .filter(((Predicate<Author>) author -> author.getAge() > 17).or(author -> author.getName().length() < 2));

    }

    private static void testAnd() {
        //打印作家中年龄大于17并且姓名的长度大于1的作家
        List<Author> authors = getAuthors();
        authors.stream()
                .filter(new Predicate<Author>() {
                    @Override
                    public boolean test(Author author) {
                        return author.getAge() > 17;
                    }
                }.and(new Predicate<Author>() {
                    @Override
                    public boolean test(Author author) {
                        return author.getName().length() > 1;
                    }
                })).forEach(author -> System.out.println(author.getAge() + " : " + author.getName()));

        authors.stream()
                .filter(((Predicate<Author>) author -> author.getAge() > 17).and(author -> author.getName().length() > 1)).forEach(author -> System.out.println(author.getAge() + " : " + author.getName()));

    }

    //reduce4
    private static void test26() {
        //使用reduce求所有作者中年龄的最小值
        List<Author> authors = getAuthors();
        Optional<Integer> min = authors.stream()
                .map(author -> author.getAge())
                .reduce((a, b) -> Math.min(a, b));
        min.ifPresent(minAge -> System.out.println(minAge));
    }

    //reduce3
    private static void test25() {
        //使用reduce求所有作者中年龄的最小值
        List<Author> authors = getAuthors();
        Integer min = authors.stream()
                .map(author -> author.getAge())
                .reduce(Integer.MAX_VALUE, (a, b) -> Math.min(a, b));
        System.out.println(min);
    }

    //reduce2
    private static void test24() {
        //使用reduce求所有作者中年龄的最大值
        List<Author> authors = getAuthors();
        Integer max = authors.stream()
                .map(author -> author.getAge())
                .reduce(0, (a, b) -> Math.max(a, b));
        System.out.println(max);
    }

    //reduce1
    private static void test23() {
        //使用reduce求所有作者年龄的和
        List<Author> authors = getAuthors();
        Integer sum = authors.stream()
                .map(author -> author.getAge())
                .reduce(0,(a,b) -> a + b);
        System.out.println(sum);
    }

    //findFirst
    private static void test22() {
        //获取一个年龄最小的作家，并输出他的姓名
        List<Author> authors = getAuthors();
        Optional<Author> first = authors.stream()
                .sorted((author1, author2) -> author1.getAge() - author2.getAge())
                .findFirst();
        first.ifPresent(author -> System.out.println(author.getName()));
    }

    //findAny
    private static void test21() {
        //获取任意一个年龄大于18的作家，如果存在就输出他的名字
        List<Author> authors = getAuthors();
        Optional<Author> any = authors.stream()
                .filter(author -> author.getAge() > 18)
                .findAny();
        any.ifPresent(author -> System.out.println(author.getAge()));
    }

    //noMatch
    private static void test20() {
        //判断作家是否都没有超过100岁的
        List<Author> authors = getAuthors();
        boolean res = authors.stream()
                .noneMatch(author -> author.getAge() > 100);

        System.out.println(res);
    }


    //allMatch
    private static void test19() {
        //判断是否所有的作家都是成年人
        List<Author> authors = getAuthors();
        boolean res = authors.stream()
                .allMatch(author -> author.getAge() > 18);
        System.out.println(res);
    }

    //anyMatch
    private static void test18() {
        //判断是否有年龄在29以上的作家
        List<Author> authors = getAuthors();
        boolean res = authors.stream()
                .anyMatch(author -> author.getAge() > 29);
        System.out.println(res);
    }

    //stream->map
    private static void test17() {
        //获取一个Map集合，map的key为作者名，value为List<Book>
        List<Author> authors = getAuthors();
        Map<String, List<Book>> map = authors.stream()
                .distinct()
                .collect(Collectors.toMap(
                        author -> author.getName(),
                        author -> author.getBooks()
                ));
        System.out.println(map);
    }

    //stream->set
    private static void test16() {
        //获取一个所有书名的Set集合。
        List<Author> authors = getAuthors();
        Set<Book> books = authors.stream()
                .flatMap(author -> author.getBooks().stream())
                .collect(Collectors.toSet());
        System.out.println(books);
    }


    //stream->list
    private static void test15() {
        //获取一个存放所有作者名字的List集合
        List<Author> authors = getAuthors();
        List<String> nameList = authors.stream()
                .map(author -> author.getName())
                .collect(Collectors.toList());

        System.out.println(nameList);
    }

    //max && min
    private static void test14() {
        //分别获取这些作家的所出书籍的最高分和最低分并打印
        List<Author> authors = getAuthors();
        Optional<Integer> max = authors.stream()
                .flatMap(author -> author.getBooks().stream())
                .map(book -> book.getScore())
                .max((score1, score2) -> score1 - score2);
        System.out.println(max.get());

        Optional<Integer> min = authors.stream()
                .flatMap(author -> author.getBooks().stream())
                .map(book -> book.getScore())
                .min((score1, score2) -> score1 - score2);
        System.out.println(min.get());
    }

    //count
    private static void test13() {
        //打印这些作家的所出书籍的数目，注意删除重复元素
        List<Author> authors = getAuthors();
        Long count = authors.stream()
                .flatMap(author -> author.getBooks().stream())
                .distinct()
                .count();
        System.out.println(count);
    }


    //forEach
    private static void test12() {
        //输出所有作家的名字
        List<Author> authors = getAuthors();
        authors.stream()
                .map(author -> author.getName())
                .distinct()
                .forEach(name -> System.out.println(name));
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