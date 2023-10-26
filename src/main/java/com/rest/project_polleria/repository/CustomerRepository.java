package com.rest.project_polleria.repository;

import com.rest.project_polleria.entity.Customer;
import com.rest.project_polleria.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {
    Page<Order> findAllByCustomer(Customer customer, Pageable pageable); //<Order, UUID>
}
