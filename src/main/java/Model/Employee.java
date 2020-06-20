package Model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class Employee {

    private List<Task> taskList = new ArrayList<Task>();
    private String name;
    private String surname;

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Employee(java.lang.String name, java.lang.String surname) {
        this.name = name;
        this.surname = surname;
    }

    public Employee() {
	
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
    
    public void addTasks(List<Task> tasks) {
        this.taskList.addAll(tasks);
    }

    public void deleteTask(Task task) {
        this.taskList.remove(task);
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((surname == null) ? 0 : surname.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (surname == null) {
			if (other.surname != null)
				return false;
		} else if (!surname.equals(other.surname))
			return false;
		return true;
	}
	
	    public double getTotalHours(int year) {
	        double sum=0;
	        for(Task task:taskList) {
	            Date date = task.getTaskDate();
	            Calendar calendar = new GregorianCalendar();
	            calendar.setTime(date);
	            if (calendar.get(Calendar.YEAR)==year) {
	                sum+=task.getHours();
	            }
	        }
	        return sum;
	    }

		@Override
		public String toString() {
			return "Employee [taskList=" + taskList + ", name=" + name + ", surname=" + surname + "]";
		}


    

}
