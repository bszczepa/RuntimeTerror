package Report;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import Model.Employee;
import Model.Model;
import Model.Task;

public class Report4Builder implements ReportBuilder {

	private int year;

	public Report4Builder(int year) {
		super();
		this.year = year;
	}

	@Override
	public Report buildReport(Model model){
		
		Report report = new Report();
		
		report.setTitle("Procentowy udział danego pracownika w projekt za dany rok");
		List<String> columnNames = new ArrayList<String>();
		List<List<String>> rows = new ArrayList<List<String>>();
		Integer rowsCounter = 1;
		
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
					rowsCounter++;
					rowToAdd.set(1, employee.getNameAndSurname());
				}
				Integer indexOfProject = columnNames.indexOf(project);

				Double projectHours = employee.getProjectHours(project);
				Double percentHours = (projectHours * 100) / totalHours;
			
				percentHours = percentHours*100;
				percentHours = (double) Math.round(percentHours);
				percentHours = percentHours/100;

				rowToAdd.set(indexOfProject, percentHours.toString() + "%");
				if (!rowExists) {
					rows.add(rowToAdd);
				}
			}
		}
		report.setColumnNames(columnNames);
		report.setRows(rows);
		
		return report;
	}
}
