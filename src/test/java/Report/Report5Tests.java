package Report;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import model.Employee;
import model.Report;
import model.Task;
import services.reportServices.Report5Builder;
import services.reportServices.ReportBuilder;

public class Report5Tests {

	@Test
	public void testTwoProjects() {
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

		ReportBuilder rBuilder = new Report5Builder();
		rBuilder.addParam("jakisProjekt");

		Report report = rBuilder.buildReport(employees);

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

	}

	@Test
	public void testSumOfHours() {
		List<Employee> employees = new ArrayList<Employee>();

		Employee employee1 = new Employee("Jan", "Nowak");

		Date date = new Date();

		Task task = new Task(date, "jakisProjekt", "jakies zadanie", 3);

		Task task2 = new Task(date, "jakisProjekt", "inne zadanie", 5);

		employee1.addTask(task);
		employee1.addTask(task2);

		employees.add(employee1);

		ReportBuilder rBuilder = new Report5Builder();
		rBuilder.addParam("jakisProjekt");
		Report report = rBuilder.buildReport(employees);

		Assert.assertEquals(String.valueOf(8.0), report.getRows().get(0).get(3));

	}

	@Test
	public void testEmptyWhenDifferentProject() {
		List<Employee> employees = new ArrayList<Employee>();

		Employee employee1 = new Employee("Jan", "Nowak");

		Date date = new Date();

		Task task = new Task(date, "jakisProjekt", "jakies zadanie", 3);

		Task task2 = new Task(date, "jakisProjekt", "inne zadanie", 5);

		employee1.addTask(task);
		employee1.addTask(task2);

		employees.add(employee1);

		ReportBuilder rBuilder = new Report5Builder();
		rBuilder.addParam("innyProjekt");
		Report report = rBuilder.buildReport(employees);

		Assert.assertTrue((report.getRows().size() == 0));
	}

	@Test
	public void testNotFilteringMasterEmployeesData() {

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

		ReportBuilder rBuilder = new Report5Builder();
		rBuilder.addParam("jakisProjekt3");
		rBuilder.buildReport(employees);

		ReportBuilder rBuilder2 = new Report5Builder();
		rBuilder2.addParam("jakisProjekt");
		Report report = rBuilder2.buildReport(employees);

		Assert.assertTrue((report.getRows().size() == 2));

	}

}
