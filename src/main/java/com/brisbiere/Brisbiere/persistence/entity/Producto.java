package com.brisbiere.Brisbiere.persistence.entity;


import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "productos")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto", nullable = false, unique = true)
    private Integer idProducto;

    @Column(name = "nombre")
    private String nombreProducto;

    @Column(name = "cantidad_stock", columnDefinition = "TINYINT")
    private Integer cantidadStock;

    private String descripcion;

    private String marca;

    private String estilo;

    private String imagen;

    @Column(columnDefinition = "DECIMAL(10,2)")
    private BigDecimal precio;

    @Column(name = "id_cervecero")
    private Integer idCervecero;

    @ManyToOne
    @JoinColumn(name = "id_cervecero", insertable = false, updatable = false)
    private Cervecero cervecero;

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public Integer getCantidadStock() {
        return cantidadStock;
    }

    public void setCantidadStock(Integer cantidadStock) {
        this.cantidadStock = cantidadStock;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getEstilo() {
        return estilo;
    }

    public void setEstilo(String estilo) {
        this.estilo = estilo;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public Integer getIdCervecero() {
        return idCervecero;
    }

    public void setIdCervecero(Integer idCervecero) {
        this.idCervecero = idCervecero;
    }

    public Cervecero getCervecero() {
        return cervecero;
    }

    public void setCervecero(Cervecero cervecero) {
        this.cervecero = cervecero;
    }
}