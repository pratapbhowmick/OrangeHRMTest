package com.pratap.orangeHRM.utils;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

	public static List<Object[]> getTestData(String filePath, String sheetName) {
		List<Object[]> testData = new ArrayList<>();
		DataFormatter formatter = new DataFormatter();
		FileInputStream fis;
		try {
			fis = new FileInputStream(filePath);

			Workbook workbook = new XSSFWorkbook(fis);
			Sheet sheet = workbook.getSheet(sheetName);
			int rowCount=sheet.getLastRowNum();
			for (int rowNum=1;rowNum<=rowCount;rowNum++) {
				Row row=sheet.getRow(rowNum);
				int cellCount = row.getLastCellNum();
				Object[] object = new Object[cellCount];
				for (int cellNum = 0; cellNum < cellCount; cellNum++) {
					object[cellNum] = formatter.formatCellValue(row.getCell(cellNum));

				}
				testData.add(object);
			}
			workbook.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return testData;
	}
}
