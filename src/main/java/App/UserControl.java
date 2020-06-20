package App;

import Model.Model;
import Report.Report1;
import Report.Report3;
import Report.Report5;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.io.IOException;
import java.util.Scanner;

public class UserControl {

    private Scanner sc = new Scanner(System.in);
    private String userOption;
    private String path = "/Users/matys/IdeaProjects/agh/RuntimeTerror/src/Resources/reporter-dane/2012";
    private Model model = new Model(path,null);

    public UserControl() throws IOException, InvalidFormatException {
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
                    System.out.println();
                    System.out.println("Wybrałes opcje 2");
                    System.out.println();
                    break;
                case "3":
                    System.out.println();
                    System.out.println("Wybrałes opcje 3");
                    System.out.println();
                    break;
                case "4":
                    System.out.println();
                    System.out.println("Wybrałes opcje 4");
                    System.out.println();
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
        System.out.println("3. Szczegółowy wykaz pracy danego pracownika ");
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

    private void generateReport5(){
        System.out.println();
        System.out.println("---------------------------------------------------------");
        System.out.println("Raport");
        System.out.println("Szczegółowy wykaz pracy pracowników w danym projekcie");
        System.out.println("---------------------------------------------------------");
        Report5 report = new Report5(model);
        report.printReport();
        System.out.println("---------------------------------------------------------");
        System.out.println();
    }

    private void generateReport1(){
        System.out.println("Podaj za jaki rok mam wygenerować raport");
        int reportYear = sc.nextInt();
        sc.nextLine();
        System.out.println();
        System.out.println("---------------------------------------------------------");
        Report1 report1 = new Report1();
        report1.report(model, reportYear);
        System.out.println("---------------------------------------------------------");
        System.out.println();
    }
}
