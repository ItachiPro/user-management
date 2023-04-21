# Api User Management
## El presente software es una api para mantenimeinto de usuarios

Para esta aplicación se utilizaron las siguientes tecnologías.
- Java JDK 20
- Springboot 3
- JPA
- Lombok
- Gestor de bases de datos PostgreSQL
## Instrucciones

- Crear la base de datos con el nombre de user-management.
- Clonar el proyecto y modificar el archivo application.properties ubucado en src/main/java/resources con las credenciales de su gestor de bases de datos PostgreSQL.
- Sincronizar el archivo pom.xml y ejecutar el proyecto.

## Consumir endpoints

A continuación las instrucciones para los endpoints:

- Crear usuario:
Para crear una usuario se usa la ruta http://localhost:5000/api/user de tipo POST con la siguiente estructura json, el id se auto genera.
```
{
    "username" : "",
    "email" : "",
    "password" : "",
}
```
- Para listar los usuarios se usa la ruta http://localhost:5000/api/user de tipo GET.
- Para ver un usuario se usa la ruta http://localhost:5000/api/user/{id} de tipo GET.
- Para modificar un usuario se usa la ruta http://localhost:5000/api/user/{id} de tipo PUT con la siguiente estructura json.
```
{
    "username" : "",
    "email" : "",
    "password" : "",
}
```
- Para eliminar un usuario se usa la ruta http://localhost:5000/api/user/{id} de tipo DELETE.

## Tareas faltantes

- Implementación de JWT para la autenticación y protección de rutas.
- Permitir modificar la contraseña de un usuario.
- Cambiar gestor de bases de datos a Oracle DB.
- Implementar solcución para el bloqueo de peticiones por CORS.

Desarrollado por Walter Stanley Guevara Aguilar
