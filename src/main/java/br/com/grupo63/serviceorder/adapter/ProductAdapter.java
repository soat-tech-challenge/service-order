package br.com.grupo63.serviceorder.adapter;

import br.com.grupo63.serviceorder.controller.dto.ProductControllerDTO;
import br.com.grupo63.serviceorder.entity.product.Category;
import br.com.grupo63.serviceorder.entity.product.Product;

public class ProductAdapter {

    public static void fillEntity(ProductControllerDTO dto, Product entity) {
        entity.setName(dto.getName());
        entity.setPrice(dto.getPrice());

        if (dto.getCategory() != null) {
            entity.setCategory(new Category(dto.getCategory()));
        }
    }

}
