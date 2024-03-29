package lt.vu.controller.admin;

import lt.vu.model.CustomerOrder;
import lt.vu.service.api.CustomerOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @RequestMapping("/{orderId}/feedback")
    public String readFeedback(@PathVariable("orderId") int orderId, Model model) {
        CustomerOrder order = customerOrderService.getOrderById(orderId);
        model.addAttribute("feedback", order.getFeedback());
        model.addAttribute("role", "admin");

        return "readOrderFeedback";
    }
}
