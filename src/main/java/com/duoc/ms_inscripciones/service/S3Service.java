package com.duoc.ms_inscripciones.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import java.nio.file.Paths;
import jakarta.annotation.PostConstruct;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;

import java.io.File;

@Service
public class S3Service {

        private final S3Client s3Client;

        @Value("${aws.bucketName}")
        private String bucketName;

        public S3Service(S3Client s3Client) {
                this.s3Client = s3Client;
        }

        public void subirArchivo(String rutaArchivo, String key) {

                File archivo = new File(rutaArchivo);

                PutObjectRequest request = PutObjectRequest.builder()
                                .bucket(bucketName)
                                .key(key)
                                .build();

                s3Client.putObject(
                                request,
                                RequestBody.fromFile(archivo));
        }

        public void descargarArchivo(String key, String rutaDestino) {

                GetObjectRequest request = GetObjectRequest.builder()
                                .bucket(bucketName)
                                .key(key)
                                .build();

                s3Client.getObject(
                                request,
                                Paths.get(rutaDestino));
        }

        public void eliminarArchivo(String key) {

                s3Client.deleteObject(builder -> builder
                                .bucket(bucketName)
                                .key(key));
        }

        @PostConstruct
        public void mostrarBucket() {
                System.out.println("BUCKET USADO: " + bucketName);
        }

        public void modificarArchivo(
                        String rutaArchivo,
                        String key) {

                PutObjectRequest request = PutObjectRequest.builder()
                                .bucket(bucketName)
                                .key(key)
                                .build();

                s3Client.putObject(
                                request,
                                RequestBody.fromFile(
                                                new File(rutaArchivo)));
        }
}