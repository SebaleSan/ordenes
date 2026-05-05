package com.semana2.ordenes.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.semana2.ordenes.dto.IngresarOrdenRequestDTO;
import com.semana2.ordenes.dto.OrdenesResponseDTO;
import com.semana2.ordenes.entity.ClienteEntity;
import com.semana2.ordenes.entity.OrdenEntity;
import com.semana2.ordenes.entity.ProductoEntity;
import com.semana2.ordenes.repository.ClienteRepository;
import com.semana2.ordenes.repository.OrdenRepository;
import com.semana2.ordenes.repository.ProductoRepository;

@ExtendWith(MockitoExtension.class)
@DisplayName("Pruebas unitarias para OrdenesService")
class OrdenesServiceTest{

    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private OrdenRepository ordenRepository;
    
    @Mock
    private ProductoRepository productoRepository;


    @InjectMocks
    private OrdenesService ordenesService;

    private OrdenEntity ordenEntity;
    private ClienteEntity clienteEntity;
    private ProductoEntity productoEntity;
    private IngresarOrdenRequestDTO ingresarOrdenRequestDTO;


    @BeforeEach
    void setUp() {

        clienteEntity = new ClienteEntity();
        clienteEntity.setIdCliente(1L);
        clienteEntity.setRutCliente("12345678-9");
        clienteEntity.setNombre("Juan");
        clienteEntity.setApellido("Pérez");

        productoEntity = new ProductoEntity();
        productoEntity.setIdProducto(1L);
        productoEntity.setNombreProducto("Producto 1");
        productoEntity.setPrecio(50.0);
        productoEntity.setStock(10);
        productoEntity.setDescripcion("Descripción del producto 1");


        ordenEntity = new OrdenEntity();
        ordenEntity.setIdOrden(1L);
        ordenEntity.setFechaOrden(LocalDateTime.of(2026, 4, 1, 10, 15, 0, 0));
        ordenEntity.setCantidad(2);
        ordenEntity.setDireccion("Calle Falsa 123");
        ordenEntity.setPrecioTotal(100.0);
        ordenEntity.setEstado("PENDIENTE");
        ordenEntity.setCliente(clienteEntity);
        ordenEntity.setProducto(productoEntity);


        ingresarOrdenRequestDTO = new IngresarOrdenRequestDTO();
        ingresarOrdenRequestDTO.setProducto("Producto 1");
        ingresarOrdenRequestDTO.setCantidad(2);
        ingresarOrdenRequestDTO.setDireccion("Calle Falsa 123");
        ingresarOrdenRequestDTO.setRutCliente("12345678-9");
        ingresarOrdenRequestDTO.setNombreCliente("Juan");
        ingresarOrdenRequestDTO.setApellidoCliente("Pérez");
        ingresarOrdenRequestDTO.setCorreoCliente("juan.perez@example.com");
        

    }


    @AfterEach
    void tearDown() {
        clienteEntity = null;
        productoEntity = null;
        ordenEntity = null;
        ingresarOrdenRequestDTO = null;
    }


    @Test
    @DisplayName("Deberia crar una orden")
        void crearOrden() {
            when(productoRepository.findByNombreProductoIgnoreCase("Producto 1")).thenReturn(List.of(productoEntity));
            when(clienteRepository.findByRutCliente("12345678-9")).thenReturn(List.of(clienteEntity));
            when(ordenRepository.save(any(OrdenEntity.class))).thenReturn(ordenEntity);

            OrdenesResponseDTO ordenCreada = ordenesService.crear(ingresarOrdenRequestDTO);

              assertNotNull(ordenCreada);
              assertEquals(1L, ordenCreada.getId());
              assertEquals("12345678-9", ordenCreada.getRutCliente());
              assertEquals("Producto 1", ordenCreada.getNombreProducto());
              assertEquals(2, ordenCreada.getCantidad());
              assertEquals(50.0, ordenCreada.getPrecioProducto());
              assertEquals(100.0, ordenCreada.getValorTotal());
              assertEquals("Calle Falsa 123", ordenCreada.getDireccion());
              assertEquals("PENDIENTE", ordenCreada.getEstado());
              verify(ordenRepository, times(1)).save(any(OrdenEntity.class));
              
        }

    @Test
    @DisplayName("Deberia buscar orden")
    void buscarOrden() {
        when(ordenRepository.findAll()).thenReturn(List.of(ordenEntity));
        List<OrdenesResponseDTO> resultado = ordenesService.buscar(null, null, null, null);
          assertEquals(1, resultado.size());
          assertEquals(1L, resultado.get(0).getId());
          assertEquals("12345678-9", resultado.get(0).getRutCliente());
          assertEquals("Producto 1", resultado.get(0).getNombreProducto());
          assertEquals(2, resultado.get(0).getCantidad());
          assertEquals(50.0, resultado.get(0).getPrecioProducto());
          assertEquals(100.0, resultado.get(0).getValorTotal());
          assertEquals("Calle Falsa 123", resultado.get(0).getDireccion());
          assertEquals("PENDIENTE", resultado.get(0).getEstado());
    }


