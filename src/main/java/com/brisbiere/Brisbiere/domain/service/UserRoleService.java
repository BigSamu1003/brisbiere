package com.brisbiere.Brisbiere.domain.service;

import com.brisbiere.Brisbiere.domain.UserRole;
import com.brisbiere.Brisbiere.persistence.RoleUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRoleService {

    @Autowired
    private RoleUsuarioRepository roleUsuarioRepository;

    public void saveUserRole(UserRole userRole){
        this.roleUsuarioRepository.save(userRole);
    }
}
