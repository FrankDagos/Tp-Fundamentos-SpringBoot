package com.utn.tareas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Clase principal de la aplicación Spring Boot.
 * Implementa CommandLineRunner para ejecutar lógica al inicio.
 */
@SpringBootApplication
public class TareasApplication{
    public static void main(String[] args) {
        SpringApplication.run(TareasApplication.class, args);
    }
}

