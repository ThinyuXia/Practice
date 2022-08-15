package direct;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * direct类型的交换机，发送消息
 */
public class EmitLogDirect {
    private static final String EXCHANGE_NAME = "direct_logs";
    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("124.223.184.27");
        factory.setUsername("guest");
        factory.setPassword("guest");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);
        String message = "hello world!";
        channel.basicPublish(EXCHANGE_NAME,"info",null,message.getBytes("utf-8"));
        System.out.println("发送了" + "等级info的消息 ：" + message);
        channel.basicPublish(EXCHANGE_NAME,"warning",null,message.getBytes("utf-8"));
        System.out.println("发送了" + "等级warning的消息 ：" + message);
        channel.basicPublish(EXCHANGE_NAME,"error",null,message.getBytes("utf-8"));
        System.out.println("发送了" + "等级error的消息 ：" + message);
        channel.close();
        connection.close();
    }
}
