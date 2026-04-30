# 🚀 Microservicios con Spring Boot
## Examen Práctico: Arquitectura de Capas y Generación Asistida con IA

**Autor:** Sebastian Naranjo  
**Fecha:** 2026  
**Tecnología:** Spring Boot, Gradle, Java

---

## 📋 Descripción del Proyecto

Este proyecto consiste en la implementación de **dos microservicios independientes** utilizando Spring Boot, como parte de un examen práctico que evalúa tanto el desarrollo manual como la utilización de herramientas de IA (skills) para la generación de código.

### 🎯 Objetivos del Examen
- ✅ Desarrollar microservicios con arquitectura por capas
- ✅ Implementar APIs REST completas
- ✅ Consumir y generar código con IA (GitHub Copilot Skills)
- ✅ Aplicar inyección de dependencias
- ✅ Manejar JSON y buenas prácticas de código
- ⭐ (Opcional) Integración con Eureka

---

## 📦 Microservicios Implementados

### 1️⃣ **Microservicio GESTION** (Desarrollo Manual - Mayor Peso)
**Ubicación:** `./gestion/`

#### Características:
- ✅ **Desarrollo:** 100% manual (sin IA)
- ✅ **Arquitectura por Capas Completa:**
  - `controllers/` - Capa de presentación
  - `services/` - Lógica de negocio
  - `repositories/` - Acceso a datos
  - `entities/` - Modelos de datos
  - `dto/` - Objetos de transferencia (opcional)
  - `exceptions/` - Manejo de excepciones (opcional)

#### Endpoints Implementados:
| Método | Endpoint | Descripción |
|--------|----------|-------------|
| `GET` | `/gestion` | Listar todos los recursos |
| `GET` | `/gestion/{id}` | Obtener por ID |
| `GET` | `/gestion/search` | Buscar por nombre/email |
| `POST` | `/gestion` | Crear nuevo recurso |
| `PUT` | `/gestion/{id}` | Actualizar recurso completo |
| `PATCH` | `/gestion/{id}` | Actualizar parcialmente |
| `DELETE` | `/gestion/{id}` | Eliminar recurso |

#### Características Técnicas:
- ✅ Inyección de dependencias
- ✅ Manejo de JSON
- ✅ Paginación implementada
- ✅ Nombres claros y buenas prácticas
- ✅ Compilación y ejecución correcta

#### Ejecución:
```bash
cd gestion
./gradlew build
./gradlew bootRun
```

Puerto: `http://localhost:8080`

---

### 2️⃣ **Microservicio INVENTARIO** (Generado con IA - GitHub Copilot Skills)
**Ubicación:** `./inventario/`

#### Características:
- ✅ **Desarrollo:** Asistido con GitHub Copilot Skills
- ✅ **Arquitectura por Capas Completa:**
  - Controllers, Services, Repositories, Entities
  - Implementación equivalente a GESTION

#### Endpoints Implementados:
| Método | Endpoint | Descripción |
|--------|----------|-------------|
| `GET` | `/inventario` | Listar todos los recursos |
| `GET` | `/inventario/{id}` | Obtener por ID |
| `GET` | `/inventario/search` | Buscar por nombre/email |
| `POST` | `/inventario` | Crear nuevo recurso |
| `PUT` | `/inventario/{id}` | Actualizar recurso |
| `PATCH` | `/inventario/{id}` | Actualizar parcialmente |
| `DELETE` | `/inventario/{id}` | Eliminar recurso |

#### Características Técnicas:
- ✅ Mismo patrón arquitectónico que GESTION
- ✅ API REST completa
- ✅ Paginación
- ✅ Inyección de dependencias
- ✅ Compilación y ejecución correcta

#### Ejecución:
```bash
cd inventario
./gradlew build
./gradlew bootRun
```

Puerto: `http://localhost:8081`

---

## 🤖 Evidencia del Uso de Skills (IA)

### Skill Utilizado: GitHub Copilot
**Herramienta:** GitHub Copilot / Cursor IDE  
**Método:** Prompts estructurados para generación de microservicio

### Prompt Utilizado:
```
Genera un microservicio Spring Boot completo llamado "Inventario" con:
- Arquitectura por capas (Controller, Service, ServiceImpl, Repository, Entity)
- API REST con endpoints: GET (listar), GET by ID, GET by name/email, POST, PUT, DELETE, PATCH
- Paginación implementada
- Inyección de dependencias
- Manejo de JSON
- Nombres claros y buenas prácticas
- Base de datos H2 o en memoria
```

### Resultado:
El código generado:
- ✅ Compiló sin errores
- ✅ Siguió las convenciones de Spring Boot
- ✅ Implementó todos los requisitos del prompt
- ✅ Fue revisado y validado manualmente
- ✅ Demuestra aplicación práctica de herramientas de IA en desarrollo

---

## ⚙️ Requisitos Técnicos Generales (Cumplidos)

✅ **Ambos microservicios implementan:**

