package commonFunctionPackage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class utility_Common_Functions {
	
	public static void EvidenceFileCreator(String FileName, String requestBody, String responseBody) throws IOException {
		
		File newFile = new File ("D:\\MSSQUARE GLOBAL\\API\\Rest Assured\\" +FileName+ ".txt");
		System.out.println("a new text file created to record request and response of API :"+ newFile.getName());
		FileWriter dataWrite = new FileWriter(newFile);
		dataWrite.write("Request Body :"+requestBody+"\n\n");
		dataWrite.write("Response Body :"+responseBody);
		dataWrite.close();
		System.out.println("request body and response body are saved in :"+newFile.getName());
		
	}
	
	public static ArrayList<String> read_data_excel(String SheetName , String TestCaseName) throws IOException 
	{
		
		ArrayList<String> ArrayData = new ArrayList<String>();
		// Step 1 : Create the Object of file input stream
		FileInputStream fis = new FileInputStream("C:\\Users\\Admin\\Desktop\\API_Test_Data.xlsx");
		// Step 2 : Access the excel file
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		// Step 3 : Access the sheet name
		int countOfSheet = workbook.getNumberOfSheets();
		for(int i=0 ; i<countOfSheet ; i++) {
			String fileSheetName = workbook.getSheetName(i);
			if(fileSheetName.equalsIgnoreCase(SheetName))
			{
				// Step 4 : Access the row from where the data is suppose to fetch
				XSSFSheet sheet = workbook.getSheetAt(i);
				Iterator<Row> rows=sheet.iterator();
				Row r = rows.next();
				while(rows.hasNext())
				{
					Row r1 = rows.next();
					if(r1.getCell(0).getStringCellValue().equalsIgnoreCase(TestCaseName))
					{
						Iterator<Cell> cellValues = r1.cellIterator();
						while(cellValues.hasNext()) {
							String testData = cellValues.next().getStringCellValue();
							ArrayData.add(testData);
						}
					}
				}
				
				
			}
		}
		workbook.close();
		return ArrayData;
	} 

}
