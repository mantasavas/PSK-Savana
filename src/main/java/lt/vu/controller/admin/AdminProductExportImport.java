package lt.vu.controller.admin;

import lt.vu.model.Product;
import lt.vu.service.impl.ExcelProductListReport;
import lt.vu.service.api.ProductService;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value="/admin")
public class AdminProductExportImport {

    @Autowired
    private ProductService productService;

    @RequestMapping(value="/generateProductExcel/products", method = RequestMethod.GET)
    public ModelAndView userListReport(HttpServletRequest req, HttpServletResponse res){

        String typeReport = req.getParameter("type");

        // Get all available products from database
        List<Product> products = productService.getProducts();

        // Check if url get request parameter contains xls, if yes download excel document with all products from db
        if(typeReport != null && typeReport.equals("xls")) {
            return new ModelAndView(new ExcelProductListReport(), "productList", products);
        }

        // If no parameter specified return product list...
        return new ModelAndView("admin/productListExport", "productList", products);
    }

    @RequestMapping("/importProductExcel/excelFile")
    public String getProducts(Model model){
        return "admin/productListImport";
    }

    @RequestMapping(value = "/importProductExcel/excelFile", method = RequestMethod.POST)
    public String importProductExcelFile(Model model, MultipartFile file){
        System.out.println("Inside importProductExcelFile()");

        try {
            List<Product> products = new ArrayList<>();
            int i = 0;
            // Creates a workbook object from the uploaded excelfile
            HSSFWorkbook workbook = new HSSFWorkbook(file.getInputStream());
            // Creates a worksheet object representing the first sheet
            HSSFSheet worksheet = workbook.getSheetAt(0);
            // Reads the data in excel file until last row is encountered
            while (i <= worksheet.getLastRowNum()) {
                // Creates an object for the product Model
                Product product = new Product();
                // Creates an object representing a single row in excel
                HSSFRow row = worksheet.getRow(i++);

                if(i != 1) {

                    // Setting product Name
                    product.setProductName(row.getCell(1).getStringCellValue());
                    // Setting product Category
                    product.setProductCategory(row.getCell(2).getStringCellValue());
                    // Setting product Description
                    product.setProductDescription(row.getCell(3).getStringCellValue());
                    // Setting product Price
                    product.setProductPrice(row.getCell(4).getNumericCellValue());
                    // Setting product Condition
                    product.setProductCondition(row.getCell(5).getStringCellValue());
                    // Setting product Status
                    product.setProductStatus(row.getCell(6).getStringCellValue());
                    // Setting product Manufacturer
                    product.setProductManufacturer(row.getCell(7).getStringCellValue());

                    products.add(product);
                }
            }
            workbook.close();

            // Persisting product data to database
            productService.addProducts(products);
            model.addAttribute("products", products);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "admin/productInventory";
    }
}
