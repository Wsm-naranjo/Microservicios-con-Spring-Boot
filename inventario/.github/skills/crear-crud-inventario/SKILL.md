---
name: crear-crud-inventario
description: 'Crear un CRUD completo con paginación para entidades de inventario (Entity, Repository, Service, Controller) usando Spring Data, Lombok, y DTOs. Use cuando: necesite implementar operaciones CRUD (GET list, GET by ID, GET con filtros, POST, PUT, DELETE, PATCH) con paginación para una entidad del catálogo.'
argument-hint: 'Nombre de la entidad (ej: Catalogo, Producto, Proveedor)'
user-invocable: true
---

# Crear CRUD Completo para Entidades de Inventario

## Cuándo Usar
- Implementar operaciones CRUD completas para una nueva entidad del catálogo
- Agregar paginación, filtros y búsqueda
- Mantener estructura consistente: controllers, entities, repositories, services
- Necesitar manejo de errores mediante APIs REST modernas

## Estructura del Skill
Este skill incluye templates para todas las capas de la aplicación:
- [Entity con Lombok](./templates/Entity.java.template)
- [DTO Request/Response](./templates/DTOs.java.template)
- [Repository con especificaciones](./templates/Repository.java.template)
- [Service con lógica de negocio](./templates/Service.java.template)
- [Controller con endpoints REST](./templates/Controller.java.template)

**Referencias complementarias**:
- [Configuración PostgreSQL](./references/postgresql-config.md) ⭐ **Comienza aquí**
- [Validación y Errores](./references/validation-errors.md) ⭐ **Ejemplos de POST correcto e incorrecto**
- [Paginación y Filtros](./references/pagination-filters.md)
- [Ejemplos de Paginación](./references/pagination-examples.md) ⭐ **Con CURL y casos de uso**
- [Manejo de Errores](./references/error-handling.md)
- [Testing con ejemplos](./references/testing.md)

## Procedimiento Paso a Paso

### 1. Crear la Entidad (Entity) con Lombok
- Ubicación: `src/main/java/com/micro1/inventario/entities/`
- Usar anotaciones Lombok: `@Getter`, `@Setter`, `@NoArgsConstructor`, `@AllArgsConstructor`, `@Builder`
- Mapear propiedades con `@Entity`, `@Table`, `@Id`, `@GeneratedValue`
- Incluir campos de auditoría: `createdAt`, `updatedAt` (opcionales pero recomendados)
- Consulta: [plantilla Entity](./templates/Entity.java.template)

### 2. Crear DTOs (Data Transfer Objects)
- Ubicación: `src/main/java/com/micro1/inventario/dtos/`
- `CreateEntityDTO`: Para recibir datos en POST
- `UpdateEntityDTO`: Para recibir datos en PUT
- `PatchEntityDTO`: Para PATCH (todos los campos opcionales)
- `EntityResponseDTO`: Para respuestas
- Incluir validaciones: `@NotNull`, `@NotBlank`, `@Min`, `@Max`, etc.
- Consulta: [plantilla DTOs](./templates/DTOs.java.template)

### 3. Crear el Repository
- Ubicación: `src/main/java/com/micro1/inventario/repositories/`
- Extender `JpaRepository<Entity, ID>`
- Extender `JpaSpecificationExecutor<Entity>` para filtros avanzados
- Métodos query: `findByNombre(String nombre)`, `findByEstado(String estado)`, etc.
- Usar `Page<Entity>` para soportar paginación
- Consulta: [plantilla Repository](./templates/Repository.java.template)

### 4. Crear el Service
- Ubicación: `src/main/java/com/micro1/inventario/services/`
- Inyectar `@Autowired` el Repository
- Implementar métodos para cada operación:
  - `getAll(Pageable)`: Listar con paginación
  - `getById(Long)`: Obtener por ID
  - `getByNombre(String, Pageable)`: Búsqueda por nombre
  - `getByEstado(String, Pageable)`: Filtro por estado
  - `create(CreateEntityDTO)`: Crear
  - `update(Long, UpdateEntityDTO)`: Actualizar completo
  - `patch(Long, PatchEntityDTO)`: Actualización parcial
  - `delete(Long)`: Eliminar
