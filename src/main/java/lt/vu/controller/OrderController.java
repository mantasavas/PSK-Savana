package lt.vu.controller;

import lt.vu.model.Cart;
import lt.vu.model.Customer;
import lt.vu.model.CustomerOrder;
import lt.vu.service.CartService;
import lt.vu.service.CustomerOrderService;
import lt.vu.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import java.security.Principal;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
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
        customerOrder.setStatus("Accepted");
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
    public String getOrders(Model model, Principal activeUser) {
        Customer customer = customerService.getCustomerByUsername(activeUser.getName());
        int customerId = customer.getCustomerId();

        List<CustomerOrder> customerOrders = customerOrderService.getCustomerOrders(customerId);
        model.addAttribute("orderList", customerOrders);

        return "orders";
    }

    @RequestMapping("/customer/orders/rate/{orderId}/{rating}")
    public String rateOrder(@PathVariable("orderId") int orderId,
                            @Valid @PathVariable("rating") int rating,
                            Principal activeUser) throws AccessDeniedException {
        Customer customer = customerService.getCustomerByUsername(activeUser.getName());
        CustomerOrder customerOrder = customerOrderService.getOrderById(orderId);

        if (customer.getCustomerId() != customerOrder.getCustomer().getCustomerId()) {
            throw new AccessDeniedException("You cannot rate order that do not belong to you!");
        }

        // only possible to give rating once
        if (customerOrder.getRating() > 0) {
            return "redirect:/customer/orders";
        }

        customerOrderService.rateOrder(orderId, rating);

        return "redirect:/customer/orders";
    }
}
