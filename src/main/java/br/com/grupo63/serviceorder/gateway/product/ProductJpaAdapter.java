package br.com.grupo63.serviceorder.gateway.product;

import br.com.grupo63.serviceorder.entity.product.Product;
import br.com.grupo63.serviceorder.gateway.product.entity.ProductPersistenceEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProductJpaAdapter implements IProductGateway  {

    private final ProductJpaRepository productJpaRepository;

    @Override
    public Product saveAndFlush(Product product) {
        ProductPersistenceEntity entity = new ProductPersistenceEntity(product);

        entity = productJpaRepository.saveAndFlush(entity);

        return entity.toModel();
    }

    @Override
    public Optional<Product> findByIdAndDeletedFalse(Long id) {
        return productJpaRepository.findByIdAndDeletedFalse(id).map(ProductPersistenceEntity::toModel);
    }

    @Override
    public List<Product> findByDeletedFalseAndCategory_Name(String categoryName) {
        return productJpaRepository.findByDeletedFalseAndCategory_Name(categoryName)
                .stream()
                .map(ProductPersistenceEntity::toModel)
                .toList();
    }

    @Override
    public List<Product> findByDeletedFalse() {
        return productJpaRepository.findByDeletedFalse()
                .stream()
                .map(ProductPersistenceEntity::toModel)
                .toList();
    }

}
