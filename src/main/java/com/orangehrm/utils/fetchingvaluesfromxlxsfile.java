package com.orangehrm.utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.orange.base.TestBase;

public class fetchingvaluesfromxlxsfile extends TestBase 
{
	public PropertiesFileDataHandeling prop;
	public Object value;
	public Object actual_header ;
	public String dprovider;
	
	
	public Object getValue_by_header(String SheetName , String HeaderName) throws Throwable
	{
		prop = new PropertiesFileDataHandeling();
		String excel_path = prop.GetPropertyValue("excel_path");
        File fi = new File(excel_path);
	    FileInputStream fis = new FileInputStream(fi);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet(SheetName);
		int total_column = sheet.getRow(0).getLastCellNum();
		System.out.println("Total number of column "+total_column);
		System.out.println("Header to be match "+HeaderName);
		for (i = 0; i<total_column; i++)			
		{ 
			 System.out.println(i);
			 actual_header = sheet.getRow(0).getCell(i).getStringCellValue();
			 System.out.println("Actual header " +actual_header);
			 if(actual_header.toString().equals(HeaderName))
			 {
				 Cell cell = sheet.getRow(1).getCell(i);
				 switch (cell.getCellType())
					{
					  case Cell.CELL_TYPE_STRING: 
					   value = sheet.getRow(1).getCell(i).getStringCellValue();	
					   System.out.println("Cell value "+value);
					  break;
					  case Cell.CELL_TYPE_NUMERIC:
					   value = (int) sheet.getRow(1).getCell(i).getNumericCellValue();
					   break;
					}
				 break;
			 } 
			
		} 
		return value;
	}
	
	public Object[][] testsheetdatatoexcel(String Sheetname) throws Throwable
	{
		String[][] tabArray = null;
		prop = new PropertiesFileDataHandeling();
		String excel_path = prop.GetPropertyValue("excel_path");
        File fi = new File(excel_path);
	    FileInputStream fis = new FileInputStream(fi);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet(Sheetname);

	    int startRow = 1;
	 
	    int startCol = 0;
	 
	    int ci,cj;
	    
	    int totalRows = sheet.getLastRowNum();
	    
	    tabArray = new String[totalRows][3];
	    
	    ci=0;
	    
	    for ( int k=startRow;k<=totalRows;k++, ci++) 
	    {              
	    	System.out.println("value of k "+k); 
	    	   cj=0;
	    	 
	    	    for ( int p=startCol;p<3;p++, cj++)
	    	    
	    	    {
	    	    	System.out.println("value of p "+p); 
	    	 
	    	    tabArray[ci][cj]= sheet.getRow(k).getCell(p).getStringCellValue();
	    	 
	    	    System.out.println(tabArray[ci][cj]); 
	    	    
	    	    }
	    }	    
	    	    
	    
		return tabArray;
		
		
	}
}
