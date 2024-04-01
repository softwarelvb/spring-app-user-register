package com.example.springappuserregister.Controller;

import com.example.springappuserregister.Model.Customer;
import com.example.springappuserregister.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private KafkaTemplate<String, Customer> kafkaTemplate; // Inject KafkaTemplate

    @PostMapping()
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        Customer savedCustomer = customerRepository.save(customer);
        kafkaTemplate.send("test", savedCustomer);  // Example using CustomerDTO
        return new ResponseEntity<>(savedCustomer, HttpStatus.CREATED);
    }

    @PostMapping("/transfer")
    public ResponseEntity<Customer> TransferMoney(@RequestBody Customer customer) {
        Customer savedCustomer = customerRepository.save(customer);
        kafkaTemplate.send("request-transfer", savedCustomer);  // Example using CustomerDTO
        return new ResponseEntity<>(savedCustomer, HttpStatus.CREATED);
    }

}
