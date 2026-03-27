package com.semana2.ordenes.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.semana2.ordenes.dto.OrdenesResponseDTO;
import com.semana2.ordenes.service.OrdenesService;



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
//obtencion de orden por id

    @GetMapping("/{id}")
	public ResponseEntity<?> obtenerPorId(@PathVariable String id) {

		OrdenesResponseDTO orden = service.obtenerPorId(id);

		if (orden == null) {

			return ResponseEntity.status(404).body("No se ha encontrado la orden con ID: " + id);
		}

		return ResponseEntity.ok(orden);
	}

//consulta de estado

// 	@GetMapping("/estado")
// 	public ResponseEntity<String> consultaEstado(@RequestParam String id) {

// 		return ResponseEntity.ok(service.consultaEstado(id));
// 	}


// //consulta de estado por nombre cliente y fecha	
// 	@GetMapping("/estadocliente")
// 	public ResponseEntity<List<OrdenesResponseDTO>> consultarOrdenFecha(@RequestParam String cliente,
// 			@RequestParam String fecha) {

// 		return ResponseEntity.ok(service.consultarOrdenFecha(cliente, fecha));
// 	}
// 
@GetMapping("/buscar")
public ResponseEntity<List<OrdenesResponseDTO>> buscar(
        @RequestParam(required = false) String id,
        @RequestParam(required = false) String cliente,
        @RequestParam(required = false) String fecha) {

    return ResponseEntity.ok(service.buscar(id, cliente, fecha));
}


    
    
}
