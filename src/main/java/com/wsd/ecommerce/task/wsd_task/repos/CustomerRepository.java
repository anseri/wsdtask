package com.wsd.ecommerce.task.wsd_task.repos;

import com.wsd.ecommerce.task.wsd_task.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
