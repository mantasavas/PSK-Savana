package lt.vu.controller;

import lt.vu.model.ProductCategory;
import lt.vu.service.api.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private ProductCategoryService productCategoryService;

    @RequestMapping("/")
    public String home(Model model) {
        List<ProductCategory> categories = productCategoryService.getAllCategories();
        model.addAttribute("categories", categories);
        return "home";
    }

    @RequestMapping("/login")
    public String login(
            @RequestParam(value="error", required = false)
                    String error,
            @RequestParam(value="logout", required = false)
                    String logout,
            Model model) {

        if (error != null) {
            model.addAttribute("error", "Invalid email or password");
        }

        if (logout != null) {
            model.addAttribute("msg", "You have been logged out successfully");
        }

        return "login";
    }

    @RequestMapping("/about")
    public String about() {
        return "about";
    }
}