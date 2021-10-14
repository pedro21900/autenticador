package manager.excel.service.email;

import javassist.tools.rmi.ObjectNotFoundException;
import manager.excel.domain.User;
import manager.excel.domain.model.VerificationToken;
import manager.excel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Date;

public abstract class AbstractEmailService implements EmailService{

    @Value("${default.sender}")
    private String sender;
    @Value("${default.url}")
    private String contextPath;
    @Autowired
    private TemplateEngine templateEngine;
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private UserService userService;

    @Override
    public void sendConfirmationHtmlEmail(User user, VerificationToken vToken){
        try{
            MimeMessage mimeMessage;
        }catch (MessagingException msg){
            throw  new ObjectNotFoundException(String.format("Erro ao tentar enviar e-mail"));
        }
    }
    protected  MimeMessage prepareMimeMessageFromUser(User user , VerificationToken vToken) throws MessagingException{
        MimeMessage mimeMessage= javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper=new MimeMessageHelper(mimeMessage,true);
        mimeMessageHelper.setTo(user.getUsername());
        mimeMessageHelper.setFrom(this.sender);
        mimeMessageHelper.setSubject("Confirmação de registro");
        mimeMessageHelper.setSentDate(new Date(System.currentTimeMillis());
        mimeMessageHelper.setText();
            }
    protected String htmlFromTemplateUser(User user, VerificationToken vToken){}

}
