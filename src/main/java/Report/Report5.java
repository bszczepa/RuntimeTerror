package Report;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import Model.Employee;
import Model.Model;
import Model.Task;

public class Report5 extends Report  {

	public Report5(Model model, String projectName) {

		this.title = "Szczegółowy wykaz pracy pracowników w danym projekcie";
		columnNames.add("L.p");
		columnNames.add("Imię i nazwisko");
		columnNames.add("Projekt");
		columnNames.add("Ilość godzin");

		List<Employee> employees = model.getEmployeeList();

		for (Employee employee : employees) {
			for (Task task : employee.getTaskList()) {

				if (task.getProjectName().equals(projectName)) {

					Integer indexOfRowToChange = null;
					for (List<String> row : rows) {
						String employeeInRow = row.get(1);
						String projectInRow = row.get(2);

						if (employeeInRow.equals(employee.getNameAndSurname())
								&& projectInRow.equals(task.getProjectName())) {
							indexOfRowToChange = rows.indexOf(row);
						}
					}

					if (indexOfRowToChange != null) {
						List<String> rowToChange = rows.get(indexOfRowToChange);
						Double hoursToChange = Double.valueOf(rowToChange.get(3));
						Double newHours = hoursToChange + task.getHours();
						rowToChange.set(3, newHours.toString());
					} else {
						List<String> newRow = new ArrayList<String>();
						newRow.add(rowsCounter.toString());
						newRow.add(employee.getNameAndSurname());
						newRow.add(task.getProjectName());
						newRow.add(task.getHours().toString());
						rows.add(newRow);
						rowsCounter++;
					}

				}

			}
		}

	}

	public void printReport() {
		int lineLength = columnNames.size() * 32;

	
	
		System.out.println(String.join("", Collections.nCopies(lineLength, "-")));
		System.out.format("%-1s", "|");
		System.out.format("%-"+ columnNames.size()*30 +"s %-" + columnNames.size() +"s", this.title, " ");
		System.out.format("%2s", "|");
		System.out.println();
		System.out.println(String.join("", Collections.nCopies(lineLength, "-")));
		for (String columnName : columnNames) {
			System.out.format("%-1s %-30s", "|", columnName);
			
		}
		System.out.format("%-1s" , "|");
		System.out.println();
		System.out.println(String.join("", Collections.nCopies(lineLength, "-")));
		for (List<String> row : rows) {
			for (String cell : row) {
				System.out.format("%-1s %-30s", "|", cell);
				
			}
			System.out.format("%-1s" , "|");
			System.out.println();
		}
		System.out.println(String.join("", Collections.nCopies(lineLength, "-")));
	}

	public void exportToXls() throws IOException {

		Workbook wb = new HSSFWorkbook();

		Sheet sheet1 = wb.createSheet("Raport");

		int titleRow = 3;
		Row row = sheet1.createRow(3);
		Cell titleCell = row.createCell(2);
		titleCell.setCellValue(this.title);

		int columnNamesRow = 5;
		row = sheet1.createRow(columnNamesRow);
		int cellsCounter = 0;
		for (String columnName : this.columnNames) {
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
		String reportName = "report5-" + String.valueOf(date.getTime());

		for (int i = 0; i < sheet1.getRow(numberOfStartingRow).getLastCellNum(); i++) {
			sheet1.autoSizeColumn(i);
		}

		try (OutputStream fileOut = new FileOutputStream("generated-reports/" + reportName + ".xls")) {
			wb.write(fileOut);
		}

	}

	public List<List<String>> getRows() {
		return rows;
	}

	public List<String> getColumnNames() {
		return columnNames;
	}

}
