package com.ecommerce;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "users")
// A classe continuará sendo referenciada como "User" mas será criada
// no banco como a tabela "users". Isso é necessário porque "user" é
// uma palavra reservada no banco de dados.
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Enumerated(EnumType.STRING)
    private Status status;
    private String firstName;
    private String lastName;
    @Column(unique = true) // impede o registro de mais de um usuário com o mesmo email
    private String email;
    private String phone;
    private String password;
    private Boolean newsletter;

    // enum interno porque é usado apenas nesta classe
    public enum Status {
        ACTIVE,
        INACTIVE,
        SUSPENDED
    }

    public User active() {
        setStatus(Status.ACTIVE);
        return this;
    }
}
