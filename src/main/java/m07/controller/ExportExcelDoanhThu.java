package m07.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExportExcelDoanhThu {

	private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<Object[]> listDT;
    
    public ExportExcelDoanhThu(List<Object[]> listDT) {
        this.listDT = listDT;
        workbook = new XSSFWorkbook();
    }
    
    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } 
        else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        }
        else if (value instanceof Double) {
            cell.setCellValue((Double) value);
        }
        else if (value instanceof BigDecimal) {
            cell.setCellValue(value.toString());
        }
        else {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(style);
    }
    
    private void writeHeaderLine(String name){
        sheet = workbook.createSheet("Thống Kê Doanh Thu");
         
        Row row = sheet.createRow(7);
         
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);
         
        createCell(row, 0, "STT", style);      
        createCell(row, 1, "Mã Sản Phẩm", style);       
        createCell(row, 2, "Tên Sản Phẩm", style);    
        createCell(row, 3, "Tổng Số Lượng Bán", style);
        createCell(row, 4, "Doanh Thu", style);
        
        Row row0 = sheet.createRow(0);
        createCell(row0, 0, "SHOP MỸ PHẨM YUMI", style);
        
        Row row2 = sheet.createRow(2);
        createCell(row2, 2, "THỐNG KÊ DOANH THU", style);
        
        DateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
        String currentDateTime = dateFormatter.format(new Date());
        
        Row row4 = sheet.createRow(4);
        createCell(row4, 0, "Ngày Tạo: ", style);
        createCell(row4, 1, currentDateTime, style);
        
        Row row5 = sheet.createRow(5);
        createCell(row5, 0, "Người Tạo: ", style);
        createCell(row5, 1, name, style);
        
    }
    
    private void writeDataLines() {
        int rowCount = 8;
        int stt;
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);
        
        for (Object[] item : listDT) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;
            stt = rowCount - 8;
            createCell(row, columnCount++, stt, style);
            createCell(row, columnCount++, item[1], style);
            createCell(row, columnCount++, item[2], style);
            createCell(row, columnCount++, item[3], style);
            createCell(row, columnCount++, item[4], style);
            
        }
    }
     
    public void export(HttpServletResponse response, String name) throws IOException {
        writeHeaderLine(name);
        writeDataLines();
         
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
         
        outputStream.close();
         
    }
}
