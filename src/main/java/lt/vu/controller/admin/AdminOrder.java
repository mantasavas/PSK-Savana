package lt.vu.controller.admin;

import lt.vu.service.CustomerOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/orders")
public class AdminOrder {

    @Autowired
    private CustomerOrderService customerOrderService;

    @RequestMapping("/status/{orderId}/{status}")
    public String enableCustomer(@PathVariable(value = "orderId") int orderId,
                                 @PathVariable(value = "status") String status) {
        customerOrderService.setOrderStatus(orderId, status);

        return "redirect:/admin/orders";
    }
}
