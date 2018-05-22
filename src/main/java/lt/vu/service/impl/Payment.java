package lt.vu.service.impl;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lt.vu.model.Card;
import lt.vu.model.CustomerOrder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import java.math.BigDecimal;

//TODO: Make setters validate: https://git.mif.vu.lt/snippets/7
@Getter
@Setter
@ToString
class Payment {
    private int amount;
    private String number;
    private String holder;
    private int exp_year;
    private int exp_month;
    private String cvv;
    private String id;
    private String created_at;

    Payment() {}

    Payment(CustomerOrder order) {
        BigDecimal decimal = order.getCart().getGrandTotal();
        amount = decimal.movePointRight(2).intValueExact();

        Card card = order.getCard();
        number = card.getNumber();
        holder = card.getName();
        exp_year = card.getExpYear();
        exp_month = card.getExpMonth();
        cvv = card.getCvv();
        id = null;
        created_at = null;
    }

}
