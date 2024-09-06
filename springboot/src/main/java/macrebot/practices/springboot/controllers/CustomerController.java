package macrebot.practices.springboot.controllers;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
    public ResponseEntity<List<Customer>> getCustomers() {
        return ResponseEntity.ok(this.customers);
    }

    @GetMapping("/{username}")
    // @RequestMapping(value = "/{username}", method = RequestMethod.GET)
    public ResponseEntity<?> getCustomer(@PathVariable String username) {
        for (Customer c : this.customers) {
            if (c.getUsername().equals(username)) {
                return ResponseEntity.ok(c);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client not found with the username: " + username);
    }

    @PostMapping
    // @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> postCustomer(@RequestBody Customer customer) {
        customers.add(customer);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{username}")
                .buildAndExpand(customer.getUsername())
                .toUri();

        return ResponseEntity.created(location).body(customer);

        // return ResponseEntity.created(location).build();

        // return ResponseEntity.status(HttpStatus.CREATED).body("Client successfully
        // created: " + customer.getUsername());
    }

    @PutMapping
    // @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<?> putClient(@RequestBody Customer customer) {
        for (Customer c : customers) {
            System.out.println("ID1: " + c.getID() + ". ID2: " + customer.getID());
            if (c.getID() == customer.getID()) {
                c.setName(customer.getName());
                c.setUsername(customer.getUsername());
                c.setPassword(customer.getPassword());

                return ResponseEntity.noContent().build();
            }
        }
        return ResponseEntity.notFound().build();

        // return ResponseEntity.status(HttpStatus.NOT_FOUND)
        // .body("Username not found with the ID: " + customer.getID());

    }

    @DeleteMapping("/{id}")
    // @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteClient(@PathVariable int id) {
        for (Customer c : customers) {
            if (c.getID() == id) {
                customers.remove(c);

                return ResponseEntity.noContent().build();
                // return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Client successfully
                // deleted: " + c.getID());
            }
        }
        return ResponseEntity.notFound().build();
        // return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client not found
        // with the id: " + id);
    }

    @PatchMapping
    // @RequestMapping(method = RequestMethod.PATCH)
    public ResponseEntity<?> patchClient(@RequestBody Customer customer) {
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
                return ResponseEntity.ok("Client successfully modified: " + c.getUsername());
            }

        }
        return ResponseEntity.notFound().build();
        // return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client not found
        // with the ID: " + customer.getID());
    }
}
