package com.utn.tareas.service;

import com.utn.tareas.model.Prioridad;
import com.utn.tareas.model.Tarea;
import com.utn.tareas.repository.TareaRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Servicio que maneja la lógica de negocio para las Tareas.
 * Incluye la inyección de propiedades de configuración.
 */
@Service
public class TareaService {

    // Inyección de dependencias por constructor
    private final TareaRepository tareaRepository;

    // Inyección de propiedades
    @Value("${app.nombre}")
    private String appNombre;

    @Value("${app.max-tareas}")
    private int maxTareas;

    @Value("${app.mostrar-estadisticas}")
    private boolean mostrarEstadisticas;

    public TareaService(TareaRepository tareaRepository) {
        this.tareaRepository = tareaRepository;
    }

    /**
     * Agrega una nueva tarea, validando el límite máximo.
     * @param descripcion Descripción de la tarea
     * @param prioridad Prioridad de la tarea
     * @return La tarea creada, o null si se supera el límite
     */
    public Tarea agregarTarea(String descripcion, Prioridad prioridad) {
        if (tareaRepository.count() >= maxTareas) {
            System.err.println("Error: No se pueden agregar más tareas. Límite alcanzado: " + maxTareas);
            return null;
        }
        Tarea nuevaTarea = new Tarea(null, descripcion, false, prioridad);
        return tareaRepository.save(nuevaTarea);
    }

    /**
     * Lista todas las tareas existentes.
     * @return Lista de tareas
     */
    public List<Tarea> listarTodas() {
        return tareaRepository.findAll();
    }

    /**
     * Lista solo las tareas pendientes (no completadas).
     * @return Lista de tareas pendientes
     */
    public List<Tarea> listarPendientes() {
        return tareaRepository.findAll().stream()
                .filter(t -> !t.isCompletada())
                .collect(Collectors.toList());
    }

    /**
     * Lista solo las tareas completadas.
     * @return Lista de tareas completadas
     */
    public List<Tarea> listarCompletadas() {
        return tareaRepository.findAll().stream()
                .filter(Tarea::isCompletada)
                .collect(Collectors.toList());
    }

    /**
     * Marca una tarea como completada.
     * @param id ID de la tarea a marcar
     */
    public void marcarCompletada(Long id) {
        Optional<Tarea> tareaOpt = tareaRepository.findById(id);
        if (tareaOpt.isPresent()) {
            Tarea tarea = tareaOpt.get();
            tarea.setCompletada(true);
            tareaRepository.save(tarea); // El save maneja la actualización
            System.out.println("Tarea " + id + " marcada como completada.");
        } else {
            System.err.println("Error: Tarea no encontrada con ID: " + id);
        }
    }

    /**
     * Obtiene estadísticas (si está habilitado).
     * @return String formateado con estadísticas o mensaje de desactivación
     */
    public String getEstadisticas() {
        if (!mostrarEstadisticas) {
            return "INFO: La visualización de estadísticas está desactivada en este perfil.";
        }
        long total = tareaRepository.count();
        long completadas = listarCompletadas().size();
        long pendientes = listarPendientes().size();
        return String.format("Estadísticas: Total de tareas=%d, Completadas=%d, Pendientes=%d",
                total, completadas, pendientes);
    }

    /**
     * Imprime las propiedades de configuración actuales.
     */
    public void imprimirConfiguracion() {
        System.out.println("----------------------------------------");
        System.out.println("Configuración de la Aplicación:");
        System.out.println("  Nombre: " + appNombre);
        System.out.println("  Max Tareas: " + maxTareas);
        System.out.println("  Mostrar Stats: " + mostrarEstadisticas);
        System.out.println("----------------------------------------");
    }
}
