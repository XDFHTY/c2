//package com.cj.core.config.rmq;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.amqp.core.AmqpAdmin;
//import org.springframework.amqp.rabbit.connection.ConnectionFactory;
//import org.springframework.amqp.rabbit.core.RabbitAdmin;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Slf4j
//@Configuration
//public class RabbitConfig {
//
//    @Bean
//    public AmqpAdmin amqpAdmin(ConnectionFactory connectionFactory) {
//        return new RabbitAdmin(connectionFactory) {
//
//            @Override
//            public void initialize() {
//                while (true) { // might want to give up after some number of tries
//                    try {
//                        super.initialize();
//                        break;
//                    }
//                    catch (Exception e) {
//                        log.error("Failed to declare elements: " + e.getCause().getCause().getMessage());
//                        try {
//                            Thread.sleep(1000);
//                        }
//                        catch (InterruptedException e1) {
//                            Thread.currentThread().interrupt();
//                        }
//                    }
//                }
//            }
//
//        };
//    }
//}
