package org.fasttrackit.pizzaonlineapi.repository;

import org.fasttrackit.pizzaonlineapi.domain.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    //queries by nested properties
    Page<Review> findByPizzaIs(Long pizzaId, Pageable pageable);

}
