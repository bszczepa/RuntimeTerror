package App;

import Model.Model;
import Reader.ScanErrorsHolder;
import Report.*;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import javax.sound.sampled.SourceDataLine;
import java.io.IOException;
import java.util.Scanner;

public class UserControl {

    private Scanner sc = new Scanner(System.in);
    private String userOption;
    private String path;
    private Model model;


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

    private void appHeaders(){
        System.out.println("----------------------------");
        System.out.println("Runtime Terror version 1.0.0");
        System.out.println("----------------------------");
    }

    private void generateReport4() throws InvalidFormatException, IOException{
        System.out.println();
        System.out.println("Podaj za jaki rok mam wygenerować raport");
        int reportYear = sc.nextInt();
        sc.nextLine();
        Report4 report = new Report4(model, reportYear);
        report.printReport();
        System.out.println();
    }

    private void generateReport5() throws InvalidFormatException, IOException{
        System.out.println();
        System.out.println("Podaj nazwę projektu");
        String projectName = sc.nextLine();
        Report5 report = new Report5(model, projectName);
        report.printReport();
        System.out.println();
    }

    private void generateReport1() throws InvalidFormatException, IOException{
        System.out.println("Podaj za jaki rok mam wygenerować raport");
        int reportYear = sc.nextInt();
        sc.nextLine();
        System.out.println();
        Report1 report1 = new Report1();
        report1.report(model, reportYear);
        ReportPrinter.printReport(report1);
        System.out.println();
    }


    private void generateReport2(){
        System.out.println("Podaj za jaki rok mam wygenerować raport");
        int reportYear = sc.nextInt();
        sc.nextLine();
        System.out.println();
        Report2 report2 = new Report2();
        report2.createReport2(model, reportYear);
        System.out.println();
    }

    private void generateReport3(){
        System.out.println("Podaj imię i nazwisko pracownika");
        String projectName = sc.nextLine();
        System.out.println("Podaj za jaki rok mam wygenerować raport");
        int reportYear = sc.nextInt();
        sc.nextLine();
        System.out.println();
        Report3 report3 = new Report3();
        report3.report(model, projectName, reportYear);
        report3.printReport();
        System.out.println();
    }
}
