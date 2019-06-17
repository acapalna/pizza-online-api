package org.fasttrackit.pizzaonlineapi.repository;

import org.fasttrackit.pizzaonlineapi.domain.Pizza;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

//Long is wrapper for primitive type long
public interface PizzaRepository extends JpaRepository<Pizza, Long> {

    //queries derives from method names springBoot
    Page<Pizza> findByNameContaining(String partialName, Pageable pageable);
    Page<Pizza> findByNameContainingAndWeightGreaterThanEqual(String partialName, Double minQuantity, Pageable pageable);
    Page<Pizza> findByNameContainingAndPriceBetween(String partialName, Double minPrice, Double maxPrice, Pageable pageable);
    Page<Pizza> findAllByPriceBetween(Double minPrice, Double maxPrice, Pageable pageable);
    Page<Pizza> findAllByPriceGreaterThan(Double minPrice, Pageable pageable);
    Page<Pizza> findAllByPriceLessThan(Double maxPrice, Pageable pageable);


}
