package br.com.grupo63.serviceorder.usecase.order;

import br.com.grupo63.serviceorder.entity.order.Order;
import br.com.grupo63.techchallenge.common.exception.NotFoundException;
import br.com.grupo63.techchallenge.common.exception.ValidationException;

import java.util.List;

public interface IOrderUseCase {

    Order create(Order entity, Long clientId) throws ValidationException, NotFoundException;

    Order read(Long id) throws NotFoundException;

    List<Order> list();

    Order update(Order entity) throws ValidationException;

    void delete(Order entity);

}
