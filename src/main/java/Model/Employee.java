package Model;

import java.util.ArrayList;
import java.util.List;

public class Employee {

    private List<Task> taskList = new ArrayList<Task>();
    private String name;
    private String surname;

    public Employee(java.lang.String name, java.lang.String surname) {
        this.name = name;
        this.surname = surname;
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    public void addTask(Task task) {

    }

    public void deleteTask(Task task) {

    }
    
    public double getTotalHours(int year) {
        double sum=0;
        for(Task task:taskList) {
            if (task.getTaskDate().getYear()==year) {
                sum+=task.getHours();
            }
        }
        return sum;
        
    }


}
