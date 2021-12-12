package com.yee.listener;


import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * ClassName: AckListener
 * Description:
 * date: 2021/12/9 15:37
 * 手动签收监听器
 *  implements ChannelAwareMessageListener
 *  继承信息监听器,自动确认监听器
 *  implements MessageListener
 * @author Yee
 * @since JDK 1.8
 */
//手动签收,注入ioc容器
@Component
public class AckListener implements ChannelAwareMessageListener {

    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        System.out.println(new String(message.getBody()));
        //签收标记
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        try {
            //第二个参数是否签收所有信息
            channel.basicAck(deliveryTag,true);
        } catch (IOException e) {
            e.printStackTrace();
            //第三个参数表示,拒签之后信息是否回到队列
            channel.basicNack(deliveryTag,true,false);
        }
    }
}

