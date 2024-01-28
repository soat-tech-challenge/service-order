package br.com.grupo63.serviceorder.order;

import br.com.grupo63.serviceorder.api.controller.order.OrderAPIController;
import br.com.grupo63.serviceorder.api.controller.order.dto.CreateOrderRequestDTO;
import br.com.grupo63.serviceorder.controller.OrderController;
import br.com.grupo63.serviceorder.controller.dto.OrderControllerDTO;
import br.com.grupo63.serviceorder.entity.order.Order;
import br.com.grupo63.serviceorder.entity.order.OrderItem;
import br.com.grupo63.serviceorder.entity.product.Category;
import br.com.grupo63.serviceorder.entity.product.Product;
import br.com.grupo63.serviceorder.gateway.identification.IIdentificationGateway;
import br.com.grupo63.serviceorder.gateway.identification.dto.ClientDTO;
import br.com.grupo63.serviceorder.gateway.order.OrderJpaAdapter;
import br.com.grupo63.serviceorder.gateway.order.OrderJpaRepository;
import br.com.grupo63.serviceorder.gateway.order.entity.OrderPersistenceEntity;
import br.com.grupo63.serviceorder.gateway.product.ProductJpaAdapter;
import br.com.grupo63.serviceorder.gateway.product.ProductJpaRepository;
import br.com.grupo63.serviceorder.gateway.product.entity.ProductPersistenceEntity;
import br.com.grupo63.serviceorder.usecase.order.OrderUseCase;
import jakarta.servlet.http.HttpServletRequest;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class OrderIntegrationTest {

    @Mock
    private ProductJpaRepository productJpaRepository;
    @Mock
    private OrderJpaRepository orderJpaRepository;
    @Mock
    private IIdentificationGateway identificationGateway;
    @InjectMocks
    private ProductJpaAdapter productJpaAdapter;
    @InjectMocks
    private OrderJpaAdapter orderJpaAdapter;
    private OrderUseCase orderUseCase;
    private OrderController orderController;
    private OrderAPIController orderAPIController;

    private final Category defaultCategory = new Category(1L, false, "category");
    private final Product defaultProduct = new Product(1L, false, "product", 10.0, defaultCategory);
    private final CreateOrderRequestDTO.Item defaultItem = new CreateOrderRequestDTO.Item(1L, 1L);
    private final Order defaultOrder = new Order(1L, false, 10.0, 1L, List.of());

    private final OrderPersistenceEntity defaultOrderPersistenceEntity =
            new OrderPersistenceEntity(defaultOrder);
    private final ProductPersistenceEntity defaultProductPersistenceEntity = new ProductPersistenceEntity(defaultProduct);

    private final CreateOrderRequestDTO defaultCreateOrderRequestDTO = new CreateOrderRequestDTO(List.of(defaultItem));
    private final ClientDTO defaultClientDTO = new ClientDTO();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        orderUseCase = new OrderUseCase(productJpaAdapter, orderJpaAdapter, identificationGateway);
        orderController = new OrderController(orderUseCase);
        orderAPIController = new OrderAPIController(orderController);
    }

    @SneakyThrows
    @Test
    void testRead_EndToEnd() {
        when(orderJpaRepository.findByIdAndDeletedFalse(1L)).thenReturn(Optional.of(defaultOrderPersistenceEntity));

        ResponseEntity<OrderControllerDTO> response = orderAPIController.read(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(Objects.requireNonNull(response.getBody()).getId(), 1L);
    }

    @SneakyThrows
    @Test
    void testList_EndToEnd() {
        when(orderJpaRepository.findByDeletedFalse()).thenReturn(List.of(defaultOrderPersistenceEntity));

        ResponseEntity<List<OrderControllerDTO>> response = orderAPIController.list();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(Objects.requireNonNull(response.getBody()).get(0).getId(), 1L);
    }

    @SneakyThrows
    @Test
    void testCreate_EndToEnd() {
        HttpServletRequest mockedRequest = new MockHttpServletRequest();
        mockedRequest.setAttribute("clientId", Long.toString(1L));

        when(identificationGateway.getById(1L)).thenReturn(Optional.of(defaultClientDTO));
        when(productJpaRepository.findByIdAndDeletedFalse(1L)).thenReturn(Optional.of(defaultProductPersistenceEntity));
        when(orderJpaRepository.saveAndFlush(any())).thenReturn(defaultOrderPersistenceEntity);

        ResponseEntity<OrderControllerDTO> response = orderAPIController.create(defaultCreateOrderRequestDTO, mockedRequest);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(response.getBody().getId(), 1L);
        verify(orderJpaRepository, times(1)).saveAndFlush(any());
    }

}
