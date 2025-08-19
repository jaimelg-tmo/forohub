# ForoHub

forohub/
│
├─ src/main/java/com/aluracursos/forohub/
│   ├─ ForohubApplication.java
│   ├─ config/
│   │   └─ SecurityConfig.java
│   ├─ domain/topico/
│   │   ├─ Topico.java
│   │   ├─ TopicoRepository.java
│   │   └─ StatusTopico.java
│   ├─ service/
│   │   └─ TopicoService.java
│   └─ web/
│       ├─ TopicoController.java
│       ├─ GlobalExceptionHandler.java
│       └─ dto/
│           ├─ TopicoRequest.java
│           ├─ TopicoResponse.java
│           └─ TopicoUpdateRequest.java
│
├─ src/main/resources/
│   ├─ application.properties
│   └─ db/migration/
│       └─ V1__crear_tabla_topico.sql
│
├─ pom.xml
└─ .env
