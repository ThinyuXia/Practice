package topic;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 特定路由键
 */
public class ReceLogsTopic1 {
    private static final String EXCHANGE_NAME = "topic_logs";
    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("124.223.184.27");
        factory.setUsername("guest");
        factory.setPassword("guest");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC);
        //生成一个随机的临时的queue
        String queueName = channel.queueDeclare().getQueue();
        String routingKey = "*.orange.*";
        channel.queueBind(queueName,EXCHANGE_NAME,routingKey);
        System.out.println("开始接受消息");
        Consumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String s = new String(body,"utf-8");
                System.out.println("收到消息 ：" + s + " routingKey : " + envelope.getRoutingKey());
            }
        };
        channel.basicConsume(queueName,true,consumer);
    }
}
