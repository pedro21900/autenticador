package manager.excel.service;

import manager.excel.domain.User;
import manager.excel.domain.model.UserSecurity;
import manager.excel.repository.UserRepository;
import manager.excel.service.excepition.ObjectNotEnabledException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserSecurityService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);

        user.orElseThrow(() -> new UsernameNotFoundException("Not found: " + username));
if (!user.get().isActive()){
    throw  new ObjectNotEnabledException(String.format("UserNotEnable"));
}
        return user.map(UserSecurity::new).get();
    }
}
