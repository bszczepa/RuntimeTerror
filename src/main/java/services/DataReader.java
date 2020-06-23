package services;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import model.Employee;
import model.ScanError;
import model.Task;
import repository.FilesFinder;

public class DataReader {

	public FilesFinder filesScanner = new FilesFinder();
	public List<File> files = new ArrayList<File>();
	public List<Employee> foundEmployees = new ArrayList<Employee>();

	public List<Employee> readFiles(String path) throws InvalidFormatException, IOException {
		FilesFinder fScanner = new FilesFinder();
		files = filesScanner.findFiles(path);
		filterFiles();
		for (File file : files) {
			Employee employee = readFile(file);
			if (foundEmployees.contains(employee)) {
				foundEmployees.get(foundEmployees.indexOf(employee)).addTasks(employee.getTaskList());
			} else {
				foundEmployees.add(employee);
			}
		}
		return foundEmployees;
	}

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

		employeeName = extractEmployeeName(fileName);
		employeeSurname = extractEmployeeSurname(fileName);

		Employee employee = new Employee(employeeName, employeeSurname);

		for (int i = 0; i < wb.getNumberOfSheets(); i++) {
			Sheet sheet = wb.getSheetAt(i);
			if (sheet.getRow(0).getCell(0) == null || sheet.getRow(0).getCell(1) == null
					|| sheet.getRow(0).getCell(2) == null
					|| !sheet.getRow(0).getCell(0).getCellTypeEnum().equals(CellType.STRING)
					|| !sheet.getRow(0).getCell(1).getCellTypeEnum().equals(CellType.STRING)
					|| !sheet.getRow(0).getCell(2).getCellTypeEnum().equals(CellType.STRING)
					|| !sheet.getRow(0).getCell(0).getStringCellValue().equals("Data")
					|| !sheet.getRow(0).getCell(1).getStringCellValue().equals("Zadanie")
					|| !sheet.getRow(0).getCell(2).getStringCellValue().equals("Czas [h]")) {
				ScanErrorsHolder.addScanError(
						new ScanError(file.getPath(), sheet.getSheetName(), "Plik nie zawiera odpowiednich kolumn"));
				continue;
			}
			project = sheet.getSheetName();
			for (int j = 1; j <= sheet.getLastRowNum(); j++) {

				Boolean error = false;

				if (sheet.getRow(j) == null) {
					ScanErrorsHolder
							.addScanError(new ScanError(file.getPath(), sheet.getSheetName(), j + 1, "pusty wiersz!"));
					continue;
				}

				Row row = sheet.getRow(j);

				if (row.getCell(0) == null || row.getCell(0).getCellTypeEnum() == CellType.BLANK) {
					ScanErrorsHolder.addScanError(
							new ScanError(file.getPath(), sheet.getSheetName(), j + 1, "DATA", "pusta komórka!"));
					error = true;
				} else if (!row.getCell(0).getCellTypeEnum().equals(CellType.NUMERIC)) {
					ScanErrorsHolder.addScanError(new ScanError(file.getPath(), sheet.getSheetName(), j + 1, "DATA",
							"komórka nie zawiera wartości numerycznej!"));
					error = true;
				}

				if (row.getCell(1) == null || row.getCell(1).getCellTypeEnum() == CellType.BLANK) {
					ScanErrorsHolder.addScanError(
							new ScanError(file.getPath(), sheet.getSheetName(), j + 1, "OPIS", "pusta komórka!"));

					error = true;
				} else if (row.getCell(1).getCellTypeEnum() != CellType.STRING) {
					ScanErrorsHolder.addScanError(new ScanError(file.getPath(), sheet.getSheetName(), j + 1, "OPIS",
							"komórka nie zawiera wartości numerycznej!"));

					error = true;
				}

				if (row.getCell(2) == null || row.getCell(2).getCellTypeEnum() == CellType.BLANK) {
					ScanErrorsHolder.addScanError(
							new ScanError(file.getPath(), sheet.getSheetName(), j + 1, "CZAS", "pusta komórka!"));
					error = true;

				} else if (row.getCell(2).getCellTypeEnum() != CellType.NUMERIC) {
					ScanErrorsHolder.addScanError(new ScanError(file.getPath(), sheet.getSheetName(), j + 1, "CZAS",
							"komórka nie zawiera wartości numerycznej!"));
					error = true;
				} else if (row.getCell(2).getNumericCellValue() > 24) {
					ScanErrorsHolder.addScanError(
							new ScanError(file.getPath(), sheet.getSheetName(), j + 1, "CZAS", "nieodpowiednie dane!"));
					error = true;
				}

				if (!error) {
					Cell dateCell = row.getCell(0);
					Cell descriptionCell = row.getCell(1);
					Cell timeCell = row.getCell(2);
					date = dateCell.getDateCellValue();
					description = descriptionCell.getStringCellValue();
					time = timeCell.getNumericCellValue();
					Task task = new Task(date, project, description, time);
					employee.addTask(task);
				}
			}
		}
		wb.close();
		return employee;

	}

	private String extractEmployeeSurname(String fileName) {
		return fileName.substring(0, fileName.indexOf("_"));
	}

	private String extractEmployeeName(String fileName) {
		return fileName.substring(fileName.indexOf("_") + 1, fileName.indexOf("."));
	}

	private void filterFiles() throws InvalidFormatException, IOException {

		List<File> filteredFiles = new ArrayList<File>();
		for (File file : files) {
			String filename = file.getName().substring(0, file.getName().indexOf("."));
			if (!filename.matches("[A-z]+_[A-z]+")) {
				ScanErrorsHolder.addScanError(new ScanError(file.getPath(), "", "", "zła nazwa pliku!"));
				continue;
			}
			filteredFiles.add(file);
		}
		files = filteredFiles;
	}

}
