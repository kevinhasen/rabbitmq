package com.yee;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * ClassName: TestRabbit
 * Description:
 * date: 2021/12/9 15:42
 * 取项目rabbitmqproducerconfirm里的信息
 * 有自动签收信息,和手动签收信息
 * 手动签收,配置文件需要设置acknowledge="manual"
 * @author Yee
 * @since JDK 1.8
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(locations = "classpath:rabbit.xml")
public class TestRabbit {
    //自动签收信息
    @Test
    public void aotuMessage(){
        while (true){

        }
    }
}
