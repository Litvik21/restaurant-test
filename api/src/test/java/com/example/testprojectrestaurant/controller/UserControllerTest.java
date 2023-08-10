package com.example.testprojectrestaurant.controller;

import com.example.testprojectrestaurant.model.Role;
import com.example.testprojectrestaurant.model.User;
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
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Set;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {
    @MockBean
    private UserService userService;
    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        RestAssuredMockMvc.mockMvc(mockMvc);
    }

    @Test
    @WithMockUser(username = "staff", roles = "STAFF")
    void shouldGetUserById() {
        Role role = new Role();
        role.setRoleName(Role.RoleName.USER);

        User user = new User();
        user.setId(1L);
        user.setEmail("litvik@gmail.com");
        user.setUsername("litvik");
        user.setPassword("Password");
        user.setRole(Set.of(role));

        Mockito.when(userService.get(user.getId())).thenReturn(user);

        RestAssuredMockMvc.given()
                .when()
                .get("/users/1")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("id", Matchers.equalTo(1))
                .body("email", Matchers.equalTo("litvik@gmail.com"))
                .body("username", Matchers.equalTo("litvik"));

    }

}