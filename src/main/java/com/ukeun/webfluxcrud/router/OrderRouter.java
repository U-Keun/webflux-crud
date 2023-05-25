package com.ukeun.webfluxcrud.router;

import com.ukeun.webfluxcrud.handler.OrderHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class OrderRouter {

    @Autowired
    OrderHandler orderHandler;
    @Bean
    public RouterFunction<ServerResponse> orderRoutes() {
        return RouterFunctions
                .route(RequestPredicates.GET("/api/order/getAll"),
                        orderHandler::getAllOrders)
//                .andRoute(RequestPredicates.GET("/api/order/{id}"), // path variable 방법
                .andRoute(RequestPredicates.GET("/api/order"),
                        orderHandler::getOrderById)
                .andRoute(RequestPredicates.POST("/api/order/create")
                        // Json 형식으로 데이터가 들어와야 받을 수 있도록 설정
                                .and(RequestPredicates
                                        .accept(MediaType.APPLICATION_JSON)),
                        orderHandler::createOrder)
                .andRoute(RequestPredicates.PUT("/api/order/update/{id}")
                        .and(RequestPredicates
                                .accept(MediaType.APPLICATION_JSON)),
                        orderHandler::updateOrder)
                .andRoute(RequestPredicates.DELETE("/api/order/delete/{id}"),
                        orderHandler::deleteOrder);
    }
}
