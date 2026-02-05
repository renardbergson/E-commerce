package com.ecommerce;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

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
    private Role role;
    @Enumerated(EnumType.STRING)
    private Status status;
    private String firstName;
    private String lastName;
    @Column(unique = true) // impede o registro de mais de um usuário com o mesmo email
    private String email;
    private String phone;
    private String password;
    private Boolean newsletter;
    // somente o banco será responsável por preencher a coluna created_at
    // columnDefinition = "TIMESTAMP DEFAULT NOW()" => a coluna será definida como TIMESTAMP
    // e, como nenhum valor é informado na hora do insert, o banco usará NOW() como padrão
    @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT NOW()", insertable = false, updatable = false) //
    private LocalDateTime createdAt;

    // enum interno porque é usado apenas nesta classe

    @Getter
    public enum Status {
        ACTIVE("Ativo"),
        INACTIVE("Inativo"),
        SUSPENDED("Suspenso");

        private final String description;

        Status(String description) {
            this.description = description;
        }
    }

    @Getter
    public enum Role {
        ADMIN("Administrador"),
        CUSTOMER("Cliente");

        private final String description;

        Role(String description) {
            this.description = description;
        }
    }

    public User active() {
        setStatus(Status.ACTIVE);
        return this;
    }
}
