package com.micro1.inventario.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CatalogoResponseDTO {
    private Long id;
    private String nombre;
    private String descripcion;
    private String estado;
    private String categoria;
    private Double precio;
    private Integer cantidadDisponible;
    private String createdAt;
    private String updatedAt;
}
