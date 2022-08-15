package com.xiaxinyu.springbootrabbitmqconsumer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 消费者1
 */
@Component
@RabbitListener(queues = "queue2")
public class Receiver2 {
    @RabbitHandler
    public void process(String message){
        System.out.println("Receiver2 : " + message);
    }
}
