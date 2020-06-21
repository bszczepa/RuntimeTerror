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

    public void printReport() {

        for (String string : columnNames) {
            System.out.print(string + "  ");
        }

        System.out.println();

        for (List<String> row : rows) {
            for (String rowCell : row) {
                System.out.print(rowCell + "  ");
            }
            System.out.println();
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
