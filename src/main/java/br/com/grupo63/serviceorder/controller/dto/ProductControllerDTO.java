package br.com.grupo63.serviceorder.controller.dto;

import br.com.grupo63.techchallenge.common.controller.dto.AbstractControllerDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class ProductControllerDTO extends AbstractControllerDTO {

    private String name;

    private Double price;

    private Long category;

}
