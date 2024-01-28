package br.com.grupo63.serviceorder.gateway.product;

import br.com.grupo63.serviceorder.gateway.product.entity.ProductPersistenceEntity;
import br.com.grupo63.techchallenge.common.gateway.repository.IJpaRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductJpaRepository extends JpaRepository<ProductPersistenceEntity, Long>, IJpaRepository<ProductPersistenceEntity> {

    List<ProductPersistenceEntity> findByDeletedFalseAndCategory_Name(String categoryName);

}
