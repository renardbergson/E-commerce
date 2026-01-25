package com.ecommerce;
import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.NoArgsConstructor;

// LOMBOK
@Getter // gera os getters para os campos da classe
@Setter // gera os setters para os campos da classe
@ToString // gera o metodo toString para a classe
@NoArgsConstructor // gera o construtor sem argumentos para a classe
// JPA
@Entity // transforma a classe em uma entidade JPA, será convertida em uma tabela no banco de dados
public class Product {
  @Id // define o campo "id" como chave primária
  @GeneratedValue(strategy = GenerationType.AUTO) // define a estratégia de geração da chave primária (auto-incremento)
  private Long id;
  private String name;

  // esta anotação serve para dizer ao JPA que
  // o enum deve ser guardado como texto, ou seja,
  // o nome da constante, e não o seu índice. Ela não é
  // obrigatória mas é recomendada, para evitar problemas
  // caso a ordem das constantes do enum mude no futuro.
  // Sem @Enumarated o padrão é ORDINAL (0, 1, 2...), de
  // modo que se o enum fosse reordenado ou um novo valor
  // fosse inserido ou removido, os números mudariam e os
  // dados no banco ficariam inconsistentes.
  @Enumerated(EnumType.STRING)
  private ProductCategory category;

  private Double price;
  private String state;
  private String description;
}
