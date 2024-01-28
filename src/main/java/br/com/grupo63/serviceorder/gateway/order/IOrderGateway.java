package br.com.grupo63.serviceorder.gateway.order;

import br.com.grupo63.serviceorder.entity.order.Order;
import br.com.grupo63.techchallenge.common.gateway.IPersistenceEntityGateway;

import java.util.Optional;

public interface IOrderGateway extends IPersistenceEntityGateway<Order> {

    Optional<Order> findByIdAndDeletedFalse(Long id);

    Order saveAndFlush(Order order);

}
