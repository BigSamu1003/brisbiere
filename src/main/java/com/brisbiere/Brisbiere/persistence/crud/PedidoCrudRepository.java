package com.brisbiere.Brisbiere.persistence.crud;

import com.brisbiere.Brisbiere.persistence.entity.Pedido;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PedidoCrudRepository extends CrudRepository<Pedido, Integer> {

    @Query(value = "SELECT * FROM pedidos WHERE id_usuario = :usuario ORDER BY fecha", nativeQuery = true)
    Optional<List<Pedido>> getByUserId(@Param("usuario") Integer userId);

    @Query(value = "SELECT * FROM pedidos WHERE correo = :correo ORDER BY fecha", nativeQuery = true)
    Optional<List<Pedido>> getByEmail(@Param("correo") String correo);

    @Query(value = "SELECT * FROM pedidos ORDER BY id_pedido DESC LIMIT 1;", nativeQuery = true)
    Optional<Pedido> getLastOrderId();

    @Query(value = "SELECT * FROM pedidos WHERE correo = :correo ORDER BY id_pedido DESC LIMIT 1;", nativeQuery = true)
    Optional<Pedido> getLastOrderByEmail(@Param("correo") String correo);
}

