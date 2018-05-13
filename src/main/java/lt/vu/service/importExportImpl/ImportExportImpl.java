package lt.vu.service.importExportImpl;

import lt.vu.model.Product;
import lt.vu.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.concurrent.Future;

@Service
@Scope("session")
public class ImportExportImpl {

    @Autowired
    private ProductService productService;

    private List<Product> products = null;

    @Async
    public Future<ModelAndView> asyncImportExcel(HttpServletRequest req, String typeReport){

        // Simulate intensive work!
        try{ Thread.sleep(8000);} catch (Exception ex) {};

        // Get all available products from database
        products = productService.getProducts();

        // Check if url get request parameter contains xls, if yes download excel document with all products from db
        if(typeReport != null && typeReport.equals("xls")) {
            ModelAndView excelModel = new ModelAndView(new ExcelProductListReport(), "productList", products);
            return new AsyncResult<>(excelModel);
        }

        // If no parameter specified return product list...
        return new AsyncResult<>(new ModelAndView("admin/productListExport", "productList", products));
    }

    public List<Product> getProducts() {
        products = productService.getProducts();
        int elements = products.size() - 5;
        if (elements < 0)
            elements = products.size() - 1;

        return products.subList(0, elements);
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
