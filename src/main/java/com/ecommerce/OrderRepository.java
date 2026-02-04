package com.ecommerce;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepository extends CrudRepository<Order, Long> {
    List<Order> findByUserId(Long userId);
    // Interessante: Order não tem uma propriedade userId,
    // mas o Spring Data JPA entende que deve navegar
    // na relação ManyToOne com User e usar o id do User
    // e construir um SELECT com filtro automaticamente 
}
