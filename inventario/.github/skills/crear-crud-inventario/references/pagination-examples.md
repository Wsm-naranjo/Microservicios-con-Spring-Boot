# Ejemplos de Paginación - CRUD Catálogo

## Parámetros de Paginación

La paginación en Spring Data Pageable soporta estos parámetros:

| Parámetro | Descripción | Ejemplo |
|-----------|-------------|---------|
| `page` | Número de página (0-indexed) | `page=0` (primera página) |
| `size` | Elementos por página | `size=10` (10 elementos) |
| `sort` | Campo y dirección de ordenamiento | `sort=nombre,asc` o `sort=precio,desc` |

## Ejemplos de URLs

### 1. Listar con Paginación por Defecto
```
GET http://localhost:8080/api/v1/catalogo
```
- Retorna: Primera página con 10 items

### 2. Especificar Página y Tamaño
```
GET http://localhost:8080/api/v1/catalogo?page=0&size=5
```
- Página 0 (primera)
- 5 elementos por página

### 3. Segunda Página
```
GET http://localhost:8080/api/v1/catalogo?page=1&size=5
```
- Página 1 (segunda)
- 5 elementos por página

### 4. Ordenar por Nombre (Ascendente)
```
GET http://localhost:8080/api/v1/catalogo?page=0&size=10&sort=nombre,asc
```

### 5. Ordenar por Precio (Descendente)
```
GET http://localhost:8080/api/v1/catalogo?page=0&size=10&sort=precio,desc
```

### 6. Múltiples Ordenamientos
```
GET http://localhost:8080/api/v1/catalogo?page=0&size=10&sort=estado,asc&sort=nombre,asc
```
- Primero ordena por estado
- Luego por nombre

### 7. Búsqueda por Nombre + Paginación
```
GET http://localhost:8080/api/v1/catalogo/search/nombre?nombre=laptop&page=0&size=5
```

### 8. Filtrar por Estado + Paginación
```
GET http://localhost:8080/api/v1/catalogo/search/estado?estado=ACTIVO&page=0&size=10
```

### 9. Filtrar por Categoría + Paginación + Orden
```
GET http://localhost:8080/api/v1/catalogo/search/categoria?categoria=Electrónica&page=0&size=10&sort=precio,desc
```

## Ejemplos con CURL

### Crear algunos catálogos de prueba

```bash
# Crear Laptop
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

# Crear Mouse
curl -X POST http://localhost:8080/api/v1/catalogo \
  -H "Content-Type: application/json" \
  -d '{
    "nombre": "Mouse Logitech MX Master",
    "descripcion": "Mouse inalámbrico profesional",
    "estado": "ACTIVO",
    "categoria": "Accesorios",
    "precio": 99.99,
    "cantidadDisponible": 20
  }'

# Crear Teclado
curl -X POST http://localhost:8080/api/v1/catalogo \
  -H "Content-Type: application/json" \
  -d '{
    "nombre": "Teclado Mecánico Corsair",
    "descripcion": "Teclado mecánico RGB",
    "estado": "ACTIVO",
    "categoria": "Accesorios",
    "precio": 199.99,
    "cantidadDisponible": 15
  }'

# Crear Monitor
curl -X POST http://localhost:8080/api/v1/catalogo \
  -H "Content-Type: application/json" \
  -d '{
    "nombre": "Monitor LG 4K",
    "descripcion": "Monitor ultrawide 4K",
    "estado": "ACTIVO",
    "categoria": "Periféricos",
    "precio": 599.99,
    "cantidadDisponible": 8
  }'

# Crear Webcam (inactiva)
curl -X POST http://localhost:8080/api/v1/catalogo \
  -H "Content-Type: application/json" \
  -d '{
    "nombre": "Webcam Logitech C920",
    "descripcion": "Cámara web Full HD",
    "estado": "INACTIVO",
    "categoria": "Accesorios",
    "precio": 79.99,
    "cantidadDisponible": 0
  }'
```

### Listar con Paginación

```bash
# Primera página (10 elementos)
curl "http://localhost:8080/api/v1/catalogo?page=0&size=10"

# Segunda página, 2 elementos por página
curl "http://localhost:8080/api/v1/catalogo?page=1&size=2"

# Ordenar por nombre ascendente
curl "http://localhost:8080/api/v1/catalogo?page=0&size=10&sort=nombre,asc"

# Ordenar por precio descendente
curl "http://localhost:8080/api/v1/catalogo?page=0&size=10&sort=precio,desc"
```

### Búsqueda con Paginación

```bash
# Buscar "Laptop" - Primera página
curl "http://localhost:8080/api/v1/catalogo/search/nombre?nombre=Laptop&page=0&size=5"

# Buscar productos ACTIVOS - Segunda página, 3 por página
curl "http://localhost:8080/api/v1/catalogo/search/estado?estado=ACTIVO&page=1&size=3"

# Buscar categoría "Accesorios" - Ordenar por precio
curl "http://localhost:8080/api/v1/catalogo/search/categoria?categoria=Accesorios&page=0&size=10&sort=precio,asc"
```

