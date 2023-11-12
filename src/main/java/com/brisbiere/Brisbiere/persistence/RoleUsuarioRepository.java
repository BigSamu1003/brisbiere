package com.brisbiere.Brisbiere.persistence;

import com.brisbiere.Brisbiere.domain.UserRole;
import com.brisbiere.Brisbiere.domain.repository.UserRoleRepository;
import com.brisbiere.Brisbiere.persistence.crud.UsuarioRoleCrudRepository;
import com.brisbiere.Brisbiere.persistence.mapper.UserRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RoleUsuarioRepository implements UserRoleRepository {

    @Autowired
    private UsuarioRoleCrudRepository usuarioRoleCrudRepository;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public void save(UserRole userRole) {
        this.usuarioRoleCrudRepository.save(this.userRoleMapper.toRoleUsuario(userRole));
    }
}
