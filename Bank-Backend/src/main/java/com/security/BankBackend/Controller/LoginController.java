package com.security.BankBackend.Controller;


import com.security.BankBackend.Entity.Customer;
import com.security.BankBackend.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    PasswordEncoder passwordEncoder;


    @PostMapping("/register")
    public ResponseEntity<Customer> registerCustomer(@RequestBody Customer customer){
        Customer savedCustomer = null;
        ResponseEntity response=null;
        try {

            String hashPwd= passwordEncoder.encode(customer.getPwd());
            customer.setPwd(hashPwd);
            savedCustomer = customerRepository.save(customer);
            if(savedCustomer.getId()>0){
                response= ResponseEntity.status(HttpStatus.CREATED)
                        .body("The Given Customer Details Have Been Saved..");
            }
        }
        catch (Exception ex){
            response= ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                  .body("An Exception Occured Due to :" +ex.getMessage());
        }
        return response;

    }









}
