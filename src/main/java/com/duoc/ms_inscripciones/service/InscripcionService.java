package com.duoc.ms_inscripciones.service;

import com.duoc.ms_inscripciones.model.Inscripcion;
import com.duoc.ms_inscripciones.repository.InscripcionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InscripcionService {

    private final InscripcionRepository repository;
    private final RabbitMQProducer rabbitMQProducer;

    public InscripcionService(
            InscripcionRepository repository,
            S3Service s3Service,
            RabbitMQProducer rabbitMQProducer) {

        this.repository = repository;
        this.rabbitMQProducer = rabbitMQProducer;
    }

    public Inscripcion guardar(Inscripcion inscripcion) {

        Inscripcion nueva = repository.save(inscripcion);

        rabbitMQProducer.enviarInscripcion(nueva);

        return nueva;
    }

    public List<Inscripcion> listar() {
        return repository.findAll();
    }

    public Inscripcion actualizar(Long id, Inscripcion inscripcion) {

        Inscripcion existente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inscripción no encontrada"));

        existente.setNombreAlumno(inscripcion.getNombreAlumno());
        existente.setCorreo(inscripcion.getCorreo());
        existente.setCurso(inscripcion.getCurso());
        existente.setFecha(inscripcion.getFecha());

        return repository.save(existente);
    }

    public void eliminar(Long id) {

        if (!repository.existsById(id)) {
            throw new RuntimeException("Inscripción no encontrada");
        }

        repository.deleteById(id);
    }
}