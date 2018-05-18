package lt.vu.controller;

import lt.vu.model.Address;
import lt.vu.model.Card;
import lt.vu.model.Customer;
import lt.vu.service.api.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;

@Controller
public class RegisterController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @RequestMapping("/register")
    public String registerCustomer(Model model){
        Customer customer = new Customer();
        Address address = new Address();
        customer.setAddress(address);
        Card card = new Card();
        customer.setCard(card);
        System.out.println("Card: " + card + ", customer: " + customer);

        model.addAttribute("customer", customer);

        return "registerCustomer";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerCustomerPost(@Valid @ModelAttribute("customer") Customer customer, BindingResult result, Model model){
        if(result.hasErrors()){
            return "registerCustomer";
        }

        Customer existingCust = customerService.getCustomerByEmail(customer.getCustomerEmail());
        System.out.println(existingCust);
        if (existingCust != null) {
            model.addAttribute("emailMsg", "Email already exists");
            return "registerCustomer";
        }

        if (!customer.getPassword().equals(customer.getPasswordRepeat())) {
            model.addAttribute("pswRepeatMsg", "Passwords must match");
            return "registerCustomer";
        }

        customer.setEnabled(true);

        System.out.println("Saving customer: " + customer);

        String encryptedPassword = passwordEncoder.encode(customer.getPassword());
        customer.setPassword(encryptedPassword);
        customerService.addCustomer(customer);

        return "registerCustomerSuccess";
    }
}