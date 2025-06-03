package org.combs.micro.hc_tests_service.entity;

import jakarta.persistence.*;
import lombok.*;
import org.combs.micro.hc_tests_service.enums.RoleType;

import jakarta.persistence.*;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "roles", schema = "users_schema")
@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", nullable = false)
    @Enumerated(EnumType.STRING)
    private RoleType name;

    @OneToMany(mappedBy = "role",fetch = FetchType.LAZY)
    private Set<User> users;
}
