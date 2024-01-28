package br.com.grupo63.serviceorder.gateway.order;

import br.com.grupo63.serviceorder.entity.order.Order;
import br.com.grupo63.serviceorder.gateway.order.entity.OrderPersistenceEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class OrderJpaAdapter implements IOrderGateway {

    private final OrderJpaRepository jpaRepository;

    @Override
    public Optional<Order> findByIdAndDeletedFalse(Long id) {
        return jpaRepository.findByIdAndDeletedFalse(id)
                .map(OrderPersistenceEntity::toModel);
    }

    @Override
    @Transactional
    public Order saveAndFlush(Order order) {
        OrderPersistenceEntity entity = new OrderPersistenceEntity(order);

        entity = jpaRepository.saveAndFlush(entity);

        return entity.toModel();
    }

    @Override
    public List<Order> findByDeletedFalse() {
        return jpaRepository.findByDeletedFalse().stream()
                .map(OrderPersistenceEntity::toModel)
                .toList();
    }

}
