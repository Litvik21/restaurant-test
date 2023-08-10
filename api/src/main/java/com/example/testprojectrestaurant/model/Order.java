package com.example.testprojectrestaurant.model;

import lombok.*;
import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;
    @Column(name = "product")
    private String product;
    @Enumerated(EnumType.STRING)
    @Column(name = "order_function")
    private Function function;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public enum Function {
        RECEIVED("RECEIVED"),
        ACCEPT("ACCEPT"),
        REJECT("REJECT");

        Function(String value) {
        }
    }

}
