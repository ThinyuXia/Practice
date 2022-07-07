##### 1.ioc控制反转

##### 1.1 ioc概念 

IoC控制反转是一种设计理念与语言无关，全称Inverse Of Control，是一种设计理念，由代理人来创建与管理对象，消费者通过代理人来获取对象。

目的：降低对象之间的耦合

##### 1.2DI概念

DI依赖注入，全称Dependency Injecttion 是IoC的具体技术实现，是微观实现，在Java中利用反射技术实现对象注入

##### 1.3IoC容器职责

* 对象的控制权交由第三方统一管理(IoC控制反转)
* 利用Java反射技术实现运行时创建对象与关联(DI依赖注入)
* 基于配置提高应用程序的可维护性与扩展性

##### 2.Spring

##### 2.1Spring含义

* 狭义：Spring是用来简化java开发的框架(Spring Framework)
* 广义：Spring是指Spring整个生态体系

##### 2.2Spring核心

IoC容器与AOP面向切面编程

##### 2.2.1IoC容器初始化

##### 2.2.1.1三种配置方式

* 基于XML配置Bean

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd">

  	
    <!--    在IoC容器启动时，自动由Spring实例化Apple，生成名为sweetApple的对象并放到容器中
						1.bean标签默认通过默认构造方法实例化对象	-->
    <bean id="sweetApple" class="com.xiaxinyu.spring.ioc.entity.Apple">
        <constructor-arg name="color" value="红色"/>
        <constructor-arg name="origin" value="欧洲"/>
        <constructor-arg name="title" value="红富士"/>
    </bean>

    <bean id="sourApple" class="com.xiaxinyu.spring.ioc.entity.Apple">
        <constructor-arg index="0" value="绿色"/>
        <constructor-arg index="1" value="中亚"/>
        <constructor-arg index="2" value="青苹果"/>
    </bean>

    <bean id="softApple" class="com.xiaxinyu.spring.ioc.entity.Apple">
        <property name="color" value="黄色"/>
        <property name="origin" value="中国"/>
        <property name="title" value="金帅"/>
    </bean>

    <bean id="lily" class="com.xiaxinyu.spring.ioc.entity.Child">
        <property name="name" value="lily"/>
        <property name="apple" ref="sweetApple"/>
    </bean>

    <bean id="andy" class="com.xiaxinyu.spring.ioc.entity.Child">
        <property name="name" value="andy"/>
        <property name="apple" ref="sourApple"/>
    </bean>

    <!--    利用setter实现对象依赖注入-->
    <bean id="luna" class="com.xiaxinyu.spring.ioc.entity.Child">
        <property name="name" value="luna"/>
        <property name="apple" ref="softApple"/>
    </bean>

    <!--    2.利用静态工厂获取对象-->
    <bean id="apple1" class="com.xiaxinyu.spring.ioc.factory.AppleStaticFactory" factory-method="createSweetApple"/>


    <!--    3.利用工厂实例方法创建对象-->
    <bean id="factoryInstance" class="com.xiaxinyu.spring.ioc.factory.AppleFactoryInstance"/>
    <bean id="apple2" factory-bean="factoryInstance" factory-method="createSweetApple"/>


    <bean id="c1" class="com.xiaxinyu.spring.ioc.entity.Computer">
        <constructor-arg name="brand" value="联想"/>
        <constructor-arg name="type" value="联想"/>
        <constructor-arg name="sn" value="台式机"/>
        <constructor-arg name="price" value="3085"/>
    </bean>
    <!-- 没有id与name的bean默认使用类名全称作为bean标识符-->
    <bean class="com.xiaxinyu.spring.ioc.entity.Computer">
        <constructor-arg name="brand" value="联想"/>
        <constructor-arg name="type" value="联想"/>
        <constructor-arg name="sn" value="台式机"/>
        <constructor-arg name="price" value="3085"/>
    </bean> 

    <bean id="company" class="com.xiaxinyu.spring.ioc.entity.Company">
        <property name="rooms">
            <list>
                <value>2001-总裁办</value>
                <value>2002-总经理办公室</value>
                <value>2003-研发部会议室</value>
            </list>
        </property>

        <property name="computers">
            <map>
                <entry key="dev-88172" value-ref="c1"/>
                <entry key="dev-88173">
                  <!--无法通过getBean方法获取该内部bean-->
                    <bean class="com.xiaxinyu.spring.ioc.entity.Computer ">
                        <constructor-arg name="brand" value="联想"/>
                        <constructor-arg name="type" value="联想"/>
                        <constructor-arg name="sn" value="台式机"/>
                        <constructor-arg name="price" value="3085"/>
                    </bean>
                </entry>
            </map>
        </property>

        <property name="info">
            <props>
                 <prop key="phone">8304011</prop>
                 <prop key="address">北京市</prop>
            </props>
        </property>
    </bean>
</beans>
```

##### 静态工厂

```java
package com.xiaxinyu.spring.ioc.factory;

