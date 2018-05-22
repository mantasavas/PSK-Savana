package lt.vu.service.api;

import lt.vu.model.CustomerOrder;

import java.io.IOException;

public interface PaymentService {

    void pay(CustomerOrder order) throws IOException;
}
