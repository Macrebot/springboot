package macrebot.practices.springboot.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import macrebot.practices.springboot.models.Customer;

@RestController
@RequestMapping("/clients")
public class CustomerController {

    private List<Customer> customers = new ArrayList<>(Arrays.asList(
            new Customer(1, "Gerardo López", "gerardol", "contrasena123"),
            new Customer(2, "Alejandra García", "alegarcia", "clave456"),
            new Customer(3, "Laura Sanchéz", "lauras", "secreto789"),
            new Customer(4, "Carlos Martínez", "carlosm", "password234")));

    @GetMapping
    // @RequestMapping(method = RequestMethod.GET)
    public List<Customer> getCustomers() {
        return this.customers;
    }

    @GetMapping("/{username}")
    // @RequestMapping(value = "/{username}", method = RequestMethod.GET)
    public Customer getCustomer(@PathVariable String username) {
        for (Customer c : this.customers) {
            if (c.getUsername().equals(username)) {
                return c;
            }
        }
        return null;
    }

    @PostMapping
    // @RequestMapping(method = RequestMethod.POST)
    public Customer postCustomer(@RequestBody Customer customer) {
        customers.add(customer);
        return customer;
    }

    @PutMapping
    // @RequestMapping(method = RequestMethod.PUT)
    public Customer putClient(@RequestBody Customer customer) {
        for (Customer c : customers) {
            System.out.println("ID1: " + c.getID() + ". ID2: " + customer.getID());
            if (c.getID() == customer.getID()) {
                c.setName(customer.getName());
                c.setUsername(customer.getUsername());
                c.setPassword(customer.getPassword());
                return c;
            }
        }
        return null;
    }

    @DeleteMapping("/{id}")
    // @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Customer deleteClient(@PathVariable int id) {
        for (Customer c : customers) {
            if (c.getID() == id) {
                customers.remove(c);
                return c;
            }
        }
        return null;
    }

    @PatchMapping
    // @RequestMapping(method = RequestMethod.PATCH)
    public Customer patchClient(@RequestBody Customer customer) {
        for (Customer c : customers) {
            if (c.getID() == customer.getID()) {
                if (customer.getName() != null) {
                    c.setName(customer.getName());
                }

                if (customer.getUsername() != null) {
                    c.setUsername(customer.getUsername());
                }

                if (customer.getPassword() != null) {
                    c.setPassword(customer.getPassword());
                }
                return c;
            }

        }
        return null;
    }
}
