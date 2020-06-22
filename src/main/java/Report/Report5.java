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


}
