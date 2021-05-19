
package com.virventure.qa.util;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.virventure.qa.base.TestBase;

public class TestUtil extends TestBase{

	public static long 	PAGE_LOAD_TIMEOUT=60;
	public static long 	IMPLICT_WAIT_TIMEOUT=60;
	
	static String EXCEL_DATA_PATH="C:\\Users\\MY-PC.DESKTOP-8EQSD1V\\git\\VirVentureLiveEnvironment_AutomatioFramework\\VirVentureLiveEnvironment_AutomatioFramework\\src\\main\\java\\com\\virventure\\qa\\testdata\\VVData.xlsx";
	static Workbook book;
	static Sheet sheet;
	
	public static Object[][] getTestData(String sheetName) {
		FileInputStream file=null;
		try {
			file= new FileInputStream(EXCEL_DATA_PATH);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			book= WorkbookFactory.create(file);
		} catch (Exception e) {
			// TODO: handle exception
		}
		sheet=book.getSheet(sheetName);
		
		Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		for(int i=0; i<sheet.getLastRowNum(); i++) {
			for(int k=0; k<sheet.getRow(0).getLastCellNum(); k++) {
				data[i][k]=sheet.getRow(i+1).getCell(k).toString();
			}
		}
		return data;
		
		
	}
	
	
}
