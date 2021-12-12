package com.yee;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * ClassName: RabbitTest
 * Description:
 * date: 2021/12/7 19:11
 *
 * @author Yee
 * @since JDK 1.8
 */
//junit4
@RunWith(SpringRunner.class)
//配置文件
@ContextConfiguration(locations ="classpath:rabbit.xml")
public class RabbitTest {

    //监听信息
    @Test
    public void testOne(){
        while (true){
            //死循环监听信息,不需要写内容
        }
    }
}
