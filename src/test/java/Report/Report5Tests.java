package Report;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import Model.Employee;
import Model.Model;
import Model.Task;

public class Report5Tests {
	
	@Test
	public void testReport5() {
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
		Model model = Mockito.mock(Model.class);
		
		
		employees.add(employee1);
		employees.add(employee2);
		
		Mockito.when(model.getEmployeeList()).thenReturn(employees);
		
		ReportBuilder rBuilder = new Report5Builder("jakisProjekt");
		
		
		Report report = rBuilder.buildReport(model);
	
		Assert.assertEquals(4, report.getColumnNames().size());
		
		Assert.assertEquals(2, report.getRows().size());
		
		Assert.assertEquals("3.0", report.getRows().get(0).get(3));
		Assert.assertEquals("7.0", report.getRows().get(1).get(3));

		
		
	}
	
	@Test
	public void testNotFilteringMasterEmployeesData() throws IOException {

	
		
		
		List<Employee> employees = new ArrayList<Employee>();
		
		Model model = Mockito.mock(Model.class);
		Mockito.when(model.getEmployeeList()).thenReturn(employees);
		
		Employee employee1 = new Employee("Jan", "Nowak");
		Calendar myCalendar = new GregorianCalendar(2012, 2, 11);
		Date date = myCalendar.getTime();
		Task task = new Task(date, "jakisProjekt", "jakies zadanie", 3);
		employee1.addTask(task);
		employees.add(employee1);
		ReportBuilder rBuilder = new Report5Builder("Projekt2");
		rBuilder.buildReport(model);
		ReportBuilder rBuilder2 = new Report5Builder("jakisProjekt");
		Report report = rBuilder2.buildReport(model);
		ReportPrinter.printReport(report);
		
		
		Assert.assertTrue((report.getRows().size() == 1));
		
		ReportPrinter.printReport(report);

	}

}
