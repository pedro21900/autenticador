package manager.excel.rest;

import manager.excel.core.datafilter.RSQLParam;
import manager.excel.domain.User;
import manager.excel.repository.UserRepository;
import manager.excel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
        return ResponseEntity.ok(userService.create(user));
    }
    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void patch(@PathVariable Long id, @RequestBody @Valid User user) {
        userRepository.patch(id, user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User user) {
        return ResponseEntity.ok(userService.update(id, user));
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
    ResponseEntity<Page<User>> findAllUsers(RSQLParam q, Pageable pageable) {
        return ResponseEntity.ok(userRepository.findAll(q.getSpecification(), pageable));

    }
}




