package lt.vu.controller;

import lt.vu.model.Cart;
import lt.vu.model.Customer;
import lt.vu.model.CustomerOrder;
import lt.vu.service.CartService;
import lt.vu.service.CustomerOrderService;
import lt.vu.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class OrderController {

    @Autowired
    private CartService cartService;

    @Autowired
    private CustomerOrderService customerOrderService;

    @Autowired
    private CustomerService customerService;

    @RequestMapping("/order/{cartId}")
    public String createOrder(@PathVariable("cartId") int cartId){
        CustomerOrder customerOrder = new CustomerOrder();
        customerOrder.setStatus("unknown");
        customerOrder.setRating(0);

        Cart cart = cartService.getCartById(cartId);
        customerOrder.setCart(cart);

        Customer customer = cart.getCustomer();
        customerOrder.setCustomer(customer);
        customerOrder.setBillingAddress(customer.getBillingAddress());
        customerOrder.setShippingAddress(customer.getShippingAddress());

        customerOrderService.addCustomerOrder(customerOrder);

        return "redirect:/checkout?cartId=" + cartId;
    }

    @RequestMapping("/customer/orders")
    public String getOrders(Model model, @AuthenticationPrincipal User activeUser) {
        Customer customer = customerService.getCustomerByUsername(activeUser.getUsername());
        int customerId = customer.getCustomerId();

        List<CustomerOrder> customerOrders = customerOrderService.getCustomerOrders(customerId);
        model.addAttribute("orderList", customerOrders);

        return "orders";
    }
}
