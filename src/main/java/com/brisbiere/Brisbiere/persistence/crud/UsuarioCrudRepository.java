package com.brisbiere.Brisbiere.persistence.crud;

import com.brisbiere.Brisbiere.persistence.entity.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UsuarioCrudRepository extends CrudRepository<Usuario, Integer> {

    @Query(value = "SELECT * FROM usuarios WHERE correo = :correo", nativeQuery = true)
    Usuario hasAccount(@Param("correo") String email);

}
