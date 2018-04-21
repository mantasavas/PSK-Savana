package lt.vu.controller;

import lt.vu.dao.api.ProductDao;
import lt.vu.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
public class AdminController {

    private Path path;

    @Autowired
    private ProductDao productDao;

    @RequestMapping("/admin")
    public String adminPage() {
        return "admin";
    }

    @RequestMapping("/admin/productInventory")
    public String productInventory(Model model) {
        List<Product> products = productDao.getProducts();
        model.addAttribute("products", products);

        return "productInventory";
    }

    @RequestMapping("/admin/productInventory/addProduct")
    public String addProduct(Model model) {
        Product product = new Product();

        product.setCategory("Other");
        product.setCondition("New");
        product.setStatus("Active");

        model.addAttribute("product", product);

        return "addProduct";
    }


    @RequestMapping(value = "/admin/productInventory/addProduct", method = RequestMethod.POST)
    public String addProductPost(@Valid @ModelAttribute("product") Product product, BindingResult result,
                                 HttpServletRequest request) {
        if (result.hasErrors()) {
            return "addProduct";
        }

        productDao.addProduct(product);

        MultipartFile image = product.getImage();
        String rootDirectory = request.getSession().getServletContext().getRealPath("/");
        path = Paths.get(rootDirectory + File.separator + "WEB-INF"
                + File.separator + "resources"
                + File.separator +"images"
                + File.separator + product.getId() + ".png");

        if (image != null && !image.isEmpty()) {
            try {
                image.transferTo(new File(path.toString()));
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("Product image saving failed!", e);
            }
        }

        return "redirect:/admin/productInventory";
    }

    @RequestMapping("/admin/productInventory/deleteProduct/{id}")
    public String deleteProduct(@PathVariable Integer id, Model model, HttpServletRequest request) {
        productDao.deleteProduct(id);

        String rootDirectory = request.getSession().getServletContext().getRealPath("/");
        path = Paths.get(rootDirectory + File.separator + "WEB-INF"
                + File.separator+ "resources"
                + File.separator + "images"
                +File.separator + id + ".png");

        if (Files.exists(path)) {
            try {
                Files.delete(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return "redirect:/admin/productInventory";
    }

    @RequestMapping("/admin/productInventory/editProduct/{id}")
    public String editProduct(@PathVariable("id") Integer id, Model model) {
        Product product = productDao.getProductById(id);
        model.addAttribute(product);

        return "editProduct";
    }

    @RequestMapping(value = "/admin/productInventory/editProduct", method = RequestMethod.POST)
    public String editProduct(@Valid @ModelAttribute("product") Product product, BindingResult result,
                              Model model, HttpServletRequest request) {
        if (result.hasErrors()) {
            return "editProduct";
        }

        MultipartFile productImage = product.getImage();

        String rootDirectory =  request.getSession().getServletContext().getRealPath("/");
        path = Paths.get(rootDirectory + File.separator + "WEB-INF"
                + File.separator + "resources"
                + File.separator + "images"
                + File.separator + product.getId() + ".png");

        if (productImage != null && !productImage.isEmpty()) {
            try {
                productImage.transferTo(new File(path.toString()));
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("Product image saving failed!");
            }
        }

        productDao.editProduct(product);

        return "redirect:/admin/productInventory";
    }
}
