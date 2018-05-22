package lt.vu.exceptions;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.InputStream;

public class PaymentException extends RuntimeException {
    @Setter @Getter
    private String displayMessage;

    public PaymentException(String msg) {
        super(msg);
        displayMessage = msg;
    }

    public PaymentException(String msg, String displayMsg) {
        super(msg);
        this.displayMessage = displayMsg;
    }
}
