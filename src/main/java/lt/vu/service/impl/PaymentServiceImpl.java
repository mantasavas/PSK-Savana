package lt.vu.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import lt.vu.exceptions.PaymentException;
import lt.vu.model.CustomerOrder;
import lt.vu.service.api.PaymentService;
import org.apache.commons.codec.binary.Base64;
import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

@Service
@Transactional
@Slf4j
public class PaymentServiceImpl implements PaymentService {
    static final String paymentServiceUrl = "http://mock-payment-processor.appspot.com/v1/payment1";
    //FIXME: Probably should bet stored somewhere else
    static final String authorizationStr = "technologines:platformos";

    @ToString
    private static class ErrorMsg {
        public String error;
        public String message;
    }

    public void pay(CustomerOrder order) throws IOException {

        int statusCode;
        Payment payment = new Payment(order);
        HttpURLConnection con = connectToPay(payment);

        statusCode = con.getResponseCode();
        ErrorMsg errorMsg = readErrorStream(con);

        String msg;
        switch (statusCode) {
            case 201:
                log.debug("Payment for: " + String.valueOf(payment.getAmount()));
                //TODO: save payment id
                break;
            case 401:
                msg = "Authorization with payment api failed";
                log.error(msg);
                throw new IllegalArgumentException(msg);
            case 400:
                msg = "Invalid inputs for payment: " + errorMsg.toString();
                log.info(msg);
                throw new PaymentException(msg, "Invalid card info");
            case 404:
                msg = "Payment api returned: " + errorMsg.toString();
                log.error(msg);
                throw new IllegalArgumentException(msg);
            case 402:
                msg = "Payment failed. Reason: " + errorMsg.toString();
                log.info(msg);
                throw new PaymentException(msg, errorMsg.message);
            default:
                msg = "Payment api returned unknown status code: " + statusCode;
                log.error(msg);
                throw new RuntimeException(msg);
        }

    }

    private ErrorMsg readErrorStream(HttpURLConnection connection) throws IOException {
        int statusCode = connection.getResponseCode();
        if (statusCode == 400 || statusCode == 402 || statusCode == 404) {
            InputStream errStream = connection.getErrorStream();
            ObjectMapper mapper = new ObjectMapper();
            ErrorMsg msg = mapper.readValue(errStream, ErrorMsg.class);
            errStream.close();
            return msg;
        }
        else
            return null;
    }

    private HttpURLConnection connectToPay(Payment payment) throws IOException {
        HttpURLConnection con = (HttpURLConnection) new URL(paymentServiceUrl).openConnection();
        con.setDoInput(true);
        con.setDoOutput(true);
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Authorization",
                "Basic " + Base64.encodeBase64String(authorizationStr.getBytes()));
        con.setRequestMethod("POST");
        con.setConnectTimeout(5000);
        con.setReadTimeout(5000);

        OutputStream stream = con.getOutputStream();
        // FIXME: should we inject it?
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(stream, payment);
        stream.close();

        return con;
    }
}
