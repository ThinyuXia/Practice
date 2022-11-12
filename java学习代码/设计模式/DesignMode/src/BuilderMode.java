import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 建造者模式(将一个复杂对象的构建与他的表示分离，使得同样的构建过程可以创建不同的表示)的Java实现
 * @author: xiaxinyu
 * @Email: xiaxinyuxy@163.com
 * @date: 2022年11月12日 18:38
 * @Copyright: 个人版权所有
 * @version: 1.0.0
 */

public class BuilderMode {
    public static void main(String[] args) {
        /**
         * 指挥者类隔离用户和建造过程之间的关联，
         * 客户端不需要知道具体的建造过程
         */
        Director director = new Director();
        Builder builder1 = new ConcreteBuilder1();
        Builder builder2 = new ConcreteBuilder2();
        director.construct(builder1);
        director.construct(builder2);
        BuildProduct p1 = builder1.getResult();
        BuildProduct p2 = builder2.getResult();
        p1.show();
        p2.show();

    }
}

/**
 * 要建造出来的产品类，由多个部件组成
 */
class BuildProduct {
    List<String> parts = new ArrayList<>();
    //添加产品部件
    public void add(String part){
        parts.add(part);
    }

    public void show(){
        System.out.println("\n产品创建-----");
        for(String part : parts){
            System.out.println(part);
        }
    }
}

interface Builder{
    void BuildPartA();
    void BuildPartB();
    BuildProduct getResult();
}

class ConcreteBuilder1 implements Builder{
    private BuildProduct buildProduct = new BuildProduct();
    @Override
    public void BuildPartA() {
        buildProduct.add("部件1");
    }

    @Override
    public void BuildPartB() {
        buildProduct.add("部件2");
    }

    @Override
    public BuildProduct getResult() {
        return buildProduct;
    }
}

class ConcreteBuilder2 implements Builder{
    private BuildProduct buildProduct = new BuildProduct();

    @Override
    public void BuildPartA() {
        buildProduct.add("部件X");
    }

    @Override
    public void BuildPartB() {
        buildProduct.add("部件Y");
    }

    @Override
    public BuildProduct getResult() {
        return buildProduct;
    }
}

class Director{
    public void construct(Builder builder){
        builder.BuildPartA();
        builder.BuildPartB();
    }
}
