import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * ClassName: RabbitTest
 * Description:
 * date: 2021/12/7 18:38
 *
 * @author Yee
 * @since JDK 1.8
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(locations = "classpath:rabbit.xml")
public class RabbitTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    //发送简单消息,没有交换机的时候,交换机可以使用队列名称代替

    @Test
    public void demoOne(){
        rabbitTemplate.convertAndSend("spring_queue","我是服务器");
    }

    //发送广播模式
    @Test
    public void demoTwo(){
        //交换机名字,
        // 路由规则,由于是广播模式,谁都可以收到,不需要路由规则,
        // 消息体
        //广播模式,路由设置为空,不能不设置
        rabbitTemplate.convertAndSend("spring_fanout_exchange","","我是广播信息");
    }
}
