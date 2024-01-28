package br.com.grupo63.serviceorder.gateway.order;

import br.com.grupo63.serviceorder.gateway.order.entity.OrderPersistenceEntity;
import br.com.grupo63.techchallenge.common.gateway.repository.IJpaRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderJpaRepository extends JpaRepository<OrderPersistenceEntity, Long>, IJpaRepository<OrderPersistenceEntity> {
}
