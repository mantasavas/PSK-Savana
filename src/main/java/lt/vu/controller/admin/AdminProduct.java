package lt.vu.controller.admin;

import lt.vu.model.Attribute;
import lt.vu.model.Product;
import lt.vu.model.ProductAttribute;
import lt.vu.model.ProductCategory;
import lt.vu.service.api.AttributeService;
import lt.vu.service.api.ProductAttributeService;
import lt.vu.service.api.ProductCategoryService;
import lt.vu.service.api.ProductService;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminProduct {

    private Path path;

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductCategoryService productCategoryService;

    @Autowired
    private AttributeService attributeService;

    @Autowired
    private ProductAttributeService productAttributeService;

    @RequestMapping("/product/addProduct")
    public String addProduct(Model model) {
        Product product = new Product();

        List<ProductCategory> categories = productCategoryService.getAllCategories();
        List<String> categoryNames = new ArrayList<>();

        for (ProductCategory category : categories) {
            categoryNames.add(category.getProductCategoryName());
        }

        List<Attribute> attributes = attributeService.getAllAttributes();

        if (!categoryNames.isEmpty()) {
            product.setProductCategory(categoryNames.get(0));
        } else {
            ProductCategory category = new ProductCategory();
            category.setProductCategoryName("Other");
            productCategoryService.addProductCategory(category);
            product.setProductCategory(category.getProductCategoryName());
        }

        product.setProductStatus("active");
        product.setProductDiscountPercentage(0);
        product.setProductDiscountExpirationDatetime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

        model.addAttribute("product", product);
        model.addAttribute("categories", categoryNames);
        model.addAttribute("attributes", attributes);

        return "admin/addProduct";
    }

    @RequestMapping(value="/product/addProduct", method = RequestMethod.POST)
    public String addProductPost(@Valid @ModelAttribute("product") Product product, BindingResult result, HttpServletRequest request){
        if (result.hasErrors()) {
            return "admin/addProduct";
        }

        productService.addProduct(product);

        List<Attribute> attributes = attributeService.getAllAttributes();
        List<ProductAttribute> productAttributes = product.getProductAttributes();

        int index = 0;
        for (ProductAttribute productAttribute: productAttributes) {
            if (!productAttribute.getAttributeValue().equals("")) {
                productAttribute.setProduct(product);
                productAttribute.setAttribute(attributes.get(index));
                productAttributeService.addNewProductAttribute(productAttribute);
                index++;
            }
        }

        MultipartFile productImage = product.getProductImage();
        String rootDirectory = request.getSession().getServletContext().getRealPath("/");
        path = Paths.get(rootDirectory + File.separator + "WEB-INF"
                + File.separator + "resources"
                + File.separator +"images"
                + File.separator + product.getProductId() + ".png");

        saveImage(productImage);

        return "redirect:/admin/inventory";
    }

    @RequestMapping("/product/editProduct/{id}")
    public String editProduct(@PathVariable("id") int id,  Model model){
        Product product = productService.getProductById(id);

        List<ProductCategory> categories = productCategoryService.getAllCategories();
        List<String> categoryNames = new ArrayList<>();

        for (ProductCategory category : categories) {
            categoryNames.add(category.getProductCategoryName());
        }

        if (!categoryNames.isEmpty() && product.getProductCategory() == null) {
            product.setProductCategory(categoryNames.get(0));
        } else if (categoryNames.isEmpty()) {
            ProductCategory category = new ProductCategory();
            category.setProductCategoryName("Other");
            productCategoryService.addProductCategory(category);
            product.setProductCategory(category.getProductCategoryName());
        }

        model.addAttribute("product", product);
        model.addAttribute("categories", categoryNames);

        return "admin/editProduct";
    }

    @RequestMapping(value="/product/editProduct", method = RequestMethod.POST)
    public String editProductPost(@Valid @ModelAttribute("product") Product product, BindingResult result, HttpServletRequest request){
        if (result.hasErrors()) {
            return "admin/editProduct";
        }

        MultipartFile productImage = product.getProductImage();
        String rootDirectory = request.getSession().getServletContext().getRealPath("/");
        path = Paths.get(rootDirectory + File.separator + "WEB-INF"
                + File.separator + "resources"
                + File.separator +"images"
                + File.separator + product.getProductId() + ".png");

        saveImage(productImage);
        productService.editProduct(product);

        return "redirect:/admin/inventory";
    }

    private void saveImage(MultipartFile productImage) throws RuntimeException {
        if (productImage != null && !productImage.isEmpty()) {
            try {
                productImage.transferTo(new File(path.toString()));
            } catch (Exception ex) {
                ex.printStackTrace();
                throw new RuntimeException("Product image saving failed", ex);
            }
        }
    }

    @RequestMapping("/product/deleteProduct/{id}")
    public String deleteProduct(@PathVariable int id, HttpServletRequest request) {
        String rootDirectory = request.getSession().getServletContext().getRealPath("/");
        path = Paths.get(rootDirectory + File.separator + "WEB-INF"
                + File.separator + "resources"
                + File.separator +"images"
                + File.separator + id + ".png");

        if (Files.exists(path)) {
            try {
                Files.delete(path);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        Product product = productService.getProductById(id);
        productService.deleteProduct(product);

        return "redirect:/admin/inventory";
    }
}