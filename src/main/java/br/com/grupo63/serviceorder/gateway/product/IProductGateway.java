package br.com.grupo63.serviceorder.gateway.product;

import br.com.grupo63.serviceorder.entity.product.Product;
import br.com.grupo63.techchallenge.common.gateway.IPersistenceEntityGateway;

import java.util.List;
import java.util.Optional;

public interface IProductGateway extends IPersistenceEntityGateway<Product> {

    Product saveAndFlush(Product product);

    Optional<Product> findByIdAndDeletedFalse(Long id);

    List<Product> findByDeletedFalseAndCategory_Name(String categoryName);

}
