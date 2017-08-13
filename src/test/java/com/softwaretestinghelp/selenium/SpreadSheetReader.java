package com.softwaretestinghelp.selenium;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SpreadSheetReader {

	private Workbook workbook;

	public SpreadSheetReader(String fileName) {
		try {
			FileInputStream excelFile = new FileInputStream(new File(fileName));
			workbook = new XSSFWorkbook(excelFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int getLastRowNum(String sheetName) {
		Sheet datatypeSheet = workbook.getSheet(sheetName);

		int noRows = datatypeSheet.getLastRowNum();
		return noRows;
	}

	public List<String> readRow(int rowNo, String sheetName) {
		List<String> row = new ArrayList<String>();
		Sheet datatypeSheet = workbook.getSheet(sheetName);

		Row currentRow = datatypeSheet.getRow(rowNo);
		for (Cell currentCell : currentRow) {
			switch (currentCell.getCellTypeEnum()) {
			case STRING:
				row.add(currentCell.getStringCellValue());
				break;
			case NUMERIC:
				row.add(String.valueOf(currentCell.getNumericCellValue()));
				break;
			case BOOLEAN:
				row.add(String.valueOf(currentCell.getBooleanCellValue()));
				break;
			case BLANK:
				row.add(currentCell.getStringCellValue());
				break;
			case _NONE:
				System.out.println("No Value in cell");
				break;
			case ERROR:
				System.out.println("Error in cell");
				break;
			case FORMULA:
				row.add(currentCell.getStringCellValue());
				break;
			}
		}
		return row;
	}

}