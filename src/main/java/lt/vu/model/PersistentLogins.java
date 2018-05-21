package lt.vu.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

@Entity
@Table(name = "persistent_logins")
public class PersistentLogins {

    @Getter
    @Setter
    @Size(max = 64)
    @NotNull
    private String username;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Setter
    @Size(max = 64)
    private String series;

    @Getter
    @Setter
    @Size(max = 64)
    @NotNull
    private String token;

    @Getter
    @Setter
    @NotNull
    @Column(name = "last_used")
    private Timestamp lastUsed;
}
