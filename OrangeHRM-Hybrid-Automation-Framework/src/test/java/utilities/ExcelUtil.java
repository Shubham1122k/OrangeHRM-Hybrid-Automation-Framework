package utilities;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {

	public static Object[][] getTestData(String fileName, String sheetName) throws IOException {

	    String path = ExcelUtil.class.getClassLoader()
	            .getResource(fileName)
	            .getFile();

	    try (FileInputStream fis = new FileInputStream(path);
	         Workbook workbook = new XSSFWorkbook(fis)) {

	        Sheet sheet = workbook.getSheet(sheetName);
	        DataFormatter formatter = new DataFormatter();

	        int rowCount = sheet.getLastRowNum();
	        int colCount = sheet.getRow(0).getLastCellNum();

	        Object[][] data = new Object[rowCount][colCount];

	        for (int i = 1; i <= rowCount; i++) {
	            for (int j = 0; j < colCount; j++) {
	                data[i - 1][j] = formatter.formatCellValue(sheet.getRow(i).getCell(j));
	            }
	        }

	        return data;
	    }
	}
}
