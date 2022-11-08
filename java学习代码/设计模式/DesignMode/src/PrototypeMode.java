/**
 * @Description: 原型模式(用原型实例指定创建对象的种类，并且通过拷贝这些原型创建新的对象)的Java实现
 * @author: xiaxinyu
 * @Email: xiaxinyuxy@163.com
 * @date: 2022年11月08日 11:58
 * @Copyright: 个人版权所有
 * @version: 1.0.0
 */

public class PrototypeMode {
    public static void main(String[] args) {
        Prototype concretePrototype1 = new ConcretePrototype1();
        concretePrototype1.setName("原型1");
        Prototype concretePrototype2 = concretePrototype1.clonePrototype();
        concretePrototype2.setName("原型2");
        System.out.println(concretePrototype1.getName());
        System.out.println(concretePrototype2.getName());
    }
}


abstract class Prototype{
    private String name;
    abstract Prototype clonePrototype();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

class ConcretePrototype1 extends Prototype implements Cloneable{

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    Prototype clonePrototype() {
        try {
            return (Prototype) this.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
}


class ConcretePrototype2 extends Prototype implements Cloneable{

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    Prototype clonePrototype() {
        try {
            return (Prototype) this.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
}