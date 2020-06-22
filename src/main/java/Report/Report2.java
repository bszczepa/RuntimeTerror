package Report;

import Model.*;
import java.util.*;

public class Report2 {

    private String title = "";
    private Integer rowsCounter = 1;
    private List<String> columnNames = new ArrayList<String>();
    private List<List<String>> rows = new ArrayList<List<String>>();

    public void createReport2(Model model, int year) {

        this.title = "Lista projektów w roku" + year + "\n";
        columnNames.add("L.p");
        columnNames.add("Projekt");
        columnNames.add("Ilość godzin");

        TreeMap<String, Double> projectsMap = new TreeMap<>();
        List<Employee> employees = model.getEmployeeList();

        for (Employee employee : employees) {
            for (Task task : employee.getTaskList()) {
                Date date = task.getTaskDate();
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(date);
                if (calendar.get(Calendar.YEAR) == year) {
                    String projectName = task.getProjectName();
                    if (projectsMap.containsKey(projectName)) {
                        projectsMap.replace(projectName, projectsMap.get(projectName) + task.getHours());
                    } else {
                        projectsMap.put(projectName, task.getHours());
                    }
                }
            }
        }

        for (Map.Entry project : projectsMap.entrySet()) {
            List<String> newRow = new ArrayList<>();
            newRow.add(rowsCounter.toString());
            newRow.add(project.getKey().toString());
            newRow.add(project.getValue().toString());
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

    public String getTitle() {
        return title;
    }

    public List<String> getColumnNames() {
        return columnNames;
    }

    public List<List<String>> getRows() {
        return rows;
    }


}