package lt.vu.controller;

import lt.vu.model.Product;
import lt.vu.service.ExcelUserListReportView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value="/generateReport")
public class ReportController {

    @RequestMapping(value="/report", method = RequestMethod.GET)
    public ModelAndView userListReport(HttpServletRequest req, HttpServletResponse res){

        System.out.println("========================================== Testas ========================================");

        String typeReport = req.getParameter("type");

        System.out.println("type of report: " + typeReport);

        // Create data
        List<Product> products = new ArrayList<>();
        Product product = new Product();

        product.setProductId(1);
        product.setProductName("Pele");
        product.setProductCategory("Mouses");
        product.setProductDescription("Fancy, new, unsused mouse kit");
        product.setProductPrice(12.20);
        product.setProductCondition("New");
        product.setProductStatus("Available");
        product.setProductManufacturer("MIT");

        products.add(product);

        if(typeReport != null && typeReport.equals("xls")) {
            System.out.println("inside if: " + typeReport);
            return new ModelAndView(new ExcelUserListReportView(), "userList", products);

        }

        return new ModelAndView("userListReport", "userList", products);
    }
}
