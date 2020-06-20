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
    	this.taskList.add(task);
    }

    public void deleteTask(Task task) {

    }


}
