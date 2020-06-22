package App;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

public class App {
    public static void main(String[] args) throws IOException, InvalidFormatException {

        String folderPath = args[0];
        if (args[0] == null) {
            System.out.println("Nie wprowadziłeś ścieżki do folderu");
        }
        UserControl userControl = new UserControl(folderPath);
        userControl.controlLoop();    	
        
    }
}