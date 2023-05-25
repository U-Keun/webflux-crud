package com.ukeun.webfluxcrud.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@AllArgsConstructor
public class OrderResponse {

    private Long id;
    private Double amount;
    private LocalDateTime placedDate;
}
