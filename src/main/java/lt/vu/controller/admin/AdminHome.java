package lt.vu.controller.admin;

import lt.vu.model.Customer;
import lt.vu.model.CustomerOrder;
import lt.vu.model.Product;
import lt.vu.service.CustomerOrderService;
import lt.vu.service.CustomerService;
import lt.vu.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminHome {

    @Autowired
    private ProductService productService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerOrderService customerOrderService;

    @RequestMapping
    public String adminPage(){
        return "admin/admin";
    }

    @RequestMapping("/inventory")
    public String productInventory(Model model) {
        List<Product> products = productService.getProducts();
        model.addAttribute("products", products);

        return "admin/productInventory";
    }

    @RequestMapping("/customers")
    public String customerManagement(Model model) {
        List<Customer> customerList = customerService.getAllCustomers();
        model.addAttribute("customerList", customerList);

        return "admin/customerManagement";
    }

    @RequestMapping("/orders")
    public String orderManagement(Model model) {
        List<CustomerOrder> orderList = customerOrderService.getAllOrders();
        model.addAttribute("orderList", orderList);

        return "admin/orderManagement";
    }
}