package lt.vu.service;

import com.lowagie.text.ListItem;
import lt.vu.model.Product;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public class ExcelUserListReportView extends AbstractXlsView {

    @Override
    protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        System.out.println("Lalalalaa 1");

        httpServletResponse.setHeader("Content-Disposition", "attachment; filename= \"user_list.xls\"");

        System.out.println("Lalalalaa 2");

        List<Product> list = (List<Product>) model.get("userList");

        System.out.println("Lalalalaa 3");

        Sheet sheet = workbook.createSheet("User List");

        System.out.println("Lalalalaa 4");

        // Header row
        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("ID");
        header.createCell(1).setCellValue("Name");
        header.createCell(2).setCellValue("Category");
        header.createCell(3).setCellValue("Description");
        header.createCell(4).setCellValue("Price");
        header.createCell(5).setCellValue("Condition");
        header.createCell(6).setCellValue("Status");
        header.createCell(7).setCellValue("Manufacturer");

        System.out.println("Lalalalaa 5");

        int rowNum = 1;

        for(Product product : list){
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(product.getProductId());
            row.createCell(1).setCellValue(product.getProductName());
            row.createCell(2).setCellValue(product.getProductCategory());
            row.createCell(3).setCellValue(product.getProductDescription());
            row.createCell(4).setCellValue(product.getProductPrice());
            row.createCell(5).setCellValue(product.getProductCondition());
            row.createCell(6).setCellValue(product.getProductStatus());
            row.createCell(7).setCellValue(product.getProductManufacturer());
        }
    }
}
