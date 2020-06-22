package Report;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import Model.Employee;
import Model.Model;
import Model.Task;

public class Report4 extends Report {

	public Report4(Model model, int year) {

		this.title = "Procentowy udział danego pracownika w projekt za dany rok";
		columnNames.add("L.p");
		columnNames.add("Imię i nazwisko");

		List<Employee> employees = model.getEmployeeList();

		for (Employee employee : employees) {

			List<Task> filteredTasks = employee.getTaskList().stream().filter(tsk -> {
				Date date = tsk.getTaskDate();
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(date);
				if (calendar.get(Calendar.YEAR) == year)
					return true;
				return false;
			}).collect(Collectors.toList());

			employee.setTaskList(filteredTasks);
		}

		List<Employee> filteredEmployees = employees.stream().filter(emp -> emp.getTaskList().size() > 0)
				.collect(Collectors.toList());

		employees = filteredEmployees;
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
		String reportName = "report4-" + String.valueOf(date.getTime());
	
		

		for ( int i = 0; i<sheet1.getRow(numberOfStartingRow).getLastCellNum(); i++) {
			sheet1.autoSizeColumn(i);
		}
	
		try (OutputStream fileOut = new FileOutputStream("generated-reports/" + reportName + ".xls")) {
			wb.write(fileOut);
		}

	}

	public List<String> getColumnNames() {
		return columnNames;
	}

	public List<List<String>> getRows() {
		return rows;
	}
	
	

}