    @Test
    @DisplayName("Deberia obtener todas las ordenes")
    void obtenerTodasLasOrdenes() {
        when(ordenRepository.findAll()).thenReturn(List.of(ordenEntity));
        List<OrdenesResponseDTO> resultado = ordenesService.obtenerTodas();
          assertEquals(1, resultado.size());
          assertEquals(1L, resultado.get(0).getId());
          assertEquals("12345678-9", resultado.get(0).getRutCliente());
          assertEquals("Producto 1", resultado.get(0).getNombreProducto());
          assertEquals(2, resultado.get(0).getCantidad());
          assertEquals(50.0, resultado.get(0).getPrecioProducto());
          assertEquals(100.0, resultado.get(0).getValorTotal());
          assertEquals("Calle Falsa 123", resultado.get(0).getDireccion());
          assertEquals("PENDIENTE", resultado.get(0).getEstado());
    }


    @Test
    @DisplayName("Debería filtrar ordenes por id, cliente, fecha y rutCliente")
    void deberiaFiltrarOrdenesCorrectamente() {
        ClienteEntity cliente = new ClienteEntity();
        cliente.setRutCliente("12345678-9");
        cliente.setNombre("Juan");

        ProductoEntity producto = new ProductoEntity();
        producto.setNombreProducto("Producto 1");
        producto.setPrecio(50.0);

        OrdenEntity orden = new OrdenEntity();
        orden.setIdOrden(1L);
        orden.setFechaOrden(LocalDateTime.of(2026, 4, 1, 10, 15));
        orden.setCantidad(2);
        orden.setDireccion("Calle Falsa 123");
        orden.setPrecioTotal(100.0);
        orden.setEstado("Pendiente");
        orden.setCliente(cliente);
        orden.setProducto(producto);

        when(ordenRepository.findAll()).thenReturn(List.of(orden));

        List<OrdenesResponseDTO> resultado = ordenesService.buscar(
                1L,
                "Juan",
                "2026-04-01",
                "12345678-9"
        );

        assertEquals(1, resultado.size());
        OrdenesResponseDTO dto = resultado.get(0);
        assertEquals(1L, dto.getId());
        assertEquals("12345678-9", dto.getRutCliente());
        assertEquals("Producto 1", dto.getNombreProducto());
        assertEquals(2, dto.getCantidad());
        assertEquals(50.0, dto.getPrecioProducto());
        assertEquals(100.0, dto.getValorTotal());
        assertEquals("Calle Falsa 123", dto.getDireccion());
        assertEquals("Pendiente", dto.getEstado());
    }


    @Test
    @DisplayName("No debería devolver resultados cuando no hay coincidencias")
    void deberiaRetornarListaVaciaSiNoCoincide() {
        ClienteEntity cliente = new ClienteEntity();
        cliente.setRutCliente("12345678-9");
        cliente.setNombre("Juan");

        OrdenEntity orden = new OrdenEntity();
        orden.setIdOrden(1L);
        orden.setFechaOrden(LocalDateTime.of(2026, 4, 1, 10, 15));
        orden.setCliente(cliente);

        when(ordenRepository.findAll()).thenReturn(List.of(orden));

        List<OrdenesResponseDTO> resultado = ordenesService.buscar(
                2L,               // id distinto
                "Pedro",          // cliente distinto
                "2026-05-01",     // fecha distinta
                "98765432-1"      // rut distinto
        );

        assertTrue(resultado.isEmpty());
    }




    @Test
    @DisplayName("Deberia actualizar orden")
    void actualizarEstadoOrden() {
   
    ordenEntity.setEstado("Pendiente");
    when(ordenRepository.findById(1L)).thenReturn(Optional.of(ordenEntity));
    when(ordenRepository.save(any(OrdenEntity.class))).thenReturn(ordenEntity);

    
    OrdenesResponseDTO resultado = ordenesService.actualizar(1L, "ENVIADO");

    
    assertNotNull(resultado);
    assertEquals("ENVIADO", resultado.getEstado());
    assertEquals(1L, resultado.getId());
    assertEquals("12345678-9", resultado.getRutCliente());
    assertEquals("Producto 1", resultado.getNombreProducto());

   
    verify(ordenRepository).findById(1L);
    verify(ordenRepository).save(any(OrdenEntity.class));
}


    @Test
    @DisplayName("Debería lanzar excepción si el estado es inválido")
        void actualizarEstadoInvalido() {
            when(ordenRepository.findById(1L)).thenReturn(Optional.of(ordenEntity));

                assertThrows(IllegalArgumentException.class,
                    () -> ordenesService.actualizar(1L, "CANCELADO"));

                }



    @Test
    @DisplayName("Debería devolver null si la orden no existe")
    void actualizarOrdenNoEncontrada() {
        when(ordenRepository.findById(99L)).thenReturn(Optional.empty());

        OrdenesResponseDTO resultado = ordenesService.actualizar(99L, "ENVIADO");

        assertNull(resultado);
    }








        


        
   
   
   
   

    
}
