package lt.vu.controller;

import lt.vu.dao.api.ProductDao;
import lt.vu.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private ProductDao productDao;

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

    @RequestMapping("/products/product/{id}")
    public String product(@PathVariable String id, Model model) {
        Product product = productDao.getProductById(id);
        model.addAttribute(product);

        return "product";
    }
}
