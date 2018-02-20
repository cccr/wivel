package today.wivel.root.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class HelloEndpoint {

    @GetMapping("/user/hello")
    public String sayHelloToUser(Principal user) {
        return "Hello " + user.getName();
    }

    @GetMapping("/free/hello")
    public String free(Principal user) {
        return "Hello Free";
    }
}