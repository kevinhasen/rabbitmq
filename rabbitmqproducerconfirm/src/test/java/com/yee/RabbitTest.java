package com.yee;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * ClassName: RabbitTest
 * Description:
 * date: 2021/12/8 9:17
 * 信息发送到队列中,队列名叫queue_confirm
 * 待会还得创建消费者取信息
 * 项目rabbitmqconsumerspring消费者
 * @author Yee
 * @since JDK 1.8
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(locations = "classpath:rabbitmq-producer.xml")
public class RabbitTest {

//注入信息模板
    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 测试死信操作
     * 过期会被抛弃
     * 信息超过设定的值也会被抛弃
     * 信息被拒绝签收
     */
    @Test
    public void testDead(){
        //先发给正常交换机和队列
        //rabbitTemplate.convertAndSend("exchange_dlx","dlx.abc","测试死信消息,十秒 过期");
        //测试超过长度,目前设置长度是10条信息
        for (int i = 0; i < 11; i++) {
            rabbitTemplate.convertAndSend("exchange_dlx","dlx.abc","测试死信消息,超过10条被抛弃");
        }
    }
    //发送过期信息
    @Test
    public void testTtl(){
        //路由规则,配置文件设置的是ttl.#,表示后缀随便写
        rabbitTemplate.convertAndSend("exchange_ttl","ttl.abc","发送一个10秒过期信息");
    }
    /**
     * 信息确认模式
     * 配置文件需要配置publisher-confirm:true
     * 回退模式
     * 配置文件需要配置publisher-return: true
     */
    @Test
    public void testConfirm(){
        //启动回调模式,处理错误信息
        rabbitTemplate.setMandatory(true);
        //回退模式
        rabbitTemplate.setReturnCallback(new RabbitTemplate.ReturnCallback() {
            /**
             *
             * @param message  信息体
             * @param i   错误码
             * @param s   错误信息
             * @param s1  交换机
             * @param s2   路由
             */
            @Override
            public void returnedMessage(Message message, int i, String s, String s1, String s2) {
                System.out.println("信息体"+message);
                System.out.println("错误码:"+i);
                System.out.println("错误信息:"+s);
                System.out.println("交换机:"+s1);
                System.out.println("路由:"+s2);
            }
        });

        //确认模式回调函数
        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            /**
             *
             * @param correlationData  信息体
             * @param b   是否发送成功
             * @param s  失败的信息
             */
            @Override
            public void confirm(CorrelationData correlationData, boolean b, String s) {
                    if (!b){
                        System.out.println("错误原因:"+s);
                    }
            }
        });
//        rabbitTemplate.convertAndSend("exchange_confirm","confirm","发送一条确认信息");
        //下面是测试削峰操作,发送10条信息
        for (int i = 0; i < 10; i++) {
            rabbitTemplate.convertAndSend("exchange_confirm","confirm","发送一条确认信息");
        }
    }
}
