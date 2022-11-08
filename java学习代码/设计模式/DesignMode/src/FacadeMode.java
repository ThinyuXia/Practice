/**
 * @Description: 外观模式(为子系统中的一组接口提供一个一致的界面，
 * 此模式定义了一个高层接口，这个接口使得子系统更加容易使用)的Java实现
 * @author: xiaxinyu
 * @Email: xiaxinyuxy@163.com
 * @date: 2022年11月08日 16:18
 * @Copyright: 个人版权所有
 * @version: 1.0.0
 */

public class FacadeMode {
    public static void main(String[] args) {
        Facade facade = new Facade();
        facade.methodA();
        facade.methodB();
    }
}

class Facade{
    SubSystemOne one;
    SubSystemTwo two;
    SubSystemThree three;
    SubSystemFour four;

    public Facade() {
        one = new SubSystemOne();
        two = new SubSystemTwo();
        three = new SubSystemThree();
        four = new SubSystemFour();
    }

    public void methodA(){
        System.out.println("方案组A----");
        one.methodOne();
        two.methodTwo();
        four.methodFour();
    }

    public void methodB(){
        System.out.println("方案组B----");
        two.methodTwo();
        three.methodThree();
    }
}

class SubSystemOne{
    public void methodOne(){
        System.out.println("子系统方法一");
    }
}

class SubSystemTwo{
    public void methodTwo(){
        System.out.println("子系统方法二");
    }
}

class SubSystemThree{
    public void methodThree(){
        System.out.println("子系统方法三");
    }
}

class SubSystemFour{
    public void methodFour(){
        System.out.println("子系统方法四");
    }
}