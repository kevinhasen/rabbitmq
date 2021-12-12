package com.yee.listener;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.stereotype.Component;

/**
 * ClassName: QosListener
 * Description:
 * date: 2021/12/9 16:19
 * 消费者限流,削峰操作,每次只拉取一条信息
 * 保证ack为手动确认,
 * listener-container设置属性
 * perfetch =1 表示每次只拉取一条信息,消费之后,才会继续拉下一条
 * @author Yee
 * @since JDK 1.8
 */
@Component
public class QosListener implements ChannelAwareMessageListener {
    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        //1.获取消息,每一秒取一条信息
        Thread.sleep(1000);
        System.out.println(new String(message.getBody()));
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        //签收信息,第二个参数表示是否签收
        channel.basicAck(deliveryTag,true);
    }
}
