package pl.rmitula.restfullshop.model.dto;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class CategoryDto implements Serializable {

    private Long id;
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
}
