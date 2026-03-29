package com.semana2.ordenes.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


import org.springframework.stereotype.Service;

import com.semana2.ordenes.dto.IngresarOrdenRequestDTO;
import com.semana2.ordenes.dto.OrdenesResponseDTO;



@Service
public class OrdenesService {

    private final List<OrdenesResponseDTO> ordenes = new ArrayList<>();


    private final List<String> productosValidos = List.of(
        "Shampoo en seco para perro",
        "Pipeta antipulgas gato",
        "Peine para gato pelo largo",
        "Churu snack cuidado de pelo",
        "Cortaunas para perro",
        "Arena sanitaria para gato 10kg",
        "Cepillo deslanador para perro",
        "Spray desenredante para mascotas"
        );



    public OrdenesService() {
        
        ordenes.add(OrdenesResponseDTO.builder().id("1").cliente("Juan Perez").rutCliente("12.345.678-9").producto("Shampoo en seco para perro").cantidad(1).precio(8990.0).fechaHora("2026-03-20T10:00:00").estado("PENDIENTE").build());
        ordenes.add(OrdenesResponseDTO.builder().id("2").cliente("Maria Gonzalez").rutCliente("11.222.333-4").producto("Pipeta antipulgas gato").cantidad(2).precio(12990.0).fechaHora("2026-03-21T11:00:00").estado("ENVIADO").build());

        ordenes.add(OrdenesResponseDTO.builder().id("3").cliente("Carlos Rojas").rutCliente("22.333.444-5").producto("Peine para gato pelo largo").cantidad(1).precio(5990.0).fechaHora("2026-03-22T09:30:00").estado("ENTREGADO").build());
        ordenes.add(OrdenesResponseDTO.builder().id("4").cliente("Ana Soto").rutCliente("33.444.555-6").producto("Churu snack cuidado de pelo").cantidad(5).precio(3490.0).fechaHora("2026-03-22T10:45:00").estado("PENDIENTE").build());

        ordenes.add(OrdenesResponseDTO.builder().id("5").cliente("Pedro Martinez").rutCliente("44.555.666-7").producto("Cortaunas para perro").cantidad(1).precio(7990.0).fechaHora("2026-03-23T12:00:00").estado("ENVIADO").build());
        ordenes.add(OrdenesResponseDTO.builder().id("6").cliente("Lucia Fernandez").rutCliente("55.666.777-8").producto("Arena sanitaria para gato 10kg").cantidad(1).precio(10990.0).fechaHora("2026-03-23T13:15:00").estado("PENDIENTE").build());

        ordenes.add(OrdenesResponseDTO.builder().id("7").cliente("Diego Morales").rutCliente("66.777.888-9").producto("Cepillo deslanador para perro").cantidad(1).precio(15990.0).fechaHora("2026-03-24T14:30:00").estado("ENTREGADO").build());
        ordenes.add(OrdenesResponseDTO.builder().id("8").cliente("Camila Torres").rutCliente("77.888.999-0").producto("Spray desenredante para mascotas").cantidad(2).precio(8990.0).fechaHora("2026-03-24T15:00:00").estado("PENDIENTE").build());

        ordenes.add(OrdenesResponseDTO.builder().id("9").cliente("Juan Perez").rutCliente("12.345.678-9").producto("Shampoo en seco para perro").cantidad(1).precio(8990.0).fechaHora("2026-03-25T09:00:00").estado("PENDIENTE").build());
        ordenes.add(OrdenesResponseDTO.builder().id("10").cliente("Maria Gonzalez").rutCliente("11.222.333-4").producto("Pipeta antipulgas gato").cantidad(1).precio(12990.0).fechaHora("2026-03-25T10:15:00").estado("ENVIADO").build());

        ordenes.add(OrdenesResponseDTO.builder().id("11").cliente("Carlos Rojas").rutCliente("22.333.444-5").producto("Peine para gato pelo largo").cantidad(2).precio(5990.0).fechaHora("2026-03-26T11:30:00").estado("ENTREGADO").build());
        ordenes.add(OrdenesResponseDTO.builder().id("12").cliente("Ana Soto").rutCliente("33.444.555-6").producto("Churu snack cuidado de pelo").cantidad(3).precio(3490.0).fechaHora("2026-03-26T12:45:00").estado("PENDIENTE").build());

        ordenes.add(OrdenesResponseDTO.builder().id("13").cliente("Pedro Martinez").rutCliente("44.555.666-7").producto("Cortaunas para perro").cantidad(1).precio(7990.0).fechaHora("2026-03-27T14:00:00").estado("ENVIADO").build());
        ordenes.add(OrdenesResponseDTO.builder().id("14").cliente("Lucia Fernandez").rutCliente("55.666.777-8").producto("Arena sanitaria para gato 10kg").cantidad(2).precio(10990.0).fechaHora("2026-03-27T15:30:00").estado("PENDIENTE").build());

        ordenes.add(OrdenesResponseDTO.builder().id("15").cliente("Diego Morales").rutCliente("66.777.888-9").producto("Cepillo deslanador para perro").cantidad(1).precio(15990.0).fechaHora("2026-03-28T16:00:00").estado("ENTREGADO").build());
        ordenes.add(OrdenesResponseDTO.builder().id("16").cliente("Camila Torres").rutCliente("77.888.999-0").producto("Spray desenredante para mascotas").cantidad(1).precio(8990.0).fechaHora("2026-03-28T17:15:00").estado("PENDIENTE").build());

        ordenes.add(OrdenesResponseDTO.builder().id("17").cliente("Juan Perez").rutCliente("12.345.678-9").producto("Shampoo en seco para perro").cantidad(2).precio(8990.0).fechaHora("2026-03-29T09:30:00").estado("ENVIADO").build());
        ordenes.add(OrdenesResponseDTO.builder().id("18").cliente("Maria Gonzalez").rutCliente("11.222.333-4").producto("Pipeta antipulgas gato").cantidad(1).precio(12990.0).fechaHora("2026-03-29T10:45:00").estado("PENDIENTE").build());


    }



