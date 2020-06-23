package services.reportServices;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import model.Employee;
import model.Task;

public class Report3Builder extends ReportBuilder {

	@Override
	void filterEmployees() {
		List<Employee> filteredEmployees = new ArrayList<Employee>();

		for (Employee employee : employees) {
			List<Task> filteredTasks = new ArrayList<Task>();
			for (Task task : employee.getTaskList()) {
				Date date = task.getTaskDate();
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(date);
				if (calendar.get(Calendar.YEAR) == (Integer) params.get(0)
						&& ((String) params.get(1)).toLowerCase().contains(employee.getName().toLowerCase())
						&& ((String) params.get(1)).toLowerCase().contains(employee.getSurname().toLowerCase())) {
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
		report.setTitle("Rok: " + params.get(0) + "; Imię i nazwisko: " + (String) params.get(1));
	}

	@Override
	void setReportCollumnNames() {
		List<String> columnNames = new ArrayList<String>();
		columnNames.add("L.p");
		columnNames.add("Miesiąc");
		columnNames.add("Projekt");
		columnNames.add("Liczba godzin");
		report.setColumnNames(columnNames);
	}

	@Override
	void setReportRows() {

		List<List<String>> rows = new ArrayList<List<String>>();
		Integer rowsCounter = 1;

		String[] polishMonths = { "Styczeń", "Luty", "Marzec", "Kwiecień", "Maj", "Czerwiec", "Lipiec", "Sierpień",
				"Wrzesień", "Pażdziernik", "Listopad", "Grudzień" };

		if (employees.size() > 0) {

			for (Employee foundEmployee : employees) {
				for (int i = 0; i < 12; i++) {
					HashMap<String, Double> hours = foundEmployee.getHoursByProject(i);
					for (String project : hours.keySet()) {
						List<String> newRow = new ArrayList();
						newRow.add(rowsCounter.toString());
						newRow.add(polishMonths[i]);
						newRow.add(project);
						newRow.add(String.valueOf(hours.get(project)));
						rows.add(newRow);
						rowsCounter++;
					}
				}
			}
		}
		report.setRows(rows);
	}
}
