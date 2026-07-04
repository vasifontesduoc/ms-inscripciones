package com.duoc.ms_inscripciones.repository;

import com.duoc.ms_inscripciones.model.ResumenInscripcion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResumenInscripcionRepository
        extends JpaRepository<ResumenInscripcion, Long> {
}