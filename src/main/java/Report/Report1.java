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

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
