package manager.excel.config.security;

import manager.excel.domain.model.UserSecurity;
//import manager.excel.service.UserSecurityService;
import manager.excel.service.UserSecurityService;
import manager.excel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity //Ele permite que o Spring encontre (é um @Configuration e, portanto, @Component) e
// aplica automaticamente a classe ao global WebSecur

public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserSecurityService userSecurityService;

    @Autowired
    private AuthenticationEntryPoint authEntryPoint;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userSecurityService).passwordEncoder(getPasswordEncoder());

    }


    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {

        return new BCryptPasswordEncoder();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/auth/**").hasAnyAuthority("ADMIN")
                .antMatchers(HttpMethod.POST, "/api/users").hasAnyAuthority("USER")
                .antMatchers(HttpMethod.GET, "/api/users/**").hasAnyAuthority("ADMIN", "USER")
                .antMatchers(HttpMethod.GET, "/api/company").hasAnyAuthority("ADMIN")
                .antMatchers(HttpMethod.GET, "/swagger-ui/**").hasAnyAuthority("ADMIN")
                .anyRequest().authenticated()
                .and().httpBasic()
                .authenticationEntryPoint(authEntryPoint);
              /*
                .defaultSuccessUrl("/swagger-ui/", true);*/


    }
}