| Requisito | Gestion | Inventario |
|-----------|---------|-----------|
| Arquitectura por Capas | ✅ | ✅ |
| Manejo de JSON | ✅ | ✅ |
| Al menos una entidad | ✅ | ✅ |
| Inyección de Dependencias | ✅ | ✅ |
| Nombres claros | ✅ | ✅ |
| Buenas prácticas | ✅ | ✅ |
| API REST Completa | ✅ | ✅ |
| Paginación | ✅ | ✅ |
| Compilación | ✅ | ✅ |
| Ejecución | ✅ | ✅ |

---

## 📋 Estructura del Proyecto

```
Microservicios-con-Spring-Boot/
├── gestion/                    (Microservicio Manual)
│   ├── src/main/java/com/micro2/gestion/
│   │   ├── controllers/
│   │   ├── services/
│   │   ├── repositories/
│   │   ├── entities/
│   │   └── GestionApplication.java
│   ├── src/main/resources/
│   │   └── application.properties
│   ├── build.gradle
│   └── gradlew
│
├── inventario/                 (Microservicio con IA)
│   ├── src/main/java/com/micro1/inventario/
│   │   ├── controllers/
│   │   ├── services/
│   │   ├── repositories/
│   │   ├── entities/
│   │   └── InventarioApplication.java
│   ├── src/main/resources/
│   │   └── application.properties
│   ├── build.gradle
│   └── gradlew
│
└── README.md
```

---

## 🚀 Cómo Ejecutar el Proyecto

### Requisitos Previos:
- ☕ Java 11 o superior
- 🔧 Gradle 7.x o superior
- 📝 Git

### Pasos:

#### Opción 1: Ejecutar ambos microservicios en paralelo

**Terminal 1 - Microservicio GESTION:**
```bash
cd gestion
./gradlew bootRun
# Disponible en http://localhost:8080
```

**Terminal 2 - Microservicio INVENTARIO:**
```bash
cd inventario
./gradlew bootRun
# Disponible en http://localhost:8081
```

#### Opción 2: Build y ejecución

```bash
# Gestion
cd gestion
./gradlew build
java -jar build/libs/gestion-*.jar

# Inventario (en otra terminal)
cd inventario
./gradlew build
java -jar build/libs/inventario-*.jar
```

---

## 🧪 Pruebas con Postman

### Ejemplos de Endpoints:

**GESTION:**
```
GET    http://localhost:8080/gestion
GET    http://localhost:8080/gestion/1
GET    http://localhost:8080/gestion/search?name=Juan
POST   http://localhost:8080/gestion
PUT    http://localhost:8080/gestion/1
DELETE http://localhost:8080/gestion/1
```

**INVENTARIO:**
```
GET    http://localhost:8081/inventario
GET    http://localhost:8081/inventario/1
GET    http://localhost:8081/inventario/search?name=Producto
POST   http://localhost:8081/inventario
PUT    http://localhost:8081/inventario/1
DELETE http://localhost:8081/inventario/1
```

---

## ⭐ Puntos Extra (Opcional)

### Integración con Eureka
Para registrar los microservicios en Netflix Eureka (próxima fase):
1. Configurar servidor Eureka
2. Agregar dependencia `spring-cloud-starter-netflix-eureka-client`
3. Actualizar `application.properties` con URL de Eureka
4. Verificar visualización en dashboard

---

## 📝 Análisis de Implementación

### Microservicio GESTION (Desarrollo Manual):
**Peso en evaluación:** 🔴🔴🔴 (Mayor)

**Ventajas:**
- Control total sobre el código
- Comprensión profunda de cada componente
- Implementación de lógica personalizada
- Mejor adaptabilidad a requisitos específicos

**Desafíos resueltos:**
- Arquitectura correcta por capas
- Manejo de excepciones
- Validación de datos
- Inyección de dependencias

---

### Microservicio INVENTARIO (Asistido con IA):
**Peso en evaluación:** 🔵 (Complementario)

**Ventajas:**
- Generación rápida de código base
- Reducción de tiempo de desarrollo
- Seguimiento de patrones de Spring Boot
- Demostración de uso de herramientas modernas

**Evidencia del proceso:**
- Prompt bien estructurado
- Código generado validado
- Compilación sin errores
- Equivalencia funcional con GESTION

---

## 📚 Conceptos Aplicados

- **Arquitectura por Capas:** Separación clara de responsabilidades
- **REST APIs:** Implementación de endpoints RESTful
- **Spring Boot:** Framework de desarrollo rápido
- **Inyección de Dependencias:** Patrón de Inversión de Control (IoC)
- **JSON:** Serialización/deserialización de datos
- **Paginación:** Manejo eficiente de grandes conjuntos de datos
- **Herramientas de IA:** Asistencia en generación de código

---

## 📞 Información del Autor

**Nombre:** Sebastian Naranjo  
**Proyecto:** Microservicios con Spring Boot  
**Tipo:** Examen Práctico  
**Año:** 2026

---

## 📄 Licencia

Este proyecto es parte de un examen educativo.

---

**Última actualización:** Abril 2026