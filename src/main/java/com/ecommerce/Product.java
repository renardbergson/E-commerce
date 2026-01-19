package com.ecommerce;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.NoArgsConstructor;

@Getter // gera os getters para os campos da classe
@Setter // gera os setters para os campos da classe
@ToString // gera o método toString para a classe
@NoArgsConstructor // gera o construtor sem argumentos para a classe
@Entity // transforma a classe em uma entidade JPA, será convertida em uma tabela no banco de dados
public class Product {
  @Id // define o campo "id" como chave primária
  @GeneratedValue(strategy = GenerationType.AUTO) // define a estratégia de geração da chave primária (auto-incremento)
  private Long id;
  private String name;
  private String category;
  private Double price;
  private String state;
  private String description;
}
