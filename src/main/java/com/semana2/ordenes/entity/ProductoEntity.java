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
@Table(name = "producto")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductoEntity {

    @Id
    @Column(name="id_producto", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProducto;

    @Column(name="nombre_producto", nullable = false)
    private String nombreProducto;

    @Column(name="precio", nullable = false)
    private Double precio;

    @Column(name="stock", nullable = false)
    private Integer stock;

    @Column(name="descripcion")
    private String descripcion;

    @OneToMany(mappedBy = "producto")
    private java.util.List<OrdenEntity> ordenes;


}
