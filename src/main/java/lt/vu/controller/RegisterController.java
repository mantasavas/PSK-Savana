package lt.vu.controller;

import lombok.extern.slf4j.Slf4j;
import lt.vu.model.Address;
import lt.vu.model.Card;
import lt.vu.model.Customer;
import lt.vu.service.api.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@Slf4j
public class RegisterController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @RequestMapping("/register")
    public String registerCustomer(Model model) {
        Customer customer = new Customer();
        Address address = new Address();
        customer.setAddress(address);
        Card card = new Card();
        customer.setCard(card);
        log.debug("Card: " + card + ", customer: " + customer);

        model.addAttribute("customer", customer);

        return "registerCustomer";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerCustomerPost(@Valid @ModelAttribute("customer") Customer customer,
                                       HttpServletRequest request,
                                       BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "registerCustomer";
        }

        Customer existingCust = customerService.getCustomerByEmail(customer.getCustomerEmail());
        if (existingCust != null) {
            model.addAttribute("emailMsg", "Email already exists");
            return "registerCustomer";
        }

        if (!customer.getPassword().equals(customer.getPasswordRepeat())) {
            model.addAttribute("pswRepeatMsg", "Passwords must match");
            return "registerCustomer";
        }

        customer.setEnabled(true);

        log.debug("Saving customer: " + customer);

        String password = customer.getPassword();
        String encryptedPassword = passwordEncoder.encode(password);
        customer.setPassword(encryptedPassword);
        customerService.addCustomer(customer);

        authenticateUser(request, customer.getCustomerEmail(), password);

        return "redirect:/";
    }

    private void authenticateUser(HttpServletRequest request, String username, String password) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
        request.getSession();
        token.setDetails(new WebAuthenticationDetails(request));

        try {
            Authentication auth = authenticationManager.authenticate(token);
            SecurityContextHolder.getContext().setAuthentication(auth);
        } catch(Exception e) {
            log.error("Error authenticating user: " + e.getMessage());
        }
    }
}