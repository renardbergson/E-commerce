package com.ecommerce;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "orders")
@Getter
@Setter
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne // ℹ️ O lado esquerdo da expressão se refere à classe atual
    @JoinColumn(name = "user_id") // o nome "user_id" será o nome da coluna
    private User user; // o que será gravado na coluna é o id do objeto
    // 👉 @JoinColumn diz ao JPA: “Este atributo 'user' é salvo como uma
    // coluna FK nessa tabela, e o nome dessa coluna é user_id.”
    // 🔑 Quem é o dono da relação?
    // No JPA: quem tem @JoinColumn é o dono, quem não tem (entidade User) usa
    // "mappedBy", ou seja, não cria coluna nenhuma, só aponta quem é o dono
    // 🧠 Pra fixar: quem tem a FK manda

    private String orderNumber;
    private Double totalValue;
    private Integer totalItems;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> items = new ArrayList<>();
    // "um pedido para muitos itens"...
    // ⬅️➡️ Relacionamento bidirecional, porque tanto o pedido aponta para o item quanto
    // o item aponta para o pedido
    // 🚩 mappedBy = "order": porque a FK está em OrderItem, no campo "order"
    // 🚩 cascade = CascadeType.ALL: toda operação relacionada a Order será propagada para OrderItem,
    // ou seja, se salvar um pedido, os itens são salvos, se atualizar um pedido, os itens são
    // atualizados também, se um pedido for apagado, os itens que pertencem a ele também serão apagados
    // 🚩 orphanRemoval = true: um OrderItem não pode existir sem um Order, se sair da
    // lista, deve ser removido do banco

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public Order() {
        // construtor sem parâmetros, usado pelo JPA
        orderNumber = "ORD/" + UUID.randomUUID().toString().substring(0, 8);
        createdAt = LocalDateTime.now();
        status = OrderStatus.PENDING;
    }

    public Order(User user, Double totalValue, Integer totalItems, PaymentMethod paymentMethod) {
        // construtor "de negócio"
        this(); // chama o construtor vazio
        this.user = user;
        this.totalValue = totalValue;
        this.totalItems = totalItems;
        this.paymentMethod = paymentMethod;
    }

    public void addItem(OrderItem item) {
        items.add(item);
        item.setOrder(this);
        // o campo "order" do item aponta para este pedido,
        // inicializado acima pelos construtores, esta classe
    }

    @Getter
    public enum OrderStatus {
        PENDING("Pendente"),
        CONFIRMED("Confirmado"),
        PAID("Pago"),
        SHIPPED("Enviado"),
        DELIVERED("Entregue"),
        CANCELLED("Cancelado");

        private final String description;
        OrderStatus(String description) {
            this.description = description;
        }
    }

    @Getter
    public enum PaymentMethod {
        PIX("PIX"),
        CREDIT_CARD("Cartão de Crédito"),
        DEBIT_CARD("Cartão de Débito"),
        BOLETO("Boleto Bancário");

        private final String description;
        PaymentMethod(String description) {
            this.description = description;
        }
    }
}
