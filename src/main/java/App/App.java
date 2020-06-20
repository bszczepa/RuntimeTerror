package App;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException, InvalidFormatException {
        UserControl userControl = new UserControl();
        userControl.controlLoop();
    }
}