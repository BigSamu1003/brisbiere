package com.brisbiere.Brisbiere.persistence.mapper;

import com.brisbiere.Brisbiere.domain.Order;
import com.brisbiere.Brisbiere.domain.ProductOrder;
import com.brisbiere.Brisbiere.persistence.entity.Pedido;
import com.brisbiere.Brisbiere.persistence.entity.PedidoProducto;
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
public class OrderMapperImpl implements OrderMapper {

    @Autowired
    private ProductOrderMapper productOrderMapper;

    @Override
    public Order toOrder(Pedido pedido) {
        if ( pedido == null ) {
            return null;
        }

        Order order = new Order();

        if ( pedido.getIdPedido() != null ) {
            order.setOrderId( pedido.getIdPedido() );
        }
        order.setTrackingGuide( pedido.getNumeroGuia() );
        order.setStatus( pedido.getStatus() );
        order.setAddress( pedido.getDireccion() );
        order.setDate( pedido.getFecha() );
        if ( pedido.getIdUsuario() != null ) {
            order.setUserId( pedido.getIdUsuario() );
        }
        order.setPayment( pedido.getMedioPago() );
        order.setTotal( pedido.getTotal() );
        order.setItems( pedidoProductoListToProductOrderList( pedido.getProductos() ) );

        return order;
    }

    @Override
    public List<Order> toOrders(List<Pedido> pedidos) {
        if ( pedidos == null ) {
            return null;
        }

        List<Order> list = new ArrayList<Order>( pedidos.size() );
        for ( Pedido pedido : pedidos ) {
            list.add( toOrder( pedido ) );
        }

        return list;
    }

    @Override
    public Pedido toPedido(Order order) {
        if ( order == null ) {
            return null;
        }

        Pedido pedido = new Pedido();

        pedido.setIdPedido( order.getOrderId() );
        pedido.setNumeroGuia( order.getTrackingGuide() );
        pedido.setStatus( order.getStatus() );
        pedido.setDireccion( order.getAddress() );
        pedido.setFecha( order.getDate() );
        pedido.setIdUsuario( order.getUserId() );
        pedido.setMedioPago( order.getPayment() );
        pedido.setTotal( order.getTotal() );
        pedido.setProductos( productOrderListToPedidoProductoList( order.getItems() ) );

        return pedido;
    }

    protected List<ProductOrder> pedidoProductoListToProductOrderList(List<PedidoProducto> list) {
        if ( list == null ) {
            return null;
        }

        List<ProductOrder> list1 = new ArrayList<ProductOrder>( list.size() );
        for ( PedidoProducto pedidoProducto : list ) {
            list1.add( productOrderMapper.toProductOrder( pedidoProducto ) );
        }

        return list1;
    }

    protected List<PedidoProducto> productOrderListToPedidoProductoList(List<ProductOrder> list) {
        if ( list == null ) {
            return null;
        }

        List<PedidoProducto> list1 = new ArrayList<PedidoProducto>( list.size() );
        for ( ProductOrder productOrder : list ) {
            list1.add( productOrderMapper.toPedidoProducto( productOrder ) );
        }

        return list1;
    }
}
