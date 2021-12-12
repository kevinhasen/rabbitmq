package com.yee.listener;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

import java.io.UnsupportedEncodingException;

/**
 * ClassName: FanoutListenTwo
 * Description:
 * date: 2021/12/7 19:26
 * 广播监听器
 * @author Yee
 * @since JDK 1.8
 */
public class FanoutListenTwo implements MessageListener {
    //打印信息体
    @Override
    public void onMessage(Message message) {
        System.out.println(new String(message.getBody()));
    }
}
