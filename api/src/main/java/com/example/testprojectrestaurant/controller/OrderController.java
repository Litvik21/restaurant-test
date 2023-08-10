package com.example.testprojectrestaurant.controller;

import com.example.testprojectrestaurant.dto.mapper.OrderMapper;
import com.example.testprojectrestaurant.dto.OrderRequestDto;
import com.example.testprojectrestaurant.dto.OrderResponseDto;
import com.example.testprojectrestaurant.dto.OrderUpdateDto;
import com.example.testprojectrestaurant.model.Order;
import com.example.testprojectrestaurant.service.OrderService;
import com.example.testprojectrestaurant.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService service;
    private final OrderMapper mapper;
    private final UserService userService;

    @PostMapping("/new")
    @ApiOperation(value = "Add new order")
    public OrderResponseDto addOrder(@RequestBody OrderRequestDto dto,
                                     Authentication auth) {
        Order order = service.save(mapper.toModel(dto, auth.getName()));

        return mapper.toDto(order);
    }

    @GetMapping(value = "/newOrder", headers = "Accept=*/*",
            consumes = MediaType.ALL_VALUE, produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    @ApiOperation(value = "Listening for updates of new orders")
    public SseEmitter streamData() {
        return service.getEmitter();
    }

    @PutMapping("/update/{orderId}")
    @ApiOperation(value = "Update order status")
    public OrderResponseDto updateFunction(@PathVariable Long orderId,
                                           @RequestBody OrderUpdateDto dto) {
        Order order = mapper.updateToModel(dto);
        order.setId(orderId);
        return mapper.toDto(service.update(order));
    }

    @GetMapping("/my")
    @ApiOperation(value = "Find orders of current user")
    public List<OrderResponseDto> getAllByCurrentUser(Authentication auth) {
        return service.findOrdersOfUser(userService.getIdByUsername(auth.getName())).stream()
                .map(mapper::toDto)
                .toList();
    }
}
