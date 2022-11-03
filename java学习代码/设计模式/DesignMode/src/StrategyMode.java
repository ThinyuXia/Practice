/**
 * @Description: 策略模式(它定义了算法家族，分别封装起来，让他们之间可以相互替换，此模式让算法的变化，
 * 不会影响到使用算法的客户)的Java实现——现金收费类
 * @author: xiaxinyu
 * @Email: xiaxinyuxy@163.com
 * @date: 2022年11月03日 16:02
 * @Copyright: 个人版权所有
 * @version: 1.0.0
 */

public class StrategyMode{
    public static void main(String[] args) {

    }
}

abstract class Strategy{
    //定义所有支持的算法的公共方法
    public abstract void algorithmMethod();
}


//可以在构造方法中传递策略类型，并利用switch函数将策略模式和简单工厂模式相结合，降低耦合度
class Context{
    private Strategy strategy;

    public Context(Strategy strategy){
        this.strategy = strategy;
    }
    //根据具体的策略对象，调用其算法的方法
    public void ContextMethod(){
        strategy.algorithmMethod();
    }
}

class ConcreteStrategyA extends Strategy{
    @Override
    public void algorithmMethod() {
        System.out.println("算法A的具体实现");
    }
}

class ConcreteStrategyB extends Strategy{
    @Override
    public void algorithmMethod() {
        System.out.println("算法B的具体实现");
    }
}

class ConcreteStrategyC extends Strategy{
    @Override
    public void algorithmMethod() {
        System.out.println("算法C的具体实现");
    }
}
