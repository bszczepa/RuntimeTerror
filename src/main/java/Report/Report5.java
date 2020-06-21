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

public class Report5 {

	private Integer rowsCounter = 1;
	private List<String> columnNames = new ArrayList<String>();
	private List<List<String>> rows = new ArrayList<List<String>>();

	public Report5(Model model, String projectName) {

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
		int cellsCounter=0;
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
		String reportName = "report5-"+String.valueOf(date.getTime());
		try  (OutputStream fileOut = new FileOutputStream("generated-reports/" + reportName + ".xls")) {
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
