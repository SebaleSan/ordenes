package com.semana2.ordenes.dto;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteResponseDTO {

    private Long idCliente;
    private String nombre;
    private String rut;

    
}
