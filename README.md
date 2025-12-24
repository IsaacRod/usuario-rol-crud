# 🚀 API REST CRUD - Sistema de Gestión de Usuarios y Roles

[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.9-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![Java](https://img.shields.io/badge/Java-21-orange.svg)](https://www.oracle.com/java/)
[![License](https://img.shields.io/badge/License-MIT-blue.svg)](LICENSE)
[![H2 Database](https://img.shields.io/badge/Database-H2-blue.svg)](https://www.h2database.com/)
[![Estado](https://img.shields.io/badge/Estado-🚀_Producción-success.svg)]()

## 📋 Tabla de Contenidos
1. [¿Qué es este proyecto?](#qué-es-este-proyecto)
2. [Características principales](#características-principales)
3. [Tecnologías utilizadas](#tecnologías-utilizadas)
4. [Estructura del proyecto](#estructura-del-proyecto)
5. [Cómo empezar](#cómo-empezar)
6. [Uso de la API](#uso-de-la-api)
7. [Ejemplos prácticos](#ejemplos-prácticos)
8. [Pruebas del sistema](#pruebas-del-sistema)
9. [Contribuciones](#contribuciones)
10. [Contacto](#contacto)

## ¿Qué es este proyecto?

¡Hola! 👋 Este es un **API REST CRUD** desarrollada con **Spring Boot** que permite gestionar usuarios y roles de manera sencilla y eficiente. Perfecto para administrar usuarios de aplicaciones web, asignar permisos y mantener todo organizado.

### ¿Para quién es útil?
- **Desarrolladores** que necesitan backend para gestión de usuarios
- **Estudiantes** que quieren aprender Spring Boot con ejemplos reales
- **Proyectos personales** que requieren autenticación básica
- **Startups** que necesitan sistema rápido para administrar accesos

### Casos de uso
- Sistema de administración de empleados
- Plataforma de cursos con diferentes tipos de usuarios
- Aplicación web con niveles de acceso
- Backend para aplicaciones móviles

## Características principales

### Gestión inteligente de usuarios
Crea, actualiza, busca y elimina usuarios de forma intuitiva. Cada usuario tiene perfil completo con nombre, apellidos, email y roles asignados.

### Roles flexibles
Define roles como "Administrador", "Editor", "Usuario Básico" o cualquier otro que necesites. Asigna múltiples roles a cada usuario.

### Relaciones dinámicas
Los usuarios pueden tener varios roles, y cada rol puede asignarse a múltiples usuarios.

### Rápido y eficiente
Con base de datos H2 en memoria, el sistema arranca en segundos.

### Validaciones automáticas
El sistema verifica que los emails sean únicos y que los datos estén completos.

## Tecnologías utilizadas

### Backend
- **Spring Boot 3.5.9** - Framework principal
- **Java 21** - Lenguaje de programación
- **Spring Data JPA** - Persistencia de datos
- **H2 Database** - Base de datos en memoria
- **Maven** - Gestión de dependencias

### Herramientas de desarrollo
- **Spring Boot DevTools** - Recarga automática
- **H2 Console** - Interfaz web para base de datos
- **Git** - Control de versiones
- **Postman** - Para probar endpoints

## Estructura del proyecto
usuario-rol-crud/
├── src/main/java/com/crud/usuariorol/
│ ├── UsuarioRolCrudApplication.java
│ ├── config/
│ ├── controller/
│ ├── model/
│ ├── repository/
│ ├── service/
│ └── exception/
├── src/main/resources/
│ ├── application.properties
│ └── data.sql
├── src/test/
├── pom.xml
├── README.md
└── .gitignore


## Cómo empezar

### Requisitos previos
- **Java 21** o superior
- Navegador web
- Conexión a internet

### Instalación paso a paso

1. Descarga el proyecto:
```bash
git clone https://github.com/IsaacRod/usuario-rol-crud.git
cd usuario-rol-crud

2.Compila:
./mvnw clean compile

3.Inicia la aplicación:
./mvnw spring-boot:run

4. ¡Listo! Accede a:

API: http://localhost:8080

Consola H2: http://localhost:8080/h2-console

JDBC URL: jdbc:h2:mem:usuariodb

Usuario: sa

Contraseña: (dejar vacío)

Ejecución rápida en Windows
Si usas Windows, puedes usar el archivo run.bat:

Haz doble clic en run.bat

Espera a que aparezca "Started UsuarioRolCrudApplication"

¡Ya está funcionando!

Uso de la API
Endpoints de Usuarios
Método	Ruta			Descripción
GET	/api/usuarios		Lista todos los usuarios
GET	/api/usuarios/{id}	Busca usuario por ID
POST	/api/usuarios		Crea nuevo usuario
PUT	/api/usuarios/{id}	Actualiza usuario
DELETE	/api/usuarios/{id}	Elimina usuario

Endpoints de Roles
Método	Ruta			Descripción
GET	/api/roles		Lista todos los roles
GET	/api/roles/{id}		Busca rol por ID
POST	/api/roles		Crea nuevo rol
PUT	/api/roles/{id}		Actualiza rol
DELETE	/api/roles/{id}		Elimina rol

Ejemplos prácticos
Crear un usuario
curl -X POST http://localhost:8080/api/usuarios \
  -H "Content-Type: application/json" \
  -d '{"nombre":"María","apellidos":"López","email":"maria@empresa.com","roleIds":[1,2]}'

Respuesta esperada:

{
  "id": 1,
  "nombre": "María",
  "apellidos": "López",
  "email": "maria@empresa.com",
  "roles": [
    {"id": 1, "nombre": "ADMIN", "descripcion": "Administrador"},
    {"id": 2, "nombre": "EDITOR", "descripcion": "Editor"}
  ],
  "fechaCreacion": "2024-01-25T14:30:00",
  "fechaActualizacion": "2024-01-25T14:30:00"
}

Buscar todos los usuarios

curl -X GET http://localhost:8080/api/usuarios

Actualizar un usuario

curl -X PUT http://localhost:8080/api/usuarios/1 \
  -H "Content-Type: application/json" \
  -d '{"nombre":"María José","apellidos":"López García"}'

Pruebas del sistema
Pruebas automáticas

./mvnw test

Pruebas con Postman
Abre Postman

Configura la URL base como: http://localhost:8080

Prueba cada endpoint en orden

Ver base de datos en vivo
Accede a: http://localhost:8080/h2-console

Conéctate con los datos proporcionados

Ejecuta: SELECT * FROM TUSUARIO;

Contribuciones
Reportar problemas
Si encuentras un error:

Ve a Issues del proyecto

Haz clic en "New Issue"

Describe el problema y cómo reproducirlo

Contribuir con código
Haz un fork del proyecto

Crea una rama: git checkout -b mi-mejora

Realiza tus cambios

Haz commit: git commit -m "feat: descripción de cambios"

Sube los cambios: git push origin mi-mejora

Abre un Pull Request

Primeras contribuciones
Busca issues etiquetados como good-first-issue

Pregunta si tienes dudas

¡Todos empezamos así!

Contacto
Autor
Isaac Rodríguez
📧 Email: isaacrr507@gmail.com
🐱 GitHub: @IsaacRod

Recursos adicionales
Documentación Spring Boot: spring.io

Tutoriales Java: Oracle

Comunidad: Stack Overflow

¿Necesitas ayuda?
Revisa los issues cerrados por si ya hay solución

Si no encuentras, abre un nuevo issue

Para preguntas rápidas, puedes enviar un email

¿Te gustó el proyecto?
Dale una estrella en GitHub ⭐

Compártelo con otros desarrolladores

Haz un fork y crea tu propia versión

Escribe un artículo sobre cómo lo usaste


¡Gracias por visitar este proyecto! Espero que te sea útil.

"El buen código es como una buena historia: claro, conciso y con propósito."






