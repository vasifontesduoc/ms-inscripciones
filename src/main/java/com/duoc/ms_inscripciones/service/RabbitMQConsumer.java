package com.duoc.ms_inscripciones.service;

import com.duoc.ms_inscripciones.config.RabbitMQConfig;
import com.duoc.ms_inscripciones.model.Inscripcion;
import com.duoc.ms_inscripciones.model.ResumenInscripcion;
import com.duoc.ms_inscripciones.repository.ResumenInscripcionRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQConsumer {

    private final ResumenInscripcionRepository resumenRepository;

    public RabbitMQConsumer(ResumenInscripcionRepository resumenRepository) {
        this.resumenRepository = resumenRepository;
    }

    @RabbitListener(queues = RabbitMQConfig.QUEUE)
    public void recibirInscripcion(Inscripcion inscripcion) {

        ResumenInscripcion resumen = new ResumenInscripcion();

        resumen.setIdInscripcion(inscripcion.getId());
        resumen.setNombreAlumno(inscripcion.getNombreAlumno());
        resumen.setCurso(inscripcion.getCurso());
        resumen.setFecha(inscripcion.getFecha());
        resumen.setNombreArchivo("resumen_" + inscripcion.getId() + ".txt");

        resumenRepository.save(resumen);

        System.out.println("Resumen procesado correctamente.");
    }
}