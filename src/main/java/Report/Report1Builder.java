package Report;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import Model.Employee;
import Model.Model;

public class Report1Builder implements ReportBuilder {

	private int year;

	public Report1Builder(int year) {
		this.year = year;
	}

	@Override
	public Report buildReport(Model model) {

		Report report = new Report();

		report.setTitle("Sumaryczna liczba godzin za rok " + year);

		List<String> columnNames = new ArrayList<String>();
		List<List<String>> rows = new ArrayList<List<String>>();
		Integer rowsCounter = 1;

		columnNames.add("L.p");
		columnNames.add("Imię i nazwisko");
		columnNames.add("Liczba godzin");

		List<Employee> employeeList = model.getEmployeeList();
		employeeList.sort(Comparator.comparing(Employee::getSurname));

		for (Employee employee : employeeList) {
			if (employee.getTotalHours(year) != 0) {
				List<String> newRow = new ArrayList();
				newRow.add(rowsCounter.toString());
				newRow.add(employee.getNameAndSurname());
				newRow.add(String.valueOf(employee.getTotalHours(year)));
				rows.add(newRow);
				rowsCounter++;
			}
		}
		
		report.setColumnNames(columnNames);
		report.setRows(rows);
		
		return report;
	}

}
