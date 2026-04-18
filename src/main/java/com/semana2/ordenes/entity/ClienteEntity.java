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
@Table(name = "cliente")
@Data
@NoArgsConstructor
@AllArgsConstructor



public class ClienteEntity {

    @Id
    @Column(name = "id_cliente", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCliente;

    @Column(name = "rut_cliente", nullable = false, unique = true)
    private String rutCliente;

    @Column(name = "nombre_cliente", nullable = false)
    private String nombre;

    @Column(name = "apellido_cliente", nullable = false)
    private String apellido;

    @Column(name = "correo_cliente", nullable = false, unique = true)
    private String correo;


    @OneToMany(mappedBy = "cliente")
    private java.util.List<OrdenEntity> ordenes;
    

}
