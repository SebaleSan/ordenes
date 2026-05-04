package com.semana2.ordenes.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.semana2.ordenes.entity.ClienteEntity;
import com.semana2.ordenes.entity.OrdenEntity;
import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;


@Repository
public interface OrdenRepository extends JpaRepository<OrdenEntity, Long> {

    List<OrdenEntity> findAll();
    Optional<OrdenEntity> findById(Long id);

    @Query("SELECT o FROM OrdenEntity o WHERE o.fechaOrden = :fechaOrden")
    List<OrdenEntity> findByFechaOrden(@Param("fechaOrden") LocalDateTime fechaOrden);

    List<OrdenEntity> findByClienteAndFechaOrden(ClienteEntity cliente, LocalDateTime fechaOrden);
    
    @Query("SELECT o FROM OrdenEntity o WHERE o.cliente = :cliente AND o.producto = :producto AND o.fechaOrden > :fechaOrden")
    List<OrdenEntity> findByClienteAndProductoAndFechaOrdenAfter(@Param("cliente") ClienteEntity cliente, @Param("producto") com.semana2.ordenes.entity.ProductoEntity producto, @Param("fechaOrden") LocalDateTime fechaOrden);









}