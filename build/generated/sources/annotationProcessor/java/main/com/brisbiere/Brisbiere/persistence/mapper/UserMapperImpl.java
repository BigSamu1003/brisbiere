package com.brisbiere.Brisbiere.persistence.mapper;

import com.brisbiere.Brisbiere.domain.Order;
import com.brisbiere.Brisbiere.domain.User;
import com.brisbiere.Brisbiere.domain.UserRole;
import com.brisbiere.Brisbiere.persistence.entity.Pedido;
import com.brisbiere.Brisbiere.persistence.entity.RoleUsuario;
import com.brisbiere.Brisbiere.persistence.entity.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-11-03T16:35:32-0600",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.2.1.jar, environment: Java 17.0.4 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private OrderMapper orderMapper;

    @Override
    public User toUser(Usuario usuario) {
        if ( usuario == null ) {
            return null;
        }

        User user = new User();

        if ( usuario.getUsuarioId() != null ) {
            user.setUserId( usuario.getUsuarioId() );
        }
        user.setName( usuario.getNombreUsuario() );
        user.setLastname( usuario.getApellidoUsuario() );
        user.setBirthday( usuario.getFechaNacimiento() );
        user.setEmail( usuario.getCorreoUsuario() );
        if ( usuario.getPedido() != null ) {
            user.setOrder( usuario.getPedido() );
        }
        user.setOrders( orderMapper.toOrders( usuario.getPedidos() ) );
        user.setRoles( roleUsuarioListToUserRoleList( usuario.getRoles() ) );
        user.setPassword( usuario.getPassword() );

        return user;
    }

    @Override
    public Usuario toUsuario(User user) {
        if ( user == null ) {
            return null;
        }

        Usuario usuario = new Usuario();

        usuario.setUsuarioId( user.getUserId() );
        usuario.setNombreUsuario( user.getName() );
        usuario.setApellidoUsuario( user.getLastname() );
        usuario.setFechaNacimiento( user.getBirthday() );
        usuario.setCorreoUsuario( user.getEmail() );
        usuario.setPedido( user.getOrder() );
        usuario.setPedidos( orderListToPedidoList( user.getOrders() ) );
        usuario.setRoles( userRoleListToRoleUsuarioList( user.getRoles() ) );
        usuario.setPassword( user.getPassword() );

        return usuario;
    }

    protected List<UserRole> roleUsuarioListToUserRoleList(List<RoleUsuario> list) {
        if ( list == null ) {
            return null;
        }

        List<UserRole> list1 = new ArrayList<UserRole>( list.size() );
        for ( RoleUsuario roleUsuario : list ) {
            list1.add( userRoleMapper.toUserRole( roleUsuario ) );
        }

        return list1;
    }

    protected List<Pedido> orderListToPedidoList(List<Order> list) {
        if ( list == null ) {
            return null;
        }

        List<Pedido> list1 = new ArrayList<Pedido>( list.size() );
        for ( Order order : list ) {
            list1.add( orderMapper.toPedido( order ) );
        }

        return list1;
    }

    protected List<RoleUsuario> userRoleListToRoleUsuarioList(List<UserRole> list) {
        if ( list == null ) {
            return null;
        }

        List<RoleUsuario> list1 = new ArrayList<RoleUsuario>( list.size() );
        for ( UserRole userRole : list ) {
            list1.add( userRoleMapper.toRoleUsuario( userRole ) );
        }

        return list1;
    }
}
