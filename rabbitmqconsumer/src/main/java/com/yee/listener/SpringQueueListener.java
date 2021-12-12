package com.yee.listener;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

/**
 * ClassName: SpringQueueListener
 * Description:
 * date: 2021/12/7 19:07
 *  消息监听器
 * @author Yee
 * @since JDK 1.8
 */
public class SpringQueueListener implements MessageListener {

    @Override
    public void onMessage(Message message) {
        //获取消息,需要封装在string,不然无法返回tostring
        System.out.println(new String(message.getBody()));
    }
}
