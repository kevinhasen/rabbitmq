# RabbitMq

RabbitMq中间件入门案例

暂时整合的是spring和rabbitMq,

后续更新整合springboot和Rabbit

## 安装Erlang

RabbitMQ由Erlang语言开发 

所以安装兔子之前需要先安装二郎神(erlang )

下载地址：http://erlang.org/download/otp_win64_20.3.exe 

erlang安装完成需要配置erlang 系统环境变量： 文件的安装位置

ERLANG_HOME=C:\Program Files\erl9.3 

在path中添加

%ERLANG_HOME%\bin; 



## 安装rabbitMQ 

下载地址：https://github.com/rabbitmq/rabbitmq-server/releases/tag/v3.7.14



## 浏览器端管理插件 

在RabbitMQ的安装目录下sbin目录 以cmd命令行运行

rabbitmq-plugins.bat enable rabbitmq_management 

打开浏览器访问网站,进入登录页面，默认账号和密码都为guest

http://localhost:15672



## 消息队列(中间件)MQ

队列的形式是:先进先出

主要的作用

1. 异步处理
    1. 不是同步进行,不影响后续组件接入
2. 解耦服务
    1. 应用程序通过队列来通信
    2. 不像之前直接调用别的程序
3. 流量削峰
    1. 将大量请求慢慢抽离
    2. 一步步的慢慢消化



## Spring 整合RabbitMQ

生产者工程项目

rabbitmqproducer

消费者工程项目

rabbitmqconsumer



### 消息的可靠投递

项目名

rabbitmqproducerconfirm

-   确认机制和回退机制

-   TTL队列过期时间
    -   当消息到达存活时间后，还没有被消费，会被自动清除。

-   死信队列  (dead letter queue)
    -   所有过期的队列和拒签的队列和超过限制长度的队列
    -   相当于被抛弃的队列都统一放在一个队列管理称为死信队列



-   延迟队列
    -   所谓”延时消息”是指当消息被发送以后，并不想让消费者立即拿到消息，
    -   而是等待指定时间后，消费者才拿到这个消息进行消费



------



项目名

rabbitmqconsumerspring

-   手动签收和自动签收信息

-   消费端限流





##  RabbitMQ与SpringBoot整合

提供者工程名

producerspringboot



消费者工程名

consumberspringboot