package manager.excel.service;

import javassist.tools.rmi.ObjectNotFoundException;
import manager.excel.domain.User;
import manager.excel.domain.model.VerificationToken;
import manager.excel.repository.AccessLevelRepository;
import manager.excel.repository.VerificationTokenReposiroty;
import manager.excel.service.email.EmailService;
import manager.excel.service.excepition.ObjectAlreadyExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import manager.excel.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.*;


@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EmailService emailService;
    @Autowired
    private PasswordEncoder passwordEncode;
    @Autowired
    private AccessLevelRepository accessLevelRepository;
    @Autowired
    private VerificationTokenReposiroty verificationTokenReposiroty;

    public List<User> findAllUsers() throws EntityNotFoundException {
        return userRepository.findAll();
    }

    public Optional<User> laodUserById(Long id) throws UsernameNotFoundException {
        return userRepository.findById(id);
    }

    public User create(User user) {
        User usr = user;
        usr.setPassword(passwordEncode.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User update(Long id, User user) {
        if (!existsByUsername(user.getUsername()))
            throw new EntityNotFoundException("Tipo de Usuário não encontrado");
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public User registerUser(User user) throws ObjectNotFoundException {
        if (existsByUsername(user.getUsername()))
            throw new ObjectAlreadyExistException(String.format("Já existe um usuário com este email "));
        user.setAccessLevel(accessLevelRepository.findByDescription("CONV").get());
        user.setActive(false);
        user = create(user);
        this.emailService.sendConfirmationHtmlEmail(user,null);
        return user;
    }

    private Boolean existsByUsername(final String username) {
        return userRepository
                .findByUsername(username)
                .isPresent();
    }

    public void createVerificationTokenForUser(User user, String token) {
        final VerificationToken vToken = new VerificationToken(token, user);
        verificationTokenReposiroty.save(vToken);
    }

    public String validateVerificationToken(String token) {
        final Optional<VerificationToken> vToken = verificationTokenReposiroty.findByToken(token);
        if (!vToken.isPresent()) {
            return "invalidToken";
        }
        final User user = vToken.get().getUser();
        final Calendar cal = Calendar.getInstance();
        if ((vToken.get().getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
            return "expired";
        }
        user.setActive(true);
        this.userRepository.save(user);
        return null;
    }
    public User findByUsername(String username){
        Optional<User> user = userRepository.findByUsername(username);
        return user.orElseThrow(()-> new EntityNotFoundException(String.format("Usuário não encontrado")));
    }
    public VerificationToken generateNewVerificationToken(String username) throws ObjectNotFoundException {
        User user=findByUsername(username);
        Optional<VerificationToken>vToken=verificationTokenReposiroty.findByUser(user);
        vToken.get().updateToken(UUID.randomUUID().toString());
        VerificationToken updateVToken=verificationTokenReposiroty.save(vToken.get());
        emailService.sendConfirmationHtmlEmail(user,updateVToken);
        return updateVToken;
    }
}
