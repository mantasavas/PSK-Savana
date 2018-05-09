package lt.vu.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Authorities {

    @Id
    @GeneratedValue
    @Getter @Setter
    private int authoritiesId;

    @Getter @Setter
    private String username;

    @Getter @Setter
    private String authority;
}