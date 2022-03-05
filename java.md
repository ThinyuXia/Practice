Java细节知识点



#### 一、基础知识



##### 1.java有八种基本数据类型分别是byte，boolean，char，short，int，float，double，long，分别占1，1，2，2，4，4，8，8个字节，1字节 = 1byte = 8bit

​	以int类型为例对类型的数字范围进行简单说明：int类型变量占4个字节，一个字节有8bit，也就是8位二进制数，那么4个字节就应该有32位二进制数，但是int类型的变量范围是[-2^31,2 ^ 31 - 1]，原因是最高位为符号位，1表示负数，0表示正数

##### 2.java有三种引用数据类型，分别是类(class)，接口(interface)，数组([])`注：String 属于类`

##### 3.java中有两条数据类型自动转换路径，且都是由低精度到高精度

* 1.char->int->long->float->double
* 2.byte->short->int->long->float->double

##### 4.java中char类型不能与byte和short类型进行转换，且这三种数据类型在进行运算时会自动转换成int类型

​	🌰：byte b1 = 10;					 short s1 = 1;

​					 short a = b1 + s1，这段代码会报错，因为在不进行强制转换的情况下不可以将精度(容量)大类型的变量赋值给精度(容量)小类型的变量

##### 5.java中自动提升原则：表达式结果的类型自动提升为操作数中最大的类型

##### 6.char类型可以保存int的常量值，但不能保存int的变量值，若想保存int的变量值则需要强制转换

##### 7.使用基本类型包装器中的parse***方法，可将`有效的`String类型数据转换成相应基本数据类型的包装器

##### 8.取模运算的本质：a % b = a - [a / b] * b

##### 9.&&(||)为短路与(短路或)，&(|)为逻辑与(或)

* &&(||)：当第一个条件为false(true)时，不判断第二个条件，最终结果为false(true)，效率高
* &(|): 不管第一个条件是true还是false，都要进行第二个条件的判断，效率低

##### 10.在进行复合赋值运算时会自动进行类型转换

​	🌰

```java
byte b = 3; 
b += 2; // 等价于 (b = (byte)(b + 2));
b ++;   // 等价于(b = (byte)(b + 1));
```



​	三目运算符的复合运算

​	🌰

```java
Object obj1 = true ? Integer.valueOf(1) : Double.valueOf(2); //三目运算符是一个整体
        System.out.println(obj1);  //将int自动转换成double类型,输出1.0
```



##### 11.java汇总标识符的命名规则

* 1.包名字母均小写
* 2.类名和接口名所有单词首字母大写
* 3.变量名和方法名采用驼峰命名法
* 4.常量名所有字母均大写，单词之间用下划线连接

##### 12.进制表示方法(其他进制在java中输出时都会转为十进制)

* 二进制以0b或0B开头
* 八进制以0开头
* 十六进制以0x或0X开头

##### 13.进制转换

* 1. n进制转10进制：从最低位(右边)开始，将每个位上的数提取出来，乘以n的(位数 - 1)次方，然后求和即可
* 2. 10进制转换成n进制：将数字不断除以n，直到商为0，然后将余数倒过来就是转换后的n进制数
* 3. 2进制转8进制：从低位开始，将二进制的数每三位一组，转成对应的八进制数即可(不足三位时，高位补零)
* 4. 2进制转16进制：每四位一组，转成对应的十六进制数即可(不足四位时，高位补零)
* 5. 8进制转2进制：将8进制的每一位转成对应的3位二进制数即可
* 6. 16进制转2进制：将16进制的每一位转成对应的4位二进制数即可

##### 14.二进制基础知识以及源码、反码、补码

 * 1.二进制最高位为符号位——0表示正数，1表示负数
 * 2.正数以及0的原码、反码、补码一致(三码合一)
 * 3.负数的反码为原码符号位不变，其它位取反，补码为反码加1
 * 4.java中没有无符号数，java中任何数都是有符号的
 * 5.计算机在运算时都是以补码来运算的，但是结果是以原码来体现的

##### 15.位运算基础

	* 1.算数右移 >> ：低位溢出，符号位不变，并且用符号位填补高位
	* 2.算数左移 << ：符号位不变，低位补0
	* 3.无符号右移>>> ：低位溢出，高位补0



#### 二、基本控制结构



