package lt.vu.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;


@Entity
public class Log implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter @Setter
    private int logId;

    @Getter @Setter
    private String errorMsg;

    @Getter @Setter
    private Timestamp date;

    @Getter @Setter
    private String userName;

    @Getter @Setter
    private String userRole;

    @Getter @Setter
    private String className;

    @Getter @Setter
    private String methodName;

    public Log(Timestamp date, String userName, String userRole, String className, String methodName, String errorMsg) {
        this.date = date;
        this.userName = userName;
        this.userRole = userRole;
        this.className = className;
        this.methodName = methodName;
        this.errorMsg = errorMsg;
    }
}
