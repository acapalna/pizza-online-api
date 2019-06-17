package org.fasttrackit.pizzaonlineapi.repository;

import org.fasttrackit.pizzaonlineapi.domain.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

//Long is wrapper for primitive type long
public interface ClientRepository extends JpaRepository<Client, Long> {

    //queries derives from method names springBoot
    Page<Client> findByIdContaining(Long partialId, Pageable pageable);
    Page<Client> findByFirstNameContaining(String partialFirstName, Pageable pageable);
    Page<Client> findByLastNameContaining(String partialLastName, Pageable pageable);
    Page<Client> findByPhoneNumberContaining(String partialPhoneNumber, Pageable pageable);




}
