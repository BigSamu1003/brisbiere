package com.brisbiere.Brisbiere.persistence.entity;


import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "pedidos_has_productos")
public class PedidoProducto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "id_pedido")
    private Integer idPedido;

    @Column(name = "id_producto")
    private Integer idProducto;

    private String nombre;

    private Integer cantidad;

    @Column(name = "precio_cerveza")
    private BigDecimal precioCerveza;

    @Column(name = "total_cerveza")
    private BigDecimal totalCerveza;

    @ManyToOne
    @JoinColumn(name = "id_pedido", insertable = false, updatable = false)
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "id_producto", insertable = false, updatable = false)
    private Producto producto;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Integer idPedido) {
        this.idPedido = idPedido;
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getPrecioCerveza() {
        return precioCerveza;
    }

    public void setPrecioCerveza(BigDecimal precioCerveza) {
        this.precioCerveza = precioCerveza;
    }

    public BigDecimal getTotalCerveza() {
        return totalCerveza;
    }

    public void setTotalCerveza(BigDecimal totalCerveza) {
        this.totalCerveza = totalCerveza;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
}
