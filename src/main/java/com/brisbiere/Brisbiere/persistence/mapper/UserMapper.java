package com.brisbiere.Brisbiere.persistence.mapper;


import com.brisbiere.Brisbiere.domain.User;
import com.brisbiere.Brisbiere.persistence.entity.Usuario;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses = {UserRoleMapper.class, OrderMapper.class})
public interface UserMapper {

    @Mappings({
            @Mapping(source = "usuarioId", target = "userId"),
            @Mapping(source = "nombreUsuario", target = "name"),
            @Mapping(source = "apellidoUsuario", target = "lastname"),
            @Mapping(source = "fechaNacimiento", target = "birthday"),
            @Mapping(source = "correoUsuario", target = "email"),
            @Mapping(source = "pedido", target = "order"),
            @Mapping(source = "pedidos", target = "orders"),
            @Mapping(target = "roles", source = "roles")
    })
    User toUser(Usuario usuario);

    @InheritInverseConfiguration
    Usuario toUsuario(User user);
}
