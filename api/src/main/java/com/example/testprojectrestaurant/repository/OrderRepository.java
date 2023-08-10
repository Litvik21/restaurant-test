package com.example.testprojectrestaurant.repository;

import com.example.testprojectrestaurant.model.Order;
import com.example.testprojectrestaurant.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("SELECT distinct o FROM orders o WHERE o.user = :user")
    List<Order> findAllByUser(@Param("user") User user);
}
