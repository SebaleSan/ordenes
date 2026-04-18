package com.semana2.ordenes.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductoResponseDTO {
    
    private Long idProducto;
    private String nombre;
    private String descripcion;
    private Double precio;
    
}