##### 1.switch(表达式)的可用类型：byte、short、int、char、enum、String

##### 2.switch中，case 字句中的值必须是常量且和表达式中的变量类型相同或者可以转换成表达式类型，default字句是可选的

##### 3.如果return写在main方法中表示结束程序



#### 三、数组与类

##### 1.一维数组的初始化方式

* 1.静态初始化

  🌰 int[] a = {2,3,4,5,6};

* 2.动态初始化

  * 先声明再初始化

    🌰 int[] a;

  ​		 a = new int[5];

  * 声明的同时初始化

    🌰int[] a = new int[5];

##### 2.数组之间赋值为引用传递，基本数据类型之间为值传递

##### 3.二维数组的初始化方式

 * 1.静态初始化

   🌰 int[] [] a = {{2,3,4,5,6},{2,3,4,5,6}};

* 2.动态初始化

  🌰 int[] [] a = new int[3] [2]; 等价于 int[] a[] = new int[3] [2]; 和  int a[] [] = new int[3] [2];

  🌰 int[] [] a;

  ​	  a = new int[3] [2]

  🌰 先声明二维数组长度再为一维数组分配空间

  ​	int[] [] a = new int[2] [];

  ​	a[0] = new int[3];

  ​	a[1] = new int[4];

##### 4.字符串数组的特殊初始化方式

🌰 String[] strs = new String[]{"211","985"}; // ok

##### 5.java中内存分配机制

	* 1.栈：一般存放基本数据类型(局部变量)
	* 2.堆：存放对象
	* 3.方法区：常量池(常量，字符串)，类加载信息

##### 6.方法调用细节

当程序执行到方法时会为该方法开辟一个独立的栈空间，当方法执行完毕或者执行到return语句时就会返回到调用方法的地方，然后继续执行方法后面的代码

##### 7.方法返回值细节

* 1.一个方法最多有一个返回值
* 2.返回值类型可以为任意类型
* 3.如果创建方法时要求有返回值，则方法体中最后一条语句必须为return 返回值;而且返回值类型必须和要求类型一致或兼容
* 4.如果方法要求返回值是void，那么可以不写return或者只写return

##### 8.方法中传递引用易错点

​	如果向一个方法A中传递一个已经创建好的对象(地址)P，那么在方法A中执行P = null;会发生什么呢？

​	实际上什么也没有发生，方法A中的P和原来创建的P是毫无关系的，它们两个存在于不同的栈中，只不过在A方法中将P置为null之前，这两个P指向的都是同一个对象而已(类似二级指针)

##### 9.方法重载细节

	* 1.方法名必须相同
	* 2.参数列表必须不同，参数类型参数顺序，参数个数至少有一样不同，但是对参数名没有要求
	* 3.返回值类型无要求

##### 10.可变参数

Java允许在方法中传递任意个参数，需要用到可变参数来实现

基本语法 ： 返回值类型 方法名(数据类型... 可变参数名)

可变参数使用细节

* 1.可变参数的实参可以为0个或者任意个，这个可变参数本质上是数组，因此实参可以为数组
* 2.可变参数不能同时接收数组和基本数据类型
* 3.可变参数可以和普通类型的参数一起放在形参列表中，但是可变参数必须放在最后且只能有一个可变参数

🌰

```java
public class Main{
    public static void main(String[] args){
        Test test = new Test();
        int[] a = {2,3,4,5};
        test.test1(2,a);   //正确

    }
}

class Test{
    // public void test1(int... a,int... b){ //多个可变参数导致错误
    // public void test1(int... a,int b){   //可变参数没有放在最后导致错误
    public void test1(int b,int... a){
        System.out.println(a.length);
    }
}
```

##### 11.变量作用域

1.java中主要的变量就是属性(成员变量/全局变量)和局部变量，局部变量一般指在成员方法中定义的变量，全局变量一般指在类中定义的属性

2.全局变量作用域为整个类，局部变量的作用域为该变量的方法或代码块中

3.全局变量和局部变量的区别

	* 全局变量可以只声明不初始化，因为有默认值，默认值的规则与数组相同，而局部变量没有默认值声明后必须要初始化
	* 全局变量可以用访问修饰符修饰，而局部变量不可以
	* 全局变量的生命周期较长，它随着对象的创建而创建，而局部变量的生命周期较短，随着代码块的执行而创建，当代码块执行结束后，局部变量会自动销毁

