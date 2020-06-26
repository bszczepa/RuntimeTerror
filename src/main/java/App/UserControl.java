package App;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

import Model.*;
import Reader.ScanErrorsHolder;
import Report.*;


public class UserControl {

    private Scanner sc = new Scanner(System.in);
    private String userOption;
    private String path;
    private Model model;
    private ReportBuilder reportBuilder;
    private Report report;
    private ReportXlsExporter reportToXls;

    public UserControl(String path) {
        this.path = path;
        model = new Model(path);
        reportToXls = new ReportXlsExporter();
    }

    public void controlLoop() {

        do {
            clearConsole();
            appHeaders();
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

            if (!userOption.equals("0")) {
                System.out.println("Naciśnij Enter aby kontynuować...");
                String pause = sc.nextLine();
            }

        } while (!userOption.equals("0"));

    }

    public void showOption() {
        System.out.println("WYBIERZ OPCJE:");
        System.out.println("1. Generuj raport godzin pracowników w podanym roku");
        System.out.println("2. Generuj raport godzin projektów w podanym roku");
        System.out.println("3. Generuj raport godzin przepracowanych miesięcznie przez pracownika w podanym roku");
        System.out.println("4. Generuj procentowy udział projektów w pracy osob dla podanego roku");
        System.out.println("5. Generuj raport ilości godzin pracowników w podanym projekcie");
        System.out.println("6. Wyświetl wykres słupkowy godzin projektów w podanym roku");
        System.out.println("7. Wyświetl wykres kołowy procentowego udziału projektów dla pracowników w podanym roku");
        System.out.println("9. Pokaż logi z odczytu pliku");
        System.out.println("0. Zakończ pracę z programem");
    }

    private void generateReport1() {
        List<String> dateList = dateRangeGenerator();
        dateRangePrinter(dateList);
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
        List<String> dateList = dateRangeGenerator();
        dateRangePrinter(dateList);
        int reportYear;
        try {
            System.out.println("Podaj za jaki rok mam wygenerować raport");
            reportYear = sc.nextInt();
            sc.nextLine();
            System.out.println();
            reportBuilder = new Report2Builder(reportYear);
            report = reportBuilder.buildReport(model);
            ReportPrinter.printReport(report);
            saveReportToFile(report);
            System.out.println();
        } catch (InputMismatchException e) {
            System.err.println("Wprowadziłeś błędne dane");
        }

    }

    private void generateReport3() {
        try {
            List<String> strings = employeeRangeGenerator();
            employeeRangePrinter(strings);
            System.out.println("Podaj imię i nazwisko pracownika");
            String name = sc.nextLine();
            List<String> dateList = dateRangeGenerator();
            dateRangePrinter(dateList);
            System.out.println("Podaj za jaki rok mam wygenerować raport");
            Integer reportYear = sc.nextInt();
            sc.nextLine();
            String year = reportYear.toString();
            if (dateList.contains(year)) {
                System.out.println();
                reportBuilder = new Report3Builder(reportYear, name);
                report = reportBuilder.buildReport(model);
                ReportPrinter.printReport(report);
                saveReportToFile(report);
                System.out.println();
            } else {
                System.out.println("Nie można wygenerować raportu...  Wprowadź poprawne dane");
            }
        } catch (InputMismatchException e) {
            System.out.println("Wprowadziłeś błędnie rok");
        }
    }

    private void generateReport4() {
        List<String> dateList = dateRangeGenerator();
        dateRangePrinter(dateList);
        System.out.println("Podaj za jaki rok mam wygenerować raport");
        int reportYear = sc.nextInt();
        sc.nextLine();
        reportBuilder = new Report4Builder(reportYear);
        report = reportBuilder.buildReport(model);
        ReportPrinter.printReport(report);
        saveReportToFile(report);
        System.out.println();
    }

    private void generateReport5() {
        projectsRangeGenerator();
        System.out.println("Podaj nazwę projektu");
        String projectName = sc.nextLine();
        reportBuilder = new Report5Builder(projectName);
        report = reportBuilder.buildReport(model);
        ReportPrinter.printReport(report);
        saveReportToFile(report);
        System.out.println();
    }

    private void generateReport6() {
        List<String> dateList = dateRangeGenerator();
        dateRangePrinter(dateList);
        int reportYear;
        try {
            System.out.println("Podaj za jaki rok mam wygenerować raport");
            reportYear = sc.nextInt();
            sc.nextLine();
            System.out.println();
            reportBuilder = new Report2Builder(reportYear);
            report = reportBuilder.buildReport(model);
            Report6Builder barChartReport = new Report6Builder();
            barChartReport.plotBarChart(report, reportYear);
            System.out.println();
        } catch (InputMismatchException e) {
            System.err.println("Wprowadziłeś błędne dane");
        }
    }

