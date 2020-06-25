package App;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import ChartMakers.Report2BarChartMaker;
import ChartMakers.Report4PieChartMaker;
import ChartMakers.ReportChartMaker;
import Model.Employee;
import Model.Model;
import Model.Task;
import Reader.ScanErrorsHolder;
import Report.Report;
import Report.Report1Builder;
import Report.Report2Builder;
import Report.Report3Builder;
import Report.Report4Builder;
import Report.Report5Builder;
import Report.ReportBuilder;
import Report.ReportPrinter;
import Report.ReportXlsExporter;

public class UserControl {

	private Scanner sc = new Scanner(System.in);
	private String userOption;
	private String path;
	private Model model;
	private ReportBuilder reportBuilder;
	private Report report;
	private ReportXlsExporter reportToXls;
	private ReportChartMaker chartMaker;

	public UserControl(String path) {
		this.path = path;
		model = new Model(path);
		reportToXls = new ReportXlsExporter();
	}

	public void controlLoop() {
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
			case "6":
				generateReport6();
				break;
			case "7":
				generateReport7();
				break;
			case "9":
				generateErrorsLog();
				break;
			case "0":
				exit();
				break;
			default:
				System.err.println("Nie znam takiej opcji");
			}
		} while (!userOption.equals("0"));

	}

	public void showOption() {
		System.out.println("WYBIERZ OPCJE:");
		System.out.println("1. Generuj raport listy pracowników za podany rok: ");
		System.out.println("2. Generuj raport listy projektów za podany rok ");
		System.out.println("3. Szczegółowy wykaz pracy danego pracownika za podany rok");
		System.out.println("4. Procentowy udział danego pracownika w poszczególnych projektach za dany rok");
		System.out.println("5. Szczegółowy wykaz pracy pracowników w danym projekcie");
		System.out.println("6. Wykres: raport godzinowy projektów w danym roku");
		System.out.println("7. Wykres: Procentowy udział danego pracownika w poszczególnych projektach za dany rok");
		System.out.println("9. Pokaż logi z odczytu pliku");
		System.out.println("0. Zakończ pracę z programem");
	}

	private void generateReport1() {
		dateRangeGenerator();
		int reportYear;
		System.out.println("Podaj za jaki rok mam wygenerować raport");
		try {
			reportYear = sc.nextInt();
			sc.nextLine();
			reportBuilder = new Report1Builder(reportYear);
			report = reportBuilder.buildReport(model);
			ReportPrinter.printReport(report);
			saveReportToFile(report);

		} catch (InputMismatchException e) {
			System.err.println("Wprowadziłeś błędne dane");
		}
	}

	private void generateReport2() {
		dateRangeGenerator();
		int reportYear;
		try {
			System.out.println("Podaj za jaki rok mam wygenerować raport");
			reportYear = sc.nextInt();
			sc.nextLine();
			System.out.println();
			reportBuilder = new Report2Builder(reportYear);
			report = reportBuilder.buildReport(model);
			ReportPrinter.printReport(report);
			System.out.println();
			saveReportToFile(report);
		} catch (InputMismatchException e) {
			System.err.println("Wprowadziłeś błędne dane");
		}

	}

	private void generateReport3() {
		System.out.println("Podaj imię i nazwisko pracownika");
		String name = sc.nextLine();
		System.out.println("Podaj za jaki rok mam wygenerować raport");
		int reportYear = sc.nextInt();
		sc.nextLine();
		System.out.println();
		reportBuilder = new Report3Builder(reportYear, name);
		report = reportBuilder.buildReport(model);
		ReportPrinter.printReport(report);
		saveReportToFile(report);
		System.out.println();
	}

	private void generateReport4() {
		System.out.println();
		System.out.println("Podaj za jaki rok mam wygenerować raport");
		int reportYear = sc.nextInt();
		sc.nextLine();
		reportBuilder = new Report4Builder(reportYear);
		report = reportBuilder.buildReport(model);
		ReportPrinter.printReport(report);
		System.out.println();
		saveReportToFile(report);
		System.out.println();
	}

	private void generateReport5() {
		System.out.println();
		System.out.println("Podaj nazwę projektu");
		String projectName = sc.nextLine();
		reportBuilder = new Report5Builder(projectName);
		report = reportBuilder.buildReport(model);
		ReportPrinter.printReport(report);
		saveReportToFile(report);
		System.out.println();
	}
	
	private void generateReport6() {
		dateRangeGenerator();
		int reportYear;
		try {
			System.out.println("Podaj za jaki rok mam wygenerować raport");
			reportYear = sc.nextInt();
			sc.nextLine();
			System.out.println();
			reportBuilder = new Report2Builder(reportYear);
			report = reportBuilder.buildReport(model);
			chartMaker = new Report2BarChartMaker();
			chartMaker.makeChart(report);
			System.out.println();
		} catch (InputMismatchException e) {
			System.err.println("Wprowadziłeś błędne dane");
		}
	}
	
	private void generateReport7() {
		System.out.println();
		System.out.println("Podaj za jaki rok mam wygenerować raport");
		int reportYear = sc.nextInt();
		sc.nextLine();
		reportBuilder = new Report4Builder(reportYear);
		report = reportBuilder.buildReport(model);
		chartMaker = new Report4PieChartMaker();
		chartMaker.makeChart(report);
		System.out.println();
	}

	private void generateErrorsLog() {
		ScanErrorsHolder.printScanErrors();
		System.out.println();
	}

	private void saveReportToFile(Report report) {
		System.out.println("\nCzy chcesz zapsiać raport do pliku T / N ?");
		try {
			String writeReportOpt = sc.nextLine();
			switch (writeReportOpt.toLowerCase()) {
			case "t": {
				File generatedReport = reportToXls.exportToXls(report);
				String reportPath = generatedReport.getCanonicalPath();
				System.out.println("Poprawnie wygenerowano raport do pliku: " + reportPath);
				openGeneratedFile(generatedReport);
				break;
			}
			default: {
				System.out.println("Zrezygnowano z zapisu pliku");
			}
			}
		} catch (IOException e) {
			System.err.println("Nie udało się zapisać pliku");
		}
	}

	private void openGeneratedFile(File generatedReport) throws IOException {
		try {
			Desktop desktop = Desktop.getDesktop();
			if (generatedReport.exists()) {
				desktop.open(generatedReport);
			}
		} catch (UnsupportedOperationException e) {
		}
	}

	private void dateRangeGenerator() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy");
		List<String> yearProject = new ArrayList<>();
		List<Employee> employeeList = model.getEmployeeList();
		for (Employee employee : employeeList) {
			List<Task> taskList = employee.getTaskList();
			for (Task task : taskList) {
				Date allDates = task.getTaskDate();
				String year = simpleDateFormat.format(allDates);
				if (!yearProject.contains(year)) {
					yearProject.add(year);
				}
			}
		}
		Collections.sort(yearProject);
		System.out.println("\nRaporty są dostępne za lata: " + yearProject + "\n");
	}

	private void appHeaders() {
		System.out.println("______                _____  _                    _____                                \n"
				+ "| ___ \\              |_   _|(_)                  |_   _|                               \n"
				+ "| |_/ / _   _  _ __    | |   _  _ __ ___    ___    | |    ___  _ __  _ __   ___   _ __ \n"
				+ "|    / | | | || '_ \\   | |  | || '_ ` _ \\  / _ \\   | |   / _ \\| '__|| '__| / _ \\ | '__|\n"
				+ "| |\\ \\ | |_| || | | |  | |  | || | | | | ||  __/   | |  |  __/| |   | |   | (_) || |   \n"
				+ "\\_| \\_| \\__,_||_| |_|  \\_/  |_||_| |_| |_| \\___|   \\_/   \\___||_|   |_|    \\___/ |_|   \n"
				+ "                                                                                       \nversion 1.0.0");
		System.out.println("----------------------------");
		System.out.println("");
	}

	public String inputUserOption() {
		System.out.println("\n______________________");
		System.out.println("Wprowadź wybraną opcję\n");
		userOption = sc.nextLine();
		return userOption;
	}

	private void exit() {
		System.out.println("Koniec programu");
		sc.close();
	}

}
