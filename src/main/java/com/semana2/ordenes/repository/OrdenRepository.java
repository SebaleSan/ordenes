package com.semana2.ordenes.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.semana2.ordenes.entity.ClienteEntity;
import com.semana2.ordenes.entity.OrdenEntity;
import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;



public interface OrdenRepository extends JpaRepository<OrdenEntity, Long> {

    List<OrdenEntity> findAll();
    Optional<OrdenEntity> findById(Long id);

    @Query("SELECT o FROM OrdenEntity o WHERE o.fechaOrden = :fechaOrden")
    List<OrdenEntity> findByFechaOrden(@Param("fechaOrden") LocalDateTime fechaOrden);

    List<OrdenEntity> findByClienteAndFechaOrden(ClienteEntity cliente, LocalDateTime fechaOrden);
  









}