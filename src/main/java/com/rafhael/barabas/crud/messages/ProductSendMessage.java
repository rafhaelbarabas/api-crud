package com.rafhael.barabas.crud.messages;

import com.rafhael.barabas.crud.data.vo.ProductVO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ProductSendMessage {

    @Value("${crud.rabbitmq.exchange}")
    String exchange;

    @Value("${crud.rabbitmq.routingkey}")
    String routingKey;

    public final RabbitTemplate rabbitTemplate;

    @Autowired
    public ProductSendMessage(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(ProductVO productVO) {
        rabbitTemplate.convertAndSend(exchange, routingKey, productVO);
    }
}
