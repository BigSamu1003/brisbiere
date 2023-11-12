package com.brisbiere.Brisbiere.persistence.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "role_usuario")
@NoArgsConstructor
@AllArgsConstructor
public class RoleUsuario {

    @Id
    @Column(nullable = false, length = 200)
    private String username;

    @Column(nullable = false, length = 20)
    private String role;

    @Column(name = "fecha_inicio", nullable = false, columnDefinition = "DATETIME")
    private LocalDateTime fechaInicio;

    @ManyToOne
    @JoinColumn(name = "username", referencedColumnName = "correo" ,insertable=false, updatable=false)
    private Usuario usuario;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public LocalDateTime getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDateTime fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}

