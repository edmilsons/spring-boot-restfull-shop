package pl.rmitula.restfullshop.model.dto;

import javax.validation.constraints.NotNull;

public class ProductDto {

    private Long id;
    @NotNull
    private Long category;
    @NotNull
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCategoryId() {
        return category;
    }

    public void setCategoryId(Long category) {
        this.category = category;
    }
}
