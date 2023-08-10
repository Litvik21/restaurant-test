package com.example.testprojectrestaurant.controller;

import com.example.testprojectrestaurant.dto.OrderRequestDto;
import com.example.testprojectrestaurant.dto.OrderResponseDto;
import com.example.testprojectrestaurant.dto.OrderUpdateDto;
import com.example.testprojectrestaurant.dto.mapper.OrderMapper;
import com.example.testprojectrestaurant.model.Order;
import com.example.testprojectrestaurant.model.User;
import com.example.testprojectrestaurant.service.OrderService;
import com.example.testprojectrestaurant.service.UserService;
import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.util.Collections;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.authentication;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class OrderControllerTest {
    @MockBean
    private OrderService orderService;
    @MockBean
    private UserService userService;
    @MockBean
    private OrderMapper orderMapper;
    @Autowired
    private MockMvc mockMvc;
    private Order order;

    @BeforeEach
    void setUp() {
        RestAssuredMockMvc.mockMvc(mockMvc);
        User user = new User();
        user.setId(1L);
        user.setUsername("litvik");
        order = new Order();
        order.setId(1L);
        order.setProduct("Product");
        order.setStatus(Order.Status.RECEIVED);
        order.setUser(user);
    }

    @Test
    @WithMockUser(username = "litvik", roles = "USER")
    void shouldAddNewOrder() {
        OrderRequestDto dto = new OrderRequestDto("Product");
        Mockito.when(orderMapper.toModel(dto, "litvik")).thenReturn(order);
        Mockito.when(orderMapper.toDto(order)).thenReturn(new OrderResponseDto(order.getId(),
                order.getProduct(), order.getStatus().name(), order.getUser().getId()));
        Mockito.when(orderService.save(order)).thenReturn(new Order(order.getId(),
                order.getProduct(), order.getStatus(), order.getUser()));

        RestAssuredMockMvc.given()
                .contentType(ContentType.JSON)
                .body(dto)
                .when()
                .post("/orders/new")
                .then()
                .statusCode(200)
                .body("id", Matchers.equalTo(1))
                .body("product", Matchers.equalTo("Product"))
                .body("status", Matchers.equalTo("RECEIVED"))
                .body("userId", Matchers.equalTo(1));

    }

    @Test
    @WithMockUser(username = "litvik", roles = "STAFF")
    void shouldUpdateOrderStatus() {
        OrderUpdateDto dto = new OrderUpdateDto("Product", Order.Status.ACCEPT.name(), 1L);
        Mockito.when(orderMapper.updateToModel(dto)).thenReturn(order);

        Mockito.when(orderMapper.toDto(order)).thenReturn(new OrderResponseDto(order.getId(),
                order.getProduct(), dto.status(), order.getUser().getId()));

        Mockito.when(orderService.update(order)).thenReturn(new Order(order.getId(),
                order.getProduct(), Order.Status.valueOf(dto.status()), order.getUser()));

        RestAssuredMockMvc.given()
                .contentType(ContentType.JSON)
                .body(dto)
                .when()
                .put("/orders/update/1")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("id", Matchers.equalTo(1))
                .body("product", Matchers.equalTo("Product"))
                .body("status", Matchers.equalTo("ACCEPT"))
                .body("userId", Matchers.equalTo(1));

    }

    @Test
    public void testGetAllByCurrentUser() throws Exception {
        Mockito.when(userService.getIdByUsername("litvik")).thenReturn(1L);
        Mockito.when(orderService.findOrdersOfUser(1L)).thenReturn(Collections.emptyList());

        mockMvc.perform(MockMvcRequestBuilders.get("/orders/my")
                        .with(authentication(getTestAuthentication())))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray());
    }

    private Authentication getTestAuthentication() {
        return new UsernamePasswordAuthenticationToken("litvik", "Password", Collections.emptyList());
    }
}
