package m07.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExportExcelTonKho   {

	private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<Object[]> listTK;
    
    public ExportExcelTonKho (List<Object[]> listTK) {
        this.listTK = listTK;
        workbook = new XSSFWorkbook();
    }
    
    private static XSSFCellStyle dong1(XSSFWorkbook workbook, XSSFSheet sheet, int from, int to) {
		sheet.addMergedRegion(new CellRangeAddress(0, 0, from, to));
		// Font
		XSSFFont font = workbook.createFont();
		font.setBold(true);

		font.setFontHeightInPoints((short) 14);

		font.setFontName("Times New Roman");

		// Style
		XSSFCellStyle style = workbook.createCellStyle();
		style.setFont(font);

		return style;
	}
    
    private static XSSFCellStyle dong2(XSSFWorkbook workbook, XSSFSheet sheet, int from, int to) {
		sheet.addMergedRegion(new CellRangeAddress(1, 1, from, to));
		// Font
		XSSFFont font = workbook.createFont();

		// Font Height
		font.setFontHeightInPoints((short) 14);

		font.setFontName("Times New Roman");

		// Style
		XSSFCellStyle style = workbook.createCellStyle();
		style.setFont(font);

		return style;
	}

	private static XSSFCellStyle dong3(XSSFWorkbook workbook, XSSFSheet sheet, int from, int to) {
		sheet.addMergedRegion(new CellRangeAddress(2, 2, from, to));
		// Font
		XSSFFont font = workbook.createFont();

		// Font Height
		font.setFontHeightInPoints((short) 14);

		font.setFontName("Times New Roman");

		// Style
		XSSFCellStyle style = workbook.createCellStyle();
		style.setFont(font);

		return style;
	}

	private static XSSFCellStyle dong4(XSSFWorkbook workbook, XSSFSheet sheet, int from, int to) {
		sheet.addMergedRegion(new CellRangeAddress(4, 4, from, to));
		// Font
		XSSFFont font = workbook.createFont();
		font.setBold(true);

		font.setFontHeightInPoints((short) 20);
		font.setColor(IndexedColors.BLUE.index);

		font.setFontName("Times New Roman");

		// Style
		XSSFCellStyle style = workbook.createCellStyle();
		style.setFont(font);

		style.setAlignment(HorizontalAlignment.CENTER);

		return style;
	}
	
	private static XSSFCellStyle dong5(XSSFWorkbook workbook, XSSFSheet sheet, int from, int to) {
		sheet.addMergedRegion(new CellRangeAddress(6, 6, from, to));
		// Font
		XSSFFont font = workbook.createFont();

		// Font Height
		font.setFontHeightInPoints((short) 14);

		font.setFontName("Times New Roman");

		// Style
		XSSFCellStyle style = workbook.createCellStyle();
		style.setFont(font);

		return style;
	}
	
	private static XSSFCellStyle dong6(XSSFWorkbook workbook, XSSFSheet sheet, int from, int to) {
		sheet.addMergedRegion(new CellRangeAddress(7, 7, from, to));
		// Font
		XSSFFont font = workbook.createFont();

		// Font Height
		font.setFontHeightInPoints((short) 14);

		font.setFontName("Times New Roman");

		// Style
		XSSFCellStyle style = workbook.createCellStyle();
		style.setFont(font);

		return style;
	}
	
	private static XSSFCellStyle dongNgay(XSSFWorkbook workbook, XSSFSheet sheet, int cot1, int cot2, int row) {
		sheet.addMergedRegion(new CellRangeAddress(row, row, cot1, cot2));
		// Font
		XSSFFont font = workbook.createFont();
		font.setItalic(true);

		font.setFontHeightInPoints((short) 14);

		font.setFontName("Times New Roman");

		// Style
		XSSFCellStyle style = workbook.createCellStyle();
		style.setFont(font);
		style.setAlignment(HorizontalAlignment.RIGHT);

		return style;
	}
	
	private static XSSFCellStyle normal(XSSFWorkbook workbook) {
		// Font
		XSSFFont font = workbook.createFont();
		font.setFontName("Times New Roman");
		font.setFontHeightInPoints((short) 14);

		// Style
		XSSFCellStyle style = workbook.createCellStyle();
		style.setFont(font);

		return style;
	}

	private static XSSFCellStyle normal1(XSSFWorkbook workbook) {
		// Font
		XSSFFont font = workbook.createFont();
		font.setFontName("Times New Roman");
		font.setFontHeightInPoints((short) 14);

		// Style
		XSSFCellStyle style = workbook.createCellStyle();
		
		style.setFont(font);
		style.setBorderBottom(BorderStyle.DASHED);
		style.setBorderLeft(BorderStyle.DASHED);
		style.setBorderRight(BorderStyle.DASHED);
		style.setBorderTop(BorderStyle.DASHED);

		return style;
	}

	private static XSSFCellStyle bold1(XSSFWorkbook workbook) {
		// Font
		XSSFFont font = workbook.createFont();
		font.setBold(true);
		font.setFontName("Times New Roman");
		font.setFontHeightInPoints((short) 14);

		// Style
		XSSFCellStyle style = workbook.createCellStyle();
		style.setFont(font);
		style.setBorderBottom(BorderStyle.DASHED);
		style.setBorderLeft(BorderStyle.DASHED);
		style.setBorderRight(BorderStyle.DASHED);
		style.setBorderTop(BorderStyle.DASHED);

		return style;
	}
	
    private void writeHeaderLine(String name, String date){
        sheet = workbook.createSheet("Thống Kê Doanh Thu");
        
        Cell cell;
        
        Row row0 = sheet.createRow(0);
        cell = row0.createCell(0, CellType.STRING);
		cell.setCellValue("SHOP MỸ PHẨM YUMI");
		XSSFCellStyle style1 = dong1(workbook, sheet, 0, 5);
		cell.setCellStyle(style1);
		
        Row row1 = sheet.createRow(1);
        cell = row1.createCell(0, CellType.STRING);
		cell.setCellValue("Địa chỉ: 97, Man Thiện, P.Hiệp Phú, Quận 9, TP.HCM");
		XSSFCellStyle style2 = dong2(workbook, sheet, 0, 5);
		cell.setCellStyle(style2);
        
        Row row2 = sheet.createRow(2);
        cell = row2.createCell(0, CellType.STRING);
		cell.setCellValue("SĐT: 033.5998.119");
		XSSFCellStyle style3 = dong3(workbook, sheet, 0, 5);
		cell.setCellStyle(style3);
        
        Row row4 = sheet.createRow(4);
        cell = row4.createCell(0, CellType.STRING);
		cell.setCellValue("THỐNG KÊ TỒN KHO");
		XSSFCellStyle style4 = dong4(workbook, sheet, 0, 5);
		cell.setCellStyle(style4);
        
		XSSFCellStyle normal = normal(workbook);
		XSSFCellStyle normal1 = normal1(workbook);
		XSSFCellStyle bold1 = bold1(workbook);
		
        Row row6 = sheet.createRow(6);
        cell = row6.createCell(0, CellType.STRING);
		cell.setCellValue("Tính đến ngày: " + date);
		XSSFCellStyle style5 = dong5(workbook, sheet, 0, 5);
		cell.setCellStyle(style5);
        
        Row row7 = sheet.createRow(7);
        cell = row7.createCell(0, CellType.STRING);
		cell.setCellValue("Người Tạo: " + name);
		XSSFCellStyle style6 = dong6(workbook, sheet, 0, 5);
		cell.setCellStyle(style6);
        
		Row row = sheet.createRow(10);
		
        createCell1(row, 0, "STT", bold1);      
        createCell1(row, 1, "Mã Sản Phẩm", bold1);       
        createCell1(row, 2, "Tên Sản Phẩm", bold1);    
        createCell1(row, 3, "Tổng Số Lượng Nhập", bold1);
        createCell1(row, 4, "Tổng Số Lượng Xuất", bold1);
        createCell1(row, 5, "Số Lượng Tồn", bold1);
    }
    
    private void createCell1(Row row, int columnCount, Object value, XSSFCellStyle style) {
    	
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
    
    private void writeDataLines() {
        int rowCount = 11;
        int stt = 1;
        int dem = 0;
        int sumSLN = 0;
        int sumSLX = 0;
        int sumSLT = 0;
        
        Cell cell;
        
        XSSFCellStyle normal = normal(workbook);
		XSSFCellStyle normal1 = normal1(workbook);
		XSSFCellStyle bold1 = bold1(workbook);
		
        for (Object[] item : listTK) {
        	
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;
            
            createCell1(row, columnCount++, stt, normal1);
            stt++;
            createCell1(row, columnCount++, item[1], normal1);
            createCell1(row, columnCount++, item[2], normal1);
            createCell1(row, columnCount++, item[3], normal1);
            createCell1(row, columnCount++, item[4], normal1);
            createCell1(row, columnCount++, item[5], normal1);
            
            sumSLN += Integer.parseInt(String.valueOf(item[3]));
            sumSLX += Integer.parseInt(String.valueOf(item[4]));
            sumSLT += Integer.parseInt(String.valueOf(item[5]));
            
            dem = rowCount;
        }
        DateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
        String currentDateTime = dateFormatter.format(new Date());
        
        Row row = sheet.createRow(dem);
        
        createCell1(row, 2, "Tổng: " , bold1);
        createCell1(row, 3, sumSLN , bold1);
        createCell1(row, 4, sumSLX , bold1);
        createCell1(row, 5, sumSLT , bold1);
        
        Row row2 = sheet.createRow(dem + 2);
        cell = row2.createCell(4, CellType.STRING);
		cell.setCellValue("TP.HCM, ngày " + currentDateTime);
		XSSFCellStyle dongNgay = dongNgay(workbook, sheet, 4, 5, dem + 2);  
		cell.setCellStyle(dongNgay);
    }
     
    public void export(HttpServletResponse response, String name, Date date) throws IOException {
    	
    	SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
    	String currentDateTime = dateFormatter.format(date);
		
        writeHeaderLine(name, currentDateTime);
        writeDataLines();
         
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
         
        outputStream.close();
         
    }
}
