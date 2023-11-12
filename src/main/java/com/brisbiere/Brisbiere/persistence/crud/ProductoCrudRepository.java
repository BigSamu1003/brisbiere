package com.brisbiere.Brisbiere.persistence.crud;

import com.brisbiere.Brisbiere.persistence.entity.Producto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductoCrudRepository extends CrudRepository<Producto, Integer> {
    @Query(value = "SELECT * FROM productos WHERE marca = ? ORDER BY nombre", nativeQuery = true)
    List<Producto> getByMarca(String marca);

    @Query(value = "SELECT * FROM productos WHERE estilo = ? ORDER BY nombre", nativeQuery = true)
    List<Producto> getByEstilo(String estilo);

}