4.就近原则：全局变量和局部变量可以重名，在访问时采取就近原则，即访问变量的语句距离哪个变量进就用那个变量，但是在一个类中全局变量是不能重名的，在一个方法或代码块中局部变量也是不能重名的

##### 12.构造方法/构造器 	! ! !重点

* 1.构造器的修饰符可以是默认的，也可以是指定的

* 2.构造器没有返回值

* 3.构造器名字必须和类名相同

* 4.参数列表和成员方法的规则相同

* 5.构造器的调用是由系统完成的，如果没有手动定义构造器，那么当创建对象时系统就会自动调用系统生成的无参构造器

  如果已经手动定义了构造器，系统就不会调用默认的无参构造器

构造器的作用就是在创建对象时完成对象的初始化操作，即给相关的属性赋值

##### 13.对象创建流程分析

首先加载类信息->在堆空间中给对象分配空间->调用构造器完成对象初始化->将堆空间中的对象地址进行返回

14.this关键字

this关键字作用

1.访问本类的属性，方法，构造器

2.区分当前类的全局变量和局部变量

3.可以在一个构造器调用另一个构造器来完成对象的初始化

🌰

```java
package com.xia.euqals;
public class Main{
    public static void main(String[] args){
        Test test = new Test("Thinyu",20,99);
        System.out.println(test.toString());
    }
}

class Test{
    private String name;
    private int age;
    private double score;
    public Test(String name){
        this.name = name;
    }
    public Test(String name,int age,double score){
        this(name); //使用this调用前一个构造器，完成对name属性的初始化
        this.age = age;
        this.score = score;
    }

    @Override
    public String toString() {
        return "Test{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", score=" + score +
                '}';
    }
}
```



this注意细节

1.this只能在类定义的方法内使用，不能在类外部使用

2.this关键字在构造器中调用另外一个构造器时必须是第一条语句，且在该构造器中只能用this调用一次其他构造器



##### 15.访问修饰符

​	

| 1    | 访问级别 | 访问控制修饰符      | 同类 | 同包 | 子类 | 不同包 |
| ---- | -------- | ------------------- | ---- | ---- | ---- | ------ |
| 2    | 公开     | public              | ✅    | ✅    | ✅    | ✅      |
| 3    | 受保护   | protected           | ✅    | ✅    | ✅    | ❌      |
| 4    | 默认     | friendly 也可以省略 | ✅    | ✅    | ❌    | ❌      |
| 5    | 私有     | private             | ✅    | ❌    | ❌    | ❌      |



使用细节

1.只有默认的和public才可以修饰类

2.采用默认修饰的父类的属性和方法在同包中的子类可以访问，不同包中的子类不能访问！！！

3.import只能导入使用public 修饰的类

#### 四、封装、继承和多态

##### 1.封装

##### 1.1封装基本概念

##### 	封装(encapsulation)就是把抽象出的数据和方法封装在一起，数据被保护在内部，程序中的其他部分只有通过被授权的方法才能对数据进行操作

##### 1.2封装的好处

  1.隐藏实现细节

  2.可以对数据进行验证，保证数据安全合理

##### 1.3封装的实现步骤 

  将属性私有化 -> 提供公共的(public)方法，用于对属性进行合理性的判断和赋值 -> 提供公共的get方法，用于获取属性的值



##### 2.继承

##### 2.1继承基本概念

​	继承可以提高代码的复用性，当多个类存在相同的属性和方法时，可以从这些类中抽象出父类，在父类中定义这些相同的属性和方法，所有的子类不需要重新定义这些属性和方法，只需要通过关键字extends来声明继承父类即可

##### 2.2继承基本语法

```java
class 子类 extends 父类{
  //1.子类会自动拥有父类定义的属性和方法
  //2.父类又叫做超类，基类
  //3.子类又叫做派生类
}
```

##### 2.3继承有关构造器细节

1.子类必须调用父类的构造器来完成父类的初始化，如果不在子类的构造器中写super和this则默认执行super();语句来调用父类的无参构造器

2.当创建子类对象时，不管使用子类的哪个构造器，默认情况下总会调用父类的无参构造器，如果父类没有提供无参构造器，则必须使用super来指定使用父类中的哪个构造器来完成父类的初始化工作

