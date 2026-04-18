package com.semana2.ordenes.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IngresarOrdenRequestDTO {



    @NotBlank(message = "El nombre del producto es obligatorio")
    private String producto;

    @NotNull(message = "Debe ingresar una cantidad")
    @Min(value = 1, message = "La cantidad debe ser mayor a 0")
    private Integer cantidad;

    @NotBlank(message = "El RUT del cliente es obligatorio")
    @Pattern(regexp = "^\\d{1,2}\\.\\d{3}\\.\\d{3}-[0-9kK]$", message = "Formato de RUT inválido (Ej: 12.345.678-9)")
    private String rutCliente;

    @NotBlank(message = "La dirección de entrega es obligatoria")
    private String direccion;

}
