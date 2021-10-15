package manager.excel.core.email;

import manager.excel.service.email.EmailService;
import manager.excel.service.email.SmtpEmailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.yml")
public class EmailConfig {
    @Bean
    public EmailService emailService(){
        return  new SmtpEmailService();
    }
}
