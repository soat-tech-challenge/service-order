package br.com.grupo63.serviceorder.gateway.product.entity;

import br.com.grupo63.serviceorder.entity.product.Category;
import br.com.grupo63.techchallenge.common.gateway.repository.entity.PersistenceEntity;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "cat_category", indexes = {})
public class CategoryPersistenceEntity extends PersistenceEntity {

    @Basic
    @Column(name = "name", nullable = false)
    private String name;

    public CategoryPersistenceEntity(Category category) {
        super(category);
        this.name = category.getName();
    }

    public Category toModel() {
        return new Category(this.getId(),
                this.isDeleted(),
                this.getName());
    }

}
