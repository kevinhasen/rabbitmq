package com.yee;

import com.yee.config.RabbitConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * ClassName: TestRabbit
 * Description:
 * date: 2021/12/9 19:47
 *
 * @author Yee
 * @since JDK 1.8
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestRabbit {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Test
    public void testMessage(){
        rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE_NAME,
                "boot.abc","我是一条信息");
    }
}
