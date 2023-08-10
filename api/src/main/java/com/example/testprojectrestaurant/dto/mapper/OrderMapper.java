package com.example.testprojectrestaurant.dto.mapper;

import com.example.testprojectrestaurant.dto.OrderRequestDto;
import com.example.testprojectrestaurant.dto.OrderResponseDto;
import com.example.testprojectrestaurant.dto.OrderUpdateDto;
import com.example.testprojectrestaurant.model.Order;
import com.example.testprojectrestaurant.model.User;
import com.example.testprojectrestaurant.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class OrderMapper {
    private final UserService userService;

    public Order toModel(OrderRequestDto dto, String username) {
        User user = userService.findByUsername(username).orElseThrow(()
                -> new RuntimeException("Can't find user by username: " + username));
        Order order = new Order();
        order.setProduct(dto.product());
        order.setStatus(Order.Status.RECEIVED);
        order.setUser(user);

        return order;
    }

    public OrderResponseDto toDto(Order order) {
        return new OrderResponseDto(
                order.getId(),
                order.getProduct(),
                order.getStatus().name(),
                order.getUser().getId()
        );
    }

    public Order updateToModel(OrderUpdateDto dto) {
        User user = userService.get(dto.userId());

        Order order = new Order();
        order.setProduct(dto.product());
        order.setStatus(Order.Status.valueOf(dto.status().toUpperCase()));
        order.setUser(user);


        return order;
    }
}
