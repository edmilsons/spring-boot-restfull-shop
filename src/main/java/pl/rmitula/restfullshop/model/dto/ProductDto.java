package pl.rmitula.restfullshop.model.dto;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class ProductDto implements Serializable {

    private Long id;
    @NotNull
    private Long category;
    @NotNull
    private String name;
    @NotNull
    private Integer quanityInStock;
    @NotNull
    private Double price;

    public ProductDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCategory() {
        return category;
    }

    public void setCategory(Long category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQuanityInStock() {
        return quanityInStock;
    }

    public void setQuanityInStock(Integer quanityInStock) {
        this.quanityInStock = quanityInStock;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
