package Report;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.CellStyle;
import org.junit.Test;
import org.mockito.Mockito;

import Model.Employee;
import Model.Task;

public class ReportXlsExporterTest {

	@Test
	public void test() throws IOException {
		List<Employee> employees = new ArrayList<Employee>();

		Employee employee1 = new Employee("Jan", "Nowak");

		Date date = new Date();
		Task task = new Task(date, "jakisProjekt", "jakies zadanie", 3);
		employee1.addTask(task);
		Employee employee2 = new Employee("Paweł", "Kwiatkowski");
		Task task2 = new Task(date, "jakisProjekt", "jakies zadanie2", 7);
		Task task3 = new Task(date, "jakisProjekt3", "jakies zadanie2", 7);
		employee2.addTask(task2);
		employee2.addTask(task3);

		employees.add(employee1);
		employees.add(employee2);
		
		Report report = Mockito.mock(Report.class);
		
		String reportTitle = "Tytuł raportu";
		List<String> columnNames = new ArrayList<String>();
		columnNames.add("Kolumna1");
		columnNames.add("Kolumna2");
		columnNames.add("Kolumna3");
		List<List<String>> rows = new ArrayList<List<String>>();
		List<String> row1 = new ArrayList<String>();
		row1.add("1");
		row1.add("2");
		row1.add("3");

		
		List<String> row2 = new ArrayList<String>();
		row2.add("4");
		row2.add("5");
		row2.add("6");
		
		List<String> row3 = new ArrayList<String>();
		row3.add("7");
		row3.add("8");
		row3.add("9");
		
		rows.add(row1);
		rows.add(row2);
		rows.add(row3);
		
		Mockito.when(report.getColumnNames()).thenReturn(columnNames);
		Mockito.when(report.getTitle()).thenReturn(reportTitle);
		Mockito.when(report.getRows()).thenReturn(rows);
		
		ReportXlsExporter exp = new ReportXlsExporter();
		System.out.println(exp.exportToXls(report));
		
	}

}
