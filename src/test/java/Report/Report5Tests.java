package Report;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;
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
		
		employee2.addTask(task2);
		
		Model model = Mockito.mock(Model.class);
		
		
		employees.add(employee1);
		employees.add(employee2);
		
		Mockito.when(model.getEmployeeList()).thenReturn(employees);
		
		Report5 report = new Report5(model, "jakisProjekt");
	
		Assert.assertEquals(4, report.getColumnNames().size());
		
		Assert.assertEquals(2, report.getRows().size());
		
		Assert.assertEquals("3.0", report.getRows().get(0).get(3));
		Assert.assertEquals("7.0", report.getRows().get(1).get(3));
		
		report.printReport();
		
	}

}
