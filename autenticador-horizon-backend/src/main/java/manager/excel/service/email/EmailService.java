package manager.excel.service.email;

import manager.excel.domain.User;
import manager.excel.domain.model.VerificationToken;
import javax.mail.internet.MimeMessage;
public interface EmailService {
        void sendHtmlEmail(MimeMessage msg);
        void sendConfirmationHtmlEmail(User user, VerificationToken vToken);
    }
