package com.utn.tareas.repository;

import com.utn.tareas.model.Prioridad;
import com.utn.tareas.model.Tarea;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Repositorio en memoria para gestionar las Tareas.
 */
@Repository
public class TareaRepository {

    // Lista en memoria para almacenar las tareas
    private final List<Tarea> tareas = new ArrayList<>();
    // Generador de IDs automáticos
    private final AtomicLong contadorId = new AtomicLong(1);

    /**
     * Constructor que inicializa el repositorio con tareas de ejemplo.
     */
    public TareaRepository() {
        tareas.add(new Tarea(contadorId.getAndIncrement(), "Estudiar Spring Boot", false, Prioridad.ALTA));
        tareas.add(new Tarea(contadorId.getAndIncrement(), "Hacer el TP de Programación III", false, Prioridad.ALTA));
        tareas.add(new Tarea(contadorId.getAndIncrement(), "Comprar comida para el gato", true, Prioridad.MEDIA));
        tareas.add(new Tarea(contadorId.getAndIncrement(), "Ver partido de Boca", false, Prioridad.BAJA));
    }

    /**
     * Guarda una tarea (nueva o existente).
     * Si es nueva, le asigna un ID.
     * Si es existente (tiene ID), la actualiza.
     * @param tarea Tarea a guardar
     * @return Tarea guardada
     */
    public Tarea save(Tarea tarea) {
        if (tarea.getId() == null || tarea.getId() == 0) {
            // Es una nueva tarea
            tarea.setId(contadorId.getAndIncrement());
            tareas.add(tarea);
        } else {
            // Es una actualización
            Optional<Tarea> existenteOpt = findById(tarea.getId());
            if (existenteOpt.isPresent()) {
                Tarea existente = existenteOpt.get();
                existente.setDescripcion(tarea.getDescripcion());
                existente.setCompletada(tarea.isCompletada());
                existente.setPrioridad(tarea.getPrioridad());
            } else {
                // Si no se encuentra, se añade como nueva (comportamiento de save)
                // O se podría lanzar una excepción
                tareas.add(tarea);
            }
        }
        return tarea;
    }

    /**
     * Devuelve todas las tareas.
     * @return Lista de todas las tareas
     */
    public List<Tarea> findAll() {
        return new ArrayList<>(tareas); // Devuelve una copia para evitar modificaciones externas
    }

    /**
     * Busca una tarea por su ID.
     * @param id ID de la tarea
     * @return Optional con la tarea si se encuentra, o vacío si no
     */
    public Optional<Tarea> findById(Long id) {
        return tareas.stream()
                .filter(t -> t.getId().equals(id))
                .findFirst();
    }

    /**
     * Elimina una tarea por su ID.
     * @param id ID de la tarea a eliminar
     */
    public void deleteById(Long id) {
        tareas.removeIf(t -> t.getId().equals(id));
    }

    /**
     * Cuenta el total de tareas.
     * @return Número total de tareas
     */
    public long count() {
        return tareas.size();
    }
}