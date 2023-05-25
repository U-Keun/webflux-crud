package com.ukeun.webfluxcrud.service;

import com.ukeun.webfluxcrud.entity.Order;
import com.ukeun.webfluxcrud.repository.OrderRepository;
import com.ukeun.webfluxcrud.request.CreateOrderRequest;
import com.ukeun.webfluxcrud.request.UpdateOrderRequest;
import com.ukeun.webfluxcrud.response.OrderResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public Flux<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public OrderResponse mapOrderToOrderResponse(Order order) {
        return new OrderResponse(order.getId(),
                order.getAmount(), order.getPlacedDate());
    }

    public Mono<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    public Order mapCreateOrderRequestToOrder(CreateOrderRequest createOrderRequest) {
        return new Order(createOrderRequest.getAmount(), createOrderRequest.getPlacedDate());
    }

    /*
    요청으로 들어온 주문을 DB에 저장
     */
    public Mono<Order> saveOrder(Order order) {
        return orderRepository.save(order); // MySQL에 저장하면서 id 값 생성
    }

    public Order mapUpdateOrderRequestToOrder(Order order, UpdateOrderRequest updateOrderRequest) {
        order.setAmount(updateOrderRequest.getAmount());
        order.setPlacedDate(updateOrderRequest.getPlacedDate());

        return order;
    }

    public Mono<Void> deleteOrder(Long id) {
        return orderRepository.deleteById(id);
    }
}
