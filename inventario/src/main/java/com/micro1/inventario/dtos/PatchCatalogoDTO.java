package com.micro1.inventario.dtos;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PatchCatalogoDTO {
    @Size(min = 3, max = 100)
    private String nombre;

    @Size(max = 255)
    private String descripcion;

    @Pattern(regexp = "^(ACTIVO|INACTIVO)$", message = "El estado debe ser exactamente ACTIVO o INACTIVO")
    private String estado;

    @Size(max = 100)
    private String categoria;

    @DecimalMin(value = "0.0", inclusive = false)
    private Double precio;

    @Min(value = 0)
    private Integer cantidadDisponible;
}