    private void generateReport7() {
        try {
            List<String> empList = employeeRangeGenerator();
            employeeRangePrinter(empList);
            System.out.println("Podaj imię i nazwisko pracownika");
            String name = sc.nextLine();
            if (empList.contains(name)) {
                List<String> dateList = dateRangeGenerator();
                dateRangePrinter(dateList);
                System.out.println("Podaj za jaki rok mam wygenerować raport");
                Integer reportYear = sc.nextInt();
                sc.nextLine();
                String year = reportYear.toString();
                if (dateList.contains(year)) {
                    reportBuilder = new Report4Builder(reportYear);
                    report = reportBuilder.buildReport(model);
                    Report7Builder report7 = new Report7Builder();
                    report7.plotChart(report, name, reportYear);
                    System.out.println();
                } else {
                    System.out.println("Nie można wygenerować raportu...  Wprowadź poprawny rok");
                }
            } else {
                System.out.println("Wprowadziłeś błędne Imię i Nazwisko");
            }
        } catch (InputMismatchException e) {
            System.err.println("Wprowadziłeś błędne dane");
        }
    }

    private void generateErrorsLog() {
        ScanErrorsHolder.printScanErrors();
        System.out.println();
    }

    private void saveReportToFile(Report report) {
        System.out.println("\nCzy chcesz zapisać raport do pliku T / N ?");
        try {
            String writeReportOpt = sc.nextLine();
            switch (writeReportOpt.toLowerCase()) {
                case "t": {
                    System.out.println("\nCzy chcesz otworzyć plik xls? T / N ?");
                    File generatedReport = reportToXls.exportToXls(report);
                    String reportPath = generatedReport.getCanonicalPath();
                    System.out.println("Poprawnie wygenerowano raport do pliku: " + reportPath);
                    String showXlsOpt = sc.nextLine();
                    switch (showXlsOpt.toLowerCase()) {
                        case "t": {
                            openGeneratedFile(generatedReport);
                        }
                        default:
                            break;
                    }
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

    private List<String> dateRangeGenerator() {
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
        return yearProject;
    }

    private List<String> employeeRangeGenerator() {
        List<String> employeeList = new ArrayList<>();
        List<Employee> allEmployeeData = model.getEmployeeList();
        for (Employee employee : allEmployeeData) {
            String nameAndSurname = employee.getNameAndSurname();
            employeeList.add(nameAndSurname);
        }
        return employeeList;
    }

    private void projectsRangeGenerator() {
        List<String> projects = new ArrayList<>();
        List<Employee> allEmployeeData = model.getEmployeeList();
        for (Employee employee : allEmployeeData) {
            Set<String> allProjects = employee.getProjects();
            for (String oneProject : allProjects) {
                if (!projects.contains(oneProject)) {
                    projects.add(oneProject);
                }
            }
        }
        System.out.println("\nRaporty są dostępne dla projektów: " + projects + "\n");
    }

    private void dateRangePrinter(List<String> dateList) {
        System.out.println("\nRaporty są dostępne za lata: " + dateList + "\n");
    }

    private void employeeRangePrinter(List<String> employeeList) {
        System.out.println("\nRaporty są dostępne dla pracowników: " + employeeList + "\n");
    }


    private void appHeaders() {
        System.out.println("______                _____  _                    _____                                \n"
                + "| ___ \\              |_   _|(_)                  |_   _|                               \n"
                + "| |_/ / _   _  _ __    | |   _  _ __ ___    ___    | |    ___  _ __  _ __   ___   _ __ \n"
                + "|    / | | | || '_ \\   | |  | || '_ ` _ \\  / _ \\   | |   / _ \\| '__|| '__| / _ \\ | '__|\n"
                + "| |\\ \\ | |_| || | | |  | |  | || | | | | ||  __/   | |  |  __/| |   | |   | (_) || |   \n"
                + "\\_| \\_| \\__,_||_| |_|  \\_/  |_||_| |_| |_| \\___|   \\_/   \\___||_|   |_|    \\___/ |_|   \n"
                + "                                                                                       \nversion 1.0.0");
        System.out.println("----------------------------\n");
    }

    public String inputUserOption() {
        System.out.println("\n______________________");
        System.out.print("Wprowadź wybraną opcję:");
        userOption = sc.nextLine();
        return userOption;
    }

    private void exit() {
        System.out.println("Copyright © 2020 RunTime Terror, All Rights Reserved. ");
        sc.close();
    }

    public void clearConsole() {
        //Clears Screen in java
        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                Runtime.getRuntime().exec("clear");
        } catch (IOException | InterruptedException ex) {
        }
    }
}
