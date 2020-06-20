package Report;

import Model.Model;
import Model.Employee;

import java.util.HashMap;
import java.util.List;

public class Report3 {

    public void report(Model model, String id) {
        Employee foundEmployee = findEmployee(model, id);

        System.out.println("Imie i Nazwisko: " + id + "\n");
        System.out.format("\t%4s 15%s 15%s 15%s \n", "Lp.", "Miesiąc", "Projekt", "Liczba godzin");

        int counter = 1;
        String[] polishMonths = {"Styczeń","Luty","Marzec","Kwiecień","Maj","Czerwiec","Lipiec","Sierpień","Wrzesień","Pażdziernik","Listopad","Grudzień"};

        for (int i=0; i<12; i++) {
            HashMap<String, Double> hours = foundEmployee.getHoursByProject(i);
            for (String project : hours.keySet()) {
                System.out.format("\t%4d 15%s 15%s 15,2%f \n", counter, polishMonths[i], project, hours.get(project));
            }
        }
    }

    public Employee findEmployee(Model model, String id){
        List<Employee> employeeList = model.getEmployeeList();
        for (Employee employee : employeeList) {
            if (employee.getNameAndSurname().equals(id)){
                return employee;
            }
        }
        return null;
    }
}
