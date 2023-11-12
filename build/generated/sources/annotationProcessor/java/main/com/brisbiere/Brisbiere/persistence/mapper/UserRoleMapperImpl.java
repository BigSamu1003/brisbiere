package com.brisbiere.Brisbiere.persistence.mapper;

import com.brisbiere.Brisbiere.domain.UserRole;
import com.brisbiere.Brisbiere.persistence.entity.RoleUsuario;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-11-03T16:35:32-0600",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.2.1.jar, environment: Java 17.0.4 (Oracle Corporation)"
)
@Component
public class UserRoleMapperImpl implements UserRoleMapper {

    @Override
    public UserRole toUserRole(RoleUsuario roleUsuario) {
        if ( roleUsuario == null ) {
            return null;
        }

        UserRole userRole = new UserRole();

        userRole.setUsername( roleUsuario.getUsername() );
        userRole.setRole( roleUsuario.getRole() );
        userRole.setGrantedDate( roleUsuario.getFechaInicio() );

        return userRole;
    }

    @Override
    public RoleUsuario toRoleUsuario(UserRole userRole) {
        if ( userRole == null ) {
            return null;
        }

        RoleUsuario roleUsuario = new RoleUsuario();

        roleUsuario.setUsername( userRole.getUsername() );
        roleUsuario.setRole( userRole.getRole() );
        roleUsuario.setFechaInicio( userRole.getGrantedDate() );

        return roleUsuario;
    }
}
