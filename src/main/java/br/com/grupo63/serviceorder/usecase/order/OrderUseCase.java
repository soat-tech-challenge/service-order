package br.com.grupo63.serviceorder.usecase.order;

import br.com.grupo63.serviceorder.entity.order.Order;
import br.com.grupo63.serviceorder.entity.order.OrderItem;
import br.com.grupo63.serviceorder.entity.product.Product;
import br.com.grupo63.serviceorder.gateway.identification.IIdentificationGateway;
import br.com.grupo63.serviceorder.gateway.identification.dto.ClientDTO;
import br.com.grupo63.serviceorder.gateway.order.IOrderGateway;
import br.com.grupo63.serviceorder.gateway.product.IProductGateway;
import br.com.grupo63.techchallenge.common.domain.validation.group.Create;
import br.com.grupo63.techchallenge.common.domain.validation.group.Update;
import br.com.grupo63.techchallenge.common.exception.NotFoundException;
import br.com.grupo63.techchallenge.common.exception.ValidationException;
import br.com.grupo63.techchallenge.common.usecase.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderUseCase implements IOrderUseCase {

    private final IProductGateway productGateway;
    private final IOrderGateway gateway;
    private final IIdentificationGateway identificationGateway;

    private void fillCurrentPrices(Order order) throws NotFoundException {
        double totalPrice = 0.0D;
        for (OrderItem orderItem : order.getItems()) {
            Product product = productGateway.findByIdAndDeletedFalse(orderItem.getProduct().getId()).orElseThrow(NotFoundException::new);
            orderItem.setPrice(product.getPrice());
            totalPrice += product.getPrice() * orderItem.getQuantity();
        }
        order.setTotalPrice(totalPrice);
    }

    @Override
    public Order create(Order entity, String clientId) throws NotFoundException {
        identificationGateway.getById(clientId).orElseThrow(NotFoundException::new);

        fillCurrentPrices(entity);
        return gateway.saveAndFlush(entity);
    }

    @Override
    public Order read(Long id) throws NotFoundException {
        return gateway.findByIdAndDeletedFalse(id).orElseThrow(NotFoundException::new);
    }

    @Override
    public List<Order> list() {
        return gateway.findByDeletedFalse();
    }

    @Override
    public Order update(Order entity) {
        return gateway.saveAndFlush(entity);
    }

    @Override
    public void delete(Order entity) {
        entity.delete();
        gateway.saveAndFlush(entity);
    }

}
