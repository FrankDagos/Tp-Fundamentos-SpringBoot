# 🚀 Trabajo Práctico - Fundamentos de Spring Boot

**Nombre:** Franco D'Agostino
**Legajo:** 47761

## 📝 Descripción del Proyecto

Este proyecto es un Sistema de Gestión de Tareas (To-Do List) básico, desarrollado como trabajo práctico para la materia Programación III de la Tecnicatura Universitaria en Programación (UTN).

La aplicación está construida con Spring Boot y demuestra conceptos clave como:
* Inyección de Dependencias (por constructor)
* Estereotipos de Spring (`@Service`, `@Repository`)
* Gestión de configuración externa (`application.properties`)
* Inyección de valores con `@Value`
* Uso de Perfiles (`dev`, `prod`) para distintos entornos
* Beans condicionales con `@Profile`
* Lógica de inicio con `CommandLineRunner`

El repositorio utiliza un `TareaRepository` en memoria para simular la persistencia de datos.

## 🛠️ Tecnologías Utilizadas

* **Java 17+**
* **Spring Boot 3.x**
* **Maven**
* **Lombok** (para reducir código boilerplate)

## 📦 Instrucciones para Clonar y Ejecutar

Sigue estos pasos para poner en marcha el proyecto en tu máquina local:

1.  **Clonar el Repositorio:**
    ```bash
    git clone (https://github.com/FrankDagos/Tp-Fundamentos-SpringBoot.git)
    cd tareas
    ```

2.  **Ejecutar la Aplicación (con Maven):**
    La aplicación se ejecutará con el perfil `dev` por defecto (definido en `application.properties`).

    ```bash
    mvn spring-boot:run
    ```

    Verás la salida de la consola con los logs de DEBUG y los mensajes de bienvenida de desarrollo.

## 🔄 Cómo Cambiar entre Perfiles

La aplicación está configurada con dos perfiles: `dev` y `prod`.

### 1. Vía `application.properties` (Recomendado para el TP)

1.  Abre el archivo `src/main/resources/application.properties`.
2.  Cambia la línea `spring.profiles.active=dev` por `spring.profiles.active=prod`.
3.  Vuelve a ejecutar la aplicación con `mvn spring-boot:run`.

    Notarás que los mensajes de bienvenida/despedida son más concisos, el límite de tareas es 1000, las estadísticas están desactivadas y el logging es menos verboso (solo ERROR).

### 2. Vía Línea de Comandos (Alternativa)

También puedes forzar un perfil al ejecutar la aplicación (esto sobreescribe el archivo `.properties`):

```bash
# Forzar perfil 'prod'
mvn spring-boot:run -Dspring-boot.run.profiles=prod

# Forzar perfil 'dev'
mvn spring-boot:run -Dspring-boot.run.profiles=dev
