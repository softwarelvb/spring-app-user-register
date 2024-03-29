package com.example.springappuserregister.Repository;

import com.example.springappuserregister.Model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}