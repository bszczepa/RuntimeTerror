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

public class Report4Tests {

	@Test
	public void testReport4() {
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
		
		ReportBuilder rBuilder = new Report4Builder();
		rBuilder.setParam(2020);
		
		Report report = rBuilder.buildReport(model);
		Assert.assertEquals(4, report.getColumnNames().size());
		Assert.assertEquals(2, report.getRows().size());
		Assert.assertEquals("100.0%", report.getRows().get(0).get(2));
		Assert.assertEquals("", report.getRows().get(0).get(3));
		Assert.assertEquals("50.0%", report.getRows().get(1).get(2));
		Assert.assertEquals("50.0%", report.getRows().get(1).get(3));

	}

}
