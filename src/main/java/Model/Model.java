package Model;

import java.util.List;
import java.util.ArrayList;

public class Model {

    private List<Employee> employeeList = new ArrayList<Employee>();

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void addEmployee(Employee employee) {
        employeeList.add(employee);
    }

    public void deleteEmployee(Employee employee) {
        employeeList.remove(employee);
    }
}
