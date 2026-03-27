package com.semana2.ordenes.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CrearOrdenRequestDTO {

    @NotBlank(message = "El nombre del cliente es obligatorio")
    private String cliente;

    @NotBlank(message = "El ingreso de producto es obligatorio")
    private String producto;

    @NotBlank(message = "Debe ingresar una cantidad")
    @Pattern(regexp = "^[1-9]\\d*$", message = "La cantidad debe ser un número mayor a 0")
    private int cantidad;

    @NotBlank(message = "El precio es obligatorio")
    @Pattern(regexp = "^[0-9]+(\\.[0-9]{1,2})?$", message = "El precio debe ser un número válido")
    private Double precio;

    @NotBlank(message = "Se debe ingresar una fecha para la compra")
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "Formato de fecha yyyy-MM-dd")
    private String fecha;
    
}
