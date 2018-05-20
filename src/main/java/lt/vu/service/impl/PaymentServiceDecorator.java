package lt.vu.service.impl;

import lt.vu.model.CustomerOrder;
import lt.vu.service.api.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class PaymentServiceDecorator implements PaymentService {

	@Autowired
	@Qualifier("paymentService")
	PaymentService paymentService;

	@Override
	public void pay(CustomerOrder order) {
		Payment payment = new Payment(order);
		if (payment.getAmount() >= 100000) {
			System.out.println("We're sorry, we don't support such large sums yet");
			throw new RuntimeException("We're sorry, we don't support such large sums yet");
		}
		else
			paymentService.pay(order);
	}
}
