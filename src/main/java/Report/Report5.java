package Report;

import java.util.ArrayList;
import java.util.List;

import com.sun.rowset.internal.Row;

import Model.Employee;
import Model.Model;
import Model.Task;

public class Report5 {

	private Integer rowsCounter = 1;
	private List<String> columnNames = new ArrayList<String>();
	private List<List<String>> rows = new ArrayList<List<String>>();

	
	
	public Report5(Model model) {

		columnNames.add("L.p");
		columnNames.add("Imię i nazwisko");
		columnNames.add("Projekt");
		columnNames.add("Ilość godzin");

		List<Employee> employees = model.getEmployeeList();

		for (Employee employee : employees) {
			for (Task task : employee.getTaskList()) {
				Integer indexOfRowToChange = null;
				for (List<String> row : rows) {
					String employeeInRow = row.get(1);
					String projectInRow = row.get(2);
					
					if (employeeInRow.equals(employee.getNameAndSurname()) && projectInRow.equals(task.getProjectName())) {
						indexOfRowToChange = rows.indexOf(row);
					}
				}

				if (indexOfRowToChange != null) {
					List<String> rowToChange = rows.get(indexOfRowToChange);
					Double hoursToChange  = Double.valueOf(rowToChange.get(3));
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

	public void printReport() {

		for (String string : columnNames) {
			System.out.print(string + "  ");
		}

		System.out.println();

		for (List<String> row : rows) {
			for (String rowCell : row) {
				System.out.print(rowCell + "  ");
			}
			System.out.println();
		}
	}

}
