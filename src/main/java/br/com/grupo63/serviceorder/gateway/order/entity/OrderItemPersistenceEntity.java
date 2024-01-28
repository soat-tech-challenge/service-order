package br.com.grupo63.serviceorder.gateway.order.entity;

import br.com.grupo63.serviceorder.entity.order.OrderItem;
import br.com.grupo63.serviceorder.gateway.product.entity.ProductPersistenceEntity;
import br.com.grupo63.techchallenge.common.gateway.repository.entity.PersistenceEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "ori_order_item", indexes = {})
public class OrderItemPersistenceEntity extends PersistenceEntity {

    @Basic
    @Column(name = "quantity", nullable = false)
    private Long quantity;

    @Basic
    @Column(name = "price", nullable = false)
    private Double price;

    @JoinColumn(name = "ori_order", foreignKey = @ForeignKey(name = "fk_order_item_order"), nullable = false)
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    private OrderPersistenceEntity order;

    @JoinColumn(name = "product", foreignKey = @ForeignKey(name = "fk_order_item_product"), nullable = false)
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    private ProductPersistenceEntity product;

    public OrderItemPersistenceEntity(OrderItem item, OrderPersistenceEntity order) {
        super(item);
        this.quantity = item.getQuantity();
        this.price = item.getPrice();
        this.order = order;
        this.product = new ProductPersistenceEntity(item.getProduct());
    }

    public OrderItem toModel() {
        return new OrderItem(this.getId(),
                this.isDeleted(),
                this.getQuantity(),
                this.getPrice(),
                null,
                this.product.toModel());
    }

}
