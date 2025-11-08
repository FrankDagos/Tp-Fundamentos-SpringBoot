package com.utn.tareas.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase que representa una Tarea en el sistema.
 * Usamos Lombok para reducir el código boilerplate.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tarea {

    private Long id;
    private String descripcion;
    private boolean completada;
    private Prioridad prioridad;

}