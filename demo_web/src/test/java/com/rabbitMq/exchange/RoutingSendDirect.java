package com.rabbitMq.exchange;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RoutingSendDirect {
    private static final String EXCHANGE_NAME = "direct_logs";
    private final static String QUEUE_NAME = "direct_queue";
    // 路由关键字
    private static final String[] routingKeys = new String[]{"info" ,"warning", "error"};
    public static void main(String[] args) throws IOException, TimeoutException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        //声明交换机
        channel.exchangeDeclare(EXCHANGE_NAME,"direct",true);//注意是direct
        channel.queueDeclare(QUEUE_NAME,true,false,false,null);
        channel.queueBind(QUEUE_NAME,EXCHANGE_NAME,"info");

        //发送信息
        for (String routingKey:routingKeys){
            String message = "RoutingSendDirect Send the message level:" + routingKey;
            channel.basicPublish(EXCHANGE_NAME,routingKey,null,message.getBytes());
            System.out.println("RoutingSendDirect Send"+routingKey +"':'" + message);
        }
        channel.close();
        connection.close();
    }
}