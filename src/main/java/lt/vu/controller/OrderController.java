package lt.vu.controller;

import lt.vu.model.Cart;
import lt.vu.model.Customer;
import lt.vu.model.CustomerOrder;
import lt.vu.service.api.CartService;
import lt.vu.service.api.CustomerOrderService;
import lt.vu.service.api.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import java.security.Principal;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    public String createOrder(@PathVariable("cartId") int cartId) {
        Cart cart = cartService.getCartById(cartId);

        if (cart.getCartItems().size() == 0) {
            return "redirect:/checkout?cartId=" + cartId;
        }

        CustomerOrder customerOrder = new CustomerOrder();
        customerOrder.setStatus("Accepted");
        customerOrder.setRating(0);
        customerOrder.setOrderDatetime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

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

    @RequestMapping("/customer/orders/{orderId}/feedback")
    public String readFeedback(@PathVariable("orderId") int orderId, Model model) {
        CustomerOrder order = customerOrderService.getOrderById(orderId);
        String feedback = order.getFeedback();

        if (feedback == null) {
            model.addAttribute("order", order);

            return "writeOrderFeedback";
        }

        model.addAttribute("feedback", order.getFeedback());
        model.addAttribute("role", "customer");

        return "readOrderFeedback";
    }

    @RequestMapping(value="/customer/orders/{orderId}/feedback", method = RequestMethod.POST)
    public String writeFeedback(@PathVariable("orderId") int orderId,
                                @Valid @ModelAttribute("order") CustomerOrder order,
                                BindingResult result, Principal activeUser) throws AccessDeniedException {
        String feedback = order.getFeedback();
        if (feedback.length() > 255) {
            return "writeOrderFeedback";
        }

        CustomerOrder customerOrder = customerOrderService.getOrderById(orderId);
        Customer customer = customerService.getCustomerByUsername(activeUser.getName());
        if (customerOrder.getCustomer().getCustomerId() != customer.getCustomerId()) {
            throw new AccessDeniedException("You can only write feedback for order that belongs to you!");
        }

        System.out.println("orderID: " + orderId);
        System.out.println("feedback: " + feedback);
        customerOrderService.writeOrderFeedback(orderId, feedback);

        return "redirect:/customer/orders";
    }
}
