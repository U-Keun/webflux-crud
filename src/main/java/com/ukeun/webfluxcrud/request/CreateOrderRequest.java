package com.ukeun.webfluxcrud.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class CreateOrderRequest {

    private Double amount;
    private LocalDateTime placedDate;
}
