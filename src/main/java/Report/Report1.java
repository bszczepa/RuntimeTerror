package Report;

import Model.Model;
import Model.Employee;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Report1 extends Report {

    public void report(Model model, int year) {

        setTitle("Sumaryczna liczba godzin za rok " + year);
        columnNames.add("L.p");
        columnNames.add("Imię i nazwisko");
        columnNames.add("Liczba godzin");

        List<Employee> employeeList = model.getEmployeeList();
        employeeList.sort(Comparator.comparing(Employee::getSurname));

        for (Employee employee : employeeList) {
            List<String> newRow = new ArrayList();
            newRow.add(rowsCounter.toString());
            newRow.add(employee.getNameAndSurname());
            newRow.add(String.valueOf(employee.getTotalHours(year)));
            rows.add(newRow);
            rowsCounter++;
        }
    }

    public void printReport() {
		int lineLength = columnNames.size() * 32;

	
	
		System.out.println(String.join("", Collections.nCopies(lineLength, "-")));
		System.out.format("%-1s", "|");
		System.out.format("%-"+ columnNames.size()*30 +"s %-" + columnNames.size() +"s", this.title, " ");
		System.out.format("%2s", "|");
		System.out.println();
		System.out.println(String.join("", Collections.nCopies(lineLength, "-")));
		for (String columnName : columnNames) {
			System.out.format("%-1s %-30s", "|", columnName);
			
		}
		System.out.format("%-1s" , "|");
		System.out.println();
		System.out.println(String.join("", Collections.nCopies(lineLength, "-")));
		for (List<String> row : rows) {
			for (String cell : row) {
				System.out.format("%-1s %-30s", "|", cell);
				
			}
			System.out.format("%-1s" , "|");
			System.out.println();
		}
		System.out.println(String.join("", Collections.nCopies(lineLength, "-")));
	}


    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
