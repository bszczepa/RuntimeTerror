package Report;

import Model.*;
import java.util.*;

public class Report2 {

    private Integer rowsCounter = 1;
    private List<String> columnNames = new ArrayList<String>();
    private List<List<String>> rows = new ArrayList<List<String>>();

    public void createReport2(Model model, int year) {

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

        List<String> newRow = new ArrayList<>();
        for (Map.Entry project : projectsMap.entrySet()) {
            newRow.add(project.getKey().toString());
            newRow.add(project.getValue().toString());
        }
        rows.add(newRow);


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




}