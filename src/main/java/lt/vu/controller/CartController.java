package lt.vu.controller;

import lombok.extern.slf4j.Slf4j;
import lt.vu.model.Cart;
import lt.vu.model.Customer;
import lt.vu.service.api.CustomerService;
import lt.vu.service.api.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;

import java.security.Principal;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/customer/cart")
@Slf4j
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
    public String getCartRedirect(@PathVariable (value = "cartId") int cartId, Model model, Principal activeUser){
        Cart cart = customerService.getCustomerByEmail(activeUser.getName()).getCart();
        if ((cart == null && cartId != -1) || cart.getCartId() != cartId) {
            throw new IllegalAccessError("Tried to access cart which does not belong to the active user");
        }

        model.addAttribute("cartId", cartId);

        return "cart";
    }

    @ExceptionHandler(IllegalAccessError.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Cart not found")
    public ModelAndView handleIllegalAccess(HttpServletRequest req, Exception exc) {
        log.info("Illegal access: ", exc);

        ModelAndView mav = new ModelAndView();

        mav.addObject("exception", exc);
        mav.addObject("url", req.getRequestURL());
        mav.addObject("displayMessage", "");
        mav.setViewName("error");

        return mav;
    }
}