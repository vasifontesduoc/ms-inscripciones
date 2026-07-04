package com.duoc.ms_inscripciones.service;

import com.duoc.ms_inscripciones.config.RabbitMQConfig;
import com.duoc.ms_inscripciones.model.Inscripcion;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQProducer {

    private final RabbitTemplate rabbitTemplate;

    public RabbitMQProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void enviarInscripcion(Inscripcion inscripcion) {

        rabbitTemplate.convertAndSend(
                RabbitMQConfig.EXCHANGE,
                RabbitMQConfig.ROUTING_KEY,
                inscripcion);

        System.out.println("Inscripción enviada a RabbitMQ");
    }
}
