package lt.vu.controller.admin;

import lt.vu.model.Attribute;
import lt.vu.model.Image;
import lt.vu.model.Product;
import lt.vu.model.ProductAttribute;
import lt.vu.model.ProductCategory;
import lt.vu.service.api.AttributeService;
import lt.vu.service.api.ProductAttributeService;
import lt.vu.service.api.ImageService;
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
import java.math.BigDecimal;
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

    @Autowired
    private ImageService imageService;

    @RequestMapping("/product/addProduct")
    public String addProduct(Model model) {
        Product product = new Product();

        List<ProductCategory> categories = productCategoryService.getAllCategories();
        List<String> categoryNames = new ArrayList<>();

        for (ProductCategory category : categories) {
            categoryNames.add(category.getProductCategoryName());
        }

        if (!categoryNames.isEmpty()) {
            product.setProductCategory(categoryNames.get(0));
        } else {
            ProductCategory category = new ProductCategory();
            category.setProductCategoryName("Other");
            productCategoryService.addProductCategory(category);
            product.setProductCategory(category.getProductCategoryName());
        }

        List<Attribute> attributes = attributeService.getAllAttributes();

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

        setDefaultValues(product);
        productService.addProduct(product);
        saveProductAttributes(product);
        saveImages(request, product);

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

        List<Attribute> attributes = attributeService.getAllAttributes();

        model.addAttribute("product", product);
        model.addAttribute("categories", categoryNames);
        model.addAttribute("attributes", attributes);

        return "admin/editProduct";
    }

    @RequestMapping(value="/product/editProduct", method = RequestMethod.POST)
    public String editProductPost(@Valid @ModelAttribute("product") Product product, BindingResult result, HttpServletRequest request){
        if (result.hasErrors()) {
            return "admin/editProduct";
        }

        setDefaultValues(product);
        productService.editProduct(product);
        saveImages(request, product);
        saveProductAttributes(product);

        return "redirect:/admin/inventory";
    }

    private void saveProductAttributes(Product product) {
        List<Attribute> attributes = attributeService.getAllAttributes();
        List<ProductAttribute> productAttributes = product.getProductAttributes();

        int index = 0;
        for (ProductAttribute productAttribute: productAttributes) {
            productAttribute.setProduct(product);
            productAttribute.setAttribute(attributes.get(index));
            if (!productAttribute.getAttributeValue().equals("")) {
                productAttributeService.addOrUpdateProductAttribute(productAttribute);
            } else {
                productAttributeService.removeProductAttribute(productAttribute);
            }
            index++;
        }
    }

    private void saveImages(HttpServletRequest request, Product product) {
        List<MultipartFile> files = product.getFiles();
        String rootDirectory = request.getSession().getServletContext().getRealPath("/");

        if (files != null && files.size() > 0) {
            for (int i = 0; i < files.size(); i++) {
                if (files.get(i) != null && !files.get(i).isEmpty()) {
                    MultipartFile productImage = files.get(i);
                    Image img = new Image();
                    img.setProduct(product);
                    imageService.addImage(img);

                    Integer featured = product.getFeaturedImage();

                    if (featured != null && i == featured) {
                        product.setFeaturedImage(img.getImageId());
                        productService.editProduct(product);
                    }

                    path = Paths.get(rootDirectory + File.separator + "WEB-INF"
                            + File.separator + "resources"
                            + File.separator + "images"
                            + File.separator + img.getImageId() + ".png");

                    try {
                        productImage.transferTo(new File(path.toString()));
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        throw new RuntimeException("Product image saving failed", ex);
                    }
                }
            }
        }
    }

    private void setDefaultValues(Product product) {
        if (product.getProductPrice() == null) product.setProductPrice(new BigDecimal(0.00));
        if (product.getProductDescription().equals("")) product.setProductDescription("Empty");
        if (product.getProductStatus().equals("")) product.setProductStatus("active");
        if (product.getProductManufacturer().equals("")) product.setProductManufacturer("Other");
        if (product.getProductDiscountPercentage() == null) product.setProductDiscountPercentage(0);
        if (product.getProductDiscountExpirationDatetime().equals("")) product.setProductDiscountExpirationDatetime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
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

        List<ProductAttribute> productAttributes = product.getProductAttributes();

        for (ProductAttribute productAttribute: productAttributes) {
            if (!productAttribute.getAttributeValue().equals("")) {
                productAttributeService.removeProductAttribute(productAttribute);
            }
        }

        productService.deleteProduct(product);

        return "redirect:/admin/inventory";
    }
}