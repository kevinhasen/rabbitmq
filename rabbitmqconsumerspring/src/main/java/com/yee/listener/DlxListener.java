package com.yee.listener;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.stereotype.Component;

/**
 * ClassName: dlxListener
 * Description:
 * date: 2021/12/9 17:11
 * 测试拒签信息,会被抛弃到死信队列
 * @author Yee
 * @since JDK 1.8
 */
@Component
public class DlxListener implements ChannelAwareMessageListener {
    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        try {
//       测试异常拒签
            int a = 10/0;
            channel.basicAck(deliveryTag,true);
        } catch (Exception e) {
            e.printStackTrace();
            channel.basicNack(deliveryTag,true,false);
        }
    }
}
