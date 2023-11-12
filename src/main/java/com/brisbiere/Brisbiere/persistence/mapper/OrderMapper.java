package com.brisbiere.Brisbiere.persistence.mapper;

import com.brisbiere.Brisbiere.domain.Order;
import com.brisbiere.Brisbiere.persistence.entity.Pedido;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ProductOrderMapper.class})
public interface OrderMapper {

    @Mappings({
            @Mapping(source = "idPedido", target = "orderId"),
            @Mapping(source = "numeroGuia", target = "trackingGuide"),
            @Mapping(source = "status", target = "status"),
            @Mapping(source = "direccion", target = "address"),
            @Mapping(source = "fecha", target = "date"),
            @Mapping(source = "idUsuario", target = "userId"),
            @Mapping(source = "medioPago", target = "payment"),
            @Mapping(source = "total", target = "total"),
            @Mapping(source = "productos", target = "items"),
    })
    Order toOrder(Pedido pedido);
    List<Order> toOrders(List<Pedido> pedidos);

    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "usuario", ignore = true),
    })
    Pedido toPedido(Order order);
}
