package lt.vu.controller;

import lt.vu.model.Cart;
import lt.vu.model.Customer;
import lt.vu.service.api.CustomerService;
import lt.vu.service.api.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;

import java.security.Principal;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/customer/cart")
public class CartController {

    @Autowired
    private CustomerService customerService;

    @RequestMapping
    public String getCart(Principal activeUser){
        Customer customer = customerService.getCustomerByEmail(activeUser.getName());
        Cart cart = customer.getCart();
        int cartId = -1;
        if (cart != null)
            cartId = customer.getCart().getCartId();

        return "redirect:/customer/cart/" + cartId;
    }

    @RequestMapping("/{cartId}")
    public String getCartRedirect(@PathVariable (value = "cartId") int cartId, Model model){
        model.addAttribute("cartId", cartId);

        return "cart";
    }
}