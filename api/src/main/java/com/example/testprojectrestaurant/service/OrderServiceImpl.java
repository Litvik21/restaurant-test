package com.example.testprojectrestaurant.service;

import com.example.testprojectrestaurant.dto.mapper.OrderMapper;
import com.example.testprojectrestaurant.model.Order;
import com.example.testprojectrestaurant.model.User;
import com.example.testprojectrestaurant.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@AllArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {
    private static final Logger LOGGER = Logger.getLogger(OrderServiceImpl.class.getName());
    private final OrderRepository repository;
    private final SseEmitter emitter;
    private final OrderMapper mapper;
    private final UserService userService;

    @Override
    public Order save(Order order) {
        Order savedOrder = repository.save(order);
        try {
            emitter.send(mapper.toDto(savedOrder));
        } catch (IOException e) {
            LOGGER.log(Level.WARNING, "Can't send emitter information.");
        }
        return savedOrder;
    }

    @Override
    public List<Order> getAll() {
        return repository.findAll();
    }

    @Override
    public Order update(Order order) {
        return repository.save(order);
    }

    @Override
    public SseEmitter getEmitter() {
        return emitter;
    }

    @Override
    public List<Order> findOrdersOfUser(Long userId) {
        User user = userService.get(userId);
        return repository.findAllByUser(user);
    }
}
