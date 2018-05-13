package lt.vu.controller.admin;

import lt.vu.model.Product;
import lt.vu.service.importExportImpl.ExcelProductListReport;
import lt.vu.service.ProductService;
import lt.vu.service.importExportImpl.ImportExportImpl;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

    int check = 0;
    private boolean downloadedFile = true;

    private ModelAndView excelModel;
    private Future<ModelAndView> excelModelFuture = null;

    @RequestMapping(value="/generateProductExcel/products", method = RequestMethod.GET)
    public ModelAndView userListReport(HttpServletRequest req, HttpServletResponse res){
        System.out.println("Inside controler");
        String typeReport = req.getParameter("type");



        if(typeReport != null && downloadedFile == true) {
            excelModelFuture = importExportservice.asyncImportExcel(req, typeReport);
            downloadedFile = false;
        }




        if (typeReport != null && excelModelFuture.isDone()) {
            System.out.println("8888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888");

            System.out.println(typeReport + " " + excelModelFuture.isDone());

            try {
                excelModel = excelModelFuture.get();
                System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ ");
                downloadedFile = true;
            } catch (Exception ex) {
                System.out.println(ex.toString());
            }

        }
        else if (excelModelFuture != null && excelModelFuture.isDone()){
            // If no parameter specified return product list...
            excelModel = new ModelAndView("admin/productListExport", "productList", importExportservice.getProducts());

        }else {
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
        System.out.println("exiting controler");
        return excelModel;
    }


    @RequestMapping(value = "/importProductExcel/isReadyFile", method=RequestMethod.GET)
    @ResponseBody
    public Map checkFileAvailability(Model model){
        System.out.println("===================================================================================================================" + check++);
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
            //productService.addProducts(products);
            model.addAttribute("products", products);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "admin/productInventory";
    }
}
