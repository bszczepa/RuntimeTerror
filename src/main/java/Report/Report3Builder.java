package Report;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Model.Employee;
import Model.Model;

public class Report3Builder implements ReportBuilder {

	private int year;
	private String id;
	

	

	public Report3Builder(int year, String id) {
		super();
		this.year = year;
		this.id = id;
	}




	@Override
	public Report buildReport(Model model){
		
			Report report = new Report();
		    List<String> columnNames = new ArrayList<String>();
		    List<List<String>> rows = new ArrayList<List<String>>();
		    Integer rowsCounter = 1;
			
		    report.setTitle("Rok: " + year + "; Imię i nazwisko: " + id);
		
	        columnNames.add("L.p");
	        columnNames.add("Miesiąc");
	        columnNames.add("Projekt");
	        columnNames.add("Liczba godzin");

	        String[] polishMonths = {"Styczeń","Luty","Marzec","Kwiecień","Maj","Czerwiec","Lipiec","Sierpień","Wrzesień","Pażdziernik","Listopad","Grudzień"};

	        Employee foundEmployee = findEmployee(model, id);

	        if (foundEmployee != null) {
	            for (int i=0; i<12; i++) {
	                HashMap<String, Double> hours = foundEmployee.getHoursByProject(i);
	                for (String project : hours.keySet()) {
	                    List<String> newRow = new ArrayList();
	                    newRow.add(rowsCounter.toString());
	                    newRow.add(polishMonths[i]);
	                    newRow.add(project);
	                    newRow.add(String.valueOf(hours.get(project)));
	                    rows.add(newRow);
	                    rowsCounter++;
	                }
	            }
	        } else {
	            System.out.println("Pracownik nie istnieje w bazie lub w tym roku nie wykonywał prac");
	        }
	        
	        report.setColumnNames(columnNames);
	        report.setRows(rows);
	        return report;
	}
	
	  public Employee findEmployee(Model model, String id){
	        List<Employee> employeeList = model.getEmployeeList();
	        for (Employee employee : employeeList) {
	            if (id.toLowerCase().contains(employee.getName().toLowerCase())
	            && id.toLowerCase().contains(employee.getSurname().toLowerCase())) {
	                return employee;
	            }
	        }
	        return null;
	    }
}
