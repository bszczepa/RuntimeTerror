package Report;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class ReportXlsExporter { 
	
	public void exportToXls(Report report) throws IOException {

		List<String> columnNames = report.getColumnNames();
		List<List<String>> rows = report.getRows();
		String title = report.getTitle();
		int lineLength = columnNames.size() * 32;
		
		
		Workbook wb = new HSSFWorkbook();

		Sheet sheet1 = wb.createSheet("Raport");
		
		int titleRow = 3;
		Row row = sheet1.createRow(3);
		Cell titleCell = row.createCell(2);
		titleCell.setCellValue(title);

		int columnNamesRow = 5;
		row = sheet1.createRow(columnNamesRow);
		int cellsCounter = 0;
		for (String columnName : columnNames) {
			Cell cell = row.createCell(cellsCounter);
			cell.setCellValue(columnName);
			cellsCounter++;
		}
		cellsCounter = 0;

		int numberOfStartingRow = 6;
		int rowsCounter = numberOfStartingRow;
		for (List<String> reportRow : rows) {
			row = sheet1.createRow(rowsCounter);
			rowsCounter++;
			for (String entry : reportRow) {
				Cell cell = row.createCell(cellsCounter);
				cell.setCellValue(entry);
				cellsCounter++;
			}
			cellsCounter = 0;
		}
		Date date = new Date();
		String reportName = "report4-" + String.valueOf(date.getTime());
	
		

		for ( int i = 0; i<sheet1.getRow(numberOfStartingRow).getLastCellNum(); i++) {
			sheet1.autoSizeColumn(i);
		}
	
		try (OutputStream fileOut = new FileOutputStream("generated-reports/" + reportName + ".xls")) {
			wb.write(fileOut);
			System.out.println("Poprawnie zapisano plik: " + reportName);
		}

	}
}