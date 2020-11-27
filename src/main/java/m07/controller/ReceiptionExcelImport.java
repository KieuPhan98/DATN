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
	
	public List<ReceipDetail> readReceipFromExcelFile(String excelFilePath) throws IOException {
	    List<ReceipDetail> listReceip = new ArrayList<>();
	    FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
	 
	    Workbook workbook = new XSSFWorkbook(inputStream);
	    Sheet firstSheet = workbook.getSheetAt(0);
	    Iterator<Row> iterator = firstSheet.iterator();
	 
	    while (iterator.hasNext()) {
	        Row nextRow = iterator.next();
	        Iterator<Cell> cellIterator = nextRow.cellIterator();
	        ReceipDetail aReceipDetail = new ReceipDetail();
	 
	        while (cellIterator.hasNext()) {
	            Cell nextCell = cellIterator.next();
	            int columnIndex = nextCell.getColumnIndex();
	 
	            switch (columnIndex) {
	            case 1:
	            	aReceipDetail.setId((int) getCellValue(nextCell));
	                break;
	            case 2:
	            	aReceipDetail.getProducts().setId((int) getCellValue(nextCell));
	                break;
	            case 3:
	            	aReceipDetail.setQuantity((int) getCellValue(nextCell));
	                break;
		        case 4:
	            	aReceipDetail.setUnitPrice((double) getCellValue(nextCell));
	                break;
            	}
	        }
	        listReceip.add(aReceipDetail);
	    }
	 
	    workbook.close();
	    inputStream.close();
	 
	    return listReceip;
	}
}
