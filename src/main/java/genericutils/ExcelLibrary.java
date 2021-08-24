package genericutils;

import org.apache.poi.ss.usermodel.*;

import java.io.*;

public class ExcelLibrary {

    private final String xlPath;
    private Workbook workbook;
    private Sheet sheet;

    public ExcelLibrary(String xlPath, String sheetName) {
        this.xlPath = xlPath;
        try {
            FileInputStream fis = new FileInputStream(xlPath);
            workbook = WorkbookFactory.create(fis);
            sheet = workbook.getSheet(sheetName);
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getXlData(int rowNo, int cellNo) {
        return sheet.getRow(rowNo).getCell(cellNo).getStringCellValue();
    }

    public int getTotalRowCount() {
        return sheet.getLastRowNum();
    }

    public int getTotalCellCount() {
        return sheet.getRow(0).getLastCellNum();
    }

    public boolean setXlData(int rowNo, int cellNo, String data) {
        Row row = sheet.createRow(rowNo);
        Cell cell = row.getCell(cellNo, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
        cell.setCellValue(data);
        try {
            FileOutputStream fos = new FileOutputStream(xlPath);
            workbook.write(fos);
            fos.close();
        }  catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean isCellEmpty(int rowNo, int cellNo) {
        String cellValue = sheet.getRow(rowNo).getCell(cellNo).getStringCellValue();
        return cellValue.trim().isEmpty();
    }

    public void closeWorkBook() {
        try {
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
