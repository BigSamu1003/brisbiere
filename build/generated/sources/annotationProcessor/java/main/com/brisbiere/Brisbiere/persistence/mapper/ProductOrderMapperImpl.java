package com.brisbiere.Brisbiere.persistence.mapper;

import com.brisbiere.Brisbiere.domain.ProductOrder;
import com.brisbiere.Brisbiere.persistence.entity.PedidoProducto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-11-03T16:35:32-0600",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.2.1.jar, environment: Java 17.0.4 (Oracle Corporation)"
)
@Component
public class ProductOrderMapperImpl implements ProductOrderMapper {

    @Override
    public ProductOrder toProductOrder(PedidoProducto pedidoProducto) {
        if ( pedidoProducto == null ) {
            return null;
        }

        ProductOrder productOrder = new ProductOrder();

        if ( pedidoProducto.getIdPedido() != null ) {
            productOrder.setOrderId( pedidoProducto.getIdPedido() );
        }
        if ( pedidoProducto.getIdProducto() != null ) {
            productOrder.setProductId( pedidoProducto.getIdProducto() );
        }
        productOrder.setName( pedidoProducto.getNombre() );
        if ( pedidoProducto.getCantidad() != null ) {
            productOrder.setQuantity( pedidoProducto.getCantidad() );
        }
        productOrder.setBeerPrice( pedidoProducto.getPrecioCerveza() );
        productOrder.setBeerTotal( pedidoProducto.getTotalCerveza() );
        if ( pedidoProducto.getId() != null ) {
            productOrder.setId( pedidoProducto.getId() );
        }

        return productOrder;
    }

    @Override
    public PedidoProducto toPedidoProducto(ProductOrder item) {
        if ( item == null ) {
            return null;
        }

        PedidoProducto pedidoProducto = new PedidoProducto();

        pedidoProducto.setIdPedido( item.getOrderId() );
        pedidoProducto.setIdProducto( item.getProductId() );
        pedidoProducto.setNombre( item.getName() );
        pedidoProducto.setCantidad( item.getQuantity() );
        pedidoProducto.setPrecioCerveza( item.getBeerPrice() );
        pedidoProducto.setTotalCerveza( item.getBeerTotal() );
        pedidoProducto.setId( item.getId() );

        return pedidoProducto;
    }
}
