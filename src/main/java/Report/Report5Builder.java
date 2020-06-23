package Report;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import Model.Employee;
import Model.Model;
import Model.Task;

public class Report5Builder extends ReportBuilder {

	@Override
	void filterEmployees(Model model) {
		List<Employee> modelEmployees = model.getEmployeeList();

		List<Employee> filteredEmployees = new ArrayList<Employee>();

		for (Employee employee : modelEmployees) {
			List<Task> filteredTasks = new ArrayList<Task>();
			for (Task task : employee.getTaskList()) {
				if (task.getProjectName().equalsIgnoreCase((String) params.get(0))) {
					filteredTasks.add(task);
				}
			}
			if (filteredTasks.size() > 0) {
				Employee employeeCopy = (Employee) employee.clone();
				employeeCopy.setTaskList(filteredTasks);
				filteredEmployees.add(employeeCopy);
			}
		}

		employees = filteredEmployees;
	}

	@Override
	void setReportTitle() {
		report.setTitle("Szczegółowy wykaz pracy pracowników w danym projekcie");
	}

	@Override
	void setReportCollumnNames() {
		List<String> columnNames = new ArrayList<String>();

		columnNames.add("L.p");
		columnNames.add("Imię i nazwisko");
		columnNames.add("Projekt");
		columnNames.add("Ilość godzin");

		report.setColumnNames(columnNames);
	}

	@Override
	void setReportRows() {
		List<List<String>> rows = new ArrayList<List<String>>();
		Integer rowsCounter = 1;

		for (Employee employee : employees) {
			for (Task task : employee.getTaskList()) {

				Integer indexOfRowToChange = null;
				for (List<String> row : rows) {
					String employeeInRow = row.get(1);
					String projectInRow = row.get(2);

					if (employeeInRow.equals(employee.getNameAndSurname())) {
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
		report.setRows(rows);
	}
}
