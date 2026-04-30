# Validación y Errores - CRUD Catálogo

## Validaciones en DTOs

### CreateCatalogoDTO - Validaciones Requeridas

```
Campo                  | Validación
-----------------------|-----------------------------------------
nombre                 | @NotBlank (requerido, min 3, max 100 chars)
descripcion            | @Size (máximo 255 chars, opcional)
estado                 | @NotBlank (ACTIVO o INACTIVO, requerido)
categoria              | @Size (máximo 100 chars, opcional)
precio                 | @NotNull, @DecimalMin(0, exclusive)
cantidadDisponible     | @Min(0)
```

## ✅ Ejemplo CORRECTO - POST Válido

```bash
curl -X POST http://localhost:8080/api/v1/catalogo \
  -H "Content-Type: application/json" \
  -d '{
    "nombre": "Laptop ASUS ROG",
    "descripcion": "Laptop gaming de alto rendimiento",
    "estado": "ACTIVO",
    "categoria": "Electrónica",
    "precio": 2499.99,
    "cantidadDisponible": 5
  }'
```

**Respuesta (201 Created):**
```json
{
  "id": 1,
  "nombre": "Laptop ASUS ROG",
  "descripcion": "Laptop gaming de alto rendimiento",
  "estado": "ACTIVO",
  "categoria": "Electrónica",
  "precio": 2499.99,
  "cantidadDisponible": 5,
  "createdAt": "2026-04-29T10:30:45",
  "updatedAt": "2026-04-29T10:30:45"
}
```

---

## ❌ Ejemplo INCORRECTO - Falta estado

```bash
curl -X POST http://localhost:8080/api/v1/catalogo \
  -H "Content-Type: application/json" \
  -d '{
    "nombre": "Laptop ASUS ROG",
    "descripcion": "Laptop gaming",
    "categoria": "Electrónica",
    "precio": 2499.99,
    "cantidadDisponible": 5
  }'
```

**Respuesta (400 Bad Request):**
```json
{
  "status": 400,
  "message": "Errores de validación",
  "details": [
    "estado: El estado es requerido (ACTIVO o INACTIVO)"
  ],
  "timestamp": "2026-04-29T10:35:00"
}
```

---

## ❌ Ejemplo INCORRECTO - Estado inválido

```bash
curl -X POST http://localhost:8080/api/v1/catalogo \
  -H "Content-Type: application/json" \
  -d '{
    "nombre": "Laptop ASUS ROG",
    "estado": "DISPONIBLE",
    "categoria": "Electrónica",
    "precio": 2499.99,
    "cantidadDisponible": 5
  }'
```

**Respuesta (400 Bad Request):**
```json
{
  "status": 400,
  "message": "Errores de validación",
  "details": [
    "estado: El estado debe ser exactamente ACTIVO o INACTIVO"
  ],
  "timestamp": "2026-04-29T10:35:00"
}
```

---

## ❌ Ejemplo INCORRECTO - Nombre muy corto

```bash
curl -X POST http://localhost:8080/api/v1/catalogo \
  -H "Content-Type: application/json" \
  -d '{
    "nombre": "PC",
    "estado": "ACTIVO",
    "precio": 2499.99,
    "cantidadDisponible": 5
  }'
```

**Respuesta (400 Bad Request):**
```json
{
  "status": 400,
  "message": "Errores de validación",
  "details": [
    "nombre: El nombre debe tener entre 3 y 100 caracteres"
  ],
  "timestamp": "2026-04-29T10:35:00"
}
```

---

## ❌ Ejemplo INCORRECTO - Precio negativo

```bash
curl -X POST http://localhost:8080/api/v1/catalogo \
  -H "Content-Type: application/json" \
  -d '{
    "nombre": "Laptop ASUS",
    "estado": "ACTIVO",
    "precio": -100,
    "cantidadDisponible": 5
  }'
```

**Respuesta (400 Bad Request):**
```json
{
  "status": 400,
  "message": "Errores de validación",
  "details": [
    "precio: El precio debe ser mayor a 0"
  ],
  "timestamp": "2026-04-29T10:35:00"
}
```

---

## ❌ Ejemplo INCORRECTO - Múltiples errores

```bash
curl -X POST http://localhost:8080/api/v1/catalogo \
  -H "Content-Type: application/json" \
  -d '{
    "nombre": "PC",
    "estado": "PENDIENTE",
    "precio": -50,
    "cantidadDisponible": -5
  }'
```

**Respuesta (400 Bad Request):**
```json
{
  "status": 400,
  "message": "Errores de validación",
  "details": [
    "nombre: El nombre debe tener entre 3 y 100 caracteres",
    "estado: El estado debe ser exactamente ACTIVO o INACTIVO",
    "precio: El precio debe ser mayor a 0",
    "cantidadDisponible: La cantidad no puede ser negativa"
  ],
  "timestamp": "2026-04-29T10:35:00"
}
```

---

## Estados Válidos

Solo se permiten estos dos valores para `estado`:

| Estado | Descripción |
|--------|------------|
| `ACTIVO` | Producto disponible para venta |
| `INACTIVO` | Producto no disponible |

**NO son válidos:**
- `activo` (minúscula) ❌
- `DISPONIBLE` ❌
- `ACTIVOS` ❌
- `null` o vacío ❌

---

## Errores de Aplicación

### 404 - Catálogo No Encontrado

```bash
curl http://localhost:8080/api/v1/catalogo/999
```

**Respuesta:**
```json
{
  "status": 404,
  "message": "Catálogo con ID 999 no encontrado",
  "timestamp": "2026-04-29T10:40:00"
}
```

---

### 409 - Nombre Duplicado

```bash
# Primero crear un catálogo
curl -X POST http://localhost:8080/api/v1/catalogo \
  -H "Content-Type: application/json" \
  -d '{"nombre":"Laptop","estado":"ACTIVO","categoria":"PC","precio":2000,"cantidadDisponible":5}'

# Intentar crear otro con el mismo nombre
curl -X POST http://localhost:8080/api/v1/catalogo \
  -H "Content-Type: application/json" \
  -d '{"nombre":"Laptop","estado":"ACTIVO","categoria":"Electrónica","precio":2500,"cantidadDisponible":3}'
```

**Respuesta:**
```json
{
  "status": 409,
  "message": "Ya existe un catálogo con el nombre: Laptop",
  "timestamp": "2026-04-29T10:42:00"
}
```

---

## Códigos HTTP Esperados

| Código | Operación | Significado |
|--------|-----------|------------|
| **200** | GET, PUT, PATCH | OK - Operación exitosa |
| **201** | POST | Created - Creado exitosamente |
| **204** | DELETE | No Content - Eliminado |
| **400** | Cualquiera | Bad Request - Validación fallida |
| **404** | GET, PUT, PATCH, DELETE | Not Found - No existe |
| **409** | POST, PUT | Conflict - Nombre duplicado |
| **500** | Cualquiera | Internal Server Error - Error del servidor |

---

## Resumen de Validación

✅ **SIEMPRE REQUIERE:**
- `nombre`: 3-100 caracteres
- `estado`: ACTIVO o INACTIVO (exactamente)
- `precio`: > 0

⚠️ **OPCIONALES:**
- `descripcion`: máximo 255 caracteres
- `categoria`: máximo 100 caracteres
- `cantidadDisponible`: >= 0

🚫 **NO PERMITE:**
- Nombre duplicado
- Estado distinto a ACTIVO/INACTIVO
- Precio negativo o cero
- Cantidad negativa
