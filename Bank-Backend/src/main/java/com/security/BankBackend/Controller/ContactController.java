package com.security.BankBackend.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ContactController {

    @GetMapping("/contact")
    public String getAccountDetails() {
        return "Here are the Contact details for the DB:";
    }
}
