package api.utilities;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class XLUtility {

    public FileInputStream fi;
    public FileOutputStream fo;
    public XSSFWorkbook wb;
    public XSSFSheet sheet;
    public XSSFRow row;
    public XSSFCell cell;
    public CellStyle style;
    String path;

    public XLUtility(String path)
    {
        this.path=path;
    }

    public int getRowCount(String sheetName) throws IOException
    {
        fi = new FileInputStream(path);
        wb = new XSSFWorkbook(fi);
        sheet = wb.getSheet(sheetName);
        int rowCount=sheet.getLastRowNum();
        wb.close();
        fi.close();
        return rowCount;
    }

    public int getCellCount(String sheetName, int rowNum) throws IOException
    {
        fi = new FileInputStream(path);
        wb = new XSSFWorkbook(fi);
        sheet = wb.getSheet(sheetName);
        row = sheet.getRow(rowNum);
        int cellCount = row.getLastCellNum();
        wb.close();
        fi.close();
        return cellCount;
    }

    public String getCellData(String sheetName, int rowNum, int colNum) throws IOException
    {
        fi = new FileInputStream(path);
        wb = new XSSFWorkbook(fi);
        sheet = wb.getSheet(sheetName);
        row = sheet.getRow(rowNum);
        cell = row.getCell(colNum);

        DataFormatter formatter = new DataFormatter();
        String data;
        try {
            data = formatter.formatCellValue(cell);
        } catch (Exception e) {
            data = "";
        }
        wb.close();
        fi.close();

        return data;
    }

    public void setCellData(String sheetName, int rowNum, int colNum, String data) throws IOException
    {
        File xlfile = new File(path);
        if (!xlfile.exists())
        {
            wb = new XSSFWorkbook();
            fo = new FileOutputStream(path);
            wb.write(fo);
        }

        fi = new FileInputStream(path);
        wb = new XSSFWorkbook(fi);

        if (wb.getSheetIndex(sheetName)==-1)
            wb.createSheet(sheetName);
        sheet = wb.getSheet(sheetName);

        if (sheet.getRow(rowNum)==null)
            sheet.createRow(rowNum);
        row = sheet.getRow(rowNum);
    }
}