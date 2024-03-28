package br.com.grupo63.serviceorder.controller;

import br.com.grupo63.serviceorder.adapter.OrderAdapter;
import br.com.grupo63.serviceorder.api.controller.order.dto.CreateOrderRequestDTO;
import br.com.grupo63.serviceorder.controller.dto.OrderControllerDTO;
import br.com.grupo63.serviceorder.entity.order.Order;
import br.com.grupo63.serviceorder.presenter.OrderPresenter;
import br.com.grupo63.serviceorder.usecase.order.OrderUseCase;
import br.com.grupo63.techchallenge.common.api.controller.AbstractAPIController;
import br.com.grupo63.techchallenge.common.exception.NotFoundException;
import br.com.grupo63.techchallenge.common.exception.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderController extends AbstractAPIController {

    private final OrderUseCase orderUseCase;

    public OrderControllerDTO create(String clientId, CreateOrderRequestDTO dto) throws ValidationException, NotFoundException {
        Order entity = new Order();
        OrderAdapter.fillEntity(dto, clientId, entity);
        entity = orderUseCase.create(entity, clientId);
        return OrderPresenter.toDto(entity);
    }

    public OrderControllerDTO read(Long orderId) throws NotFoundException {
        return OrderPresenter.toDto(orderUseCase.read(orderId));
    }

    public List<OrderControllerDTO> list() {
        return orderUseCase.list().stream().map(OrderPresenter::toDto).toList();
    }

    public OrderControllerDTO update(OrderControllerDTO dto, Long orderId) throws NotFoundException {
        Order entity = orderUseCase.read(orderId);
        OrderAdapter.fillEntity(dto, entity);
        entity = orderUseCase.update(entity);
        return OrderPresenter.toDto(entity);
    }

    public void delete(Long orderId) throws NotFoundException {
        Order entity = orderUseCase.read(orderId);
        orderUseCase.delete(entity);
    }

}
