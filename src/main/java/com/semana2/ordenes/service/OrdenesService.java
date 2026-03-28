package com.semana2.ordenes.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.semana2.ordenes.dto.IngresarOrdenRequestDTO;
import com.semana2.ordenes.dto.OrdenesResponseDTO;

@Service
public class OrdenesService {

    private final List<OrdenesResponseDTO> ordenes = new ArrayList<>();

    public OrdenesService() {
        
        ordenes.add(OrdenesResponseDTO.builder().id("1").cliente("Juan Pérez").producto("Shampoo en seco para perro").cantidad(1).precio(8990.0).fecha("2026-03-20").estado("PENDIENTE").build());
        ordenes.add(OrdenesResponseDTO.builder().id("2").cliente("María González").producto("Pipeta antipulgas gato").cantidad(2).precio(12990.0).fecha("2026-03-21").estado("ENVIADO").build());

        ordenes.add(OrdenesResponseDTO.builder().id("3").cliente("Carlos Rojas").producto("Peine para gato pelo largo").cantidad(1).precio(5990.0).fecha("2026-03-22").estado("ENTREGADO").build());
        ordenes.add(OrdenesResponseDTO.builder().id("4").cliente("Ana Soto").producto("Churu snack cuidado de pelo").cantidad(5).precio(3490.0).fecha("2026-03-22").estado("PENDIENTE").build());

        ordenes.add(OrdenesResponseDTO.builder().id("5").cliente("Pedro Martínez").producto("Cortaúñas para perro").cantidad(1).precio(7990.0).fecha("2026-03-23").estado("ENVIADO").build());
        ordenes.add(OrdenesResponseDTO.builder().id("6").cliente("Lucía Fernández").producto("Arena sanitaria para gato 10kg").cantidad(1).precio(10990.0).fecha("2026-03-23").estado("PENDIENTE").build());

        ordenes.add(OrdenesResponseDTO.builder().id("7").cliente("Diego Morales").producto("Cepillo deslanador para perro").cantidad(1).precio(15990.0).fecha("2026-03-24").estado("ENTREGADO").build());
        ordenes.add(OrdenesResponseDTO.builder().id("8").cliente("Camila Torres").producto("Spray desenredante para mascotas").cantidad(2).precio(8990.0).fecha("2026-03-24").estado("PENDIENTE").build());

        ordenes.add(OrdenesResponseDTO.builder().id("9").cliente("Juan Pérez").producto("Cepillo para perro").cantidad(1).precio(7990.0).fecha("2026-03-25").estado("PENDIENTE").build());
        ordenes.add(OrdenesResponseDTO.builder().id("10").cliente("María González").producto("Arena sanitaria perfumada").cantidad(2).precio(11990.0).fecha("2026-03-25").estado("ENVIADO").build());

        ordenes.add(OrdenesResponseDTO.builder().id("11").cliente("Carlos Rojas").producto("Rascador para gato").cantidad(1).precio(19990.0).fecha("2026-03-26").estado("ENTREGADO").build());
        ordenes.add(OrdenesResponseDTO.builder().id("12").cliente("Ana Soto").producto("Snacks dentales para perro").cantidad(3).precio(4990.0).fecha("2026-03-26").estado("PENDIENTE").build());

        ordenes.add(OrdenesResponseDTO.builder().id("13").cliente("Pedro Martínez").producto("Correa ajustable para perro").cantidad(1).precio(10990.0).fecha("2026-03-27").estado("ENVIADO").build());
        ordenes.add(OrdenesResponseDTO.builder().id("14").cliente("Lucía Fernández").producto("Juguete interactivo para gato").cantidad(2).precio(8990.0).fecha("2026-03-27").estado("PENDIENTE").build());

        ordenes.add(OrdenesResponseDTO.builder().id("15").cliente("Diego Morales").producto("Shampoo antipulgas perro").cantidad(1).precio(12990.0).fecha("2026-03-28").estado("ENTREGADO").build());
        ordenes.add(OrdenesResponseDTO.builder().id("16").cliente("Camila Torres").producto("Toallitas húmedas para mascotas").cantidad(4).precio(5990.0).fecha("2026-03-28").estado("PENDIENTE").build());

        ordenes.add(OrdenesResponseDTO.builder().id("17").cliente("Juan Pérez").producto("Alimento premium para perro 3kg").cantidad(1).precio(18990.0).fecha("2026-03-29").estado("ENVIADO").build());
        ordenes.add(OrdenesResponseDTO.builder().id("18").cliente("María González").producto("Fuente de agua para gato").cantidad(1).precio(24990.0).fecha("2026-03-29").estado("PENDIENTE").build());


    }

