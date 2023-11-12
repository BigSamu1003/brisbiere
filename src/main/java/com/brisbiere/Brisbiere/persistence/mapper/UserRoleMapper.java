package com.brisbiere.Brisbiere.persistence.mapper;


import com.brisbiere.Brisbiere.domain.UserRole;
import com.brisbiere.Brisbiere.persistence.entity.RoleUsuario;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface UserRoleMapper {

    @Mappings({
            @Mapping(source = "username", target = "username"),
            @Mapping(source = "role", target = "role"),
            @Mapping(source = "fechaInicio", target = "grantedDate"),
            @Mapping(target = "user", ignore = true)
    })
    UserRole toUserRole(RoleUsuario roleUsuario);

    @InheritInverseConfiguration
    @Mapping(target = "usuario", ignore = true)
    RoleUsuario toRoleUsuario(UserRole userRole);
}
