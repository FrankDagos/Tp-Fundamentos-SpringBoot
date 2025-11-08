package com.utn.tareas;

import com.utn.tareas.model.Prioridad;
import com.utn.tareas.model.Tarea;
import com.utn.tareas.service.MensajeService;
import com.utn.tareas.service.TareaService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Clase principal de la aplicación Spring Boot.
 * Implementa CommandLineRunner para ejecutar lógica al inicio.
 */
@SpringBootApplication
public class TareasApplication implements CommandLineRunner {

    // Inyección de los servicios por constructor
    private final TareaService tareaService;
    private final MensajeService mensajeService;

    public TareasApplication(TareaService tareaService, MensajeService mensajeService) {
        this.tareaService = tareaService;
        this.mensajeService = mensajeService;
    }

    public static void main(String[] args) {
        SpringApplication.run(TareasApplication.class, args);
    }

    /**
     * Método run que se ejecuta al iniciar la aplicación.
     * Contiene el flujo de prueba del PDF.
     */
    @Override
    public void run(String... args) throws Exception {
        // 1. Mostrar mensaje de bienvenida
        System.out.println(mensajeService.mostrarBienvenida());

        // 2. Mostrar la configuración actual
        tareaService.imprimirConfiguracion();

        // 3. Listar todas las tareas iniciales
        System.out.println("\n--- 3. Tareas Iniciales ---");
        imprimirTareas(tareaService.listarTodas());

        // 4. Agregar una nueva tarea
        System.out.println("\n--- 4. Agregando Nueva Tarea ---");
        Tarea nueva = tareaService.agregarTarea("Ir al supermercado", Prioridad.MEDIA);
        if (nueva != null) {
            System.out.println("Tarea agregada: " + nueva);
        }
        // Intento agregar otra para probar el límite (si estás en dev)
        tareaService.agregarTarea("Pasear al perro", Prioridad.BAJA);

        // 5. Listar tareas pendientes
        System.out.println("\n--- 5. Tareas Pendientes ---");
        imprimirTareas(tareaService.listarPendientes());

        // 6. Marcar una tarea como completada (ej: la Tarea con ID 1)
        System.out.println("\n--- 6. Marcando Tarea ID 1 como Completada ---");
        tareaService.marcarCompletada(1L);

        // 7. Mostrar estadísticas
        System.out.println("\n--- 7. Estadísticas ---");
        System.out.println(tareaService.getEstadisticas());

        // 8. Listar tareas completadas
        System.out.println("\n--- 8. Tareas Completadas ---");
        imprimirTareas(tareaService.listarCompletadas());

        // 9. Mostrar mensaje de despedida
        System.out.println("\n" + mensajeService.mostrarDespedida());
    }

    /**
     * Helper para imprimir listas de tareas de forma legible.
     */
    private void imprimirTareas(Iterable<Tarea> tareas) {
        int count = 0;
        for (Tarea tarea : tareas) {
            System.out.println("  - " + tarea);
            count++;
        }
        if (count == 0) {
            System.out.println("  (No hay tareas para mostrar)");
        }
    }
}
