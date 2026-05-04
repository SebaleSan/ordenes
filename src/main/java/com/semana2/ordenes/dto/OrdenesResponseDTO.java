package com.semana2.ordenes.dto;

import org.springframework.hateoas.RepresentationModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Builder
public class OrdenesResponseDTO extends RepresentationModel<OrdenesResponseDTO> {

    private Long id;
    private String rutCliente;
    private String nombreProducto;
    private int cantidad;
    private Double precioProducto;
    private Double valorTotal;
    private String direccion;
    private String estado;

    
}
