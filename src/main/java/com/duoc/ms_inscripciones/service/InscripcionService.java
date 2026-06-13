package com.duoc.ms_inscripciones.service;

import com.duoc.ms_inscripciones.model.Inscripcion;
import com.duoc.ms_inscripciones.repository.InscripcionRepository;
import com.duoc.ms_inscripciones.utils.ArchivoUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class InscripcionService {

    private final InscripcionRepository repository;
    private final S3Service s3Service;

    public InscripcionService(
            InscripcionRepository repository,
            S3Service s3Service) {
        this.repository = repository;
        this.s3Service = s3Service;
    }

    public Inscripcion guardar(Inscripcion inscripcion) {

        Inscripcion nueva = repository.save(inscripcion);

        try {

            String rutaArchivo = ArchivoUtils.generarResumen(nueva);

            String carpetaFecha = LocalDate.now()
                    .format(DateTimeFormatter.ofPattern("yyyyMM"));

            String key = carpetaFecha + "/resumen_" + nueva.getId() + ".txt";

            s3Service.subirArchivo(rutaArchivo, key);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return nueva;
    }

    public List<Inscripcion> listar() {
        return repository.findAll();
    }
}