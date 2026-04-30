# Configuración PostgreSQL - Proyecto Inventario

## Credenciales de Conexión

| Parámetro | Valor |
|-----------|-------|
| **URL** | `jdbc:postgresql://127.0.0.1:5432/unibe` |
| **Host** | `127.0.0.1` |
| **Puerto** | `5432` |
| **Base de Datos** | `unibe` |
| **Usuario** | `postgres` |
| **Contraseña** | `0112` |

## Configuración en Spring Boot

### 1. application.properties

```properties
# PostgreSQL configuration
spring.datasource.url=jdbc:postgresql://127.0.0.1:5432/unibe
spring.datasource.username=postgres
spring.datasource.password=0112
spring.datasource.driver-class-name=org.postgresql.Driver

# JPA / Hibernate
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

### 2. application.yml

```yaml
spring:
  datasource:
    url: jdbc:postgresql://127.0.0.1:5432/unibe
    username: postgres
    password: '0112'
    driver-class-name: org.postgresql.Driver
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
```

## Dependencias Necesarias

### Maven (pom.xml)

```xml
<!-- PostgreSQL Driver -->
<dependency>
    <groupId>org.postgresql</groupId>
    <artifactId>postgresql</artifactId>
    <version>42.7.1</version>
    <scope>runtime</scope>
</dependency>

<!-- Spring Data JPA -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>

<!-- Spring Web -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>

<!-- Lombok -->
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <optional>true</optional>
</dependency>

<!-- Jakarta Validation -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-validation</artifactId>
</dependency>
```

### Gradle (build.gradle)

```gradle
dependencies {
    runtimeOnly 'org.postgresql:postgresql:42.7.1'
    implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
    implementation 'org.springframework.boot:spring-boot-starter-web'
    compileOnly 'org.projectlombok:lombok'
    annotationProcessor 'org.projectlombok:lombok'
    implementation 'org.springframework.boot:spring-boot-starter-validation'
}
```

## Validación de Conexión

### Desde Terminal/CMD

```bash
# Verificar conexión a PostgreSQL
psql -h 127.0.0.1 -U postgres -d unibe -c "SELECT 1"
```

### Desde Spring Boot

En `application.properties`, agregar para validar al iniciar:

```properties
spring.datasource.test-on-borrow=true
spring.datasource.test-on-return=true
spring.datasource.test-while-idle=true
spring.datasource.validation-query=SELECT 1
```

## Valores de ddl-auto

| Valor | Comportamiento |
|-------|----------------|
| `create` | Crea las tablas, borra las existentes al iniciar |
| `update` | ⭐ **RECOMENDADO**: Actualiza schema existente sin borrar datos |
| `validate` | Solo valida que el schema coincida (error si no coincide) |
| `create-drop` | Crea al iniciar, borra al terminar (pruebas) |
| `none` | No hace nada (manual) |

Para **producción**, usa `validate` o `none`.
Para **desarrollo**, usa `update`.

## Troubleshooting

### Error: "FATAL: database 'unibe' does not exist"

Crear la BD manualmente:

```bash
psql -h 127.0.0.1 -U postgres -c "CREATE DATABASE unibe;"
```

### Error: "connection refused"

Verificar que PostgreSQL está ejecutándose:

```bash
# Windows
net start PostgreSQL14

# Linux
sudo service postgresql start

# macOS
brew services start postgresql
```

### Error: "authentication failed for user 'postgres'"

Verificar credenciales en `application.properties`. Asegúrate que la contraseña es exactamente `0112` (sin espacios).

## Logs Útiles

Para ver SQL generado:

```properties
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
logging.level.org.hibernate.SQL=DEBUG
logging.level.org.hibernate.type.descriptor.sql.BasicBinder=TRACE
```

Esto mostrará en consola:

```
Hibernate: 
    select
        c1_0.id,
        c1_0.nombre,
        ...
    from
        catalogo c1_0
```

## Información General

| Parámetro | Valor |
|-----------|-------|
| **Base de Datos** | `unibe` |
| **Host** | `127.0.0.1` |
| **Puerto** | `5432` |
| **Usuario** | `postgres` |
| **Contraseña** | `0112` |
| **DDL Auto** | `update` (crea/actualiza tablas automáticamente) |

Al iniciar la aplicación, Hibernate creará automáticamente las tablas basadas en las entidades.
