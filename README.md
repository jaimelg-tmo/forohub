# ForoHub - API Backend con Spring Boot

ForoHub es una API RESTful para la gestión de un foro de discusión, construida con **Java 21**, **Spring Boot 3.5.4** y **MySQL 8**. Permite crear usuarios, iniciar sesión mediante JWT, y gestionar tópicos con operaciones CRUD.

---

## Índice

1. [Tecnologías utilizadas](#tecnologías-utilizadas)  
2. [Requisitos](#requisitos)  
3. [Instalación y configuración](#instalación-y-configuración)  
4. [Base de datos](#base-de-datos)  
5. [Estructura de paquetes](#estructura-de-paquetes)  
6. [Endpoints de la API](#endpoints-de-la-api)  
7. [Seguridad con JWT](#seguridad-con-jwt)  
8. [Pruebas de la API](#pruebas-de-la-api)  
9. [Ejecución de la aplicación](#ejecución-de-la-aplicación)  
10. [Notas importantes](#notas-importantes)

---

## Tecnologías utilizadas

- Java 21  
- Maven  
- Spring Boot 3.5.4  
- Spring Data JPA  
- Spring Security  
- JWT (JSON Web Token)  
- MySQL 8+  
- Flyway para migraciones de base de datos  
- Lombok  
- Spring Boot DevTools  
- Jakarta Validation  

---

## Requisitos

- JDK 21  
- Maven  
- MySQL 8 o superior  
- IDE recomendado: IntelliJ IDEA o VS Code  

---

## Instalación y configuración

1. Clonar el repositorio:
```
bash
git clone <url-del-repositorio>
cd forohub
```
2. Configurar application.properties con los datos de conexión a la base de datos y JWT:
```
properties

spring.datasource.url=jdbc:mysql://localhost:3306/forohub?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
spring.datasource.username=root
spring.datasource.password=root
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect

spring.flyway.enabled=true
spring.flyway.locations=classpath:db/migration

jwt.secret=mi_secreto_super_seguro
jwt.expiration=3600000
```
3. Crear la base de datos forohub en MySQL:
```
sql
CREATE DATABASE forohub;
```
4. Las migraciones se aplicarán automáticamente con Flyway al iniciar la aplicación.
```
Base de datos   
Tablas

usuario

Campo	Tipo	Restricciones
id	BIGINT	PRIMARY KEY, AUTO_INCREMENT
username	VARCHAR(50)	UNIQUE, NOT NULL
password	VARCHAR(255)	NOT NULL

topico

Campo	Tipo	Restricciones
id	BIGINT	PRIMARY KEY, AUTO_INCREMENT
titulo	VARCHAR(200)	NOT NULL
mensaje	TEXT	NOT NULL
fecha_creacion	DATETIME	NOT NULL
status	VARCHAR(20)	NOT NULL
autor	VARCHAR(120)	NOT NULL
curso	VARCHAR(120)	NOT NULL
```
5. Estructura de paquetes
```
com.aluracursos.forohub
├── config          # Configuración de seguridad y filtros JWT
├── domain
│   ├── usuario     # Entidad Usuario
│   └── topico      # Entidad Topico y StatusTopico
├── repository      # Repositorios JPA
├── service         # Lógica de negocio (TopicoService, TokenService)
└── web
    ├── dto         # DTOs para requests/responses
    └── controllers # Controladores REST (AuthController, UsuarioController, TopicoController)
```
6. Endpoints de la API
```
Usuarios
Método	URL	Descripción	Body JSON
POST	/usuarios/registrar	Registrar nuevo usuario	{"username":"juan","password":"1234"}

Autenticación
Método	URL	Descripción	Body JSON
POST	/login	Login y generar JWT	{"username":"juan","password":"1234"}

Tópicos
Método	URL	Descripción	Body JSON
POST	/topicos	Crear un nuevo tópico	{"titulo":"Tema","mensaje":"Mensaje","autor":"Juan","curso":"Java"}
GET	/topicos	Listar tópicos (paginado)	-
GET	/topicos/{id}	Detalle de un tópico	-
PUT	/topicos/{id}	Actualizar un tópico	{"titulo":"Nuevo","mensaje":"Actualizado"}
DELETE	/topicos/{id}	Eliminar un tópico	-
```
Todos los endpoints de tópicos requieren token JWT válido en header:
```
Authorization: Bearer <token>
```
7. Seguridad con JWT
    1. Generación del token en AuthController al hacer login.

    2. Validación del token mediante JwtAuthFilter, que intercepta todas las solicitudes y autentica el usuario.

    3. La contraseña no se encripta en esta versión, pero se podría mejorar en futuras actualizaciones.

8. Pruebas de la API
    1. Instalar Postman o Insomnia.

    2. Crear usuarios usando POST /usuarios/registrar.

    3. Iniciar sesión con POST /login y copiar el token generado.

    4. Usar ese token en Authorization: Bearer <token> para probar los endpoints de tópicos.

9. Ejecución de la aplicación
```
mvn spring-boot:run
```
10. Luego abrir: http://localhost:8080

- Para endpoints REST se recomienda Postman o Insomnia.

- Flyway aplicará automáticamente migraciones en las tablas usuario y topico.

## Notas importantes
- La contraseña se guarda sin encriptado.

- Las rutas de tópicos están protegidas por JWT.

- Las pruebas de la API pueden realizarse con Postman o Insomnia.

- La base de datos debe estar corriendo antes de iniciar la aplicación.

## Autor
Desarrollado como proyecto educativo con Spring Boot, JWT y MySQL por Jaime Lira García para Alura Latam.