package com.security.BankBackend.Repository;

import com.security.BankBackend.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {


    List<Customer> findByEmail(String email);
}
