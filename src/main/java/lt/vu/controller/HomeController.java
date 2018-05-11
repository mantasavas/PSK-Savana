package lt.vu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    @RequestMapping("/")
    public String home(){
        return "home";
    }

    @RequestMapping("/login")
    public String login(
            @RequestParam(value="error", required = false)
                    String error,
            @RequestParam(value="logout", required = false)
                    String logout,
            @RequestParam(value="disabled", required = false)
                    String disabled,
            Model model) {

        if (error != null) {
            model.addAttribute("error", "Invalid username or password");
        }

        if (logout != null) {
            model.addAttribute("msg", "You have been logged out successfully");
        }

        if (disabled != null) {
            model.addAttribute("disabled", "Your account has been disabled");
        }

        return "login";
    }

    @RequestMapping("/about")
    public String about(){
        return "about";
    }
}