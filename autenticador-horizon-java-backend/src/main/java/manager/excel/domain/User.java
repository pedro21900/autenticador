package manager.excel.domain;


import lombok.*;
//Jpa
import javax.persistence.*;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "tb_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String username;

    private String password;

    private String name;

    private boolean active;

    @ManyToOne
    private Company company;

    @ManyToOne
    private AccessLevel accessLevel;
}
