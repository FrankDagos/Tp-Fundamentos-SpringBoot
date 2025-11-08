package com.utn.tareas.service;

/**
 * Interfaz para un servicio de mensajes que cambiará según el perfil.
 */
public interface MensajeService {
    String mostrarBienvenida();
    String mostrarDespedida();
}
