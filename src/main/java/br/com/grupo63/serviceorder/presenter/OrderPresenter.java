package br.com.grupo63.serviceorder.presenter;

import br.com.grupo63.serviceorder.controller.dto.OrderControllerDTO;
import br.com.grupo63.serviceorder.controller.dto.OrderItemControllerDTO;
import br.com.grupo63.serviceorder.entity.order.Order;

public class OrderPresenter {

    public static OrderControllerDTO toDto(Order entity) {
        OrderControllerDTO dto = new OrderControllerDTO();

        dto.setId(entity.getId());
        dto.setTotalPrice(entity.getTotalPrice());
        dto.setPaymentId(entity.getPaymentId());
        dto.setClientId(entity.getClientId());
        dto.setItems(entity.getItems().stream().map(orderItemEntity -> {
            OrderItemControllerDTO orderItemUseCaseDTO = new OrderItemControllerDTO();

            orderItemUseCaseDTO.setId(orderItemEntity.getId());
            orderItemUseCaseDTO.setQuantity(orderItemEntity.getQuantity());
            orderItemUseCaseDTO.setPrice(orderItemEntity.getPrice());
            orderItemUseCaseDTO.setProductId(orderItemEntity.getProduct().getId());

            return orderItemUseCaseDTO;
        }).toList());

        return dto;
    }

}
