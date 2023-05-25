package com.ukeun.webfluxcrud.repository;

import com.ukeun.webfluxcrud.entity.Order;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends R2dbcRepository<Order, Long> {

}
