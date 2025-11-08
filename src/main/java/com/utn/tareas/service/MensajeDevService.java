package com.utn.tareas.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * Implementación del servicio de mensajes para el perfil 'dev'.
 * Mensajes detallados para desarrollo.
 */
@Service
@Profile("dev") // Este bean solo se crea si el perfil 'dev' está activo
public class MensajeDevService implements MensajeService {

    @Value("${app.nombre}")
    private String appNombre;

    @Override
    public String mostrarBienvenida() {
        return "¡BIENVENIDO A " + appNombre + "! [MODO DESARROLLO]. " +
                "Este es un entorno para pruebas. ¡Feliz coding!";
    }

    @Override
    public String mostrarDespedida() {
        return "Cerrando " + appNombre + " [MODO DESARROLLO]. " +
                "¡Hasta la próxima, desarrollador!";
    }
}
