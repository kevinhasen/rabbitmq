package com.yee.listener;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * ClassName: RabbitListener
 * Description:
 * date: 2021/12/9 19:53
 * 消费者不需要测试类
 * 有专属注解
 * @author Yee
 * @since JDK 1.8
 */
@Component
public class MqListener {

    @RabbitListener(queues = "boot_queue")
    public void listenerQueue(Message message){
        System.out.println(new String(message.getBody()));
    }
}
