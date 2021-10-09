package manager.excel.service;

import manager.excel.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import manager.excel.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;


@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncode;
    public List<User> findAllUsers() throws EntityNotFoundException {
        return userRepository.findAll();
    }

    public Optional<User> laodUserById(Long id) throws UsernameNotFoundException {

        return userRepository.findById(id);
    }

    public User saveUser(User user) {
        User usr=user;
        usr.setPassword(passwordEncode.encode(user.getPassword()));

        return userRepository.save(user);
    }
    public User update(Long id, User user) {
        if (!userRepository.existsById(id)) throw new EntityNotFoundException(
            "Tipo de Usuário não encontrado"
        );
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
