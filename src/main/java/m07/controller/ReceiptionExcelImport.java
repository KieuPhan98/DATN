package m07.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import m07.entity.ReceipDetail;
import m07.entity.ViewReceiptionDetail;

public class ReceiptionExcelImport {

	private Object getCellValue(Cell cell) {
		CellType type = cell.getCellTypeEnum();
		
		if(type == CellType.STRING) {
			return cell.getStringCellValue();
		}
		else if(type == CellType.BOOLEAN) {
			return cell.getBooleanCellValue();
		}
		else if(type == CellType.NUMERIC){
			return cell.getNumericCellValue();
		}
		
		return null;
	}
	
	public static void ReadFile(String excelFilePath, ArrayList<ViewReceiptionDetail> listView) throws IOException {
	    List<ReceipDetail> listReceip = new ArrayList<>();
	    
	    // Đọc một file XSL
	    FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
	    Workbook workbook = new XSSFWorkbook(inputStream);
	    
	    // Lấy ra sheet đầu tiên từ workbook
	    Sheet sheet = workbook.getSheetAt(0);
	    
	    //------đọc chi tiết phiếu nhập
	    
	    int rownum = 11;
		boolean flag = true;
		while(flag) {
			ViewReceiptionDetail r = new ViewReceiptionDetail();
			
			Row row = sheet.getRow(rownum);
			
			for (int i = 0; i < 6 ; i++) {
				if(row.getCell(i) == null) {
					flag = false;
					break;
				}

				Cell cell = row.getCell(i);

				CellType cellType = cell.getCellTypeEnum();
				if (cellType == CellType._NONE || cellType == CellType.BLANK) {
					flag = false;
					break;
				}
				//stt
				if(i == 0) {
					r.setStt((int) cell.getNumericCellValue());
				}

				// id_product
				if (i == 1) {
					r.setIdSp((int) cell.getNumericCellValue());
				}

				// sl
				if (i == 4) {
					r.setSoluong((int) cell.getNumericCellValue());
				}

				// don gia
				if (i == 5) {
					r.setDongia((double) cell.getNumericCellValue());
				}
			}
			if(r.getStt() != 0) listView.add(r);
			rownum ++;
		}
	}
}
