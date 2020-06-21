package Report;

import Model.Model;
import Model.Employee;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Report3 {

    private String title = null;
    private Integer rowsCounter = 1;
    private List<String> columnNames = new ArrayList<String>();
    private List<List<String>> rows = new ArrayList<List<String>>();

    public void report(Model model, String id, int year) {

        setTitle("Rok: " + year + "; Imię i nazwisko: " + id);
        columnNames.add("L.p");
        columnNames.add("Miesiąc");
        columnNames.add("Projekt");
        columnNames.add("Liczba godzin");

        String[] polishMonths = {"Styczeń","Luty","Marzec","Kwiecień","Maj","Czerwiec","Lipiec","Sierpień","Wrzesień","Pażdziernik","Listopad","Grudzień"};

        Employee foundEmployee = findEmployee(model, id);

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
