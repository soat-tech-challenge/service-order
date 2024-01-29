package br.com.grupo63.serviceorder.product;

import br.com.grupo63.serviceorder.api.controller.product.ProductAPIController;
import br.com.grupo63.serviceorder.controller.ProductController;
import br.com.grupo63.serviceorder.controller.dto.ProductControllerDTO;
import br.com.grupo63.serviceorder.entity.product.Category;
import br.com.grupo63.serviceorder.entity.product.Product;
import br.com.grupo63.serviceorder.gateway.product.ProductJpaAdapter;
import br.com.grupo63.serviceorder.gateway.product.ProductJpaRepository;
import br.com.grupo63.serviceorder.gateway.product.entity.ProductPersistenceEntity;
import br.com.grupo63.serviceorder.usecase.product.ProductUseCase;
import br.com.grupo63.techchallenge.common.api.controller.dto.DefaultResponseDTO;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class ProductIntegrationTest {

    @Mock
    private ProductJpaRepository productJpaRepository;
    @InjectMocks
    private ProductJpaAdapter productJpaAdapter;
    private ProductUseCase productUseCase;
    private ProductController productController;
    private ProductAPIController productAPIController;

    private final Category defaultCategory = new Category(1L, false, "category");
    private final Product defaultProduct = new Product(1L, false, "product", 10.0, defaultCategory);
    private final ProductPersistenceEntity defaultProductPersistenceEntity = new ProductPersistenceEntity(defaultProduct);
    private final ProductControllerDTO defaultProductControllerDTO = new ProductControllerDTO("product", 10.0, 1L);

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        productUseCase = new ProductUseCase();
        productController = new ProductController(productUseCase, productJpaAdapter);
        productAPIController = new ProductAPIController(productController);
    }

    @SneakyThrows
    @Test
    void testRead_EndToEnd() {
        when(productJpaRepository.findByIdAndDeletedFalse(1L)).thenReturn(Optional.of(defaultProductPersistenceEntity));

        ResponseEntity<ProductControllerDTO> response = productAPIController.read(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(Objects.requireNonNull(response.getBody()).getId(), 1L);
    }

    @SneakyThrows
    @Test
    void testList_EndToEnd() {
        when(productJpaRepository.findByDeletedFalse()).thenReturn(List.of(defaultProductPersistenceEntity));

        ResponseEntity<List<ProductControllerDTO>> response = productAPIController.list();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(Objects.requireNonNull(response.getBody()).get(0).getId(), 1L);
    }

    @SneakyThrows
    @Test
    void testListByCategory_EndToEnd() {
        when(productJpaRepository.findByDeletedFalseAndCategory_Name("category")).thenReturn(List.of(defaultProductPersistenceEntity));

        ResponseEntity<List<ProductControllerDTO>> response = productAPIController.listByCategoryName("category");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(Objects.requireNonNull(response.getBody()).get(0).getId(), 1L);
    }

    @SneakyThrows
    @Test
    void testCreate_EndToEnd() {
        when(productJpaRepository.saveAndFlush(any())).thenReturn(defaultProductPersistenceEntity);

        ResponseEntity<ProductControllerDTO> response = productAPIController.create(defaultProductControllerDTO);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(response.getBody().getId(), 1L);
        verify(productJpaRepository, times(1)).saveAndFlush(any());
    }

    @SneakyThrows
    @Test
    public void testDelete_EndToEnd() {
        when(productJpaRepository.findByIdAndDeletedFalse(defaultProductPersistenceEntity.getId())).thenReturn(Optional.of(defaultProductPersistenceEntity));
        when(productJpaRepository.saveAndFlush(any())).thenReturn(new ProductPersistenceEntity(defaultProductPersistenceEntity.getName(), defaultProductPersistenceEntity.getPrice(), defaultProductPersistenceEntity.getCategory()));

        ResponseEntity<DefaultResponseDTO> response = productAPIController.delete(defaultProductPersistenceEntity.getId());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNull(response.getBody());
        verify(productJpaRepository, times(1)).findByIdAndDeletedFalse(any());
        verify(productJpaRepository, times(1)).saveAndFlush(any());
    }

}