    public List<OrdenesResponseDTO> obtenerTodas() {

		return ordenes;
	}

    public OrdenesResponseDTO obtenerPorId(String id) {

		for (OrdenesResponseDTO orden : ordenes) {

			if (orden.getId().equals(id)) {

				return orden;
			}
		}
		return null;
	}

    public OrdenesResponseDTO crear(IngresarOrdenRequestDTO request) {

       int nuevoId = ordenes.size() + 1;

        OrdenesResponseDTO nuevaOrden = OrdenesResponseDTO.builder()
            .id(String.valueOf(nuevoId))
            .cliente(request.getCliente())
            .producto(request.getProducto())
            .cantidad(request.getCantidad())
            .precio(request.getPrecio())
            .fecha(request.getFecha())
            .estado("PENDIENTE")
            .build();

        ordenes.add(nuevaOrden);
        return nuevaOrden;
    }

    public String consultaEstado(String id) {

        for (OrdenesResponseDTO orden : ordenes) 
         {

             if (orden.getId().equals(id)) {
                 return String.format("El estado de la orden es: %s", orden.getEstado());
                
             }
        }

            return "El Id de orden no existe";
    }

    // public List<OrdenesResponseDTO> consultarOrdenFecha(String cliente, String fecha) {

	// 	List<OrdenesResponseDTO> encontrado = new ArrayList<>();

	// 	for (OrdenesResponseDTO orden : ordenes) {

	// 		boolean clienteBuscado = orden.getCliente().equalsIgnoreCase(cliente);
	// 		boolean fechaBuscada = orden.getFecha().equals(fecha);
			

	// 		if (clienteBuscado && fechaBuscada) {

	// 			encontrado.add(orden);
	// 		}
	// 	}

	// 	return encontrado;
	// }
    public List<OrdenesResponseDTO> buscar(String id, String cliente, String fecha) {

    List<OrdenesResponseDTO> resultado = new ArrayList<>();

    for (OrdenesResponseDTO orden : ordenes) {

        boolean coincide = true;

        if (id != null && !orden.getId().equals(id)) {
            coincide = false;
        }

        if (cliente != null && !orden.getCliente().equalsIgnoreCase(cliente)) {
            coincide = false;
        }

        if (fecha != null && !orden.getFecha().equals(fecha)) {
            coincide = false;
        }
        

        if (coincide) {
            resultado.add(orden);
        }
    }

    return resultado;
    }

// Actualizar estado de un pedido por id
//     public OrdenesResponseDTO actualizar(String id, String estado) {
    
//         for (OrdenesResponseDTO orden : ordenes) {
//         if (!orden.getId().equals(id)) {
            
//             if (estado.equalsIgnoreCase("PENDIENTE") || estado.equalsIgnoreCase("ENVIADO") || estado.equalsIgnoreCase("ENTREGADO")) {
//                 orden.setEstado(estado.toUpperCase());
//                 return orden;
//             }
            
            
//         }
//     }
//             return null;
// }

    public OrdenesResponseDTO actualizar(String id, String estado) {
    for (OrdenesResponseDTO orden : ordenes) {
        if (orden.getId().equals(id)) {
            if (estado.equalsIgnoreCase("PENDIENTE") || estado.equalsIgnoreCase("ENVIADO") || estado.equalsIgnoreCase("ENTREGADO")) {

                orden.setEstado(estado.toUpperCase());
                return orden;
            } else {
                // Estado inválido
                throw new IllegalArgumentException("Estado no permitido: " + estado);
            }
        }
    }
    // Si no se encontró el id
    return null;
}


}