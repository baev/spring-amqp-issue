package org.example.processor;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

/**
 * @author charlie (Dmitry Baev).
 */
@Component
@RabbitListener(queues = "allure_result_file")
public class ResultFileProcessor {

    @RabbitHandler
    public void processResultFile(@Payload final String event) {
    }

}
