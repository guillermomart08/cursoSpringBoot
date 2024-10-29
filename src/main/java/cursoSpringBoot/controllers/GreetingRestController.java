package cursoSpringBoot.controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingRestController {

    @GetMapping({"/saludo/{name}", "/hola/{name}"}) //Obtenemos el nombre de la URL
    public String greeting(@PathVariable String name){ //Con @pathvariable le decimos que la variable se obtiene de la URL
        return "Hola " + name;

    }

}
