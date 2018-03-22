package lt.vu.controller;

import lt.vu.dao.ProductDao;
import lt.vu.model.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class HomeController {

    private ProductDao productDao = new ProductDao();

    @RequestMapping("/")
    public String home(){
        return "home";
    }

    @RequestMapping("/products")
    public String getProducts(Model model){
        List<Product> products = productDao.getProducts();
        model.addAttribute("products", products);

        return "products";
    }

    @RequestMapping("/products/product")
    public String product(){
        return "product";
    }
}
