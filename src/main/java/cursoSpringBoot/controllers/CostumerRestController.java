package cursoSpringBoot.controllers;

import cursoSpringBoot.domain.Customer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/clientes") //Esto es una utilizacion a nivel de clase, oblicatorio si se utiliza esta notacion a nivel de metodo
public class CostumerRestController {

    private List<Customer> customers = new ArrayList<>(Arrays.asList(
            new Customer(123 ,  "Guillermo Martinez", "guillermoml23", "contrasena123"),
            new Customer(456, "Pablo Martinez", "pableras", "cont123"),
            new Customer(1453,  "Concha Lopez", "concep", "contrasena"),
            new Customer(1290,  "Antonio Martinez", "tonito", "pssw")

    ));

    //@RequestMapping(method = RequestMethod.GET)
    // Utilizacion a nivel de metodo, ambas sintaxis son correctas
    @GetMapping
    public ResponseEntity<List<Customer>> getCustomers(){
        return ResponseEntity.ok(customers);
        // return customers;
    }

    //@RequestMapping(value = "/{username}", method = RequestMethod.GET)
    //Este si se necesita un parametro web
    @GetMapping("/{username}")
    public ResponseEntity<?> getCliente(@PathVariable String username){

        for(Customer c : customers){

            if(c.getUsername().equalsIgnoreCase(username)){

                return ResponseEntity.ok(c);
                // return c; Codigo anterior

            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente no encontrado con username: " + username);
        // return null; Codigo anterior
    }

    @PostMapping
    public ResponseEntity<?> postCliente(@RequestBody Customer customer){
        customers.add(customer);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{username1}")
                .buildAndExpand(customer.getUsername())
                .toUri();

        return ResponseEntity.created(location).body(customer); //Retornar el contenido del mensaje
        // return ResponseEntity.created(location).build(); Para retornar la URI
        // return customer;
    }

    @PutMapping
    public ResponseEntity<?> putCliente(@RequestBody Customer customer){
        for(Customer c : customers){
            if(c.getID() == customer.getID()){
                c.setName(customer.getName());
                c.setUsername(customer.getUsername());
                c.setPassword(customer.getPassword());

                return ResponseEntity.noContent().build(); //El put necesita un tipo de respuesta 204
                // return c;
            }
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCliente(@PathVariable int id){
        for(Customer c : customers){
            if(c.getID() == id){
                customers.remove(c);

                return ResponseEntity.noContent().build();
            }
        }
        return ResponseEntity.notFound().build();
    }

    @PatchMapping
    public ResponseEntity<?> patchCliente(@RequestBody Customer customer){
        for(Customer c : customers){
            if(c.getID() == customer.getID()){

                if(customer.getName() != null){
                    c.setName(customer.getName());
                }
                else if(c.getUsername() != null){
                    c.setUsername(customer.getUsername());
                }
                else if(c.getPassword() != null){
                    c.setPassword((customer.getPassword()));
                }

                return ResponseEntity.ok("Cliente modificado satisfactoriamente: " + c.getUsername());
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente no encontrado con el ID: " + customer.getID());
    }
}
