package Report;

import java.util.ArrayList;
import java.util.List;

import com.sun.rowset.internal.Row;

import Model.Employee;
import Model.Task;

public class Report5 {

	private List<String> columnNames = new ArrayList<String>();
	private List<List<String>> rows = new ArrayList<List<String>>();

	public void createReport(List<Employee> employees) {
		columnNames.add("Imiê i nazwisko");
		columnNames.add("Projekt");
		columnNames.add("Iloœæ godzin");

		for (Employee employee : employees) {
			for (Task task : employee.getTaskList()) {
				Integer indexOfRowToChange = null;
				for (List<String> row : rows) {
					if (row.get(0).equals(employee.getNameAndSurname()) && row.get(1).equals(task.getProjectName())) {
						indexOfRowToChange = rows.indexOf(row);
					}
				}

				if (indexOfRowToChange != null) {
					List<String> rowToChange = rows.get(indexOfRowToChange);
					Double newHours = Double.valueOf(rowToChange.get(2)) + task.getHours();
					rowToChange.set(2, newHours.toString());
				} else {
					List<String> newRow = new ArrayList<String>();
					newRow.add(employee.getNameAndSurname());
					newRow.add(task.getProjectName());
					newRow.add(task.getHours().toString());
					rows.add(newRow);
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
