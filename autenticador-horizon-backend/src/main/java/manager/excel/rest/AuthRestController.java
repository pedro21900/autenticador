package manager.excel.rest;


import manager.excel.config.security.AuthenticationBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/auth")
public class AuthRestController {

    @GetMapping
    public AuthenticationBean mensager() {
        return new AuthenticationBean("You are authenticated");

    }

    }

