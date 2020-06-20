package Reader;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import Model.Employee;

public class FilesScanner {

	
	private List<File> findFiles(String path) throws IOException {
		File masterDirectory = new File(path);
		masterDirectory.getCanonicalPath();

		return (List<File>) FileUtils.listFiles(masterDirectory, new String[] { "xls", "xlsx" }, true);
	}
	
public List<Employee> scanFiles(String path, String employeeName) throws InvalidFormatException, IOException {
		
		List<File> files = findFiles(path);
		
		List<Employee> employees = new ArrayList();
		DataReader dataReader = new DataReader();
		
		for (File file : files) {
			String filename = file.getName().substring(0,file.getName().indexOf("."));
			if(employeeName == null || filename.equals(employeeName)) {
			Employee employee = new Employee();
			employee = dataReader.readFile(file);
			if(employees.contains(employee)) {
				employees.get(employees.indexOf(employee)).addTasks(employee.getTaskList());
			}
			else {
				employees.add(employee);
			}
		    }
		}
		return employees;
	}
}