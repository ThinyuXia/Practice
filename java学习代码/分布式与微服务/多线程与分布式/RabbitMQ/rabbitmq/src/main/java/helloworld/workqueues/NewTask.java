package helloworld.workqueues;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 任务有所耗时，有多个任务
 */
public class NewTask {
    private final static String TASK_QUEUE_NAME = "task_queue";
    public static void main(String[] args) throws IOException, TimeoutException {
        //创建连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        //设置RabbitMQ地址
        factory.setHost("124.223.184.27");
        factory.setUsername("guest");
        factory.setPassword("guest");
        //建立连接
        Connection connection = factory.newConnection();
        //获得信道
        Channel channel = connection.createChannel();
        //声明队列
        channel.queueDeclare(TASK_QUEUE_NAME,true,false,false,null);
        //发布消息
        for(int i = 0;i < 10;i ++){
            String message = i + "...";
            channel.basicPublish("",TASK_QUEUE_NAME,null,message.getBytes("utf-8"));
            System.out.println("发送了消息 ：" + message);
        }
        //关闭连接
        channel.close();
        connection.close();
    }
}
