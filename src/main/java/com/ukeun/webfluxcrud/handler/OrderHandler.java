package com.ukeun.webfluxcrud.handler;

import com.ukeun.webfluxcrud.request.CreateOrderRequest;
import com.ukeun.webfluxcrud.request.UpdateOrderRequest;
import com.ukeun.webfluxcrud.response.OrderResponse;
import com.ukeun.webfluxcrud.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class OrderHandler {

    private final OrderService orderService;
    /*
    GET /api/order/getAll
     */
    public Mono<ServerResponse> getAllOrders(ServerRequest serverRequest) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(orderService.getAllOrders()
                        .map(orderService::mapOrderToOrderResponse), OrderResponse.class);
    }
    /*
    GET /api/order/{id}
     */
    public Mono<ServerResponse> getOrderById(ServerRequest serverRequest) {
//        Long id = Long.valueOf(serverRequest.pathVariable("id")); // path variable 방법
        Long id = Long.valueOf(serverRequest.queryParam("id").get());

        // ServerRequest로부터 header 값을 받아오기 - access, refresh token 같은 것을 받아올 수 있을 것 같다.
        String firstHeader = serverRequest.headers().firstHeader("first-header");
        String secondHeader = serverRequest.headers().firstHeader("second-header");

        System.out.println(firstHeader);
        System.out.println(secondHeader);

        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                // 응답할 때 header에 값 넣어주기(순서대로 key, value)
                .header("response-header", "response-header-value")
                .body(orderService.getOrderById(id)
                        .map(orderService::mapOrderToOrderResponse),
                        OrderResponse.class);
    }
    /*
    POST /api/order/create
     */
    public Mono<ServerResponse> createOrder(ServerRequest serverRequest) {
        // 들어온 요청을 Mono 객체로 바꿔준다.
        Mono<CreateOrderRequest> monoCreateOrderRequest = serverRequest.bodyToMono(CreateOrderRequest.class);
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(
                        monoCreateOrderRequest
                                .map(orderService::mapCreateOrderRequestToOrder) // Order 객체로 바꾸고
                                .flatMap(orderService::saveOrder) // Order를 DB에 저장한 뒤
                                .map(orderService::mapOrderToOrderResponse), // 응답용 객체로 바꿔준다.
                        OrderResponse.class
                );
    }
    /*
    PUT /api/order/update/{id}
     */
    public Mono<ServerResponse> updateOrder(ServerRequest serverRequest) {
        Mono<UpdateOrderRequest> monoUpdateOrderRequest = serverRequest.bodyToMono(UpdateOrderRequest.class);

        Long id = Long.valueOf(serverRequest.pathVariable("id"));
        // JPA..보고 싶다..
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(
                        orderService.getOrderById(id)
                                .flatMap(order ->
                                        monoUpdateOrderRequest.map(request ->
                                                orderService.mapUpdateOrderRequestToOrder(order, request)))
                                .flatMap(orderService::saveOrder)
                                .map(orderService::mapOrderToOrderResponse)
                        , OrderResponse.class
                );
    }
    /*
    DELETE /api/order/delete/{id}
     */
    public Mono<ServerResponse> deleteOrder(ServerRequest serverRequest) {

        Long id = Long.valueOf(serverRequest.pathVariable("id"));

        return ServerResponse.ok()
                .contentType(MediaType.TEXT_PLAIN)
                .body(
                        orderService.deleteOrder(id)
                                .map(a -> "Order has been deleted"),
                        String.class
                );
    }
}
