package com.ecommerce;

import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
public class ShoppingCart {
    // Usa Map em vez de List para evitar produtos duplicados no carrinho.
    // Chave: ID do produto (Long) | Valor: CartItem (produto + quantidade)
    // Vantagem: incrementa quantidade automaticamente ao não permitir IDs
    // duplicados, o que elimina a necessidade de implementar um loop for +
    // lógica manual para incrementar a quantidade de um produto já existente no carrinho.
    private final Map<Long, CartItem> items; // ou: private final Map<Long, CartItem> items = new HashMap<>();
    private boolean isOpen;

    public ShoppingCart() {
        items = new HashMap<>();
        isOpen = false;
    }

    public void addProduct(Product item) {
        Long productId = item.getId();
        if(items.containsKey(productId)) {
            items.get(productId).incrementQuantity();
        } else {
            // Chamando o construtor de CartItem
            // Funciona como uma espécie de conversão
            items.put(productId, new CartItem(item, 1));
        }
    }

    public int getSize() {
        return items.size();
    }

    public Collection<CartItem> getItems() {
        return items.values();
    }

    public int getTotalQuantity() {
        int totalQuantity = 0;
        for(CartItem item : getItems()) {
            totalQuantity += item.getQuantity();
        }
        return totalQuantity;
    }

    public void printItems() {
        System.out.println("---- PRODUTOS NO CARRINHO ----");
        for(ShoppingCart.CartItem item : getItems()) {
            String productName = item.getProduct().getName();
            int quantity = item.getQuantity();
            Double subTotal = item.getSubTotal();
            System.out.println("Nome do produto: " + productName + ", Quantidade: " + quantity + ", Subtotal: " + subTotal);
            System.out.println("Quantidade total de itens no carrinho: " + getTotalQuantity());
            System.out.println("==> Total do pedido: " + getTotal());
        }
        System.out.println("------------------------------");
    }

    public Double getTotal() {
        // 🚩Sintaxe alternativa e mais moderno em relação
        // à que foi usada no metodo getTotalQuantity()
        // 1 - getItems()
        // Retorna Collection<CartItem> (todos os itens do carrinho)
        //
        // 2 - .stream()
        // Converte a Collection em um Stream (fluxo de dados)
        // Stream permite operações funcionais (map, filter, reduce, etc.)
        //
        // 3 - .mapToDouble(CartItem::getSubTotal)
        // Aqui tem 2 partes:
        // mapToDouble(...)
        // Transforma cada CartItem em um double
        // Cria um DoubleStream (stream especializado para números)
        //
        // 4 - CartItem::getSubTotal
        // Method reference (referência de metodo)
        // É uma redução para lambdas que chamam apenas um metodo
        // Equivalente a: item -> item.getSubTotal()
        // Significa: "Para cada objeto do tipo CartItem, chame o
        // metodo getSubTotal()"
        //
        // 5 - .sum()
        // Soma todos os valores do DoubleStream
        // Retorna o total
        return getItems()
                .stream()
                .mapToDouble(CartItem::getSubTotal)
                .sum();
    }

    // Inner Class (só é uma boa se não for uma entidade)
    // ℹ️ Regra do Java para inner classes: A classe externa (ShoppingCart)
    // tem acesso a todos os membros privados da inner class (CartItem),
    // e vice-versa.
    // ⚠️ atenção para o modificador "static"
    @Getter
    public static class CartItem {
        private final Product product;
        private int quantity;

        public CartItem(Product product, int quantity) {
            this.product = product;
            this.quantity = quantity;
        }

        public void incrementQuantity() {
            quantity++;
        }

        public Double getSubTotal() {
            return product.getPrice() * quantity;
        }
    }
}

