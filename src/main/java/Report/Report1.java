package Report;

import Model.Model;
import Model.Employee;

import java.util.Comparator;
import java.util.List;

public class Report1 {

    public void report(Model model, int year) {
        List<Employee> employeeList = model.getEmployeeList();
        employeeList.sort(Comparator.comparing(Employee::getSurname));
        System.out.println("Sumaryczna liczba godzin za rok " + year + "\n");
        int counter = 1;
        for (Employee employee : employeeList) {
            System.out.println("\t" + counter + ". " + employee.getSurname() + " " + employee.getName() + " -> " + employee.getTotalHours(year) + " h");
        }
    }
}
