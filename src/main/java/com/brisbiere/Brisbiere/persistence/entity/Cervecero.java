package com.brisbiere.Brisbiere.persistence.entity;


import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;

@Entity
@Table(name = "cerveceros")
public class Cervecero {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cervecero", nullable = false, unique = true)
    private Integer cerveceroId;

    @Column(name = "correo", length = 45)
    private String correoCervecero;

    private String telefono;

    private String marca;

    @Column(name = "rfc")
    private String RFC;

    @Column(name = "nombre_cervecero")
    private String nombreCervecero;

    @Column(name = "apellido_cervecero")
    private String apellidoCervecero;

    @OneToMany(mappedBy = "cervecero")
    private List<Producto> productos;

    public Integer getCerveceroId() {
        return cerveceroId;
    }

    public void setCerveceroId(Integer cerveceroId) {
        this.cerveceroId = cerveceroId;
    }

    public String getCorreoCervecero() {
        return correoCervecero;
    }

    public void setCorreoCervecero(String correoCervecero) {
        this.correoCervecero = correoCervecero;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getRFC() {
        return RFC;
    }

    public void setRFC(String RFC) {
        this.RFC = RFC;
    }

    public String getNombreCervecero() {
        return nombreCervecero;
    }

    public void setNombreCervecero(String nombreCervecero) {
        this.nombreCervecero = nombreCervecero;
    }

    public String getApellidoCervecero() {
        return apellidoCervecero;
    }

    public void setApellidoCervecero(String apellidoCervecero) {
        this.apellidoCervecero = apellidoCervecero;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }
}

