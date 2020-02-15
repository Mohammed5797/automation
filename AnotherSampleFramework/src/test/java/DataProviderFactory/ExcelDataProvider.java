package DataProviderFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;

public class ExcelDataProvider {
	
	HSSFWorkbook wb;
	
	public ExcelDataProvider ()
	{// just in case if the file is not read try catch
		try 
		{ wb=new HSSFWorkbook(new FileInputStream(new File(System.getProperty("user.dir")+"/TestData/TestData.xls")));}
			
		catch(IOException e)
		{ System.out.println("unable to read Excel Data"+e.getMessage());}
		
}// this method was made to read the info from the xcel file
	// the method lets me read my sheet name and the column
	public String getCellData(String sheetName, int row, int col)
	{
		HSSFCell cell=wb.getSheet(sheetName).getRow(row).getCell(col);
		// if one of the cells is empty then it ignores the cell
		String data=null;
		// the first condition is goin to get the cell type if its a string
		if(cell.getCellTypeEnum()==CellType.STRING)
		{
			data=cell.getStringCellValue();
		}// if its a numeric get the int value
		else if(cell.getCellTypeEnum()==CellType.NUMERIC)
		{
			data=String.valueOf((int)cell.getNumericCellValue());
		}// if it is blank then leave it blank
		else if (cell.getCellTypeEnum()==CellType.BLANK)
		{
			data="";
		}
		return data;
	}// returns the actual number of rows that is within the excel file
		public int getRows(String sheetName)
		{
			return wb.getSheet(sheetName).getPhysicalNumberOfRows();
		}// returns the actual columns within the excel file 
		public int getColumns(String sheetName)
		{
			return wb.getSheet(sheetName).getRow(0).getPhysicalNumberOfCells();
		}
		
			}














