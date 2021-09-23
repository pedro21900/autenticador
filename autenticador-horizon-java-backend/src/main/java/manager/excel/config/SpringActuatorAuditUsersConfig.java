package manager.excel.config;

import org.springframework.boot.actuate.audit.InMemoryAuditEventRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
public class SpringActuatorAuditUsersConfig {

    @Bean //evento para colocar em memoria os resultados de auditoria
    public InMemoryAuditEventRepository repository() {
        return new InMemoryAuditEventRepository();
    }
}
