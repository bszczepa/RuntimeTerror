package App;

import java.io.IOException;
import java.util.Scanner;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import Model.Model;
import Reader.ScanErrorsHolder;
import Report.Report;
import Report.Report1Builder;
import Report.Report2Builder;
import Report.Report3Builder;
import Report.Report4Builder;
import Report.Report5Builder;
import Report.ReportBuilder;
import Report.ReportPrinter;

public class UserControl {

	private Scanner sc = new Scanner(System.in);
	private String userOption;
	private String path;
	private Model model;
	private ReportBuilder reportBuilder;
	private Report report;

	public UserControl(String path) throws IOException, InvalidFormatException {
		this.path = path;
		model = new Model(path);

		ScanErrorsHolder.printScanErrors();
	}

	public void controlLoop() throws IOException, InvalidFormatException {
		appHeaders();
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

	public void showOption() {
		System.out.println("1. Generuj raport listy pracowników za podany rok: ");
		System.out.println("2. Generuj raport listy projektów za podany rok ");
		System.out.println("3. Szczegółowy wykaz pracy danego pracownika za podany rok");
		System.out.println("4. Procentowy udział danego pracownika w projekt za dany rok");
		System.out.println("5. Szczegółowy wykaz pracy pracowników w danym projekcie");
		System.out.println("0. Zakończ pracę z programem");
	}

	public String inputUserOption() {
		System.out.println("______________________");
		System.out.println("Wprowadź wybraną opcję");
		userOption = sc.nextLine();
		return userOption;
	}

	private void exit() {
		System.out.println("Koniec programu");
		sc.close();
	}

	private void appHeaders() {
		System.out.println("----------------------------");
		System.out.println("Runtime Terror version 1.0.0");
		System.out.println("----------------------------");
	}

	private void generateReport4() throws InvalidFormatException, IOException {
		System.out.println();
		System.out.println("Podaj za jaki rok mam wygenerować raport");
		int reportYear = sc.nextInt();
		sc.nextLine();
		reportBuilder = new Report4Builder();
		reportBuilder.addParam(reportYear);
		report = reportBuilder.buildReport(model);
		ReportPrinter.printReport(report);
		System.out.println();
	}

	private void generateReport5() throws InvalidFormatException, IOException {
		System.out.println();
		System.out.println("Podaj nazwę projektu");
		String projectName = sc.nextLine();
		reportBuilder = new Report5Builder();
		reportBuilder.addParam(projectName);
		report = reportBuilder.buildReport(model);
		ReportPrinter.printReport(report);
		System.out.println();
	}

	private void generateReport1() throws InvalidFormatException, IOException {
		System.out.println("Podaj za jaki rok mam wygenerować raport");
		int reportYear = sc.nextInt();
		sc.nextLine();
		reportBuilder = new Report1Builder();
		reportBuilder.addParam(reportYear);
		report = reportBuilder.buildReport(model);
		ReportPrinter.printReport(report);
		System.out.println();
	}

	private void generateReport2() {
		System.out.println("Podaj za jaki rok mam wygenerować raport");
		int reportYear = sc.nextInt();
		sc.nextLine();
		System.out.println();
		reportBuilder = new Report2Builder();
		reportBuilder.addParam(reportYear);
		report = reportBuilder.buildReport(model);
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
		reportBuilder = new Report3Builder();
		reportBuilder.addParam(reportYear, name);
		report = reportBuilder.buildReport(model);
		ReportPrinter.printReport(report);
		System.out.println();
	}
}
