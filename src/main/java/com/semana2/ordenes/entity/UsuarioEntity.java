package com.semana2.ordenes.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "comprador")
@Data
@NoArgsConstructor
@AllArgsConstructor



public class UsuarioEntity {

    @Id
    @Column(name = "id_comprador", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idComprador;

    @Column(name = "rut", nullable = false, unique = true)
    private String rut;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "apellido", nullable = false)
    private String apellido;

    @Column(name = "correo", nullable = false, unique = true)
    private String correo;


    @OneToMany(mappedBy = "comprador")
    private java.util.List<OrdenEntity> ordenes;
    

}
