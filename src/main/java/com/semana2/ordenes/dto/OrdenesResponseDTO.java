package com.semana2.ordenes.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrdenesResponseDTO {

    private String id;
    private String cliente;
    private String producto;
    private Integer cantidad;
    private Double precio;
    private String fecha;
    private String estado;
    
}
