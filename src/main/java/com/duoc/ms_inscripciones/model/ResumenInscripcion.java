package com.duoc.ms_inscripciones.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResumenInscripcion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long idInscripcion;

    private String nombreAlumno;

    private String curso;

    private String fecha;

    private String nombreArchivo;
}