package com.example.testprojectrestaurant.service;

import com.example.testprojectrestaurant.model.Order;
import com.example.testprojectrestaurant.model.User;
import com.example.testprojectrestaurant.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest {
    @InjectMocks
    private OrderServiceImpl orderService;
    @Mock
    private UserService userService;
    @Mock
    private OrderRepository orderRepository;
    private Order order;
    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId(1L);
        user.setUsername("litvik");
        order = new Order();
        order.setId(1L);
        order.setProduct("Product");
        order.setStatus(Order.Status.RECEIVED);
        order.setUser(user);
    }

    @Test
    void shouldUpdateOrder() {
        Mockito.when(orderRepository.save(order)).thenReturn(order);

        Order actual = orderService.update(order);

        assertEquals(order, actual);
    }

    @Test
    void shouldGetAllByUser() {
        Mockito.when(userService.get(1L)).thenReturn(user);
        Mockito.when(orderRepository.findAllByUser(user)).thenReturn(List.of(order));

        List<Order> actual = orderService.findOrdersOfUser(1L);

        assertEquals(1, actual.size());
        assertEquals(order.getProduct(), actual.get(0).getProduct());
    }
}