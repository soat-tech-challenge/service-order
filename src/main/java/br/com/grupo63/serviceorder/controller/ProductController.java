package br.com.grupo63.serviceorder.controller;

import br.com.grupo63.serviceorder.adapter.ProductAdapter;
import br.com.grupo63.serviceorder.controller.dto.ProductControllerDTO;
import br.com.grupo63.serviceorder.entity.product.Product;
import br.com.grupo63.serviceorder.gateway.product.IProductGateway;
import br.com.grupo63.serviceorder.presenter.ProductPresenter;
import br.com.grupo63.serviceorder.usecase.product.ProductUseCase;
import br.com.grupo63.techchallenge.common.exception.NotFoundException;
import br.com.grupo63.techchallenge.common.exception.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductController {

    private final ProductUseCase useCase;
    private final IProductGateway gateway;

    public ProductControllerDTO create(ProductControllerDTO productControllerDTO) {
        Product product = new Product();
        ProductAdapter.fillEntity(productControllerDTO, product);
        product = useCase.create(product, gateway);
        return ProductPresenter.toDto(product);
    }

    public ProductControllerDTO read(Long id) throws NotFoundException {
        return ProductPresenter.toDto(useCase.read(id, gateway));
    }

    public List<ProductControllerDTO> list() {
        return useCase.list(gateway).stream().map(ProductPresenter::toDto).toList();
    }

    public ProductControllerDTO update(ProductControllerDTO productControllerDTO, Long id) throws NotFoundException {
        Product entity = useCase.read(id, gateway);
        ProductAdapter.fillEntity(productControllerDTO, entity);
        entity = useCase.update(entity, gateway);
        return ProductPresenter.toDto(entity);
    }

    public void delete(Long id) throws NotFoundException {
        Product entity = useCase.read(id, gateway);
        useCase.delete(entity, gateway);
    }

    public List<ProductControllerDTO> listByCategoryName(String categoryName) {
        List<Product> entities = useCase.listByCategoryName(categoryName, gateway);
        return entities.stream().map(ProductPresenter::toDto).toList();
    }

}
