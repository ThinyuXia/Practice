package com.xiaxinyu.springbootrabbitmqproducer;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringbootRabbitmqProducerApplicationTests {

    @Autowired
    MsgSender msgSender;

    @Test
    public void send1(){
        msgSender.send1();
    }

    @Test
    public void send2(){
        msgSender.send2();
    }

}
