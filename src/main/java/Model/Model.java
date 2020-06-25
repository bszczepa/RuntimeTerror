package Model;

import java.io.IOException;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import Reader.FilesScanner;

public class Model {

    public Model(String directoryPath) {
       try {
           FilesScanner fileScanner = new FilesScanner();
           employeeList = fileScanner.scanFiles(directoryPath);
       } catch (IOException e){
           System.err.println("Nie znaleziono pliku");
       } catch ( InvalidFormatException e ){
           System.err.println("Bład odczytu pliku");
       }
    }

    private List<Employee> employeeList;

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void addEmployee(Employee employee) {
        employeeList.add(employee);
    }

    public void deleteEmployee(Employee employee) { employeeList.remove(employee); }
}
