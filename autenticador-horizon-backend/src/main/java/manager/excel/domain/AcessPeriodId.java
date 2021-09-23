package manager.excel.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@NoArgsConstructor
@Embeddable
public class AcessPeriodId implements Serializable {
    private long idUser;
    private long idAccessPeriod;
}
