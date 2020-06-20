package Model;

import java.util.List;
import java.util.ArrayList;

import Reader.DataReader;
import Reader.FileScanner;

public class Model {


    public Model(String directoryPath) {
        FileScanner fileScanner = new FileScanner();
        employeeList = fileScanner.scanFiles(directoryPath);
    }

    private List<Employee> employeeList;

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
