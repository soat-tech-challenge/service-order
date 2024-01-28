package br.com.grupo63.serviceorder.usecase.product;

import br.com.grupo63.serviceorder.entity.product.Product;
import br.com.grupo63.serviceorder.gateway.product.IProductGateway;
import br.com.grupo63.techchallenge.common.domain.validation.group.Create;
import br.com.grupo63.techchallenge.common.domain.validation.group.Update;
import br.com.grupo63.techchallenge.common.exception.NotFoundException;
import br.com.grupo63.techchallenge.common.exception.ValidationException;
import br.com.grupo63.techchallenge.common.usecase.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor

@Service
public class ProductUseCase implements IProductUseCase {

    @Override
    public Product create(Product entity, IProductGateway gateway) {
        return gateway.saveAndFlush(entity);
    }

    @Override
    public Product read(Long id, IProductGateway gateway) throws NotFoundException {
        return gateway.findByIdAndDeletedFalse(id).orElseThrow(NotFoundException::new);
    }

    @Override
    public List<Product> list(IProductGateway gateway) {
        return gateway.findByDeletedFalse();
    }


    @Override
    public Product update(Product entity, IProductGateway gateway) {
        return gateway.saveAndFlush(entity);
    }

    @Override
    public void delete(Product entity, IProductGateway gateway) {
        entity.delete();
        gateway.saveAndFlush(entity);
    }

    @Override
    public List<Product> listByCategoryName(String categoryName, IProductGateway gateway) {
        return gateway.findByDeletedFalseAndCategory_Name(categoryName);
    }

}
