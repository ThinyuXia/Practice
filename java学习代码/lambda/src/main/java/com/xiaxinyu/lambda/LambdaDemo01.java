package com.xiaxinyu.lambda;

import java.util.function.Function;
import java.util.function.IntBinaryOperator;
import java.util.function.IntConsumer;
import java.util.function.IntPredicate;

/**
 * @Description:
 * @author: xiaxinyu
 * @Email: xiaxinyuxy@163.com
 * @date: 2022年11月12日 15:21
 * @Copyright: 个人版权所有
 * @version: 1.0.0
 */

public class LambdaDemo01 {
    public static void main(String[] args) {

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("新线程中run方法被执行了");
//            }
//        }).start();

        /**
         * lambda简化匿名内部类的前提是，匿名内部类实现的接口中只有一个抽象方法
         */
//        new Thread(() -> System.out.println("新线程中run方法被执行了")).start();

//        int res = calculateNum((a,b) -> a + b);
//        System.out.println(res);

//        printNum(new IntPredicate() {
//            @Override
//            public boolean test(int value) {
//                return value % 2 == 0;
//            }
//        });
//        printNum(n -> n % 2 == 0);

//        Integer res = typeConvert(new Function<String, Integer>() {
//            @Override
//            public Integer apply(String s) {
//                return Integer.valueOf(s);
//            }
//        });
//        Integer res = typeConvert((t) -> Integer.valueOf(t));
//        System.out.println(res);
//        foreachArr(new IntConsumer() {
//            @Override
//            public void accept(int value) {
//                System.out.println(value);
//            }
//        });
//        foreachArr(n -> System.out.println(n));
        printNum2(n -> n % 2 == 0,n -> n > 4);
    }

    public static int calculateNum(IntBinaryOperator operator){
        int a = 10;
        int b = 20;
        return operator.applyAsInt(a,b);
    }

    public static <R> R typeConvert(Function<String,R> function){
        String str = "1235";
        R result = function.apply(str);
        return result;
    }

    public static void printNum2(IntPredicate predicate,IntPredicate predicate2){
        int[] arr = {1,2,3,4,5,6,7,8,9,10};
        for(int n : arr){
            if(predicate.and(predicate2).test(n)){
                System.out.println(n);
            }
        }
    }
    public static void printNum(IntPredicate predicate){
        int[] arr = {1,2,3,4,5,6,7,8,9,10};
        for(int n : arr){
            if(predicate.test(n)){
                System.out.println(n);
            }
        }
    }

    public static void foreachArr(IntConsumer consumer){
        int[] arr = {1,2,3,4,5,6,7,8,9,10};
        for(int i : arr){
            consumer.accept(i);
        }
    }
}