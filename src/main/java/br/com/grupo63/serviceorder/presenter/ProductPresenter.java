package br.com.grupo63.serviceorder.presenter;

import br.com.grupo63.serviceorder.controller.dto.ProductControllerDTO;
import br.com.grupo63.serviceorder.entity.product.Product;

public class ProductPresenter {

    public static ProductControllerDTO toDto(Product entity) {
        ProductControllerDTO dto = new ProductControllerDTO();

        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setPrice(entity.getPrice());
        dto.setCategory(entity.getCategory().getId());

        return dto;
    }

}
