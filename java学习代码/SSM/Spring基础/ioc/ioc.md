#### 一、SpringIoC

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
