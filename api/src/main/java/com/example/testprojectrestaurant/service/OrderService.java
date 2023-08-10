package com.example.testprojectrestaurant.service;

import com.example.testprojectrestaurant.model.Order;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import java.util.List;

public interface OrderService {
    Order save(Order order);

    Order update(Order order);

    SseEmitter getEmitter();

    List<Order> findOrdersOfUser(Long userId);

}
