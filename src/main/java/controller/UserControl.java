package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import model.Employee;
import model.Report;
import services.DataReader;
import services.ScanErrorsHolder;
import services.reportServices.ReportBuilder;
import services.reportServices.ReportBuilderFactory;
import services.reportServices.ReportPrinter;

public class UserControl {

	private Scanner sc = new Scanner(System.in);
	private String userOption;
	private DataReader dataReader = new DataReader();
	private ReportBuilder reportBuilder;
	private Report report;

	List<Employee> employees = new ArrayList<Employee>();

	public UserControl(String path) throws IOException, InvalidFormatException {
		employees = dataReader.readFiles(path);
	}

	private void appHeaders() {
		System.out.println("----------------------------");
		System.out.println("Runtime Terror version 1.0.0");
		System.out.println("----------------------------");
	}

	public void controlLoop() throws IOException, InvalidFormatException {
		appHeaders();
		ScanErrorsHolder.printScanErrors();
		do {
			showOption();
			String userOption = inputUserOption();
			switch (userOption) {
			case "1":
				generateReport1();
				break;
			case "2":
				generateReport2();
				break;
			case "3":
				generateReport3();
				break;
			case "4":
				generateReport4();
				break;
			case "5":
				generateReport5();
				break;
			case "0":
				exit();
				break;
			default:
				System.out.println("Nie znam takiej opcji");
			}
		} while (!userOption.equals("0"));

	}

	private void exit() {
		System.out.println("Koniec programu");
		sc.close();
	}

	private void generateReport1() throws InvalidFormatException, IOException {
		System.out.println("Podaj za jaki rok mam wygenerować raport");
		int reportYear = sc.nextInt();
		sc.nextLine();
		reportBuilder = ReportBuilderFactory.getReprtBuilder("1");
		reportBuilder.addParam(reportYear);
		report = reportBuilder.buildReport(employees);
		ReportPrinter.printReport(report);
		System.out.println();
	}

	private void generateReport2() {
		System.out.println("Podaj za jaki rok mam wygenerować raport");
		int reportYear = sc.nextInt();
		sc.nextLine();
		System.out.println();
		reportBuilder = ReportBuilderFactory.getReprtBuilder("2");
		reportBuilder.addParam(reportYear);
		report = reportBuilder.buildReport(employees);
		ReportPrinter.printReport(report);
		System.out.println();
	}

	private void generateReport3() {
		System.out.println("Podaj imię i nazwisko pracownika");
		String name = sc.nextLine();
		System.out.println("Podaj za jaki rok mam wygenerować raport");
		int reportYear = sc.nextInt();
		sc.nextLine();
		System.out.println();
		reportBuilder = ReportBuilderFactory.getReprtBuilder("3");
		reportBuilder.addParam(reportYear, name);
		report = reportBuilder.buildReport(employees);
		ReportPrinter.printReport(report);
		System.out.println();
	}

	private void generateReport4() throws InvalidFormatException, IOException {
		System.out.println();
		System.out.println("Podaj za jaki rok mam wygenerować raport");
		int reportYear = sc.nextInt();
		sc.nextLine();
		reportBuilder = ReportBuilderFactory.getReprtBuilder("4");
		reportBuilder.addParam(reportYear);
		report = reportBuilder.buildReport(employees);
		ReportPrinter.printReport(report);
		System.out.println();
	}

	private void generateReport5() throws InvalidFormatException, IOException {
		System.out.println();
		System.out.println("Podaj nazwę projektu");
		String projectName = sc.nextLine();
		reportBuilder = ReportBuilderFactory.getReprtBuilder("5");
		reportBuilder.addParam(projectName);
		report = reportBuilder.buildReport(employees);
		ReportPrinter.printReport(report);
		System.out.println();
	}

	public String inputUserOption() {
		System.out.println("______________________");
		System.out.println("Wprowadź wybraną opcję");
		userOption = sc.nextLine();
		return userOption;
	}

	public void showOption() {
		System.out.println("1. Generuj raport listy pracowników za podany rok: ");
		System.out.println("2. Generuj raport listy projektów za podany rok ");
		System.out.println("3. Szczegółowy wykaz pracy danego pracownika za podany rok");
		System.out.println("4. Procentowy udział danego pracownika w projekt za dany rok");
		System.out.println("5. Szczegółowy wykaz pracy pracowników w danym projekcie");
		System.out.println("0. Zakończ pracę z programem");
	}
}
