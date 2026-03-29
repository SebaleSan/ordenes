package com.semana2.ordenes.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.semana2.ordenes.dto.IngresarOrdenRequestDTO;
import com.semana2.ordenes.dto.OrdenesResponseDTO;
import com.semana2.ordenes.service.OrdenesService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;




@RestController
@RequestMapping("/ordenes")
public class OrdenesController {

	//inyeccion de dependencias
    private final OrdenesService service;

    public OrdenesController(OrdenesService service) {

		this.service = service;
	}
//listar todas las ordenes

    @GetMapping
    public ResponseEntity<List<OrdenesResponseDTO>> obtenerTodas() {

		return ResponseEntity.ok(service.obtenerTodas());
	}






//buscar una orden por el id, nombre, fecha, rut	
	@GetMapping("/buscar")
	public ResponseEntity<?> buscar(
        @RequestParam(required = false) String id,
        @RequestParam(required = false) String cliente,
        @RequestParam(required = false) String fecha,
		@RequestParam(required = false) String rutCliente) {

    if (id == null && cliente == null && fecha == null && rutCliente == null) {
        return ResponseEntity.badRequest().body("Debe enviar al menos un parámetro");
    }

    List<OrdenesResponseDTO> resultado = service.buscar(id, cliente, fecha, rutCliente);

    if (resultado.isEmpty()) {
        return ResponseEntity.status(404)
                .body("No se encontraron órdenes con los datos ingresados");
    }

    return ResponseEntity.ok(resultado);
	}

	// Creacion de orden de compra
	@PostMapping()
	public ResponseEntity<?> ingresaOrden(@Valid @RequestBody IngresarOrdenRequestDTO request) {

		OrdenesResponseDTO ingresa = service.crear(request);

		return ResponseEntity.ok(ingresa);

	}

	
	@PutMapping("/{id}/actualizar")
	public ResponseEntity<?> actualizar(@PathVariable String id, @RequestParam String estado) {
    try {
        OrdenesResponseDTO ordenActualizar = service.actualizar(id, estado);

        if (ordenActualizar == null) {
            return ResponseEntity.status(404).body("No se encontró la reserva con id: " + id);
        }

        return ResponseEntity.ok(ordenActualizar);
    } catch (IllegalArgumentException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}







    
    
}
