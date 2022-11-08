/**
 * @Description: 模板方法模式(定义一个操作中的算法的骨架，而将一些步骤延迟到子类中。模板方法使得子类可以
 * 不改变一个算法的结构即可重新定义该算法的某些特定步骤)的Java实现
 * @author: xiaxinyu
 * @Email: xiaxinyuxy@163.com
 * @date: 2022年11月08日 16:07
 * @Copyright: 个人版权所有
 * @version: 1.0.0
 */

public class TemplateMethodMode {
    public static void main(String[] args) {
        AbstractClass c = new ConcreteClassA();
        c.templateMethod();
        c = new ConcreteClassB();
        c.templateMethod();
    }
}

abstract class AbstractClass{
    public abstract void primitiveOperation1();
    public abstract void primitiveOperation2();

    public void templateMethod(){
        primitiveOperation1();
        primitiveOperation2();
    }
}

class ConcreteClassA extends AbstractClass{
    @Override
    public void primitiveOperation1() {
        System.out.println("具体类A方法1实现");
    }

    @Override
    public void primitiveOperation2() {
        System.out.println("具体类A方法2实现");
    }
}

class ConcreteClassB extends AbstractClass{
    @Override
    public void primitiveOperation1() {
        System.out.println("具体类B方法1实现");
    }

    @Override
    public void primitiveOperation2() {
        System.out.println("具体类B方法2实现");
    }
}