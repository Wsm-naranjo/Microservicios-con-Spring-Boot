# Manejo Global de Errores en APIs

## GlobalExceptionHandler (Recomendado)

Crear un manejador global de excepciones en `src/main/java/com/micro1/inventario/exceptions/GlobalExceptionHandler.java`:

```java
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleEntityNotFound(EntityNotFoundException ex) {
        ErrorResponse error = ErrorResponse.builder()
                .status(HttpStatus.NOT_FOUND.value())
                .message(ex.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(DuplicateEntityException.class)
    public ResponseEntity<ErrorResponse> handleDuplicate(DuplicateEntityException ex) {
        ErrorResponse error = ErrorResponse.builder()
                .status(HttpStatus.CONFLICT.value())
                .message(ex.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationError(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .toList();
        
        ErrorResponse error = ErrorResponse.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .message("Errores de validación")
                .details(errors)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(Exception ex) {
        log.error("Error no manejado", ex);
        ErrorResponse error = ErrorResponse.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message("Error interno del servidor")
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}
```

## ErrorResponse DTO

Crear respuesta uniforme en `src/main/java/com/micro1/inventario/dtos/ErrorResponse.java`:

```java
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorResponse {
    private int status;
    private String message;
    private List<String> details;
    private LocalDateTime timestamp;
}
```

## HTTP Status Codes

| Método | Código | Descripción |
|--------|--------|------------|
| GET    | 200    | OK - Lista o entidad obtenida |
| POST   | 201    | Created - Entidad creada |
| PUT    | 200    | OK - Actualización exitosa |
| PATCH  | 200    | OK - Actualización parcial |
| DELETE | 204    | No Content - Eliminación exitosa |
| Error  | 400    | Bad Request - Validación fallida |
| Error  | 404    | Not Found - Entidad no existe |
| Error  | 409    | Conflict - Duplicado |
| Error  | 500    | Internal Server Error |

## Configuración application.properties

```properties
# Base de datos H2 o MySQL
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=false
spring.jpa.properties.hibernate.format_sql=true

# Validación
spring.mvc.throw-exception-if-no-handler-found=true
spring.web.resources.add-mappings=false
```
