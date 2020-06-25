package Report;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFFont;

public class ReportXlsExporter {

	private String generatedReportsPath = "generated-reports";

	Workbook wb;

	Sheet sheet1;

	List<String> columnNames = new ArrayList<String>();
	List<List<String>> rows = new ArrayList<List<String>>();
	String title = "";

	public File exportToXls(Report report) throws IOException {
		wb = new HSSFWorkbook();
		sheet1 = wb.createSheet("Raport");

		String generatedReportsPath = "generated-reports";
		columnNames = report.getColumnNames();
		rows = report.getRows();
		title = report.getTitle();

		int reportWidth = 150 * 256;

		int titleRow = 3;
		createTittle(titleRow);
		int columnNamesRow = 5;
		createHeaders(5);
		createRows();

		for (int i = 0; i < columnNames.size(); i++) {
			sheet1.setColumnWidth(i, reportWidth / columnNames.size());
		}

		File file;
		String filePath;
		int filesOfSameNameCounter = 0;
		do {
			filesOfSameNameCounter++;
			String reportName = "report" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "("
					+ filesOfSameNameCounter + ")";
			filePath = generatedReportsPath + "/" + reportName + ".xls";
			file = new File(filePath);
		} while (file.exists());

		try (OutputStream fileOut = new FileOutputStream(filePath)) {
			wb.write(fileOut);
		}
		return file;
	}

	private void createRows() {
		Row row;
		int numberOfStartingRow = 6;
		int rowsCounter = numberOfStartingRow;
		int cellsCounter = 0;
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
	}

	private void createHeaders(int columnNamesRow) {
		Row row = sheet1.createRow(columnNamesRow);
		int cellsCounter = 0;
		
		Font font = wb.createFont();
		font.setFontName("Arial");
		font.setBold(true);
		font.setItalic(false);

		CellStyle headersCellStyle = wb.createCellStyle();
		headersCellStyle.setFont(font);
		headersCellStyle.setAlignment(HorizontalAlignment.CENTER);
		for (String columnName : columnNames) {
			Cell cell = row.createCell(cellsCounter);
			cell.setCellStyle(headersCellStyle);
			cell.setCellValue(columnName);
			cellsCounter++;
		}
		cellsCounter = 0;
	}

	private void createTittle(int titleRow) {
		Row row = sheet1.createRow(titleRow);
		Cell titleCell = row.createCell(0);

		sheet1.addMergedRegion(new CellRangeAddress(titleRow, titleRow, 0, columnNames.size() - 1));
		titleCell.setCellValue(title);
		CellStyle titleCellStyle = wb.createCellStyle();
		titleCellStyle.setAlignment(HorizontalAlignment.CENTER);

		Font font = wb.createFont();
		font.setFontHeightInPoints((short) 25);
		font.setBold(true);
		titleCellStyle.setFont(font);
		titleCell.setCellStyle(titleCellStyle);
	}
}