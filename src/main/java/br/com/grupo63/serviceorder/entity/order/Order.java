package br.com.grupo63.serviceorder.entity.order;

import br.com.grupo63.techchallenge.common.domain.Entity;
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
public class Order extends Entity {

    private Double totalPrice;
    private Long clientId;
    private List<OrderItem> items = new ArrayList<>();

    public Order(Long id, boolean deleted, Double totalPrice, Long clientId, List<OrderItem> items) {
        super(id, deleted);
        this.totalPrice = totalPrice;
        this.clientId = clientId;
        this.items = items;
    }

    public OrderItem getByProductId(Long id) {
        List<OrderItem> selectedItems = this.items.stream().filter(item -> item.getProduct().getId().equals(id)).toList();

        return selectedItems.isEmpty() ? new OrderItem() : selectedItems.get(0);
    }

}
