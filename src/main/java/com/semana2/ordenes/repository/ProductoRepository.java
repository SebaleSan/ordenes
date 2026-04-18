package com.semana2.ordenes.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.semana2.ordenes.entity.ProductoEntity;

public interface ProductoRepository extends JpaRepository<ProductoEntity, Long> {

    List<ProductoEntity> findAll();
    Optional<ProductoEntity> findById(Long id);

    List<ProductoEntity> findByNombreProducto(String nombreProducto);
    
    List<ProductoEntity> findByPrecio(Double precio);
    List<ProductoEntity> findByStock(Integer stock);
    


    
}