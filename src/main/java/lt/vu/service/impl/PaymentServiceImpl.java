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
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.TimeZone;

@Service
@Transactional
@Slf4j
public class PaymentServiceImpl implements PaymentService {
    static final String paymentServiceUrl = "http://mock-payment-processor.appspot.com/v1/payment";
    //FIXME: Probably should bet stored somewhere else
    static final String authorizationStr = "technologines:platformos";
    static final SimpleDateFormat ourDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    static final SimpleDateFormat apiDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

    public PaymentServiceImpl() {
        apiDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
    }

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
                payment = readPayment(con);
                log.debug("Payment successfull: " + payment);
                order.setPaymentId(payment.getId());
                String date;
                try {
                    date = ourDateFormat.format(apiDateFormat.parse(payment.getCreated_at().split("\\.")[0]));
                }
                catch (ParseException exc) {
                    throw new RuntimeException("API returned date in unexpected format");
                }
                order.setOrderDatetime(date);
                break;
            case 401:
                msg = "Authorization with payment api failed";
                throw new IllegalArgumentException(msg);
            case 400:
                msg = "Invalid inputs for payment: " + errorMsg.toString();
                throw new PaymentException(msg, "Invalid card info");
            case 404:
                msg = "Payment api returned: " + errorMsg.toString();
                throw new IllegalArgumentException(msg);
            case 402:
                msg = "Payment failed. Reason: " + errorMsg.toString();
                throw new PaymentException(msg, errorMsg.message);
            default:
                msg = "Payment api returned unknown status code: " + statusCode;
                throw new RuntimeException(msg);
        }

    }

    private Payment readPayment(HttpURLConnection connection) throws IOException {
        InputStream inStream = connection.getInputStream();
        ObjectMapper mapper = new ObjectMapper();
        Payment p = mapper.readValue(inStream, Payment.class);
        inStream.close();
        return p;
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
