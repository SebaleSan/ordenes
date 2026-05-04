package com.semana2.ordenes.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import org.springframework.stereotype.Service;

import com.semana2.ordenes.dto.IngresarOrdenRequestDTO;
import com.semana2.ordenes.dto.OrdenesResponseDTO;
import com.semana2.ordenes.entity.ClienteEntity;
import com.semana2.ordenes.entity.OrdenEntity;
import com.semana2.ordenes.repository.ClienteRepository;
import com.semana2.ordenes.repository.OrdenRepository;
import com.semana2.ordenes.repository.ProductoRepository;



@Service
public class OrdenesService {

    private final OrdenRepository ordenRepository;
    private final ClienteRepository clienteRepository;
    private final ProductoRepository productoRepository;

    public OrdenesService(OrdenRepository ordenRepository, ClienteRepository clienteRepository, ProductoRepository productoRepository) {
        this.ordenRepository = ordenRepository;
        this.clienteRepository = clienteRepository;
        this.productoRepository = productoRepository;
    }



    private OrdenesResponseDTO toDTO(OrdenEntity orden) {
        return OrdenesResponseDTO.builder()
                .id(Long.valueOf(orden.getIdOrden()))
                .rutCliente(orden.getCliente().getRutCliente())
                .nombreProducto(orden.getProducto().getNombreProducto())
                .cantidad(orden.getCantidad())
                .precioProducto(orden.getProducto().getPrecio())
                .valorTotal(orden.getPrecioTotal())
                .direccion(orden.getDireccion())
                .estado(orden.getEstado())
                .build();
    }

    //Obtener todas las ordenes
    public List<OrdenesResponseDTO> obtenerTodas() {
    
        return ordenRepository.findAll().stream()
                .map(this::toDTO)
                .toList();
    }



    //Crear orden
    
    public OrdenesResponseDTO crear(IngresarOrdenRequestDTO request) {
        
        // 1. Guardar o recuperar cliente por RUT
        var clientes = clienteRepository.findByRutCliente(request.getRutCliente());
        ClienteEntity cliente;
        if (clientes.isEmpty()) {
            cliente = new ClienteEntity();
            cliente.setRutCliente(request.getRutCliente());
            cliente.setNombre(request.getNombreCliente());
            cliente.setApellido(request.getApellidoCliente());
            cliente.setCorreo(request.getCorreoCliente());
        } else {
            cliente = clientes.get(0);
        }
        cliente = clienteRepository.save(cliente);

        // 2. Buscar producto por nombre (case-insensitive)
        String nombreProducto = request.getProducto().trim();
        var productos = productoRepository.findByNombreProductoIgnoreCase(nombreProducto);
        if (productos.isEmpty()) {
            productos = productoRepository.findByNombreProductoContainingIgnoreCase(nombreProducto);
        }
        if (productos.isEmpty()) {
            throw new RuntimeException("Producto no encontrado: " + request.getProducto());
        }
        var producto = productos.get(0);
        
        // 3. Validar que hay stock disponible
        if (producto.getStock() < request.getCantidad()) {
            throw new RuntimeException("Stock insuficiente. Disponible: " + producto.getStock());
        }
        
        // 4. Validar que no haya una orden duplicada en los últimos 5 minutos
        LocalDateTime hace5Minutos = LocalDateTime.now().minusMinutes(5);
        var ordenReciente = ordenRepository.findByClienteAndProductoAndFechaOrdenAfter(cliente, producto, hace5Minutos);
        
        if (!ordenReciente.isEmpty()) {
            throw new RuntimeException("No se puede repetir la misma orden dentro de 5 minutos para el mismo cliente");
        }
        
        // 5. Crear la nueva orden
        OrdenEntity nuevaOrden = new OrdenEntity();
        nuevaOrden.setCliente(cliente);
        nuevaOrden.setProducto(producto);
        nuevaOrden.setCantidad(request.getCantidad());
        nuevaOrden.setPrecioTotal((producto.getPrecio() * request.getCantidad()) + 3000); // Precio productos + costo envío
        nuevaOrden.setDireccion(request.getDireccion());
        nuevaOrden.setEstado("PENDIENTE");
        
        // 6. Guardar en base de datos
        OrdenEntity ordenGuardada = ordenRepository.save(nuevaOrden);
        
        // 7. Convertir a DTO y retornar
        return toDTO(ordenGuardada);
    }


   
    public List<OrdenesResponseDTO> buscar(Long id, String cliente, String fecha, String rutCliente) {
        List<OrdenEntity> ordenes = ordenRepository.findAll();
        List<OrdenesResponseDTO> resultado = new ArrayList<>();

        for (OrdenEntity orden : ordenes) {
            boolean coincide = true;

            if (id != null && !String.valueOf(orden.getIdOrden()).equals(id)) {
                coincide = false;
            }
            if (cliente != null && !orden.getCliente().getNombre().equalsIgnoreCase(cliente)) {
                coincide = false;
            }
            if (fecha != null) {
                // Extraemos solo la fecha (YYYY-MM-DD) de fechaOrden
                String soloFecha = orden.getFechaOrden().toLocalDate().toString();
                if (!soloFecha.equals(fecha)) {
                    coincide = false;
                }
            }
            if (rutCliente != null) {
                if (orden.getCliente().getRutCliente() == null || !orden.getCliente().getRutCliente().equalsIgnoreCase(rutCliente)) {
                    coincide = false;
                }
            }

            if (coincide) {
                resultado.add(toDTO(orden));
            }
        }

        return resultado;
    }

  

    public OrdenesResponseDTO actualizar(Long id, String estado) {
        // Buscar la orden por ID
        Optional<OrdenEntity> ordenOptional = ordenRepository.findById(Long.valueOf(id));

        if (ordenOptional.isPresent()) {
            OrdenEntity orden = ordenOptional.get();

            // Validar que el estado sea válido
            if (estado.equalsIgnoreCase("PENDIENTE") || estado.equalsIgnoreCase("ENVIADO") || estado.equalsIgnoreCase("ENTREGADO")) {
                orden.setEstado(estado.toUpperCase());

                // Guardar los cambios
                OrdenEntity ordenActualizada = ordenRepository.save(orden);

                return toDTO(ordenActualizada);
            } else {
                throw new IllegalArgumentException("Estado no permitido: " + estado);
            }
        }

        return null; // Orden no encontrada
    }


}