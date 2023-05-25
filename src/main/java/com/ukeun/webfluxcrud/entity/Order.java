package com.ukeun.webfluxcrud.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Getter
@Setter
@Table("orders")
public class Order {

    @Id
    @Column("id")
    private Long id;

    @Column("amount")
    private Double amount;

    @Column("placedDate")
    private LocalDateTime placedDate;

    public Order(Double amount, LocalDateTime placedDate) {
        this.amount = amount;
        this.placedDate = placedDate;
    }
}
