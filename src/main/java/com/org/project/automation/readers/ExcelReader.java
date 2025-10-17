package com.org.project.automation.readers;

/**
 * @author Rajat P.
 */

import com.org.project.automation.webdriver.ResourceHelper;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

public class ExcelReader {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExcelReader.class);
    private String excelFilePath;
    private String sheetName;

    public ExcelReader() {
        excelFilePath = new ConfigFileReader().getDefaultExcelFilePath();
    }

    public ExcelReader(String excelFilePath) {
        this.excelFilePath = excelFilePath;
    }

    /**
     * Method to read excel data from given sheet
     *
     * @param sheetName
     * @return two dimensional array with sheet array
     */

    public String[][] getExcelData(String sheetName) {
        LOGGER.info("reading excel file {} with sheet {}", excelFilePath, sheetName);
        return readExcel(excelFilePath, sheetName);
    }

    /**
     * Method to read excel file with given sheet
     *
     * @param fileName
     * @param sheetName
     * @return two dimensional array with sheet content
     */

    private String[][] readExcel(String fileName, String sheetName) {

        String[][] excelArray = null;
        int rowIndex = 0;
        int columnIndex;
        File file = new File(ResourceHelper.getDefaultResourcesLocation() + excelFilePath);

//        File file = new File(getClass().getClassLoader().getResource(fileName).getFile());
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            Workbook workbook;
            if (fileName.endsWith("xls")) workbook = new HSSFWorkbook(fileInputStream);
            else workbook = new XSSFWorkbook(fileInputStream);

            Sheet sheet = workbook.getSheet(sheetName);
            Iterator<Row> rowIterator = sheet.rowIterator();
            int numberOfRows = sheet.getLastRowNum() + 1;
            int numberOfColumns = sheet.getRow(0).getLastCellNum();
            excelArray = new String[numberOfRows][numberOfColumns];

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();
                columnIndex = 0;
                while (cellIterator.hasNext()) {
                    String cellValue = null;
                    Cell cell = cellIterator.next();
                    switch (cell.getCellType()) {
                        case BOOLEAN:
                            cellValue = String.valueOf(cell.getBooleanCellValue());
                            break;
                        case NUMERIC:
                            cellValue = String.valueOf(cell.getNumericCellValue());
                            break;
                        case STRING:
                            cellValue = String.valueOf(cell.getStringCellValue());
                            break;
                        default:
                            LOGGER.error("can't read cell type");
                    }
                    excelArray[rowIndex][columnIndex++]=cellValue;
                }
                rowIndex++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return excelArray;
    }


}
