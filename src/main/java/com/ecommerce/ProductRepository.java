package com.ecommerce;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
    // Este metodo não foi herdado da interface CrudRepository
    // ⚠️ No Spring Data JPA, métodos como "findBy..." geram consultas automaticamente.
    // A parte após "By" deve corresponder a uma propriedade existente na entidade
    // (ou propriedade aninhada). Ex.: "findByCategoria" só funciona se "categoria"
    // existir em Product, caso contrário, falha. Para casos fora da convenção, use @Query.
    public Iterable<Product> findByCategory(ProductCategory category);
}
