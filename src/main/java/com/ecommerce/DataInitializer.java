package com.ecommerce;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {
    @Autowired
    ProductRepository productRepository;

    @Override
    public void run(String... args) throws Exception {
        // Esta classe será executada na inicialização do
        // Spring Boot por causa da anotação @Component.
        System.out.println("Data initializer class is being executed...");

        // saveAll() espera Iterable<Object>, não uma sequência de objetos soltos,
        // então, List.of() pega os objetos soltos do tipo "Product" e transforma
        // numa List, que é um Iterable, sendo aceito pelo metodo
        productRepository.saveAll(
                List.of(
                        insertProduct("Notebook", ProductCategory.ELECTRONICS, "ativo", 3850.00, "Notebook Lenovo Ideapad S145"),
                        insertProduct("Smartphone", ProductCategory.ELECTRONICS, "ativo", 1500.00, "Smartphone Samsung Galaxy S20"),
                        insertProduct("Camiseta", ProductCategory.CLOTHING, "ativo", 50.00, "Camiseta 100% algodão"),
                        insertProduct("Corolla XEI 2019", ProductCategory.AUTOMOTIVE, "ativo", 105000.00, "Corolla XEI 2019, motor 2.0, Branco"),
                        insertProduct("Fone de Ouvido", ProductCategory.ELECTRONICS, "ativo", 200.00, "Fone de ouvido Bluetooth JBL"),
                        insertProduct("Smart TV 50", ProductCategory.ELECTRONICS, "ativo", 2650.00, "Smart TV 50 polegadas 4K"),
                        insertProduct("Tablet 10", ProductCategory.ELECTRONICS, "ativo", 1200.00, "Tablet 10 polegadas com 64GB"),
                        insertProduct("Camera Digital", ProductCategory.ELECTRONICS, "ativo", 2100.00, "Camera compacta 20MP"),
                        insertProduct("Smartwatch", ProductCategory.ELECTRONICS, "ativo", 650.00, "Smartwatch com GPS e monitor cardiaco"),
                        insertProduct("Roteador Wi-Fi", ProductCategory.ELECTRONICS, "ativo", 220.00, "Roteador dual band AC1200"),
                        insertProduct("Console", ProductCategory.ELECTRONICS, "ativo", 3200.00, "Console com 1TB e controle sem fio"),
                        insertProduct("Mouse Gamer", ProductCategory.ELECTRONICS, "ativo", 180.00, "Mouse gamer 12000 DPI"),
                        insertProduct("Teclado Mecanico", ProductCategory.ELECTRONICS, "ativo", 420.00, "Teclado mecanico RGB switch blue"),
                        insertProduct("Jaqueta Jeans", ProductCategory.CLOTHING, "ativo", 180.00, "Jaqueta jeans masculina"),
                        insertProduct("Tenis Esportivo", ProductCategory.CLOTHING, "ativo", 260.00, "Tenis leve para corrida"),
                        insertProduct("Vestido Florido", ProductCategory.CLOTHING, "ativo", 120.00, "Vestido midi florido"),
                        insertProduct("Calca Jeans", ProductCategory.CLOTHING, "ativo", 140.00, "Calca jeans skinny"),
                        insertProduct("Moletom Canguru", ProductCategory.CLOTHING, "ativo", 150.00, "Moletom com capuz"),
                        insertProduct("Sofa 3 Lugares", ProductCategory.HOME_GARDEN, "ativo", 1900.00, "Sofa de tecido cinza"),
                        insertProduct("Mesa Jantar 6 Lugares", ProductCategory.HOME_GARDEN, "ativo", 2200.00, "Mesa de madeira com 6 cadeiras"),
                        insertProduct("Conjunto de Panelas", ProductCategory.HOME_GARDEN, "ativo", 350.00, "Conjunto antiaderente com 5 pecas"),
                        insertProduct("Aspirador de Po", ProductCategory.HOME_GARDEN, "ativo", 480.00, "Aspirador vertical 1000W"),
                        insertProduct("Ventilador Torre", ProductCategory.HOME_GARDEN, "ativo", 320.00, "Ventilador silencioso com timer"),
                        insertProduct("Luminaria de Mesa", ProductCategory.HOME_GARDEN, "ativo", 90.00, "Luminaria LED ajustavel"),
                        insertProduct("Kit Jardinagem", ProductCategory.HOME_GARDEN, "ativo", 110.00, "Kit com pa, rastelo e luvas"),
                        insertProduct("Bicicleta MTB", ProductCategory.SPORTS, "ativo", 1800.00, "Bicicleta aro 29 com 21 marchas"),
                        insertProduct("Bola de Futebol", ProductCategory.SPORTS, "ativo", 120.00, "Bola oficial tamanho 5"),
                        insertProduct("Kit Halteres", ProductCategory.SPORTS, "ativo", 260.00, "Kit halteres ajustavel 10kg"),
                        insertProduct("Tapete de Yoga", ProductCategory.SPORTS, "ativo", 80.00, "Tapete antiderrapante 6mm"),
                        insertProduct("Raquete de Tenis", ProductCategory.SPORTS, "ativo", 420.00, "Raquete leve com capa"),
                        insertProduct("Luvas de Boxe", ProductCategory.SPORTS, "ativo", 220.00, "Luvas 12oz para treino"),
                        insertProduct("Boneca", ProductCategory.TOYS, "ativo", 90.00, "Boneca com acessorios"),
                        insertProduct("Carrinho Controle Remoto", ProductCategory.TOYS, "ativo", 180.00, "Carrinho 4x4 com bateria"),
                        insertProduct("Quebra Cabeca 1000 Pecas", ProductCategory.TOYS, "ativo", 85.00, "Quebra cabeca paisagem 1000 pecas"),
                        insertProduct("Blocos de Montar", ProductCategory.TOYS, "ativo", 130.00, "Blocos de montar 500 pecas"),
                        insertProduct("Jogo de Tabuleiro", ProductCategory.TOYS, "ativo", 150.00, "Jogo de estrategia para 2-4 jogadores"),
                        insertProduct("Shampoo", ProductCategory.BEAUTY, "ativo", 35.00, "Shampoo hidratante 300ml"),
                        insertProduct("Perfume", ProductCategory.BEAUTY, "ativo", 180.00, "Perfume floral 50ml"),
                        insertProduct("Creme Hidratante", ProductCategory.BEAUTY, "ativo", 45.00, "Creme hidratante corporal 200ml"),
                        insertProduct("Secador de Cabelo", ProductCategory.BEAUTY, "ativo", 210.00, "Secador 2000W com difusor"),
                        insertProduct("Kit Maquiagem", ProductCategory.BEAUTY, "ativo", 160.00, "Kit com sombras e pincel"),
                        insertProduct("Kit Ferramentas Automotivas", ProductCategory.AUTOMOTIVE, "ativo", 260.00, "Kit com chaves e alicates"),
                        insertProduct("Capa de Banco", ProductCategory.AUTOMOTIVE, "ativo", 120.00, "Capa de banco em couro sintetico"),
                        insertProduct("GPS Automotivo", ProductCategory.AUTOMOTIVE, "ativo", 520.00, "GPS 7 polegadas com mapas"),
                        insertProduct("Pneu 175/70 R13", ProductCategory.AUTOMOTIVE, "ativo", 360.00, "Pneu para carros compactos"),
                        insertProduct("Oleo 5W30", ProductCategory.AUTOMOTIVE, "ativo", 55.00, "Oleo sintetico 1L 5W30")
                )
        );
    }

    private Product insertProduct(String name, ProductCategory category, String state, double price, String description) {
        Product product = new Product();
        product.setName(name);
        product.setCategory(category);
        product.setState(state);
        product.setPrice(price);
        product.setDescription(description);
        return product;
    }
}
