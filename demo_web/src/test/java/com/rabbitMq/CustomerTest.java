package com.rabbitMq;


import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Created by pc on 2017-08-01.
 *
 * @author pc
 * @version 3.0.0-SNAPSHOT
 * @description
 * @since 3.0.0-SNAPSHOT
 */
public class CustomerTest {
    private final static String QUEUE_NAME = "rabbitMQ.test";
    public final static String EXCHANGE_NAME="rabbitmq_exchange";

    public static void main(String[] args) throws IOException, TimeoutException {
        // 创建连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        //设置RabbitMQ地址
        factory.setHost("127.0.0.1");

        //创建一个新的连接
        Connection connection = factory.newConnection();

        String routingkey = "rabbitmq_routingkey";

        //创建一个通道
        Channel channel = connection.createChannel();
        //声明要关注的队列
//        channel.queueBind(QUEUE_NAME,EXCHANGE_NAME,routingkey);
        System.out.println("Customer Waiting Received messages");

        //DefaultConsumer类实现了Consumer接口，通过传入一个频道，
        // 告诉服务器我们需要那个频道的消息，如果频道中有消息，就会执行回调函数handleDelivery
        Consumer consumer = new DefaultConsumer(channel) {

//            public void handleDelivery(String consumerTag, Envelope envelope,
//                                       AMQP.BasicProperties properties, byte[] body)
//                    throws IOException {
//                String message = new String(body, "UTF-8");
//                System.out.println("Customer Received '" + message + "'");
//            }

            @Override
            public void handleDelivery(String consumerTag, com.rabbitmq.client.Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                try {
                    String message = new String(body,"UTF-8");
                    System.out.println("Customer Received '"+message +"'");
                    System.out.println("process....... ");
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println("finish it");
                    channel.basicAck(envelope.getDeliveryTag(),false);
                }

            }
        };
        //自动回复队列应答 -- RabbitMQ中的消息确认机制
        channel.basicConsume(QUEUE_NAME, false, consumer);
    }
}
