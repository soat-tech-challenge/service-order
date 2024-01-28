package br.com.grupo63.serviceorder.adapter;

import br.com.grupo63.serviceorder.api.controller.order.dto.CreateOrderRequestDTO;
import br.com.grupo63.serviceorder.controller.dto.OrderControllerDTO;
import br.com.grupo63.serviceorder.controller.dto.OrderItemControllerDTO;
import br.com.grupo63.serviceorder.entity.order.Order;
import br.com.grupo63.serviceorder.entity.order.OrderItem;
import br.com.grupo63.serviceorder.entity.product.Product;

import java.util.stream.Collectors;

public class OrderAdapter {

    public static void fillEntity(CreateOrderRequestDTO dto, String clientId, Order order) {
        OrderControllerDTO orderDTO = new OrderControllerDTO();
        orderDTO.setClientId(clientId);

        if (dto.getItems() != null) {
            orderDTO.setItems(dto.getItems().stream()
                    .map(i -> new OrderItemControllerDTO(i.getQuantity(), null, i.getId()))
                    .collect(Collectors.toList()));
        }

        fillEntity(orderDTO, order);
    }

    public static void fillEntity(OrderControllerDTO dto, Order entity) {
        entity.setTotalPrice(dto.getTotalPrice());
        entity.setItems(dto.getItems().stream().map(item -> {
            OrderItem orderItem = entity.getByProductId(item.getProductId());

            orderItem.setQuantity(item.getQuantity());
            orderItem.setPrice(item.getPrice());
            if (orderItem.getProduct() == null)
                orderItem.setProduct(new Product(item.getProductId()));

            return orderItem;
        }).toList());

        entity.setClientId(dto.getClientId());
    }

}
