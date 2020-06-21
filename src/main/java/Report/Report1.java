package Report;

import Model.Model;
import Model.Employee;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Report1 {

    private String title = null;
    private Integer rowsCounter = 1;
    private List<String> columnNames = new ArrayList<String>();
    private List<List<String>> rows = new ArrayList<List<String>>();

    public void report(Model model, int year) {

        setTitle("Sumaryczna liczba godzin za rok " + year + "\n");
        columnNames.add("L.p");
        columnNames.add("Imię i nazwisko");
        columnNames.add("Ilosc godzin");

        List<Employee> employeeList = model.getEmployeeList();
        employeeList.sort(Comparator.comparing(Employee::getSurname));

        for (Employee employee : employeeList) {
            List<String> newRow = new ArrayList();
            newRow.add(rowsCounter.toString());
            newRow.add(employee.getSurname());
            newRow.add(employee.getName());
            newRow.add(String.valueOf(employee.getTotalHours(year)));
            rows.add(newRow);
            rowsCounter++;
        }
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

    public void setTitle(String title) {
        this.title = title;
    }
}
