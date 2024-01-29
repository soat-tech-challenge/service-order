package br.com.grupo63.serviceorder.gateway.order.entity;

import br.com.grupo63.serviceorder.entity.order.Order;
import br.com.grupo63.techchallenge.common.gateway.repository.entity.PersistenceEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "ord_order", indexes = {})
public class OrderPersistenceEntity extends PersistenceEntity {

    @Basic
    @Column(name = "total_price", nullable = false)
    private Double totalPrice;

    @Basic
    @Column(name = "ord_client", nullable = false)
    private String clientId;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "order")
    private List<OrderItemPersistenceEntity> items = new ArrayList<>();

    public OrderPersistenceEntity(Long id) {
        this.id = id;
    }

    public OrderPersistenceEntity(Order order) {
        super(order);
        this.totalPrice = order.getTotalPrice();
        this.clientId = order.getClientId();
        this.items = order.getItems().stream().map(item -> new OrderItemPersistenceEntity(item, this)).toList();
    }

    public Order toModel() {
        return new Order(
                this.getId(),
                this.isDeleted(),
                this.getTotalPrice(),
                this.getClientId(),
                this.getItems().stream().map(OrderItemPersistenceEntity::toModel).toList());
    }

}
