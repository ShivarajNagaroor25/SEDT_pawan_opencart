package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtilities {
	public FileInputStream fi;
	public FileOutputStream fo;
	public XSSFWorkbook workbook;
	public XSSFSheet sheet;
	public XSSFRow row;
	public XSSFCell cell;
	public CellStyle style;
	String path;

	public ExcelUtilities(String path) {
		this.path=path;
	}

	public int getRowCount(String sheetName) throws Exception{
		fi=new FileInputStream(path);
		workbook=new XSSFWorkbook(fi);
		sheet=workbook.getSheet(sheetName);
		int rowCount=sheet.getLastRowNum();
		workbook.close();
		fi.close();
		return rowCount;
	}

	public int getCellCount(String sheetName,int rowCount) throws Exception{
		fi=new FileInputStream(path);
		workbook=new XSSFWorkbook(fi);
		sheet=workbook.getSheet(sheetName);
		row=sheet.getRow(rowCount);
		int cellCount=row.getLastCellNum();
		workbook.close();
		fi.close();
		return cellCount;
	}

	public String getCellData(String sheetName, int rowCount, int cellCount) throws Exception{
		fi=new FileInputStream(path);
		workbook=new XSSFWorkbook(fi);
		sheet=workbook.getSheet(sheetName);
		row=sheet.getRow(rowCount);
		cell=row.getCell(cellCount);

		DataFormatter formatter=new DataFormatter();
		String data;
		try {
			data=formatter.formatCellValue(cell);
		}
		catch(Exception e) {
			data="";
		}
		workbook.close();
		fi.close();
		return data;
	}

	public void setCellData(String sheetName, int rowCount, int cellCount, String data) throws Exception{
		File xlfile=new File(path);
		if(!xlfile.exists()) {
			workbook=new XSSFWorkbook(fi);
			fo=new FileOutputStream(path);
			workbook.write(fo);
		}

		fi=new FileInputStream(path);
		workbook=new XSSFWorkbook(fi);

		if(workbook.getSheetIndex(sheetName)==-1)
			workbook.createSheet(sheetName);
		sheet=workbook.getSheet(sheetName);

		if(sheet.getRow(rowCount)==null)
			sheet.createRow(rowCount);
		row=sheet.getRow(rowCount);

		cell=row.createCell(cellCount);
		cell.setCellValue(cellCount);
		fo=new FileOutputStream(path);
		workbook=new XSSFWorkbook();
		workbook.write(fo);
		workbook.close();
		fi.close();
		fo.close();
	}

	public void fillGreenColour(String sheetName, int rowCount,int cellCount) throws Exception{
		fi=new FileInputStream(path);
		workbook=new XSSFWorkbook(fi);
		sheet=workbook.getSheet(sheetName);
		row=sheet.getRow(rowCount);
		cell=row.getCell(cellCount);

		style=workbook.createCellStyle();

		style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		cell.setCellStyle(style);
		workbook.write(fo);
		workbook.close();
		fo.close();
		fi.close();
	}

	public void fillRedColour(String sheetName,int rowCount, int cellCount) throws Exception{
		fi=new FileInputStream(path);
		workbook=new XSSFWorkbook(fi);
		sheet=workbook.getSheet(sheetName);
		row=sheet.getRow(rowCount);
		cell=row.getCell(cellCount);

		style.setFillForegroundColor(IndexedColors.RED.index);
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		cell.setCellStyle(style);
		workbook.write(fo);
		workbook.close();
		fi.close();
		fo.close();
	}
}	
