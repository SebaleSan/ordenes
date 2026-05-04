package com.semana2.ordenes.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.semana2.ordenes.entity.ClienteEntity;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteEntity, Long> {

    List<ClienteEntity> findAll();
    Optional<ClienteEntity> findById(Long id);

    List<ClienteEntity> findByRutCliente(String rutCliente);
    List<ClienteEntity> findByNombre(String nombre);
    List<ClienteEntity> findByApellido(String apellido);
    List<ClienteEntity> findByCorreo(String correo);
    
}