3.可以使用super来指定调用父类的哪个构造器但是需要参数列表的参数一一对应 ：super(参数列表) 对应 想使用的构造器(参数列表)

4.super与this在使用时都只能放在第一行说明super和this不能共存与同一个构造器

5.java中所有类都是Object的子类，Object是所有类的祖先类

6.父类构造器的调用不限于父类，将一直追溯到Object类

7.子类只能继承一个父类，即类之间在java中是单继承机制

8.子类和父类之间必须满足 is - a 关系

##### 2.4查看子类属性和调用子类方法的细节

1.首先看子类中是否有该属性

2.如果子类中有该属性并且可以访问则返回信息

3.如果子类没有这个属性就看父类中有没有这个属性，如果父类中有这个属性并且可以访问则返回信息

4.如果父类中没有这个属性，就按照3的规则继续找上级父类直到Object

##### 2.5继承的内存布局

![](https://z3.ax1x.com/2021/10/09/5Fd4r4.md.png)

​								子类对象在堆中开辟的空间为子类和父类的属性和方法开辟的空间总和！！ ！

##### 2.6super关键字

##### 2.6.1 super基本介绍

​	super代表父类的引用，用来访问父类的属性、方法、构造器

##### 2.6.2 super基本语法

1.在同一个包中，不能访问父类的private方法

2.super(参数列表); 必须放在构造器的第一句而且在一个构造器中只能出现一次，不能和this同时出现在一个构造器中

3.super的访问不限于父类，如果爷爷类和本类中有同名的成员，可以使用super来访问爷爷类的成员，如果多个基类(上级类)中都有同名的成员，super的访问遵循就近原则

##### 2.6.3 super与this的比较

| No.  | 区别点     | this                                                       | super                                                 |
| ---- | ---------- | ---------------------------------------------------------- | ----------------------------------------------------- |
| 1    | 访问属性   | 访问本类中的属性，如果本类中没有该属性，则向父类中寻找     | 访问父类中属性，如果父类中没有则向父类的父类中寻找    |
| 2    | 调用方法   | 访问本类中的方法，如果本类中没有该方法，则从父类中继续寻找 | 访问父类中的方法，如果父类中没有则向父类的父类中寻找  |
| 3    | 调用构造器 | 调用本类的构造器，this语句必须放在构造器的首行             | 调用父类的构造器，super语句必须放在`子类`构造器的首行 |
| 4    | 特殊       | 表示当前对象                                               | 表示父类对象                                          |
|      |            |                                                            |                                                       |

##### 2.7方法重载(overload)和重写(override)的比较 

| 名称           | 发生范围 | 方法名   | 形参列表                       | 返回类型                                                     | 修饰符                             |
| -------------- | -------- | -------- | ------------------------------ | ------------------------------------------------------------ | ---------------------------------- |
| 重载(overload) | 本类     | 必须相同 | 类型，顺序，个数至少有一个不同 | 无要求                                                       | 无要求                             |
| 重写(override) | 子类     | 必须相同 | 必须相同                       | 要求子类重写的方法的返回类型和父类返回的类型一致或者是父类返回类型的子类 | 子类方法不能缩小父类方法的访问范围 |



🌰

```java
class A{
    public Object test(int a){
        return 1;
    }
}

class B extends A{
    public String test(int a){  //如果访问修饰符不使用public会报错
        return "111";
    }
}
```



##### 3多态

##### 3.1多态基本概念

方法或者对象具有多种形态，是面向对象的第三大特征，多态是建立在封装和继承的基础之上

多态体现

1.方法多态

(1)重载体现多态   (2)重写体现多态

2.对象多态

(1)对象的编译类型和运行类型可以不一致，编译类型在定义时就已确定，不可改变

(2)对象的运行类型可以改变，通过getClass()来查看当前运行类型 

(3)编译类型看运行时 = 的左边，运行类型看 = 的右边

##### 3.2多态的具体实现

1.一个对象的编译类型和运行类型可以不一致

2.编译类型在定义对象时就已确定，不能改变

3.运行类型是可以变化的

4.编译类型 = 运行类型

🌰

```java
Animal animal = new Dog(); // Animal 就是编译类型，而运行类型是Dog
animal = new Cat() //animal的运行类型改变为Cat,编译类型仍然是Animal
```

##### 3.3多态的使用细节

1.使用多态的前提是两个类之间，或实现类与接口之间存在继承关系

2.1 多态的向上转型本质是父类的引用指向了子类的对象

2.2 多态向上转型语法： 父类类型 引用名 = new 子类类型();

2.3 多态向上转型特点：编译类型看左边，运行类型看右边。 

​	 可以调用父类中的所有成员(需遵守访问权限)

​	 不能调用子类中的特有成员——因为在编译过程中已经确定编译类型以及该类型的成员，也就是说能调用哪些成员和访问哪些属性是由编译类型决定的，子类可以重写编译类型的`方法`，不可以用子类中的属性覆盖父类中的属性，不可以用编译类型的变量访问子类中的特有成员

2.4 多态向下转型语法：子类类型 引用名 = (子类类型) 父类引用 (有点强制类型转换的意思);

2.5 多态向下转型特点：将指向子类对象的父类引用转换成指向子类类型的子类引用，被转换的父类引用可调用子类特有方法

##### 3.4多态的应用

1.多态数组：数组的定义类型为父类类型，里面保存的实际元素为子类类型

2.多态参数：方法定义的形参类型为父类类型，实参类型允许为子类类型

3.instanceof 比较操作符的使用，用于判断左边对象运行类型是否等于右边或者是右边类型的子类型，可根据判断结果进行向下转型来调用子类特有的方法

🌰

```java
class Student extends Person{
    private String id;

    public Student(String name, char sex, int age, String id) {
        super(name,sex,age);
        this.id = id;
    }

    public String play(){
        return super.play() + "足球";
    }

    public void printInfo(){
        System.out.println("学生的信息：");
        System.out.println(super.baseinfo());
        System.out.println("学号：" + id);
        study(); // 组合，千变万化
        System.out.println(play());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                '}' + super.toString();
    }

    public void study(){
        System.out.println("我承诺我会好好学习 java");
    }

}

class Teacher extends Person{
    private int workAge;

    public Teacher(String name, char gender, int age, int workAge) {
        super(name,gender,age);
        this.workAge = workAge;
    }


    public String play(){
        return super.play() + "象棋";
    }

    public void printInfo(){
        System.out.println("老师的信息：");
        System.out.println(super.baseinfo());
        System.out.println("工龄：" + workAge);
        teach();
        System.out.println(play());
    }


    public int getWorkAge() {
        return workAge;
    }

    public void setWorkAge(int workAge) {
        this.workAge = workAge;
    }

    public void teach(){
        System.out.println("我承诺，我会认真教学 java");
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "workAge=" + workAge +
                '}' + super.toString();
    }
}

class Person{
    private String name;
    private char gender;
    private int age;

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", gender=" + gender +
                ", age=" + age +
                '}';
    }

    public Person(String name, char gender, int age) {
        this.name = name;
        this.gender = gender;
        this.age = age;
    }

    // 把共有的输出内容写到父类
    public String play(){
        return name + "爱玩";
    }

    // 返回基本信息
    public String baseinfo(){
        return "姓名：" + name + "\n年龄：" + age + "\n性别：" + gender;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

public class Main{
    public static void main(String[] args){
        Student student = new Student("小夏", '男', 20, "20203644");
        student.printInfo();
        System.out.println("--------------");
        Teacher teacher = new Teacher("xiaowei", '女', 40, 10);
        teacher.printInfo();

        Person[] persons = new Person[4];
        persons[0] = new Student("jack", '男', 15, "20203645");
        persons[1] = new Student("mary", '女', 15, "20203646");
        persons[2] = new Teacher("smith", '男', 36,5);
        persons[3] = new Teacher("scott", '男', 26,4);

        bubbleSort(persons);

        System.out.println("输出排序后的数组---------");
        for(int i = 0;i < persons.length;i ++){
            System.out.println(persons[i]);
        }

        System.out.println("遍历数组调用test方法---------");
        for(int i = 0;i < persons.length;i ++){
            test(persons[i]);
        }
    }

    //方法，完成年龄从高到底排序

    public static void bubbleSort(Person[] persons){
        for(int i = 0;i < persons.length;i ++){
            for(int j = i + 1;j < persons.length;j ++){
                if(persons[i].getAge() < persons[j].getAge()){
                    Person temp = persons[i];
                    persons[i] = persons[j];
                    persons[j] = temp;
                }
            }
        }
    }

    // 定义方法，形参为Person类型，功能：调用学生的study或教师的teach方法
    // 使用向下转型
    public static void  test(Person p){
        if(p instanceof Student){
            ((Student)p).study();
        }
        else if(p instanceof Teacher){
            ((Teacher)p).teach();
        }else{
            System.out.println("do nothing...");
        }
    }


```



##### 3.5java的动态绑定机制

1. 当调用对象方法的时候，该方法会和对象的内存地址/运行类型绑定(忘了，有时间回来复习)
2. 当调用对象的属性时，没有动态绑定机制，采取就近原则

##### 3.6==运算符

1.既可以判断基本类型，又可以判断引用类型

2.如果判断基本类型，则判断值是否相等。

3.如果判断引用类型，则判断地址是否相等即判断是不是同一个对象



##### 3.7hashCode方法

1.提高具有哈希结构的容器的效率

2.两个引用，如果指向的是同一个对象则他们的哈希值是一样的，不是同一个对象则哈希值不同

3.哈希值不完全等价于地址，可近似看成地址



🌰

```java
Person p1 = new Person();
p1.name = "java-yyds";

Person p2 = new Person();
p2.name = "java-yyds";

System.out.println(p1 == p2); // False 比较对象是否相等，因为使用了两次 new 操作符所以对象肯定不相等
System.out.println(p1.name.equals(p2.name)); // True 这里判断两个字符串是否相等，因为String类中重写了eauals方法
																						 //所以比较的是内容，字符串内容相等则返回true；
System.out.println(p1.equals(p2)); // False 比较对象是否相等，因为使用了两次 new 操作符所以对象肯定不相等

String a = "111";
String b = "111";
System.out.println(a == b);  //这时输出的是true，说明不使用new创建字符串时，引用a和引用b的地址是相同的

String c = new String("111");
String d = new String("111");
System.out.println(c.hashCode() == d.hashCode()); //输出为true，因为String类重写的hashCode方法
																									//只要字符串内容相同，hashCode方法的返回值就相同


```



##### 3.8toString方法

1.默认返回值：类的完全限定名 + @ + 哈希值的16进制(hashCode()方法返回值转换成16进制后的值)

##### 3.9finalize方法

1.当对象被回收时，系统自动调用该对象的finalize方法，子类可以重写该方法。

2.什么时候被回收：当某个对象没有任何引用时，则jvm就认为这个对象是一个垃圾对象，就会使用回收机制来销毁该对象，会先调用finalize方法

3.垃圾回收机制的调用是由系统决定的，也可以通过System.gc()主动触发垃圾回收机制

##### 4.0== 和 equals 的区别

| 名称   | 概念           | 用于基本数据类型 | 用于引用数据类型                                         |
| ------ | -------------- | ---------------- | -------------------------------------------------------- |
| ==     | 比较运算符     | 判断值是否相等   | 比较地址是否相等                                         |
| equals | Object类的方法 | **不可以**       | 默认判断地址是否相等，子类重写后可判断对象的属性是否相等 |



##### 4static与接口

##### 4.1静态方法使用细节

1.静态方法中不允许使用和对象有关的关键字

2.静态方法中不能直接调用非静态方法

3.静态方法可以通过类名和对象名调用

4.普通的成员方法可以访问普通成员和静态成员

##### 4.2main()

1.main方法是由JVM调用的

2.java虚拟机需要调用main方法，所以该方法的访问权限必须是public

3.java虚拟机在调用main方法时不必创建对象，所以该方法必须为static

4.Sting[] args形参数组中保存执行java命令时传递给所运行的类的参数(即在命令行传入的参数)

 

##### 5.抽象类

##### 1.当父类中的方法存在不确定性时，考虑使用抽象类，抽象类以及抽象方法使用abstract关键字来修饰

##### 2.抽象类使用细节

* 抽象类中的抽象方法是没有方法体({})的
* 抽象类不能直接被实例化，可通过子类间接实例化
* 抽象类可以不包括抽象方法，但是包括抽象方法的类一定要是抽象类
* abstract只能修饰类和方法
* 抽象类也是类，所以类中有的成员，抽象类也可以有，例如构造器，非抽象方法，静态属性等
* 如果类A继承了抽象类B，则在A中需要实现B的所有抽象方法，否则需要将A也声明为抽象类
* 抽象方法不能用private，static，final，因为这些关键字都是和重写相违背的



##### 6.接口

##### 1.接口就是给出一些没有实现的方法，封装到一起，当某个类要使用的时候，再根据具体情况把这些方法实现

##### 2.接口使用细节

* JDK8以后接口中可以包含默认(default)方法，静态方法，抽象方法，而在JDK8以前，接口中只有抽象方法
* 接口不能被实例化
* 接口中的方法默认使用public和abstract修饰
* 一个普通类实现接口，就必须将该接口的所有方法都实现，抽象类实现接口可以不用实现接口的所有方法
* 一个类可以实现多个接口，而类之间的继承关系却是单继承关系
* 接口中的属性是默认以public static final修饰，访问形式是接口名加属性名
* 接口不能继承其他的类，但是接口可以继承接口
* 接口的修饰符和类的修饰符一样只能是public或者默认

##### 3.接口 VS 继承

* 接口和继承解决的问题不同
  * 继承的价值主要在于解决代码的可维护性和复用性
  * 接口的价值主要在于设计好各种规范(方法)，让其他类去实现这些方法
* 接口比继承更加灵活，继承是满足is - a的关系，而接口只需满足like - a的关系，接口在一定程度上实现代码解藕(即：接口规范性 + 动态绑定)

##### 4.接口的多态特性

*   接口引用可以指向实现了接口的类的对象实例并调用该类实现好的方法

*   接口同样可以实现多态数组，使用instanceof 判断当前元素的运行类型来调用某个实现类的特有方法

*   接口的多态传递

  有A，B两个接口，B接口继承A接口，C类实现了B接口，这时，等同于C类需要同时实现A，B两个接口的方法，而且A，

  B这两个接口的引用都可以指向B类的实例

🌰  

```java
public interface A {
    int x = 1;
}

class B{
    int x = 1;
}

class C extends B implements A{
    public void pX(){
        // System.out.println(x); //错误，原因是x不明确
        //明确指定x后即可
        //访问接口的 x 使用 A.x
        //访问父类的 x 使用 super.x

        System.out.println(A.x + " " + super.x);
    }

    public static void main(String[] args){
        new C().pX();
    }

}
```

五、异常

异常基本概念：Java语言中，将程序执行过程中发生的不正常情况称为“异常”。（开发过程中的语法错误和逻辑错误不是异常）

执行过程中发生的异常事件可以分为两类

* Error：Java虚拟机无法解决的严重错误，例如JVM系统内部错误、资源耗尽等严重情况。比如：StackOverflowError[栈溢出]和OOM(out of memory)，Error是严重错误，程序会崩溃
* Exception：其它因编程错误或偶然的外在因素导致的一般性问题，可以使用针对性的代码进行处理。例如空指针访问，试图读取不存在的文件，网络连接中断等等，Exception分为两大类：**运行时异常**和**编译时异常**
  	* 运行时异常编译器检查不出来，也就是编译器不要求强制处置的异常，一般是指编程时的逻辑错误，是程序员应该避免其出现的异常。java.lang.RuntimeException类及它的子类都是运行时异常，对于运行时异常可以不做处理，因为这类异常很普遍，若全处理可能会对程序的可读性和运行效率产生影响
  	* 编译时异常是编译器要求必须处置的异常

##### 1.异常处理的方式

1）try - catch - finally

​		程序员在代码中捕获发生的异常，自行处理

```java
try{
  //可能发生异常的代码块
  //一旦执行到抛出异常的代码，则try块中的余下代码不会执行
  //会直接进入到catch块
}catch(Exception e){
  //系统将异常封装成Exception类的对象e，传递给catch
  //得到异常对象后，程序员可自己进行处理
}finally{
  //不管在try代码块中的代码是否发生异常，始终要执行finally代码块中代码，
  //但是如果没有发生异常，catch代码块中的代码是不会执行的
}
```

1.如果try代码块可能有多个异常，可以使用多个catch分别捕获不同的异常进行相应处理

2.要求子类异常写在前面，父类异常写在后面

3.可以进行try-finally配合使用，但是这种用法相当于没有捕获异常，如果发生异常程序会直接崩掉。应用场景就是执行一段代码，不管是否发生异常，都必须执行某个业务逻辑(即执行finally代码块的代码)

🌰

```java
public class Main {
    public static int method(){
      int i = 1;
       try {
         	 i ++; //i = 2
           String[] names = new String[3];
           if (names[1].equals("tom")) //NullPointerException
               System.out.println(names[1]);
           else{
               names[3] = "111";
           }
           return 1;
       }catch(ArrayIndexOutOfBoundsException e){
           return 2;
        }catch (NullPointerException e){
           return ++ i; //由于finally代码块中语句有return 所以此处return不能返回值
         								//但是++ i操作正常执行
       }finally{     
           return ++ i;//由于finally代码块中的代码
                       //必须执行，所以即使捕获异常了也只能返回4
           
        	 // ++ i; 如果finall代码块中没有return 只有++ i
         	 // 那么返回的就是3，return会用一个临时变量来保存i在自增前的值
           // 也就是说进行这时进行自增的变量引用并不是i
       }
    }
    public static void main(String[] args){
        System.out.println(method());
    }
}
```

2）throws

简介：

1.如果一个方法中的语句执行时可能生成某种异常，但是并不能确定如何处理这种异常，则此方法应显示地声明抛出异常，表明该方法将不对这些异常进行处理，而由该方法的调用者负责处理，最顶级的处理者就是JVM

2.在方法声明中用throws语句可以声明抛出异常的列表，throws后面的异常类型可以是方法中产生的异常类型，也可以是他的父类 

细节：

* 对于编译异常，程序中必须进行相应处理，比如try-catch或throws
* 对于运行时异常，程序中如果没有处理，默认就是throws的方式处理
* 子类重写父类的方法时对抛出的异常的规定：子类重写的方法，所抛出的异常类型要么和父类抛出的异常类型一致，要么为父类抛出的异常类型的子类型
* 在throws中，如果有方法try-catch，就相当于处理异常，则不必进行throws

3）自定义异常

当程序中出现了某些错误，但该错误信息并没有在Throwable子类中描述处理，这个时候可以自己设计异常类，用于描述该错误信息

###### 步骤1.自定义异常类名，继承Exception或RuntimeException

###### 步骤2.如果继承Exception，属于编译异常

###### 步骤3.如果继承RuntimeException，属于运行异常

🌰

```java
public class Main {
    public static void main(String[] args){
        int age = 20;
        //要求age在18 - 120之间，否则抛出异常
        if(! (age >= 18 && age <= 120)){
            throw new AgeException("age需要在18 - 120之间");
        }
        System.out.println("年龄范围正确");
    }
}

class AgeException extends RuntimeException{
    public AgeException(String message){
        super(message);
    }
}
```



##### throw和throws的区别

|        | 意义                     | 位置       | 后面跟的东西 |
| ------ | ------------------------ | ---------- | ------------ |
| throws | 异常处理的一种方式       | 方法声明处 | 异常类型     |
| throw  | 手动生成异常对象的关键字 | 方法体中   | 异常对象     |

🌰编写两个数相除的程序，对数据格式不正确，缺少命令行参数，除0进行异常处理

```java
public class Main {
    public static void main(String[] args){
        try {
            //验证输入的参数个数是否正确
            if (args.length != 2) {
                throw new ArrayIndexOutOfBoundsException("参数个数不对");
            }
            //把接受到的参数转换成整数
            int n1 = Integer.parseInt(args[0]);
            int n2 = Integer.parseInt(args[1]);
            double ans = cal(n1,n2);
            System.out.println(ans);
        }catch(ArrayIndexOutOfBoundsException e){
            System.out.println(e.getMessage());
        }catch (NumberFormatException e){
            System.out.println("参数格式不正确，需要输入整数");
        }catch(ArithmeticException e){
            System.out.println("出现了除0的异常");
        }
    }
    public static double cal(int n1,int n2){
        return n1 / n2;
    }
}
```

#### 五、常用类

##### 1.包装类与String类之间的转换

```java
Integer i = 100;

//直接对包装类赋值不可以自动类型转换
// Double a = 100 //错误

//包装类 -> String
//三种方式
String str1 = i + "";
String str2 = i.toString();
String str3 = String.valueOf(i);

// String -> 包装类
// 两种方式
String s = "12345";
Integer i1 = Integer.parseInt(s); //该方法返回值是int，所以这里使用自动装箱
Integer i2 = Integer.valueOf(s);;

```



