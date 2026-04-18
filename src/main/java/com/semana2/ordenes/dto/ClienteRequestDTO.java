package com.semana2.ordenes.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteRequestDTO {

    @NotBlank(message = "El nombre del cliente es obligatorio")
    private String nombre;

    @NotBlank(message = "El apellido del cliente es obligatorio")
    private String apellido;


    @NotBlank(message = "El RUT del cliente es obligatorio")
    private String rut;

    
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$", message = "El correo del cliente debe ser un correo electrónico válido")
    @NotBlank(message = "El correo del cliente es obligatorio")
    private String correo;
}
