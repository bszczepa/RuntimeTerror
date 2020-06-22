package Report;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import Model.Employee;
import Model.Model;
import Model.Task;

public class Report2Builder implements ReportBuilder {

	private int year;

	public Report2Builder(int year) {
		this.year = year;
	}

	@Override
	public Report buildReport(Model model) {

		Report report = new Report();

		report.setTitle("Raport listy projektów za podany rok " + year);

		List<String> columnNames = new ArrayList<String>();
		List<List<String>> rows = new ArrayList<List<String>>();

		TreeMap<String, Double> projectsMap = new TreeMap<>();
		List<Employee> employees = model.getEmployeeList();

		for (Employee employee : employees) {
			for (Task task : employee.getTaskList()) {
				Date date = task.getTaskDate();
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(date);
				if (calendar.get(Calendar.YEAR) == year) {
					String projectName = task.getProjectName();
					if (projectsMap.containsKey(projectName)) {
						projectsMap.replace(projectName, projectsMap.get(projectName) + task.getHours());
					} else {
						projectsMap.put(projectName, task.getHours());
					}
				}
			}
		}

		for (Map.Entry project : projectsMap.entrySet()) {
			System.out.println(project.getKey() + "    " + project.getValue());
		}

		// TODO: Konwersja mapy na listę nazw kolumn i listę wierszy !

		report.setColumnNames(columnNames);
		report.setRows(rows);
		return report;
	}

}
