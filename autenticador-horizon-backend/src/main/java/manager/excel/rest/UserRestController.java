package manager.excel.rest;

import manager.excel.domain.User;
import manager.excel.repository.UserRepository;
import manager.excel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;


@RequestMapping("/api/users")
@RestController
public class UserRestController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;


    @PostMapping
    public ResponseEntity<User> createrUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.saveUser(user));
    }

    @PutMapping
    public ResponseEntity<User> updateUser(@RequestBody User user) {

        return ResponseEntity.ok(userService.updateUser(user));
    }

    @DeleteMapping("/{idUser}")
    public void DeleteUser(@PathVariable() Long idUser) {
        userService.deleteUser(idUser);
        ResponseEntity.ok();
    }

    @GetMapping("/{idUser}")
    public ResponseEntity<User> findUser(@PathVariable() Long idUser) throws UsernameNotFoundException {
        return ResponseEntity.ok(userService
                .laodUserById(idUser)
                .orElseThrow(() -> new NoSuchElementException("Usuário não encontrado")));
    }

    @GetMapping
    ResponseEntity<List<User>> findAllUsers() {
        return ResponseEntity.ok(userService.findAllUsers());
    }

}




