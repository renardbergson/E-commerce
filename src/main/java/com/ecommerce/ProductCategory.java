package com.ecommerce;

import lombok.Getter;

@Getter
public enum ProductCategory {
    // os parênteses após cada valor do enum são argumentos passados
    // para o construtor do enum. Cada constante do enum é na prática
    // uma instância dele mesmo e, neste caso, com os campos "description"
    // e "icon". Assim, é possível armazenar dados por constante do enum
    // e expor esses dados via métodos: getDescription() e getIcon().
    // Aqui foi eliminada a necessidade de ter esses métodos escritos
    // manualmente, usando a anotação @Getter do Lombok.
    ELECTRONICS("Eletrônicos", "fa-mobile-alt"),
    CLOTHING("Roupas", "fa-tshirt"),
    HOME_GARDEN("Casa e Jardim", "fa-chair"),
    SPORTS("Esportes", "fa-running"),
    TOYS("Brinquedos", "fa-puzzle-piece"),
    BEAUTY("Beleza", "fa-magic"),
    AUTOMOTIVE("Automotivo", "fa-car");

    private final String description;
    private final String icon;

    ProductCategory(String description, String icon) {
        this.description = description;
        this.icon = icon;
    }
}
