package manager.excel.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
//Falta Adicionar chave composta
public class AccessPeriod {

    @EmbeddedId
    @GeneratedValue(strategy=GenerationType.AUTO)
    private AcessPeriodId id;
    private LocalDateTime entryTime;
    private LocalDateTime departureTime;
    private BigDecimal periodTime;
}
