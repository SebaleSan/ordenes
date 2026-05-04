package com.semana2.ordenes.dto;

import org.springframework.hateoas.RepresentationModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ProductoResponseDTO extends RepresentationModel<ProductoResponseDTO> {
    
    private Long idProducto;
    private String nombre;
    private String descripcion;
    private Double precio;
    
}
