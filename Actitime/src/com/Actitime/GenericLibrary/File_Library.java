package com.Actitime.GenericLibrary;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
//it is generic class present in generic package
public class File_Library {
    String path="./TestData/commondata.property"; //. means current project / means open the file
    //generic methods
	public String reaDataFromPropertyFile(String key) throws IOException
	{
		FileInputStream fis=new FileInputStream(path);
		Properties p=new Properties();
		p.load(fis);
	    String value = p.getProperty(key);
		return value;
	}
	public String readDataFromExcelFile(String Sheetname,int row,int cell) throws EncryptedDocumentException, IOException
	{
		FileInputStream fis=new FileInputStream("./TestData/Testdata.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		String value = wb.getSheet(Sheetname).getRow(row).getCell(cell).getStringCellValue();
		return value;
	}
}
