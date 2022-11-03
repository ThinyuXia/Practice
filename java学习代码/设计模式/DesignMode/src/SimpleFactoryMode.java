/**
 * @Description: 简单工厂模式的Java实现——实现了一个简单运算工厂类,可根据不同的运算操作符，返回不同的实例化对象
 * @author: xiaxinyu
 * @Email: xiaxinyuxy@163.com
 * @date: 2022年11月03日 15:21
 * @Copyright: 个人版权所有
 * @version: 1.0.0
 */

public class SimpleFactoryMode {
    public static Operation createOperation(String operate){
        Operation operation = null;
        switch (operate){
            case "+":
                operation = new OperationAdd();
                break;
            case "-":
                operation = new OperationSub();
                break;
            case "*":
                operation = new OperationMul();
                break;
            case "/":
                operation = new OperationDiv();
                break;
        }
        return operation;
    }

    public static void main(String[] args) {
        Operation opr = createOperation("+");
        opr.setNumberA(1);
        opr.setNumberB(2);
        System.out.println(opr.getResult());
    }
}

class Operation{
    private double numberA;
    private double numberB;

    public double getNumberA() {
        return numberA;
    }

    public void setNumberA(double numberA) {
        this.numberA = numberA;
    }

    public double getNumberB() {
        return numberB;
    }

    public void setNumberB(double numberB) {
        this.numberB = numberB;
    }

    public double getResult(){
        double result = 0;
        return result;
    }
}

class OperationAdd extends Operation{
    @Override
    public double getResult() {
        return getNumberA() + getNumberB();
    }
}

class OperationSub extends Operation{
    @Override
    public double getResult(){
        return getNumberA() - getNumberB();
    }
}

class OperationMul extends  Operation{
    @Override
    public double getResult(){
        return getNumberA() * getNumberB();
    }
}

class OperationDiv extends Operation{
    @Override
    public double getResult(){
        try{
            return getNumberA() / getNumberB();
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }
}