package macrebot.practices.springboot.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import macrebot.practices.springboot.models.Customer;

@RestController
public class CustomerController {

    private List<Customer> customers = new ArrayList<>(Arrays.asList(
            new Customer(1, "Gerardo López", "gerardo1", "contrasena123"),
            new Customer(2, "Alejandra García", "alegarcia", "clave456"),
            new Customer(3, "Laura Sanchéz", "lauras", "secreto789"),
            new Customer(4, "Carlos Martínez", "carlosm", "password234")));

    @GetMapping("/clientes")
    public List<Customer> getCustomers() {
        return this.customers;
    }
}
