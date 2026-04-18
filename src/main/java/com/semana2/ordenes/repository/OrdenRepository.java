package com.semana2.ordenes.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.semana2.ordenes.entity.OrdenEntity;
import java.util.List;
import java.util.Optional;


public interface OrdenRepository extends JpaRepository<OrdenEntity, Long> {

    List<OrdenEntity> findAll();
    Optional<OrdenEntity> findById(Long id);

    @Query("SELECT o FROM OrdenEntity o WHERE o.comprador.rutCliente = :rutCliente")
    List<OrdenEntity> findByRutCliente(@Param("rutCliente") String rutCliente);

    List<OrdenEntity> findByFechaAndRutCliente(String fecha, String rutCliente);

    





}