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
    private String rutCliente;
    private String cliente;
    private String producto;
    private int cantidad;
    private Double precio;
    private String fechaHora;
    private String estado;

    
}
