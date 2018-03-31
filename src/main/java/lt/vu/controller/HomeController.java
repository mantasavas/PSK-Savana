package lt.vu.controller;

import lt.vu.dao.api.ProductDao;
import lt.vu.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
    public String product(@PathVariable Integer id, Model model) {
        Product product = productDao.getProductById(id);
        model.addAttribute(product);

        return "product";
    }

    @RequestMapping("/admin")
    public String adminPage(){
        return "admin";
    }

    @RequestMapping("/admin/productInventory")
    public String productInventory(Model model){
        List<Product> products = productDao.getProducts();
        model.addAttribute("products", products);

        return "productInventory";
    }

    @RequestMapping("/admin/productInventory/addProduct")
    public String addProduct(Model model){
        Product product = new Product();

        product.setCategory("Other");
        product.setCondition("New");
        product.setStatus("Active");

        model.addAttribute("product", product);

        return "addProduct";
    }

    @RequestMapping(value = "/admin/productInventory/addProduct", method = RequestMethod.POST)
    public String addProductPost(@ModelAttribute("product") Product product){
        productDao.addProduct(product);

        return "redirect:/admin/productInventory";
    }

    @RequestMapping("/admin/productInventory/deleteProduct/{id}")
    public String deleteProduct(@PathVariable Integer id, Model model) {
        productDao.deleteProduct(id);

        return "redirect:/admin/productInventory";    }
}
