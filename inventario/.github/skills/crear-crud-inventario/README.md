# Skill: Crear CRUD Inventario

Este skill documenta el flujo completo para implementar un CRUD con paginación para entidades en el proyecto de inventario Spring Boot.

## Contenido

- **SKILL.md** → Guía paso a paso del workflow
- **templates/** → Plantillas de código para cada capa
  - `Entity.java.template` → Ejemplo de entidad con Lombok
  - `DTOs.java.template` → Ejemplo de DTOs (Create, Update, Patch, Response)
  - `Repository.java.template` → Interfaz JPA Repository
  - `Service.java.template` → Lógica de negocio
  - `Controller.java.template` → Endpoints REST

- **references/** → Documentación complementaria
  - `postgresql-config.md` → ⭐ **Configuración PostgreSQL (comienza aquí)**
  - `error-handling.md` → GlobalExceptionHandler y manejo de errores
  - `pagination-filters.md` → Paginación con Spring Data Pageable
  - `testing.md` → Ejemplos de prueba con curl/Postman

## ⭐ Primer Paso: Configurar PostgreSQL

**ANTES de crear cualquier entidad**, abre [postgresql-config.md](./references/postgresql-config.md) y:

1. Copia la configuración a `src/main/resources/application.properties`
2. Agrega la dependencia PostgreSQL (`org.postgresql:postgresql`)
3. Reinicia tu aplicación

**Credenciales pre-configuradas**:
```properties
spring.datasource.url=jdbc:postgresql://127.0.0.1:5432/unibe
spring.datasource.username=postgres
spring.datasource.password=0112
```

Hibernate creará automáticamente las tablas basadas en tus entities.

## Cómo Usar Este Skill

1. Abre el chat y escribe: `/crear-crud-inventario`
2. O escriba: "Necesito crear un CRUD para [entidad]"
3. El skill te guiará a través del proceso paso a paso

## Rápido: Crear Entity "Catalogo"

1. Copia la plantilla de [Entity.java.template](./templates/Entity.java.template)
2. Ajusta nombres de campos según tu entidad
3. Crea archivo: `src/main/java/com/micro1/inventario/entities/Catalogo.java`
4. Repite para DTOs, Repository, Service, Controller

## Características Incluidas

✅ **PostgreSQL pre-configurado** (127.0.0.1:5432/inventario)
✅ CRUD completo (GET list, GET by ID, GET search, POST, PUT, DELETE, PATCH)
✅ Paginación con Spring Data Pageable
✅ Validación con Jakarta Validation
✅ DTOs separados para request/response
✅ Lombok para getters/setters automáticos
✅ Manejo global de excepciones
✅ HTTP status codes apropiados
✅ Respuestas JSON estructuradas
✅ Métodos de búsqueda (nombre, estado, categoría)
✅ Auditoría (createdAt, updatedAt)

## Stack Tecnológico

- **Framework**: Spring Boot 3.x
- **Base de datos**: PostgreSQL (127.0.0.1:5432/inventario)
- **ORM**: Hibernate/JPA
- **Validación**: Jakarta Validation
- **Lombok**: Para anotaciones (@Data, @NoArgsConstructor, @AllArgsConstructor, @Builder)
- **Java**: JDK 26+

## Estructura del Proyecto

```
src/main/java/com/micro1/inventario/
├── entities/              # Clases de dominio con @Entity
├── dtos/                  # Data Transfer Objects
├── repositories/          # Interfaces JpaRepository
├── services/              # Lógica de negocio
├── controllers/           # Endpoints REST
└── exceptions/            # Excepciones personalizadas
```

## Próximos Pasos Recomendados

1. **Tests**: Agregar tests unitarios para Service/Controller
2. **Autenticación**: Implementar JWT o seguridad según requiera
3. **Documentación**: Agregar Swagger/OpenAPI
4. **Relaciones**: Configurar relaciones One-to-Many, Many-to-Many
5. **Auditoría**: Implementar cambios de usuario (createdBy, updatedBy)

## Notas Importantes

- Los templates son ejemplos; personaliza según tus campos específicos
- Las excepciones personalizadas pueden centralizarse en GlobalExceptionHandler
- Usa `@PageableDefault` para valores por defecto en endpoints
- Respeta naming conventions: singular para entities, DTOs con sufijo
- Siempre valida entrada con `@Valid` en controllers

---
Última actualización: Abril 2026
Proyecto: Inventario - Microservicios
