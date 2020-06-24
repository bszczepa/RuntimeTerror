package App;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

public class App {
    public static void main(String[] args) {

        try {
            String folderPath = args[0];
            UserControl userControl = new UserControl(folderPath);
            userControl.controlLoop();
        } catch ( ArrayIndexOutOfBoundsException e) {
            System.out.println("Nie wprowadziłeś poprawnej ścieżki do folderu");
        }
    }
}