package br.com.grupo63.serviceorder.entity.product;

import br.com.grupo63.techchallenge.common.domain.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Product extends Entity {

    private String name;
    private Double price;
    private Category category;

    public Product(Long id) {
        this.id = id;
    }

    public Product(Long id, boolean deleted, String name, Double price, Category category) {
        super(id, deleted);
        this.name = name;
        this.price = price;
        this.category = category;
    }

}
