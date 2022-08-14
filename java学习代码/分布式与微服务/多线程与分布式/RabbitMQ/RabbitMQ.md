##### 一、什么是RabbitMQ

##### 1.核心思想：接收并转发消息，可以把RabbitMQ想象成一个邮局。

* producer(消息生产者)
* queue(无限长的队列)
* consumer(消费者)

##### 2.消息队列(Message Queue)

* 业务无关
* FIFO
* 容灾
* 性能

##### 3.为什么要使用消息队列

* 系统结偶 
* 异步调用
* 流量削峰

##### 4.RabbitMQ特点

* 开源、跨语言
* Erlang语言编写
* 应用广泛
* 社区活跃、API丰富

##### 5.AMQP协议(高级消息队列协议)



##### 6.RabbitMQ核心概念

* Server：服务

* connection：与Server建立连接

* channel：信道，几乎所有的操作都在信道上进行，客户端可以建立多个信道

* message：消息，properties和body组成

* virtual：虚拟主机，顶层隔离。同一个虚拟主机下，不能有重复的交换机和queue

* Exchange：交换机，接受生产者的消息，然后根据指定的路由器去把消息转发到所绑定的队列上

* binding：绑定交换机和队列

* routing key：路由键，路由规则，虚拟机可以用它来确定一个消息如何进行一个路由

* queue：队列，消费者只需要监听队列来消费消息，不需要关注消息来自于哪个交换机，一个交换机可以绑定多个消息队列

  

