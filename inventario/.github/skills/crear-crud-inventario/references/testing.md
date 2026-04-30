# Testing del CRUD - Ejemplos Curl

## Requisitos
- Aplicación ejecutándose en `http://localhost:8080`
- Base de datos inicializada

## 1. Crear un Catálogo (POST)

```bash
curl -X POST http://localhost:8080/api/v1/catalogo \
  -H "Content-Type: application/json" \
  -d '{
    "nombre": "Laptop ASUS",
    "descripcion": "Laptop para gaming",
    "estado": "ACTIVO",
    "categoria": "Electrónica",
    "precio": 1299.99,
    "cantidadDisponible": 5
  }'
```

Respuesta esperada: 201 Created
```json
{
  "id": 1,
  "nombre": "Laptop ASUS",
  "descripcion": "Laptop para gaming",
  "estado": "ACTIVO",
  "categoria": "Electrónica",
  "precio": 1299.99,
  "cantidadDisponible": 5,
  "createdAt": "2026-04-29T10:30:00",
  "updatedAt": "2026-04-29T10:30:00"
}
```

## 2. Listar Todos (GET)

```bash
curl http://localhost:8080/api/v1/catalogo
curl http://localhost:8080/api/v1/catalogo?page=0&size=5&sort=nombre,asc
```

## 3. Obtener por ID (GET)

```bash
curl http://localhost:8080/api/v1/catalogo/1
```

Respuesta esperada: 200 OK

## 4. Buscar por Nombre (GET)

```bash
curl http://localhost:8080/api/v1/catalogo/search/nombre?nombre=Laptop&page=0&size=10
```

## 5. Filtrar por Estado (GET)

```bash
curl http://localhost:8080/api/v1/catalogo/search/estado?estado=ACTIVO&page=0&size=10
```

## 6. Filtrar por Categoría (GET)

```bash
curl http://localhost:8080/api/v1/catalogo/search/categoria?categoria=Electrónica&page=0&size=10
```

## 7. Actualizar Completo (PUT)

```bash
curl -X PUT http://localhost:8080/api/v1/catalogo/1 \
  -H "Content-Type: application/json" \
  -d '{
    "nombre": "Laptop ASUS ROG",
    "descripcion": "Laptop gaming actualizada",
    "estado": "ACTIVO",
    "categoria": "Gaming",
    "precio": 1499.99,
    "cantidadDisponible": 10
  }'
```

Respuesta esperada: 200 OK

## 8. Actualización Parcial (PATCH)

```bash
curl -X PATCH http://localhost:8080/api/v1/catalogo/1 \
  -H "Content-Type: application/json" \
  -d '{
    "precio": 1599.99,
    "cantidadDisponible": 8
  }'
```

Respuesta esperada: 200 OK (solo los campos especificados se actualizan)

## 9. Eliminar (DELETE)

```bash
curl -X DELETE http://localhost:8080/api/v1/catalogo/1
```

Respuesta esperada: 204 No Content

## 10. Manejo de Errores

### Error: No encontrado (404)
```bash
curl http://localhost:8080/api/v1/catalogo/999
```

Respuesta esperada: 404 Not Found

### Error: Nombre duplicado (409)
```bash
curl -X POST http://localhost:8080/api/v1/catalogo \
  -H "Content-Type: application/json" \
  -d '{
    "nombre": "Laptop ASUS",  # Ya existe
    "descripcion": "Test",
    "estado": "ACTIVO",
    "categoria": "Electrónica",
    "precio": 999.99,
    "cantidadDisponible": 5
  }'
```

Respuesta esperada: 409 Conflict

### Error: Validación fallida (400)
```bash
curl -X POST http://localhost:8080/api/v1/catalogo \
  -H "Content-Type: application/json" \
  -d '{
    "nombre": "",  # Vacío - falla validación
    "estado": "INVALIDO",  # No es ACTIVO|INACTIVO
    "precio": -50  # Negativo
  }'
```

Respuesta esperada: 400 Bad Request con detalles de validación

## Usar en Thunder Client / Postman

1. Importar como colección o crear manualmente
2. Usar variable `{{baseUrl}} = http://localhost:8080`
3. Prueba de principio a fin:
   - Crear → Listar → Buscar → Actualizar → Eliminar
   - Verificar paginación con diferentes valores
   - Probar casos de error
