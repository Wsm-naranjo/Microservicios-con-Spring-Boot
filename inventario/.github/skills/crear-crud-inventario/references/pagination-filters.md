# Paginación y Filtros en Spring Data

## Spring Data Pageable

Parámetros soportados automáticamente:

```
?page=0&size=20&sort=nombre,asc&sort=precio,desc
```

| Parámetro | Descripción | Ejemplo |
|-----------|-------------|---------|
| `page`    | Página (0-indexed) | `?page=0` |
| `size`    | Elementos por página | `?size=20` |
| `sort`    | Ordenamiento | `?sort=nombre,asc` |

## Ejemplos de Consultas

### Listar con paginación por defecto
```bash
GET http://localhost:8080/api/v1/catalogo
```
Respuesta: Primera página con 10 items

### Listar página 2, 15 items por página
```bash
GET http://localhost:8080/api/v1/catalogo?page=1&size=15
```

### Ordenar por nombre ascendente, luego por precio descendente
```bash
GET http://localhost:8080/api/v1/catalogo?sort=nombre,asc&sort=precio,desc
```

### Buscar con filtros
```bash
GET http://localhost:8080/api/v1/catalogo/search/nombre?nombre=laptop&page=0&size=10
GET http://localhost:8080/api/v1/catalogo/search/estado?estado=ACTIVO&page=0&size=10
GET http://localhost:8080/api/v1/catalogo/search/categoria?categoria=Electrónica&page=0&size=10
```

## Respuesta de Paginación

```json
{
  "content": [
    {
      "id": 1,
      "nombre": "Laptop",
      "estado": "ACTIVO",
      "precio": 999.99,
      ...
    }
  ],
  "pageable": {
    "pageNumber": 0,
    "pageSize": 10,
    "sort": {
      "empty": false,
      "unsorted": false,
      "sorted": true
    }
  },
  "totalElements": 45,
  "totalPages": 5,
  "size": 10,
  "number": 0,
  "numberOfElements": 10,
  "first": true,
  "last": false,
  "empty": false
}
```

## Especificaciones Avanzadas (JpaSpecificationExecutor)

Para filtros más complejos, extender `JpaSpecificationExecutor<Entity>` en Repository:

```java
@Repository
public interface CatalogoRepository extends JpaRepository<Catalogo, Long>, 
                                          JpaSpecificationExecutor<Catalogo> {
}
```

Crear Specifications:

```java
public class CatalogoSpecifications {

    public static Specification<Catalogo> porNombreYEstado(String nombre, String estado) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (nombre != null) {
                predicates.add(cb.like(cb.lower(root.get("nombre")), 
                                       "%" + nombre.toLowerCase() + "%"));
            }
            if (estado != null) {
                predicates.add(cb.equal(root.get("estado"), estado));
            }
            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
```

Usar en Service:

```java
Page<Catalogo> resultados = repository.findAll(
    CatalogoSpecifications.porNombreYEstado("laptop", "ACTIVO"),
    pageable
);
```

## Validación en @PageableDefault

En Controller, usar `@PageableDefault` para establecer valores por defecto:

```java
@GetMapping
public ResponseEntity<Page<CatalogoResponseDTO>> getAll(
        @PageableDefault(size = 10, page = 0, sort = "nombre", direction = Sort.Direction.ASC) 
        Pageable pageable) {
    return ResponseEntity.ok(service.getAll(pageable));
}
```
