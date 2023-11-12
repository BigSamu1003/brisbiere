package com.brisbiere.Brisbiere.persistence;

import com.brisbiere.Brisbiere.domain.Order;
import com.brisbiere.Brisbiere.domain.repository.OrderRepository;
import com.brisbiere.Brisbiere.persistence.crud.PedidoCrudRepository;
import com.brisbiere.Brisbiere.persistence.entity.Pedido;
import com.brisbiere.Brisbiere.persistence.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PedidoRepository implements OrderRepository {
    @Autowired
    private PedidoCrudRepository pedidoCrudRepository;

    @Autowired
    private OrderMapper mapper;

    @Override
    public List<Order> getAll() {
        return mapper.toOrders((List<Pedido>) pedidoCrudRepository.findAll());
    }

    @Override
    public Optional<List<Order>> getByClient(int userId) {
        return pedidoCrudRepository.getByUserId(userId).map(Pedidos -> mapper.toOrders(Pedidos));
    }

    @Override
    public Optional<List<Order>> getByEmail(String email) {
        return pedidoCrudRepository.getByEmail(email).map(Pedidos -> mapper.toOrders(Pedidos));
    }

    @Override
    public Order save(Order order) {
        Pedido pedido = mapper.toPedido(order);
        pedido.getProductos().forEach(producto -> producto.setPedido(pedido));

        return mapper.toOrder(pedidoCrudRepository.save(pedido));
    }

    @Override
    public Integer getLastOrderId() {
        Order lastOrder = this.pedidoCrudRepository.getLastOrderId().map(Pedido->mapper.toOrder(Pedido)).orElse(null);
        assert lastOrder != null;
        Integer lastOrderId = lastOrder.getOrderId();
        return lastOrderId;
    }

    @Override
    public Order getLastOrder(String email) {
        Order lastOrder = this.pedidoCrudRepository.getLastOrderByEmail(email).map(Pedido->mapper.toOrder(Pedido)).orElse(null);

        return lastOrder;
    }


}

