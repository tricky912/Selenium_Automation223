package utilities;
import java.io.IOException;

import org.testng.annotations.DataProvider;

public class Dataprovider {
	
	@DataProvider(name="Logindata")
	public String[][] getDatafromExcel() throws IOException
	{
		
		String path=".//Testdata//data.xlsx";
	Excelutility xlutil=new Excelutility(path);
		int rowCount=xlutil.getRowCount("Sheet1");
		int colCount=xlutil.getCellCount("Sheet1", 1);
		
		String LoginData[][]=new String[rowCount][colCount];
		
			for(int i=1;i<=rowCount;i++) {
				for(int j=0;j<colCount;j++) {
					LoginData[i-1][j]=xlutil.getCellData("Sheet1", i, j);
				}
			}
			return LoginData;
	}
	
	

}
