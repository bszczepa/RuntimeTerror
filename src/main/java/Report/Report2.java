package Report;

import Model.*;
import java.util.*;

public class Report2 extends Report{

    public void createReport2(Model model, int year) {

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
            System.out.println(project.getKey() + "    " + project.getValue());
        }
        
// TODO:  Konwersja mapy na listę nazw kolumn i listę wierszy!
    }
    
    
}