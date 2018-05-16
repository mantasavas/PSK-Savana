package lt.vu.service.api;

import lt.vu.model.CustomerOrder;

public interface PaymentService {

    void pay(CustomerOrder order);
}
