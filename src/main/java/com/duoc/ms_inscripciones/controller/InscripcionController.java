package com.duoc.ms_inscripciones.controller;

import com.duoc.ms_inscripciones.model.Inscripcion;
import com.duoc.ms_inscripciones.service.InscripcionService;
import com.duoc.ms_inscripciones.service.S3Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inscripciones")
public class InscripcionController {

    private final InscripcionService service;
    private final S3Service s3Service;

    public InscripcionController(
            InscripcionService service,
            S3Service s3Service) {
        this.service = service;
        this.s3Service = s3Service;
    }

    // Crear inscripción
    @PostMapping
    public Inscripcion crear(@RequestBody Inscripcion inscripcion) {
        return service.guardar(inscripcion);
    }

    // Listar inscripciones
    @GetMapping
    public List<Inscripcion> listar() {
        return service.listar();
    }

    // Actualizar inscripción
    @PutMapping("/{id}")
    public Inscripcion actualizar(
            @PathVariable Long id,
            @RequestBody Inscripcion inscripcion) {

        return service.actualizar(id, inscripcion);
    }

    // Eliminar inscripción
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {

        service.eliminar(id);
    }

    // Descargar archivo desde S3
    @GetMapping("/download/{archivo}")
    public String descargarArchivo(@PathVariable String archivo) {

        try {

            String rutaDestino = "descarga_" + archivo;

            s3Service.descargarArchivo(
                    "202606/" + archivo,
                    rutaDestino);

            return "Archivo descargado correctamente";

        } catch (Exception e) {
            e.printStackTrace();
            return "Error al descargar archivo";
        }
    }

    // Eliminar archivo de S3
    @DeleteMapping("/delete/{archivo}")
    public String eliminarArchivo(@PathVariable String archivo) {

        try {

            s3Service.eliminarArchivo(
                    "202606/" + archivo);

            return "Archivo eliminado correctamente";

        } catch (Exception e) {
            e.printStackTrace();
            return "Error al eliminar archivo";
        }
    }

    // Actualizar archivo en S3
    @PutMapping("/update/{archivo}")
    public String actualizarArchivo(@PathVariable String archivo) {

        try {

            String ruta = "202606/" + archivo;

            s3Service.modificarArchivo(
                    ruta,
                    "202606/" + archivo);

            return "Archivo actualizado correctamente";

        } catch (Exception e) {
            e.printStackTrace();
            return "Error al actualizar archivo";
        }
    }
}