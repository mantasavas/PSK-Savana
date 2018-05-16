package lt.vu.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import lt.vu.model.CustomerOrder;
import lt.vu.service.api.PaymentService;
import org.apache.commons.codec.binary.Base64;
import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

@Service
@Transactional
public class PaymentServiceImpl implements PaymentService {
    static final String paymentServiceUrl = "http://mock-payment-processor.appspot.com/v1/payment";
    //FIXME: Probably should bet stored somewhere else
    static final String authorizationStr = "technologines:platformos";

    public void pay(CustomerOrder order) {
        //FIXME: Error handling

        int statusCode;
        String responseMsg;
        Payment payment = new Payment(order);
        try {
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

            System.out.println("Payment for: " + String.valueOf(payment.getAmount()));

            statusCode = con.getResponseCode();
            responseMsg = con.getResponseMessage();
        } catch (Exception exc) {
            System.out.println("Unable to send request to api server. Error: " + exc.toString());
            throw new RuntimeException("Unable to send request to api server", exc);
        }

        if (statusCode != 201) {
            System.out.println("Payment unsuccessful: " + responseMsg + ", status code: " + statusCode);
            throw new RuntimeException("Payment unsuccessful: " + responseMsg + ", status code: " + statusCode);
        }
    }
}
