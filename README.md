# ForoHub

ForoHub es una API REST diseñada para la gestión de un foro en el que los participantes pueden crear y gestionar tópicos de discusión. Inspirado en el sistema de foros de Alura, este proyecto replica las funcionalidades clave de un backend de foro, permitiendo a los usuarios realizar operaciones de CRUD sobre los tópicos y gestionando usuarios y autenticación.

# Índice

- [Introducción](#foroHub)
- [Funcionalidades principales](#funcionalidades-principales)
- [Requisitos del sistema](#requisitos-del-sistema)
- [Configuración inicial](#configuración-inicial)
- [Endpoints principales](#endpoints-principales)
- [Arquitectura](#arquitectura)
- [Herramientas utilizadas](#herramientas-utilizadas)


## Funcionalidades principales

- Crear un nuevo tópico
- Listar todos los tópicos
- Ver los detalles de un tópico específico
- Actualizar un tópico existente
- Eliminar un tópico
- Implementación de autenticación y autorización mediante Spring Security
- Validaciones según reglas de negocio
- Persistencia de datos mediante MySQL

## Requisitos del sistema

- **Java JDK**: versión 17 o superior ([Descargar aquí](https://www.oracle.com/java/technologies/javase-downloads.html)).
- **Maven**: versión 4 o superior ([Descargar aquí](https://maven.apache.org/)).
- **Spring Boot**: versión 3 o superior.
- **MySQL**: versión 8 o superior ([Descargar aquí](https://dev.mysql.com/downloads/)).
- **IDE**: IntelliJ IDEA (recomendado, [Descargar aquí](https://www.jetbrains.com/idea/)).


## Configuración inicial

### Creación del proyecto con Spring Initializr

- **Lenguaje**: Java
- **Build Tool**: Maven
- **Packaging**: JAR
- **Dependencias incluidas**:
    - Lombok.
    - Spring Web.
    - Spring Boot DevTools.
    - Spring Data JPA.
    - Flyway Migration.
    - MySQL Driver.
    - Validation.
    - Spring Security.
    - Java JWT.

### Base de datos

La aplicación utiliza MySQL como base de datos para la persistencia de la información. Es necesario contar con una instancia de MySQL configurada y actualiza las credenciales en el archivo `application.properties` o `application.yml`.

### Configuración del entorno

1. Clona este repositorio:
   ```bash
   git clone <URL-del-repositorio>
   ```

2. Configura las credenciales de conexión a la base de datos en el archivo `src/main/resources/application.properties`:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/foro_hub
   spring.datasource.username=tu_usuario
   spring.datasource.password=tu_contraseña
   spring.jpa.hibernate.ddl-auto=update
   spring.flyway.enabled=true
   ```

3. Construye el proyecto con Maven:
   ```bash
   mvn clean install
   ```

4. Ejecuta las migraciones de Flyway:
   ```bash
   mvn flyway:migrate
   ```

5. Inicia la aplicación:
   ```bash
   mvn spring-boot:run
   ```

## Endpoints principales

### Usuarios

- **POST /usuarios**: Registrar un nuevo usuario.

### Tópicos

- **POST /topicos**: Crear un nuevo tópico.
- **GET /topicos**: Listar todos los tópicos (con soporte para filtros).
- **GET /topicos/{id}**: Ver los detalles de un tópico por su ID.
- **PUT /topicos/{id}**: Actualizar un tópico existente.
- **DELETE /topicos/{id}**: Eliminar un tópico.

### Autenticación

- **POST /login**: Autenticar un usuario y obtener un token JWT.

**Ejemplo de request/response:**

- **POST /topicos**
  ```json
  {
    "titulo": "Aprender Java Spring",
    "mensaje": "Este es un mensaje sobre aprender Spring Boot.",
    "autorId": 1,
    "cursoId": 1
  }
  ```
  **Response:**
  ```json
  {
    "id": 33,
    "titulo": "Aprender Java Spring final",
    "mensaje": "Este es un mensaje sobre aprender Spring Boot.",
    "autor": {
        "id": 1,
        "nombre": "Juan Pérez"
    },
    "curso": {
        "id": 1,
        "nombre": "Java Básico"
    },
    "status": "ACTIVO",
    "fechaCreacion": "2025-01-18T18:48:29.400703"
    }
    ```

## Arquitectura

El proyecto sigue una arquitectura en capas que incluye:

1. **Controladores**: Gestionan las solicitudes HTTP y devuelven las respuestas correspondientes.
2. **Servicios**: Contienen la lógica de negocio.
3. **Repositorios**: Interactúan con la base de datos mediante Spring Data JPA.
4. **DTOs**: Facilitan la transferencia de datos entre las capas.



## Herramientas utilizadas

- **Spring Boot**: Framework principal para el desarrollo de la API.
- **Spring Security**: Gestión de autenticación y autorización.
- **MySQL**: Base de datos para la persistencia de datos.
- **Flyway**: Herramienta para la gestión de migraciones de la base de datos.
- **Lombok**: Simplificación del código eliminando boilerplate.
- **Maven**: Gestión de dependencias y construcción del proyecto.
- **Java JWT**: Gestión de generación, firma y validación de tokens de acceso.