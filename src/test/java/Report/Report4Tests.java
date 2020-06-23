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

import model.Employee;
import model.Report;
import model.Task;
import services.reportServices.Report4Builder;
import services.reportServices.ReportBuilder;
import services.reportServices.ReportPrinter;

public class Report4Tests {

	@Test
	public void testTwoProjects() {
		List<Employee> employees = new ArrayList<Employee>();

		Employee employee1 = new Employee("Jan", "Nowak");

		Calendar myCalendar = new GregorianCalendar(2012, 2, 11);
		Date date = myCalendar.getTime();	
		Task task = new Task(date, "jakisProjekt", "jakies zadanie", 1);
		employee1.addTask(task);
		Employee employee2 = new Employee("Paweł", "Kwiatkowski");
		Task task2 = new Task(date, "jakisProjekt", "jakies zadanie2", 5);
		Task task3 = new Task(date, "jakisProjekt2", "jakies zadanie2", 5);
		employee2.addTask(task2);
		employee2.addTask(task3);
		employees.add(employee1);
		employees.add(employee2);
		
		
		
		ReportBuilder rBuilder = new Report4Builder();
		rBuilder.addParam(2012);
		
		Report report = rBuilder.buildReport(employees);
		Assert.assertEquals(4, report.getColumnNames().size());
		Assert.assertEquals(2, report.getRows().size());
		Assert.assertEquals("100.0%", report.getRows().get(0).get(2));
		Assert.assertEquals("", report.getRows().get(0).get(3));
		Assert.assertEquals("50.0%", report.getRows().get(1).get(2));
		Assert.assertEquals("50.0%", report.getRows().get(1).get(3));
		ReportPrinter.printReport(report);

	}

	@Test
	public void testNotFilteringMasterEmployeesData() throws IOException {
		
		List<Employee> employees = new ArrayList<Employee>();
		Employee employee1 = new Employee("Jan", "Nowak");
		Calendar myCalendar = new GregorianCalendar(2012, 2, 11);
		Date date = myCalendar.getTime();	
		Task task = new Task(date, "jakisProjekt", "jakies zadanie", 3);
		employee1.addTask(task);
		employees.add(employee1);
		ReportBuilder rBuilder = new Report4Builder();
		rBuilder.addParam(2013);
		rBuilder.buildReport(employees);
		ReportBuilder rBuilder2 = new Report4Builder();
		rBuilder2.addParam(2012);
		Report report = rBuilder2.buildReport(employees);
		Assert.assertTrue((report.getRows().size() == 1));
		
		
	}
	
	@Test
	public void testEmptyWhenDifferentYear(){
	
		List<Employee> employees = new ArrayList<Employee>();

		Employee employee1 = new Employee("Jan", "Nowak");

		Calendar myCalendar = new GregorianCalendar(2012, 2, 11);
		Date date = myCalendar.getTime();	
		Task task = new Task(date, "jakisProjekt", "jakies zadanie", 3);
		employee1.addTask(task);
		Employee employee2 = new Employee("Paweł", "Kwiatkowski");
		Task task2 = new Task(date, "jakisProjekt", "jakies zadanie2", 7);
		Task task3 = new Task(date, "jakisProjekt2", "jakies zadanie2", 7);
		employee2.addTask(task2);
		employee2.addTask(task3);
		employees.add(employee1);
		employees.add(employee2);
		
		ReportBuilder rBuilder = new Report4Builder();
		rBuilder.addParam(2020);
	
		Report report = rBuilder.buildReport(employees);
		Assert.assertTrue((report.getRows().size() == 0));
		
	
	
	}
	

}
