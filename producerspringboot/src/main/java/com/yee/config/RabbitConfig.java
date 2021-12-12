package com.yee.config;


import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ClassName: RabbitConfig
 * Description:
 * date: 2021/12/9 17:23
 *
 * @author Yee
 * @since JDK 1.8
 */
@Configuration
public class RabbitConfig {
    //交换机名字
    public static final String EXCHANGE_NAME = "boot_exchange";
    //路由规则
    public static final String QUEUE_NAME = "boot_queue";

    //构建交换机
    @Bean
    public Exchange bootExchange(){
        /**
         * durable表示是否持久化
         * topicExchange表示通配交换机
         * 也可以选择其他的
         */
        return ExchangeBuilder.topicExchange(EXCHANGE_NAME).durable(true).build();
    }


    //构建队列
    @Bean
    public Queue bootQueue(){
        return QueueBuilder.durable(QUEUE_NAME).build();
    }


    //交换机绑定队列
    @Bean
    public Binding bindQueueExchange(Queue queue,Exchange exchange){
        /**
         * 先绑定队列,在绑定交换机,由于上面的交换机是通配交换机
         * 所以还需要绑定路由规则,选择无参
         */
        return BindingBuilder.bind(queue).to(exchange).with("boot.#").noargs();
    }
}
