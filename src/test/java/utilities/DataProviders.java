package utilities;

import org.testng.annotations.*;

public class DataProviders {
	
	//DataProvider1
	
	@DataProvider(name="LoginData")
	
	public String [][] getData() throws Exception{
		String path=".\\testData\\ExcelData.xlsx"; //taking excel file from test data
		
		ExcelUtilities excel=new ExcelUtilities(path);//Create an object for ExcelUtilities
		
		int totalrows=excel.getRowCount("Sheet1");
		int totalcell=excel.getCellCount("Sheet1",1);
		
		String loginData[][]=new String[totalrows][totalcell];//Create two dimensional array which can store String]
		
		for(int i=1;i<=totalrows;i++) {
			for(int j=0;j<totalcell;j++) {
				loginData[i-1][j]=excel.getCellData("Sheet1", i, j);			
			}
		}
		return loginData;//returning two dimension
	}
}
