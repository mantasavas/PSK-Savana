package lt.vu.controller.admin;

import lt.vu.model.Customer;
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

//    @RequestMapping("/enable/{customerId}")
//    public String enableCustomer(@PathVariable(value = "customerId") int customerId) {
//        Customer customer = customerService.getCustomerById(customerId);
//        customerService.setEnabledCustomer(customer, true);
//
//        return "redirect:/admin/customers";
//    }
//
//    @RequestMapping("/disable/{customerId}")
//    public String disableCustomer(@PathVariable(value = "customerId") int customerId) {
//        Customer customer = customerService.getCustomerById(customerId);
//        customerService.setEnabledCustomer(customer, false);
//
//        return "redirect:/admin/customers";
//    }
}
