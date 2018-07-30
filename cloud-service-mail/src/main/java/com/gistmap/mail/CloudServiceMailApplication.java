package com.gistmap.mail;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.context.annotation.Bean;

import javax.jms.Queue;

@SpringCloudApplication
public class CloudServiceMailApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudServiceMailApplication.class, args);
    }

    @Bean
    public Queue queue() {
        return new ActiveMQQueue("email.queue");
    }
}
