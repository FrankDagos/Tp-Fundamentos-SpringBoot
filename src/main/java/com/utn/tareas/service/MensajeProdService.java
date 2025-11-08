package com.utn.tareas.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * Implementación del servicio de mensajes para el perfil 'prod'.
 * Mensajes concisos para producción.
 */
@Service
@Profile("prod") // Este bean solo se crea si el perfil 'prod' está activo
public class MensajeProdService implements MensajeService {

    @Value("${app.nombre}")
    private String appNombre;

    @Override
    public String mostrarBienvenida() {
        return "Iniciando: " + appNombre;
    }

    @Override
    public String mostrarDespedida() {
        return "Sistema " + appNombre + " finalizado.";
    }
}