## Ejemplo de Respuesta con Paginación

```json
{
  "content": [
    {
      "id": 1,
      "nombre": "Laptop ASUS ROG",
      "descripcion": "Laptop gaming de alto rendimiento",
      "estado": "ACTIVO",
      "categoria": "Electrónica",
      "precio": 2499.99,
      "cantidadDisponible": 5,
      "createdAt": "2026-04-29T10:30:45.123456",
      "updatedAt": "2026-04-29T10:30:45.123456"
    },
    {
      "id": 2,
      "nombre": "Mouse Logitech MX Master",
      "descripcion": "Mouse inalámbrico profesional",
      "estado": "ACTIVO",
      "categoria": "Accesorios",
      "precio": 99.99,
      "cantidadDisponible": 20,
      "createdAt": "2026-04-29T10:35:12.654321",
      "updatedAt": "2026-04-29T10:35:12.654321"
    }
  ],
  "pageable": {
    "pageNumber": 0,
    "pageSize": 2,
    "sort": {
      "empty": false,
      "sorted": true,
      "unsorted": false
    },
    "offset": 0,
    "paged": true,
    "unpaged": false
  },
  "totalElements": 5,
  "totalPages": 3,
  "last": false,
  "size": 2,
  "number": 0,
  "sort": {
    "empty": false,
    "sorted": true,
    "unsorted": false
  },
  "numberOfElements": 2,
  "first": true,
  "empty": false
}
```

### Interpretación de la Respuesta

| Campo | Valor | Significado |
|-------|-------|------------|
| `content` | Array | Items en la página actual |
| `totalElements` | 5 | Total de items en BD |
| `totalPages` | 3 | Total de páginas (5 items ÷ 2 por página = 3 páginas) |
| `number` | 0 | Página actual (0-indexed) |
| `size` | 2 | Items por página |
| `numberOfElements` | 2 | Items en esta página |
| `first` | true | Es la primera página |
| `last` | false | No es la última página |
| `empty` | false | Hay datos |

## Cálculos Importantes

Con 5 elementos totales y 2 por página:

| Página | URL | Items | Rango |
|--------|-----|-------|-------|
| 0 | `?page=0&size=2` | 2 | 1-2 |
| 1 | `?page=1&size=2` | 2 | 3-4 |
| 2 | `?page=2&size=2` | 1 | 5 |

## Casos de Uso Comunes

### Mostrar 20 productos por página
```
?page=0&size=20
```

### Siguiente página
```
?page=1&size=20  (si estabas en page=0)
```

### Página anterior
```
?page=0&size=20  (si estabas en page=1)
```

### Ordenar por precio (más barato primero)
```
?page=0&size=20&sort=precio,asc
```

### Ordenar por precio (más caro primero)
```
?page=0&size=20&sort=precio,desc
```

### Buscar + Paginar + Ordenar
```
/search/nombre?nombre=laptop&page=0&size=10&sort=precio,asc
```

## En Postman/Thunder Client

1. **Base URL**: `http://localhost:8080/api/v1/catalogo`

2. **Params**:
   ```
   Key        | Value
   -----------+--------
   page       | 0
   size       | 10
   sort       | nombre,asc
   ```

3. **Enviar**: GET request

## En JavaScript/Frontend

```javascript
// Página 1, 10 items, ordenar por nombre
const page = 0;
const size = 10;
const sort = 'nombre,asc';

const url = `http://localhost:8080/api/v1/catalogo?page=${page}&size=${size}&sort=${sort}`;

fetch(url)
  .then(res => res.json())
  .then(data => {
    console.log(`Total: ${data.totalElements} items`);
    console.log(`Página ${data.number + 1} de ${data.totalPages}`);
    console.log('Items:', data.content);
  });
```

## Ejemplo Completo: Implementar Botones de Navegación

```javascript
let currentPage = 0;
const pageSize = 10;

// Obtener página actual
async function loadPage(pageNumber) {
  const response = await fetch(
    `http://localhost:8080/api/v1/catalogo?page=${pageNumber}&size=${pageSize}&sort=nombre,asc`
  );
  const data = await response.json();
  
  currentPage = pageNumber;
  displayItems(data.content);
  updatePaginationButtons(data);
}

function displayItems(items) {
  items.forEach(item => {
    console.log(`${item.id}. ${item.nombre} - $${item.precio}`);
  });
}

function updatePaginationButtons(data) {
  console.log(`Página ${data.number + 1} de ${data.totalPages}`);
  
  // Habilitar/deshabilitar botón anterior
  document.getElementById('prevBtn').disabled = data.first;
  
  // Habilitar/deshabilitar botón siguiente
  document.getElementById('nextBtn').disabled = data.last;
}

// Event listeners
document.getElementById('prevBtn').onclick = () => loadPage(currentPage - 1);
document.getElementById('nextBtn').onclick = () => loadPage(currentPage + 1);

// Cargar primera página al iniciar
loadPage(0);
```
