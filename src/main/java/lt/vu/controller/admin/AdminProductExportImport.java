package lt.vu.controller.admin;

import lt.vu.model.Product;

import lt.vu.service.impl.ProductServiceImpl;
import lt.vu.service.importExportImpl.ImportExportImpl;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

@Controller
@Scope("session")
@RequestMapping(value="/admin")
public class AdminProductExportImport {
    @Autowired
    private ImportExportImpl importExportservice;

    @Autowired
    private ProductServiceImpl productService;

    private ModelAndView excelModel;
    private Future<ModelAndView> excelModelFuture = null;

    @RequestMapping(value="/generateProductExcel/products", method = RequestMethod.GET)
    public ModelAndView userListReport(HttpServletRequest req, HttpServletResponse res){
        String typeReport = req.getParameter("type");

        // If admin confirmed he wants to download excel file
        if (typeReport != null && typeReport.equals("xls") && excelModelFuture != null && excelModelFuture.isDone()) {
            try {
                excelModel = excelModelFuture.get();
                excelModelFuture = null;
            } catch (Exception ex) {
                System.out.println(ex.toString());
            }
        }
        else{
            // If no parameter specified return product list...
            excelModel = new ModelAndView("admin/productListExport", "productList", importExportservice.getProducts());
        }

        /*
        int index = 0;
        while (typeReport != null) {
            System.out.println("========================================================================================");
            System.out.println("Future " + excelModelFuture);
            System.out.println("Is done " + excelModelFuture.isDone());
            if (excelModelFuture != null && excelModelFuture.isDone()) {
                System.out.println("Result from asynchronous process - ");
                try {
                    excelModel = excelModelFuture.get();
                    System.out.println("Succesfully received! ");
                }catch (Exception ex)
                {
                    System.out.println("Something went wrong while getting future! Stack trace: " + ex.toString());
                }
                break;
            }
            try{ Thread.sleep(1000);} catch (Exception ex) {};
            System.out.println("Continue doing something else. " + index++ );
            System.out.println("========================================================================================");
        }
        */
        return excelModel;
    }


    // Then user requests to export file (sends POST request)
    @RequestMapping(value="/generateProductExcel/products/start", method = RequestMethod.GET)
    public ModelAndView startExportationOfExcel(HttpServletRequest req, HttpServletResponse res){
        String[] selectedProducts = req.getParameterValues("selected");


        // In order to generate excel file it must be either extraction process finished, or excelModelFuture null (administrator downloaded file)
        if (excelModelFuture == null){
            // Starting process in the background. Returns excel future interface, we would check every time if document is ready.
            excelModelFuture = importExportservice.asyncImportExcel(req, "xls", selectedProducts);
            System.out.println("Is ready " + excelModelFuture.isDone());

        }

        // Returning all products from database
        return new ModelAndView("admin/productListExport", "productList", importExportservice.getProducts());
    }



    @RequestMapping(value = "/importProductExcel/isReadyFile", method=RequestMethod.GET)
    @ResponseBody
    public Map checkFileAvailability(Model model){
        if (excelModelFuture != null && excelModelFuture.isDone()){
            return Collections.singletonMap("response", "true");
        }
        else { return Collections.singletonMap("response", "false"); }
    }

    @RequestMapping(value = "/importProductExcel/fileExport", method = RequestMethod.GET)
    public ModelAndView sendExcelFile(Model model){
        if (excelModelFuture != null && excelModelFuture.isDone()){
            try {
                return new ModelAndView("admin/productListExport", "productList", excelModelFuture.get());
            }catch (Exception ex)
            {
                System.out.println("Exception occured while getting file! " + ex.toString());
            }
        }

        return null;
    }

    @RequestMapping("/importProductExcel/excelFile")
    public String getProducts(Model model){
        return "admin/productListImport";
    }




    @RequestMapping(value = "/importProductExcel/excelFile", method = RequestMethod.POST)
    public String importProductExcelFile(Model model, MultipartFile file){

        List<Product> products = new ArrayList<>();

        try {
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
                    product.setProductPrice(new BigDecimal(row.getCell(4).getNumericCellValue()));
                    // Setting product Condition
                    product.setProductCondition(row.getCell(5).getStringCellValue());
                    // Setting product Status
                    product.setProductStatus(row.getCell(6).getStringCellValue());
                    // Setting product Manufacturer
                    product.setProductManufacturer(row.getCell(7).getStringCellValue());
                    // Setting product discount percentage
                    product.setProductDiscountPercentage((int) row.getCell(8).getNumericCellValue());
                    // Setting product product discount expiration date
                    product.setProductDiscountExpirationDatetime(row.getCell(9).getStringCellValue());

                    /*
                    System.out.println(product.getProductName());
                    System.out.println(product.getProductCategory());
                    System.out.println(product.getProductDescription());
                    System.out.println(product.getProductPrice());
                    System.out.println(product.getProductCondition());
                    System.out.println(product.getProductStatus());
                    System.out.println(product.getProductManufacturer());
                    System.out.println(product.getProductDiscountExpirationDatetime());
                    System.out.println(product.getProductDiscountPercentage());
                    */

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
