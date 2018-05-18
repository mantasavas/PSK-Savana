package lt.vu.controller.admin;

import lt.vu.model.Customer;
import lt.vu.model.CustomerOrder;
import lt.vu.model.Product;
import lt.vu.model.ProductCategory;
import lt.vu.service.api.CustomerOrderService;
import lt.vu.service.api.CustomerService;
import lt.vu.service.api.ProductCategoryService;
import lt.vu.service.api.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminHome {

    @Autowired
    private ProductService productService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerOrderService customerOrderService;

    @Autowired
    private ProductCategoryService productCategoryService;

    @RequestMapping
    public String adminPage(){
        return "admin/admin";
    }

    @RequestMapping("/inventory")
    public String productInventory(Model model) {
        List<Product> products = productService.getProducts();
        model.addAttribute("products", products);

        return "admin/productInventory";
    }

    @RequestMapping("/productCategories")
    public String productCategories(Model model) {
        List<ProductCategory> categories = productCategoryService.getAllCategories();
        List<String> categoryNames = new ArrayList<>();

        for (ProductCategory category : categories) {
            categoryNames.add(category.getProductCategoryName());
        }

        ProductCategory productCategory = new ProductCategory();
        model.addAttribute("productCategory", productCategory);
        model.addAttribute("categories", categoryNames);

        return "admin/productCategories";
    }

    @RequestMapping(value="/productCategories", method = RequestMethod.POST)
    public String productCategories(@Valid @ModelAttribute("productCategory") ProductCategory productCategory,
                                    BindingResult result, HttpServletRequest request) {
        if (result.hasErrors()) {
            return "admin/productCategories";
        }

        productCategoryService.addProductCategory(productCategory);

        MultipartFile productImage = productCategory.getProductCategoryImage();
        String rootDirectory = request.getSession().getServletContext().getRealPath("/");
        Path path = Paths.get(rootDirectory + File.separator + "WEB-INF"
                + File.separator + "resources"
                + File.separator +"images"
                + File.separator
                + productCategory.getProductCategoryName() + productCategory.getProductCategoryId() + ".png");

        saveImage(productImage, path);

        return "redirect:/admin/productCategories";
    }

    private void saveImage(MultipartFile productImage, Path imagePath) throws RuntimeException {
        if (productImage != null && !productImage.isEmpty()) {
            try {
                productImage.transferTo(new File(imagePath.toString()));
            } catch (Exception ex) {
                ex.printStackTrace();
                throw new RuntimeException("Product category image saving failed", ex);
            }
        }
    }

    @RequestMapping("/customers")
    public String customerManagement(Model model) {
        List<Customer> customerList = customerService.getAllCustomers();
        model.addAttribute("customerList", customerList);

        return "admin/customerManagement";
    }

    @RequestMapping("/orders")
    public String orderManagement(Model model) {
        List<CustomerOrder> orderList = customerOrderService.getAllOrders();
        model.addAttribute("orderList", orderList);

        return "admin/orderManagement";
    }
}