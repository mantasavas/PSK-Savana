package lt.vu.service.impl;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

//TODO: Make setters validate: https://git.mif.vu.lt/snippets/7
@Getter
@Setter
class Payment {
    private Integer amount;
    private String number;
    private String holder;
    private Integer exp_year;
    private Integer exp_month;
    private String cvv;

    Payment() {
        //FIXME: delete:
        amount = 10;
        number = "4111111111111111";
        holder = "Vardenis Pavardenis";
        exp_year = 2020;
        exp_month = 9;
        cvv = "123";
    }

}
