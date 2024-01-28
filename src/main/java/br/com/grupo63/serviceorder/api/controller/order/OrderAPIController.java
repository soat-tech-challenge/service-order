package br.com.grupo63.serviceorder.api.controller.order;

import br.com.grupo63.serviceorder.api.controller.order.dto.CreateOrderRequestDTO;
import br.com.grupo63.serviceorder.controller.OrderController;
import br.com.grupo63.serviceorder.controller.dto.OrderControllerDTO;
import br.com.grupo63.techchallenge.common.api.controller.AbstractAPIController;
import br.com.grupo63.techchallenge.common.api.controller.dto.DefaultResponseDTO;
import br.com.grupo63.techchallenge.common.exception.NotFoundException;
import br.com.grupo63.techchallenge.common.exception.ValidationException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Pedidos", description = "Gerencia o processo de pedidos.")
@RequiredArgsConstructor
@RestController
@RequestMapping("/orders")
public class OrderAPIController extends AbstractAPIController {

    private final OrderController controller;

    @Operation(
            tags = "2ª chamada - Fluxo principal - Pedido",
            summary = "Fake checkout: Tela de resumo do pedido",
            description = "Registra um pedido a ser realizado, retorna o valor total",
            security = @SecurityRequirement(name = "bearerAuth"))
    @PostMapping
    public ResponseEntity<OrderControllerDTO> create(@Valid @RequestBody CreateOrderRequestDTO createOrderRequestDTO,
                                                     HttpServletRequest request) throws ValidationException, NotFoundException {
        return ResponseEntity.ok(controller
                .create(Long.parseLong((String) request.getAttribute("clientId")),
                        createOrderRequestDTO));
    }

    @Operation(
            tags = {"3ª chamada - Fluxo principal - Pagamento", "5ª chamada - Fluxo principal - Acompanhamento e entrega"},
            summary = "Recupera pedido",
            description = "Exibe os dados de um pedido a partir de seu id",
            security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping("/{id}")
    public ResponseEntity<OrderControllerDTO> read(@PathVariable("id") Long id) throws NotFoundException {
        return ResponseEntity.ok(controller.read(id));
    }

    @Operation(
            summary = "Listar pedidos",
            description = "Lista todos os pedidos",
            security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping
    public ResponseEntity<List<OrderControllerDTO>> list() {
        return ResponseEntity.ok(controller.list());
    }

    @Operation(
            summary = "Excluir pedido",
            description = "Exclui um pedido por id",
            security = @SecurityRequirement(name = "bearerAuth"))
    @DeleteMapping("/{id}")
    public ResponseEntity<DefaultResponseDTO> delete(@PathVariable("id") Long id) throws NotFoundException {
        controller.delete(id);
        return ResponseEntity.ok().build();
    }

}