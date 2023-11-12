package com.brisbiere.Brisbiere.persistence.mapper;


import com.brisbiere.Brisbiere.domain.ProductOrder;
import com.brisbiere.Brisbiere.persistence.entity.PedidoProducto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses = {ProductMapper.class})
public interface ProductOrderMapper {
    @Mappings({
            @Mapping(source = "idPedido", target = "orderId"),
            @Mapping(source = "idProducto", target = "productId"),
            @Mapping(source = "nombre", target = "name"),
            @Mapping(source = "cantidad", target = "quantity"),
            @Mapping(source = "precioCerveza", target = "beerPrice"),
            @Mapping(source = "totalCerveza", target = "beerTotal"),
    })
    ProductOrder toProductOrder(PedidoProducto pedidoProducto);

    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "pedido", ignore = true),
            @Mapping(target = "producto", ignore = true),
    })
    PedidoProducto toPedidoProducto(ProductOrder item);
}
