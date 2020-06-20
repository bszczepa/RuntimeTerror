package Reader;

import Model.Employee;
import Model.Task;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DataReader {

    public Employee readFile(File file) throws IOException, InvalidFormatException {
        List<Task> tasks = new ArrayList<Task>();

        Workbook wb = WorkbookFactory.create(file);
        String fileName = file.getName();
        String employeeName;
        String employeeSurname;
        String project;
        Date date;
        String description;
        double time;

        employeeName = extractEmployerame(fileName);
        employeeSurname = extractemployerSurname(fileName);

        Employee employee = new Employee(employeeName, employeeSurname);

        for (int i = 0; i < wb.getNumberOfSheets(); i++) {
            Sheet sheet = wb.getSheetAt(i);
            project = sheet.getSheetName();
            for (int j = 1; j <= sheet.getLastRowNum(); j++) {
                Row row = sheet.getRow(j);

                Cell dateCell = row.getCell(0);
                Cell descriptionCell = row.getCell(1);
                Cell timeCell = row.getCell(2);
            
                try {
                	 date = dateCell.getDateCellValue();
                     description = descriptionCell.getStringCellValue();
                     time = timeCell.getNumericCellValue();
                     Task task = new Task(date, project, description ,time);
                     employee.addTask(task);
                }
                catch(NullPointerException e) {
                	continue;
                }            
            }
        }
        wb.close();
        return employee;
    }

    private String extractEmployerame(String fileName) {
        return fileName.substring(0, fileName.indexOf("_"));
    }

    private String extractemployerSurname(String fileName) {
        return fileName.substring(fileName.indexOf("_") + 1, fileName.indexOf("."));
    }


}