- Manejar excepciones: `EntityNotFoundException`, validaciones
- Mapear DTOs ↔ Entidades (manual o usar MapStruct si es necesario)
- Consulta: [plantilla Service](./templates/Service.java.template)

### 5. Crear el Controller
- Ubicación: `src/main/java/com/micro1/inventario/controllers/`
- Anotaciones: `@RestController`, `@RequestMapping("/api/v1/catalogo")` (ajustar según entidad)
- Métodos REST:
  - `GET /`: Listar con paginación
  - `GET /{id}`: Obtener por ID
  - `GET /search/nombre?nombre=...&page=0`: Búsqueda
  - `POST /`: Crear
  - `PUT /{id}`: Actualizar completo
  - `PATCH /{id}`: Actualización parcial
  - `DELETE /{id}`: Eliminar
- Inyectar el Service
- Usar `ResponseEntity` para control de HTTP status
- Manejar excepciones con `@ExceptionHandler` o GlobalExceptionHandler
- Consulta: [plantilla Controller](./templates/Controller.java.template)

### 6. Validar Paginación
- Parámetros esperados: `?page=0&size=10&sort=nombre,asc`
- Response incluye: `content[]`, `totalPages`, `totalElements`, `currentPage`, `size`
- Usar `org.springframework.data.domain.Pageable`

### 7. Implementar Manejo de Errores
- Crear respuesta uniforme de error
- Capturar excepciones por entidad no encontrada (404)
- Validar duplicados o restricciones (400)
- Usar status HTTP apropiados: 200, 201, 204, 400, 404, 500

### 8. Probar Endpoints
- Usar Postman, Thunder Client, o curl
- Verificar: listar, crear, actualizar, filtrar, eliminar
- Probar paginación con diferentes valores de page/size
- Validar respuestas de error

## Configuración de Base de Datos PostgreSQL

### application.properties
Agregar o actualizar las siguientes propiedades en `src/main/resources/application.properties`:

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


### Dependencia Gradle (build.gradle)
Si usas Gradle:

```gradle
dependencies {
    runtimeOnly 'org.postgresql:postgresql:42.7.1'
}
```

### Verificar Conexión
- Base de datos: `unibe`
- Host: `127.0.0.1`
- Puerto: `5432`
- Usuario: `postgres`
- Contraseña: `0112`

Al iniciar la aplicación, Hibernate creará automáticamente las tablas basadas en las entidades (si `ddl-auto=update`).

## Convenciones Usadas en Este Proyecto
- **Package naming**: `com.micro1.inventario.{entities, dtos, repositories, services, controllers}`
- **Clase Entity**: Singular, ej: `Catalogo.java`
- **DTO**: `CatalogoDTO.java`, `CreateCatalogoDTO.java`, etc.
- **Repository**: `CatalogoRepository.java`
- **Service**: `CatalogoService.java`
- **Controller**: `CatalogoController.java`
- **Base URLs**: `/api/v1/{entidad}`
- **JDK**: 26+ (usar `var`, sealed classes, records si es aplicable)

## Checklist de Completitud
- [ ] Base de datos PostgreSQL configurada en `application.properties`
- [ ] Dependencia PostgreSQL agregada (Maven/Gradle)
- [ ] Entity creada con anotaciones Lombok
- [ ] DTOs creadas (Create, Update, Patch, Response)
- [ ] Repository con métodos query
- [ ] Service con lógica de negocio completa
- [ ] Controller con 7+ endpoints (GET list, GET by ID, GET search, POST, PUT, PATCH, DELETE)
- [ ] Paginación implementada
- [ ] Manejo de errores en todos los endpoints
- [ ] Endpoints probados exitosamente

## Próximos Pasos
Después de crear el CRUD:
1. Agregue tests unitarios para Service y Controller
2. Implemente autenticación si es requerida
3. Agregue documentación Swagger/OpenAPI
4. Configure relaciones con otras entidades si es necesario
