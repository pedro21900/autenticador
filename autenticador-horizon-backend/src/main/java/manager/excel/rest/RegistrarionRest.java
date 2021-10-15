package manager.excel.rest;

import javassist.tools.rmi.ObjectNotFoundException;
import manager.excel.domain.User;
import manager.excel.repository.UserRepository;
import manager.excel.rest.Response.GenericResponse;
import manager.excel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@RequestMapping("/api/public")
@RestController
public class RegistrarionRest {
    @Autowired
    UserService userService;

    TokenStore tokenStore = new InMemoryTokenStore();

    @Autowired
    DefaultTokenServices tokenServices = new DefaultTokenServices();
    @PostMapping("/registration/user")
    public ResponseEntity<User> registerUser(@RequestBody User user) throws ObjectNotFoundException {
        this.userService.registerUser(user);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/registrationConfirm/user")
    public ResponseEntity<GenericResponse> registrationConfirmUser(@RequestParam("token") String token) {
        final Object result = this.userService.validateVerificationToken(token);
        if (result == null) {
            return ResponseEntity.ok().body(new GenericResponse("Success"));
        }
        return ResponseEntity.status(HttpStatus.SEE_OTHER).body(new GenericResponse(result.toString()));
    }

    @GetMapping("/user/main")
    public ResponseEntity<User> getUserMain(Principal principal) {
        return ResponseEntity.ok().body(userService.findByUsername(principal.getName()));
    }
    @GetMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletRequest request){
        String authHeader = request.getHeader("Authorization");
        if (authHeader!=null){
            String tokenValue = authHeader.replace("Bearer","").trim();
            OAuth2AccessToken auth2AccessToken=tokenServices.readAccessToken(tokenValue);
            tokenStore.removeAccessToken(auth2AccessToken);
            tokenServices.revokeToken(String.valueOf(auth2AccessToken));
        }
        return  ResponseEntity.noContent().build();
    }
    @GetMapping("/resendRegistrationToken/user")
    public ResponseEntity<Void> resendRegistrationToken(@RequestParam("username") String username) throws ObjectNotFoundException {
        this.userService.generateNewVerificationToken(username);
        return ResponseEntity.noContent().build();
    }
}
