package com.duoc.ms_inscripciones.repository;

import com.duoc.ms_inscripciones.model.Inscripcion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InscripcionRepository extends JpaRepository<Inscripcion, Long> {
}