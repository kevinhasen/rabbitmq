package com.yee.listener;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

/**
 * ClassName: FanoutListenOne
 * Description:
 * date: 2021/12/7 19:26
 * 广播监听器
 * @author Yee
 * @since JDK 1.8
 */
public class FanoutListenOne implements MessageListener {
    @Override
    public void onMessage(Message message) {
        System.out.println(new String(message.getBody()));
    }
}
