package com.ukeun.webfluxcrud.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table("orders")
@Getter
@Setter
public class Order {

    @Id
    @Column("id")
    private Long id;

    @Column("amount")
    private Double amount;

    @Column("placed_date")
    private LocalDateTime placeDate;
}
