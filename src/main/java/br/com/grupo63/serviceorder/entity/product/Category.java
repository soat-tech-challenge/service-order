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

public class Category extends Entity {

    private String name;

    public Category(Long id, boolean deleted, String name) {
        super(id, deleted);
        this.name = name;
    }

    public Category(Long id) {
        this.id = id;
    }

}