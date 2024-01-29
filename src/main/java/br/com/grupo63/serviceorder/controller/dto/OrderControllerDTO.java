package br.com.grupo63.serviceorder.controller.dto;

import br.com.grupo63.techchallenge.common.controller.dto.AbstractControllerDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderControllerDTO extends AbstractControllerDTO {

    private Double totalPrice;
    private String clientId;
    private List<OrderItemControllerDTO> items = new ArrayList<>();

}
