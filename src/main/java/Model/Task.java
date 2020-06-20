package Model;


import java.time.LocalDate;
import java.util.Date;

public class Task {

    private Date taskDate;
    private String projectName;
    private String taskName;
    private double hours;

    public Task(Date taskDate, java.lang.String projectName, java.lang.String taskName, double hours) {
        this.taskDate = taskDate;
        this.projectName = projectName;
        this.taskName = taskName;
        this.hours = hours;
    }

    public Date getTaskDate() {
        return taskDate;
    }

    public void setTaskDate(Date taskDate) {
        this.taskDate = taskDate;
    }

    public java.lang.String getProjectName() {
        return projectName;
    }

    public void setProjectName(java.lang.String projectName) {
        this.projectName = projectName;
    }

    public java.lang.String getTaskName() {
        return taskName;
    }

    public void setTaskName(java.lang.String taskName) {
        this.taskName = taskName;
    }

    public double getHours() {
        return hours;
    }

    public void setHours(double hours) {
        this.hours = hours;
    }

	@Override
	public String toString() {
		return "Task [taskDate=" + taskDate + ", projectName=" + projectName + ", taskName=" + taskName + ", hours="
				+ hours + "]";
	}

    
}