import com.xiaxinyu.spring.ioc.entity.Apple;
/**
*静态工厂通过静态方法创建对象，隐藏创建对象的细节
*/
public class AppleStaticFactory {
    public static Apple createSweetApple(){
        Apple apple = new Apple();
        apple.setTitle("红富士");
        apple.setOrigin("欧洲");
        apple.setColor("红色");
        return apple;
    }
}

```



##### 工厂实例

```java
package com.xiaxinyu.spring.ioc.factory;

import com.xiaxinyu.spring.ioc.entity.Apple;

/**
 * 工厂实例方法创建对象是指IoC容器对工厂类进行实例化并调用对应的方法完成实例创建的过程
 */
public class AppleFactoryInstance {
    public Apple createSweetApple(){
        Apple apple = new Apple();
        apple.setTitle("红富士");
        apple.setOrigin("欧洲");
        apple.setColor("红色");
        return apple;
    }
}

```





```java
public class SpringApplication {
    public static void main(String[] args) {
      //初始化IoC容器并实例化对
        String[] configLocations = {"classpath:applicationContext-1.xml","classpath:applicationContext.xml"};
      	ApplicationContext context = new ClassPathXmlApplicationContext(configLocations);
        Apple sweetApple = context.getBean("sweetApple", Apple.class); //从IoC容器中获取bean
        // Apple sweetApple = (Apple)context.getBean("sweetApple"); 不推荐做法
        
    }
}
```

##### bean标签补充内容

##### 1.id与name属性相同点

* id 与 name都是设置对象在IoC容器中的唯一标识
* 两者在同一个配置文件中都不允许出现重复
* 两者允许在多个配置文件中出现重复，新对象覆盖旧对象

##### 2.id与name属性不同点

* id要求更为严格，一次只能定义一个对象标识（推荐）
* name更为宽松，一次允许定义多个对象标识（用，分割）

##### 对象依赖注入

概念：依赖注入是指运行时将容器内对象利用反射赋给其他对象的操作

* 利用setter方法注入对象
* 利用构造方法注入对象

##### 查看容器内对象

```java
 //获取容器内所有beanId的数组
String[] beanNames = context.getBeanDefinitionNames();
for(String beanName : beanNames){
  System.out.println(beanName);
  System.out.println("类型：" + context.getBean(beanName).getClass().getName());
  System.out.println("类型：" + context.getBean(beanName).toString());
}
```



##### bean scope属性

概念：

##### 	1.bean scope属性用于决定对象何时被创建与作用范围

##### 	2.bean scope配置将影响容器内对象的数量

##### 	3.bean scope默认值singleton(单例)，指全局共享同一个对象实例

##### 	å4.默认情况下bean会在IoC容器创建后自动实例化，全局唯一

![截屏2022-07-06 下午7.22.05.png](https://pic.rmb.bdstatic.com/bjh/9433bea57ee0a8219af67b01d0917aae.png)



![截屏2022-07-06 下午7.39.14.png](https://pic.rmb.bdstatic.com/bjh/24d6a27c2b6ef38d1fe27d0634526098.png)



##### bean的生命周期

 ![截屏2022-07-06 下午8.10.25.png](https://pic.rmb.bdstatic.com/bjh/c442abaf8a75124dc6ab6e6e0822b2ff.png)



* 基于注解配置Bean

##### 开启组件扫描

```java

```

##### 优势

* 摆脱繁琐的XML形式的bean与依赖注入配置
* 基于“声明式”的原则，更适合轻量级的现代企业应用
* 让代码可读性变得更好，研发人员拥有更好的开发体验

##### 注解类型

* 组件类型注解-声明当前类的功能与职责

		##### 	1.@Component: 组件注解，通用注解，被该注解描述的类将被IoC容器管理并实例化

##### 	2.@Controller：语义注解，说明当前类是MVC应用中的控制器类

##### 	3.@Service：语义注解，说明当前类是Service业务服务类

##### 	4.@Repository：语义注解，说明当前类用于业务持久层，通常描述对应Dao类

* 自动装配注解-根据属性特征自动注入对象

![截屏2022-07-06 下午11.10.08.png](https://pic.rmb.bdstatic.com/bjh/2b9875d2707e0e6fe3f4f9c20db2238f.png)

* 元数据注解-更细化的辅助IoC容器管理对象的注解

![截屏2022-07-06 下午11.52.15.png](https://pic.rmb.bdstatic.com/bjh/e74881c0ba8e9583b16a8c44649c1ada.png)

* 基于JavaConfig配置IoC容器

##### 优势

* 完全摆脱XML的束缚，使用独立Java类管理对象与依赖
* 注解配置相对分散，利用Java Config可对配置集中管理
* 可以在编译时进行依赖检查，不容易出错



##### Spring Test

概念：

* Spring Test是Spring中用于测试的mokuai
* Spring Test对JUnit单元测试框架有良好的整合
* 通过Spring TEst可在JUnit在单元测试时自动初始化IoC容器

Spring与Junit4整合过程

* Maven工程以来spring-test
* 利用@RunWith与@ContextConfiguration描述测试用例类
* 测试用例类从容器获取对象完成测试用例的执行
