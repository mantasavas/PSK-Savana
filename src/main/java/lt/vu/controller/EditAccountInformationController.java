package lt.vu.controller;

import lt.vu.model.Customer;
import lt.vu.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
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
public class EditAccountInformationController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @RequestMapping("/customer/edit")
    public String editAccountInfo(Model model, Principal activeUser){
        Customer customer = customerService.getCustomerByUsername(activeUser.getName());
        model.addAttribute("customer", customer);

        return "editAccountInfo";
    }

    @RequestMapping(value = "/customer/edit", method = RequestMethod.POST)
    public String editAccountInfoPost(@Valid @ModelAttribute("customer") Customer customer, BindingResult result, Model model){
        if (result.hasErrors()) {
            return "editAccountInfo";
        }

        List<Customer> customerList = customerService.getAllCustomers();

        for (Customer existingCustomer : customerList) {
            if (customer.getCustomerEmail().equals(existingCustomer.getCustomerEmail())
                    && customer.getCustomerId() != existingCustomer.getCustomerId()) {
                model.addAttribute("emailMsg", "Email already exists");
                return "editAccountInfo";
            }

            if (customer.getUsername().equals(existingCustomer.getUsername())
                    && customer.getCustomerId() != existingCustomer.getCustomerId()) {
                model.addAttribute("usernameMsg", "Username already exists");
                return "editAccountInfo";
            }
        }

        if (!customer.getPassword().equals(customer.getPasswordRepeat())) {
            model.addAttribute("pswRepeatMsg", "Passwords must match");
            return "editAccountInfo";
        }

        String encryptedPassword = passwordEncoder.encode(customer.getPassword());
        customer.setPassword(encryptedPassword);
        customerService.updateCustomer(customer);

        return "editAccountInfoSuccess";
    }
}