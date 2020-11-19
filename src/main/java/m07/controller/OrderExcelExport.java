package m07.controller;

import java.io.IOException;
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

import m07.entity.OrderForSuplierDetail;

public class OrderExcelExport {

	private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<OrderForSuplierDetail> listOrders;
    
    public OrderExcelExport(List<OrderForSuplierDetail> listOrders) {
        this.listOrders = listOrders;
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
        else {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(style);
    }
    
    private void writeHeaderLine(){
        sheet = workbook.createSheet("OrderForSupplier");
         
        Row row = sheet.createRow(10);
         
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);
         
        createCell(row, 0, "STT", style);      
        createCell(row, 1, "Ma san pham", style);       
        createCell(row, 2, "Ten San Pham", style);    
        createCell(row, 3, "Don Vi Tinh", style);
        createCell(row, 4, "So Luong", style);
        createCell(row, 5, "Don Gia", style);
        createCell(row, 6, "Thanh Tien", style);
        
        Row row0 = sheet.createRow(0);
        createCell(row0, 0, "SHOP MY PHAM YUMI", style);
        
        Row row1 = sheet.createRow(1);
        createCell(row1, 0, "Dia chi: 97, Man Thien, P.Hiep Phu, Quan 9, TP.HCM", style);
        
        Row row2 = sheet.createRow(2);
        createCell(row2, 0, "SDT: 0335998119", style);
        
        Row row4 = sheet.createRow(4);
        createCell(row4, 2, "PHIEU DAT HANG", style);
        
        DateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
        String currentDateTime = dateFormatter.format(new Date());
        Row row6 = sheet.createRow(6);
        createCell(row6, 0, "Ngay Lap Phieu: ", style);
        createCell(row6, 1, currentDateTime, style);
        
		/*
		 * Row row7 = sheet.createRow(7); createCell(row7, 0, "Nha Cung Cap: ", style);
		 */
    }
    
    private void writeDataLines() {
        int rowCount = 11;
        int stt;
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);
        
        Row row7 = sheet.createRow(7);
        createCell(row7, 0, "Nha Cung Cap: ", style);
        //createCell(row7, 1, listOrders[0].getOrderForSupplier().getSupplier().getName(), style);
        
        int sum = 0;
        int dem = 0;
        for (OrderForSuplierDetail order : listOrders) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;
            stt = rowCount - 11;
            createCell(row, columnCount++, stt, style);
            createCell(row, columnCount++, order.getProducts().getId(), style);
            createCell(row, columnCount++, order.getProducts().getName(), style);
            createCell(row, columnCount++, order.getProducts().getUnitBrief(), style);
            createCell(row, columnCount++, order.getQuantity(), style);
            createCell(row, columnCount++, order.getUnitPrice(), style);
            createCell(row, columnCount++, order.getQuantity() * order.getUnitPrice(), style);
            
            sum += order.getQuantity() * order.getUnitPrice();
            dem = rowCount;
            
            createCell(row7, 1, order.getOrderForSupplier().getSupplier().getName(), style);
        }
        Row row = sheet.createRow(dem);
        createCell(row, 5, "Tong Tien: ", style);
        createCell(row, 6, sum, style);
    }
     
    public void export(HttpServletResponse response) throws IOException {
        writeHeaderLine();
        writeDataLines();
         
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
         
        outputStream.close();
         
    }
}
