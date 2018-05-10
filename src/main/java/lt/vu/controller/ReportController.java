package lt.vu.controller;

import lt.vu.model.Product;
import lt.vu.service.ExcelProductListReport;
import lt.vu.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value="/admin/generateReport")
public class ReportController {

    @Autowired
    private ProductService productService;

    @RequestMapping(value="/report", method = RequestMethod.GET)
    public ModelAndView userListReport(HttpServletRequest req, HttpServletResponse res){

        String typeReport = req.getParameter("type");

        // Get all available products from database
        List<Product> products = productService.getProducts();

        // Check if url get request parameter contains xls, if yes download excel document with all products from db
        if(typeReport != null && typeReport.equals("xls")) {
            System.out.println("inside if: " + typeReport);
            return new ModelAndView(new ExcelProductListReport(), "productList", products);

        }

        // If no parameter specified return product list...
        return new ModelAndView("productListReport", "productList", products);
    }
}
