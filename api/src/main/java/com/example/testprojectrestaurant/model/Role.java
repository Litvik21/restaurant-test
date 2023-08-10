package com.example.testprojectrestaurant.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;
    @Enumerated(EnumType.STRING)
    private RoleName roleName;

    public enum RoleName {
        STAFF("STAFF"),
        USER("USER");
        private String value;

        RoleName(String value) {
            this.value = value;
        }
    }
}
