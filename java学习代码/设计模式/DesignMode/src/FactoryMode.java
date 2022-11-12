/**
 * @Description: 工厂模式(定义一个用于创建对象的工厂接口，让子类决定实例化哪一个类，工厂模式使一个类的实例化延迟到子类)的Java实现
 * @author: xiaxinyu
 * @Email: xiaxinyuxy@163.com
 * @date: 2022年11月08日 11:48
 * @Copyright: 个人版权所有
 * @version: 1.0.0
 */

public class FactoryMode {
    public static void main(String[] args) {
        Creator creator1 = new ConcreteCreator1();
        Creator creator2 = new ConcreteCreator2();
        Product product1 = creator1.factoryMethod();
        Product product2 = creator2.factoryMethod();
        product1.showInfo();
        product2.showInfo();
    }
}

interface Product{
    public void showInfo();
}

class ConcreteProduct1 implements Product {
    @Override
    public void showInfo() {
        System.out.println("产品1信息");
    }
}

class ConcreteProduct2 implements Product {
    @Override
    public void showInfo() {
        System.out.println("产品2信息");
    }
}

interface Creator{
    Product factoryMethod();
}

class ConcreteCreator1 implements Creator{
    @Override
    public Product factoryMethod() {
        return new ConcreteProduct1();
    }
}

class ConcreteCreator2 implements Creator{
    @Override
    public Product factoryMethod() {
        return new ConcreteProduct2();
    }
}