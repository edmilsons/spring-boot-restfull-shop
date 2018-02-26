package pl.rmitula.restfullshop.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

//TODO: All Orders
public class Order implements Serializable {

    public enum Status {PLACED, APPROVED, DELIVERED}

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Product product;

    @NotNull
    private Integer quantity;

    @NotNull
    private Date orderDate;

    @Enumerated(EnumType.STRING)
    private Status status;


}
