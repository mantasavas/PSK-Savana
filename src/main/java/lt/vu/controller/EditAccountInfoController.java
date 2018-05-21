package lt.vu.controller;

import lt.vu.model.Customer;
import lt.vu.service.api.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.security.Principal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;

@Controller
public class EditAccountInfoController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @RequestMapping("/customer/edit")
    public String editAccountInfo(Model model, Principal activeUser){
        Customer customer = customerService.getCustomerByEmail(activeUser.getName());
        model.addAttribute("customer", customer);

        throw new RuntimeException("Test");

        //return "editAccountInfo";
    }

    @RequestMapping(value = "/customer/edit", method = RequestMethod.POST)
    public String editAccountInfoPost(@Valid @ModelAttribute("customer") Customer customer, BindingResult result, Model model){
        if (result.hasErrors()) {
            return "editAccountInfo";
        }

        Customer existingCustomer = customerService.getCustomerByEmail(customer.getCustomerEmail());
        if (existingCustomer != null && existingCustomer.getCustomerId() != customer.getCustomerId()) {
            model.addAttribute("emailMsg", "Email already exists");
            return "editAccountInfo";
        }

        if (!customer.getPassword().equals(customer.getPasswordRepeat())) {
            model.addAttribute("pswRepeatMsg", "Passwords must match");
            return "editAccountInfo";
        }

        String encryptedPassword = passwordEncoder.encode(customer.getPassword());
        customer.setPassword(encryptedPassword);
        customer.setEnabled(true);
        customerService.updateCustomer(customer);

        return "editAccountInfoSuccess";
    }
}