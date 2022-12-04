import java.util.*;

/**
 * @Description: 学习源码
 * @author: xiaxinyu
 * @Email: xiaxinyuxy@163.com
 * @date: 2022年11月11日 15:30
 * @Copyright: 个人版权所有
 * @version: 1.0.0
 */

public class Main {
    public static void main(String[] args) {
       TestI t = new TestC();
       System.out.println(t.getX());
    }
}

interface TestI{
    double getX();
}

class TestC implements TestI{
    double x;

    @Override
    public double getX() {
        return x;
    }
}