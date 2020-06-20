package Model;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

import Reader.DataReader;

import Reader.FilesScanner;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

public class Model {


    public Model(String directoryPath) throws IOException, InvalidFormatException {
        FilesScanner fileScanner = new FilesScanner();
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
