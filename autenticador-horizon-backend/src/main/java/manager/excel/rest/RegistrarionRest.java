package manager.excel.rest;

import manager.excel.domain.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api")
@RestController
public class RegistrarionResource {


    @PostMapping("/public/registration/user")
    public ResponseEntity<Void> registerUser (@RequestBody User user){
        return ResponseEntity.ok(null);
    }
}
