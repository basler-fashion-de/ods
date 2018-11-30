package com.blauband.ods.parse;

import com.blauband.ods.CatalogueDto;
import com.blauband.ods.OdsTest;
import com.blauband.ods.Settings;
import com.blauband.ods.downloader.ImageDownloder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author sergeizenevich
 */
public class XlsxParser {

    public static void main(final String[] args) throws IOException, Exception {
        final File file = new File("source.xlsx");
        final CatalogueDto catalogueDto = new XlsxParser(file).parse();

        new ImageDownloder(Settings.FILES_FOLDER).download(catalogueDto);

        new OdsTest().createUserInvoicePDF(catalogueDto);
    }

    private final File source;

    public XlsxParser(final String source) {
        this(new File(source));
    }

    public XlsxParser(final File source) {
        this.source = source;
    }

    public CatalogueDto parse() throws FileNotFoundException, IOException {
        final FileInputStream fis = new FileInputStream(source); // Finds the workbook instance for XLSX file 
        final XSSFWorkbook myWorkBook = new XSSFWorkbook(fis);
        final XSSFSheet mySheet = myWorkBook.getSheetAt(0);
        final List<String> names = new ArrayList<>();
        final List<List<Object>> rows = new ArrayList<>();
        for (Row each : mySheet) {
            if (names.isEmpty()) { // header
                for (Cell eachCell : each) {
                    names.add(eachCell.getStringCellValue().toLowerCase());
                }
            } else {
                final List<Object> values = new ArrayList<>();
                for (int i = 0; i < each.getLastCellNum(); i++) {
                    final Cell eachCell = each.getCell(i);
                    if (eachCell == null) {
                        values.add(null);
                        continue;
                    }

                    switch (eachCell.getCellType()) {
                        case Cell.CELL_TYPE_BLANK:
                            values.add(null);
                            break;
                        case Cell.CELL_TYPE_BOOLEAN:
                            values.add(eachCell.getBooleanCellValue());
                            break;
                        case Cell.CELL_TYPE_ERROR:
                            values.add("error: " + eachCell.getErrorCellValue());
                            break;
                        case Cell.CELL_TYPE_FORMULA:
                            values.add(eachCell.getCellFormula());
                            break;
                        case Cell.CELL_TYPE_NUMERIC:
                            values.add(eachCell.getNumericCellValue());
                            break;
                        case Cell.CELL_TYPE_STRING:
                            values.add(eachCell.getStringCellValue());
                            break;
                        default:
                            throw new RuntimeException("Unexpected type: " + eachCell.getCellType());
                    }
                }
                rows.add(values);
            }
        }

        return new CatalogueDtoBuilder(names).build(rows);
    }
}