    public List<OrdenesResponseDTO> obtenerTodas() {

		return ordenes;
	}

    //Crear orden
    
    public OrdenesResponseDTO crear(IngresarOrdenRequestDTO request) {

 
    boolean productoValido = false;

    for (String producto : productosValidos) {
        if (producto.equalsIgnoreCase(request.getProducto())) {
            productoValido = true;
            break;
        }
    }

    if (!productoValido) {
        throw new RuntimeException("El producto no es valido");
    }

 
    if (request.getPrecio() == null || request.getPrecio() < 0) {
        throw new RuntimeException("El precio no puede ser negativo");
    }

    if (request.getPrecio() == null || request.getPrecio() <= 0) {
    throw new RuntimeException("El precio del producto debe ser mayor a 0");
    }

   
    LocalDateTime ahora = LocalDateTime.now();


    for (OrdenesResponseDTO orden : ordenes) {

        boolean mismoRut = orden.getRutCliente().equalsIgnoreCase(request.getRutCliente());
        boolean mismoProducto = orden.getProducto().equalsIgnoreCase(request.getProducto());

        if (mismoRut && mismoProducto) {

            LocalDateTime fechaOrden = LocalDateTime.parse(orden.getFechaHora());

            long minutos = java.time.Duration.between(fechaOrden, ahora).toMinutes();

            if (minutos < 5) {
                throw new RuntimeException("No se puede repetir la misma orden dentro de 5 minutos para el mismo cliente");
            }
        }
}

  
    int nuevoId = ordenes.size() + 1;

    String fechaHoraActual = ahora.toString(); // yyyy-MM-ddTHH:mm:ss

   
    OrdenesResponseDTO nuevaOrden = OrdenesResponseDTO.builder()
            .id(String.valueOf(nuevoId))
            .rutCliente(request.getRutCliente())
            .cliente(request.getCliente())
            .producto(request.getProducto())
            .cantidad(request.getCantidad())
            .precio(request.getPrecio())
            .fechaHora(fechaHoraActual)
            .estado("PENDIENTE")
            .build();

    ordenes.add(nuevaOrden);

    return nuevaOrden;
}


   
    public List<OrdenesResponseDTO> buscar(String id, String cliente, String fecha, String rutCliente) {
    List<OrdenesResponseDTO> resultado = new ArrayList<>();

        for (OrdenesResponseDTO orden : ordenes) {
            boolean coincide = true;

            if (id != null && !orden.getId().equals(id)) {
                coincide = false;
            }
            if (cliente != null && !orden.getCliente().equalsIgnoreCase(cliente)) {
                coincide = false;
            }
            if (fecha != null) {
                // Extraemos solo la fecha (YYYY-MM-DD) de fechaHora
                String soloFecha = orden.getFechaHora().split("T")[0];
                if (!soloFecha.equals(fecha)) {
                    coincide = false;
                }
            }
            if (rutCliente != null) {
                if (orden.getRutCliente() == null || !orden.getRutCliente().equalsIgnoreCase(rutCliente)) {
                    coincide = false;
                }
            }

            if (coincide) {
                resultado.add(orden);
            }
        }

        return resultado;
}

  

    public OrdenesResponseDTO actualizar(String id, String estado) {
    for (OrdenesResponseDTO orden : ordenes) {
        if (orden.getId().equals(id)) {
            if (estado.equalsIgnoreCase("PENDIENTE") || estado.equalsIgnoreCase("ENVIADO") || estado.equalsIgnoreCase("ENTREGADO")) {

                orden.setEstado(estado.toUpperCase());
                return orden;
            } else {
        
                throw new IllegalArgumentException("Estado no permitido: " + estado);
            }
        }
    }

    return null;
}


}