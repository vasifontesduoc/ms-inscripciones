package com.duoc.ms_inscripciones.utils;

import com.duoc.ms_inscripciones.model.Inscripcion;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ArchivoUtils {

    public static String generarResumen(Inscripcion inscripcion) throws IOException {

        String carpetaFecha = LocalDate.now()
                .format(DateTimeFormatter.ofPattern("yyyyMM"));

        File carpeta = new File(carpetaFecha);

        if (!carpeta.exists()) {
            carpeta.mkdir();
        }

        String nombreArchivo = "resumen_" + inscripcion.getId() + ".txt";

        String rutaCompleta = carpetaFecha + "/" + nombreArchivo;

        FileWriter writer = new FileWriter(rutaCompleta);

        writer.write("RESUMEN INSCRIPCIÓN\n");
        writer.write("-------------------------\n");
        writer.write("ID: " + inscripcion.getId() + "\n");
        writer.write("Alumno: " + inscripcion.getNombreAlumno() + "\n");
        writer.write("Correo: " + inscripcion.getCorreo() + "\n");
        writer.write("Curso: " + inscripcion.getCurso() + "\n");
        writer.write("Fecha: " + inscripcion.getFecha() + "\n");

        writer.close();

        return rutaCompleta;
    }
}