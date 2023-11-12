package com.brisbiere.Brisbiere.persistence.crud;

import com.brisbiere.Brisbiere.persistence.entity.RoleUsuario;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioRoleCrudRepository extends CrudRepository<RoleUsuario, String> {
}
