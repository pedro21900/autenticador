package manager.excel.config;

import manager.excel.domain.model.UserSecurity;
//import manager.excel.service.UserSecurityService;
import manager.excel.service.UserSecurityService;
import manager.excel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity //Ele permite que o Spring encontre (é um @Configuration e, portanto, @Component) e
// aplica automaticamente a classe ao global WebSecur
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserSecurityService userSecurityService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userSecurityService);

    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(HttpMethod.POST,"/api/users").hasAnyAuthority("USER")
                .antMatchers(HttpMethod.GET,"/api/users").hasAnyAuthority("USER")
                .antMatchers(HttpMethod.GET,"/api/company").hasAnyAuthority("ADMIN")
                .antMatchers(HttpMethod.GET,"/swagger-ui/**").hasAnyAuthority("ADMIN")
                .antMatchers("/").hasAnyAuthority("ADMIN","USER")
                .and()
                .formLogin()
                .defaultSuccessUrl("/swagger-ui/", true);
    }
}

