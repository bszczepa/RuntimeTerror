package Model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections4.ListUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import Reader.FilesScanner;

public class Model {

    public Model(String directoryPath) throws IOException, InvalidFormatException {
        FilesScanner fileScanner = new FilesScanner();
        employeeList = fileScanner.scanFiles(directoryPath);
    }

    private List<Employee> employeeList;

    public List<Employee> getEmployeeList() {
    	return ListUtils.unmodifiableList(employeeList);
    }

    public void addEmployee(Employee employee) {
        employeeList.add(employee);
    }

    public void deleteEmployee(Employee employee) { employeeList.remove(employee); }
}
