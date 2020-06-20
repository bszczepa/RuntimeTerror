package App;

import java.util.Scanner;

public class UserControl {

    private Scanner sc = new Scanner(System.in);
    private String userOption;


    public void controlLoop() {
        do {
            showOption();
            String userOption = inputUserOption();
            switch (userOption) {
                case "1":
                    System.out.println();
                    System.out.println("Wybrałes opcje 1");
                    System.out.println();
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
                    System.out.println();
                    System.out.println("Wybrałes opcje 5");
                    System.out.println();
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
        System.out.println("5. Procentowy udział danego pracownika w projekt za dany rok");
        System.out.println("6. Szczegółowy wykaz pracy pracowników w danym projekcie");
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


}
