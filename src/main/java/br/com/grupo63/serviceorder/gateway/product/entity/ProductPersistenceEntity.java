package br.com.grupo63.serviceorder.gateway.product.entity;

import br.com.grupo63.serviceorder.entity.product.Product;
import br.com.grupo63.techchallenge.common.gateway.repository.entity.PersistenceEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor

@Getter
@Setter

@Entity
@Table(name = "prd_product", indexes = {})
public class ProductPersistenceEntity extends PersistenceEntity {

    @Basic
    @Column(name = "name", nullable = false)
    private String name;

    @Basic
    @Column(name = "price", nullable = false)
    private Double price;

    @JoinColumn(name = "category", foreignKey = @ForeignKey(name = "fk_product_category"), nullable = false)
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    private CategoryPersistenceEntity category;

    public ProductPersistenceEntity(Product product) {
        super(product);
        this.name = product.getName();
        this.price = product.getPrice();
        if (product.getCategory() != null)
            this.category = new CategoryPersistenceEntity(product.getCategory());
    }

    public Product toModel() {
        return new Product(this.getId(),
                this.isDeleted(),
                this.getName(),
                this.getPrice(),
                this.getCategory() != null ? this.getCategory().toModel() : null);
    }

}
