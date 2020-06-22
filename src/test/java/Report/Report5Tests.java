package Report;

import java.util.ArrayList;
import java.util.Date;
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
		
		ReportBuilder rBuilder = new Report5Builder();
		rBuilder.setParam("jakisProjekt");
		
		
		Report report = rBuilder.buildReport(model);
	
		Assert.assertEquals(4, report.getColumnNames().size());
		
		Assert.assertEquals(2, report.getRows().size());
		
		Assert.assertEquals("3.0", report.getRows().get(0).get(3));
		Assert.assertEquals("7.0", report.getRows().get(1).get(3));
		
		Assert.assertEquals("1", report.getRows().get(0).get(0));
		Assert.assertEquals("2", report.getRows().get(1).get(0));
		
		Assert.assertEquals("Jan Nowak", report.getRows().get(0).get(1));
		Assert.assertEquals("Paweł Kwiatkowski", report.getRows().get(1).get(1));
		
		Assert.assertEquals("jakisProjekt", report.getRows().get(0).get(2));
		Assert.assertEquals("jakisProjekt", report.getRows().get(1).get(2));

		ReportPrinter.printReport(report);
		
		
	}

}
