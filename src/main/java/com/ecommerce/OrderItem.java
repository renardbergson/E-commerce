package com.ecommerce;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor // construtor sem parâmetros, usado pelo JPA
@ToString
@Entity
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false) // não aceita valores nulos
    private Order order;
    // Leitura correta: MANY OrderItem para UM Order
    // O OrderItem não é global, ele nasce e morre com o Order
    // ou seja, aqui não levamos em conta outros pedidos do mesmo
    // cliente ou de outros clientes
    // 🚩Dica: O “to” do JPA não é bidirecional, é direcional,
    // ele descreve uma seta, não uma frase espelhada, que
    // precisa ser lida ao contrário

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;
    // Leitura correta: MANY OrderItem podem referenciar UM mesmo Product
    // 🚩Product é global, ele existe antes, durante e depois de um pedido
    // Por isso, sim, aqui estamos considerante o contexto global, em que
    // mais de um pedido do mesmo cliente ou até de outros clientes podem
    // referenciar o mesmo produto

    private Double priceAtPurchase;
    private Integer quantity;
    private Double subTotal;

    public OrderItem(Product product, Double price, Integer quantity) {
        // construtor com as regras de negócio
        this.product = product;
        this.priceAtPurchase = price;
        this.quantity = quantity;
        this.subTotal = price * quantity;
    }
}
