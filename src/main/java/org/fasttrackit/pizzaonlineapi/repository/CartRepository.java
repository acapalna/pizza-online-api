package org.fasttrackit.pizzaonlineapi.repository;

import org.fasttrackit.pizzaonlineapi.domain.Cart;
import org.springframework.data.repository.CrudRepository;

public interface CartRepository extends CrudRepository<Cart, Long> {


}
