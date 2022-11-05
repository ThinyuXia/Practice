/**
 * @Description: 装饰模式(动态地给一个对象添加一些额外的职责，就增加功能来说，装饰模式比生成子类更为灵活 )的Java实现
 * @author: xiaxinyu
 * @Email: xiaxinyuxy@163.com
 * @date: 2022年11月05日 17:09
 * @Copyright: 个人版权所有
 * @version: 1.0.0
 */

public class DecoratorMode {
    public static void main(String[] args) {
        /**
         * 装饰的方法是：首先用ConcreteComponent实例化对象c，然后用ConcreteDecoratorA实例化的对象d1
         * 来装饰c，然后用ConcreteDecoratorB实例化的对象d2来装饰d1，最终执行d2的operation()方法
         */
        ConcreteComponent c = new ConcreteComponent();
        ConcreteDecoratorA d1 = new ConcreteDecoratorA();
        ConcreteDecoratorB d2 = new ConcreteDecoratorB();
        d1.setComponent(c);
        d2.setComponent(d1);
        d2.operation();
    }
}

/**
 * 定义Component对象接口，可以给这些对象动态的添加职责
 */
interface  Component{
    void operation();
}

/**
 * 定义了一个具体的对象，也可以给这个对象添加一些职责
 */
class ConcreteComponent implements Component{
    @Override
    public void operation() {
        System.out.println("具体对象的操作");
    }
}

/**
 * 装饰抽象类，实现了Component接口，扩展了Component的功能，但对于Component来讲，是不需要知道Decorator的存在的
 */
class Decorator implements Component{
    protected  Component component;

    public void setComponent(Component component) {
        this.component = component;
    }

    @Override
    public void operation() {
        if(component != null){
            component.operation();
        }
    }
}

/**
 * 实例化具体的装饰对象，起到给Component添加职责的作用
 */
class ConcreteDecoratorA extends Decorator{
    private String addedState; //本类的独有功能，以区别于ConcreteDecorationB

    @Override
    public void operation() {
        //首先运行原Component的Operation()，再执行本类的功能，相当于对原Component进行了装饰
        super.operation();
        addedState = "New State";
        System.out.println("具体装饰对象A的操作");
    }
}

class ConcreteDecoratorB extends Decorator{

    @Override
    public void operation() {
        //首先执行原Component的Operation()，再执行本类的功能，如addedBehavior，相当于对原Component进行了装饰
        super.operation();
        addedBehavior();
        System.out.println("具体装饰对象B的操作");
    }

    private void addedBehavior(){

    }
}