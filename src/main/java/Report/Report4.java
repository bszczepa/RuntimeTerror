package Report;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
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

public class Report4 {

	private Integer rowsCounter = 1;
	private List<String> columnNames = new ArrayList<String>();
	private List<List<String>> rows = new ArrayList<List<String>>();

	public Report4(Model model) {

		columnNames.add("L.p");
		columnNames.add("Imię i nazwisko");

		List<Employee> employees = model.getEmployeeList();

		for (Employee employee : employees) {
			for (String project : employee.getProjects()) {
				if (!columnNames.contains(project)) {
					columnNames.add(project);
				}
			}
		}

		for (Employee employee : employees) {

			Double totalHours = employee.getTotalHours();
			for (String project : employee.getProjects()) {
				List<String> rowToAdd = new ArrayList<String>();
				for (int i = 0; i < columnNames.size(); i++) {
					rowToAdd.add("");
				}

				String employeeName = employee.getNameAndSurname();
				boolean rowExists = false;
				for (List<String> row : rows) {
					if (row.get(1).contains(employeeName)) {
						rowToAdd = row;
						rowExists = true;
					}
				}

				if (!rowExists) {
					rowToAdd.set(0, rowsCounter.toString());
					this.rowsCounter++;
					rowToAdd.set(1, employee.getNameAndSurname());
				}

				Integer indexOfProject = columnNames.indexOf(project);

				Double projectHours = employee.getProjectHours(project);

				Double percentHours = (projectHours * 100) / totalHours;
				rowToAdd.set(indexOfProject, percentHours.toString() + "%");

				if (!rowExists) {
					rows.add(rowToAdd);
				}

			}

		}

	}

	public void printReport() {

		for (String string : columnNames) {
			System.out.print(string + " \t\t ");
		}

		System.out.println();

		for (List<String> row : rows) {
			for (String rowCell : row) {
				System.out.print(rowCell + " \t\t ");
			}
			System.out.println();
		}
	}

	public void exportToXls() throws IOException {

		Workbook wb = new HSSFWorkbook();

		Sheet sheet1 = wb.createSheet("Raport");

		Row row = sheet1.createRow(3);
		int cellsCounter = 0;
		for (String columnName : this.columnNames) {
			Cell cell = row.createCell(cellsCounter);
			cell.setCellValue(columnName);
			cellsCounter++;
		}
		cellsCounter = 0;

		int rowsCounter = 4;
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
		try (OutputStream fileOut = new FileOutputStream("generated-reports/" + reportName + ".xls")) {
			wb.write(fileOut);
		}

	}

}
