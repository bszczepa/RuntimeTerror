package App;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import Model.Model;
import Report.Report4;
import Report.Report5;

import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException, InvalidFormatException {
        UserControl userControl = new UserControl();
        userControl.controlLoop();
        
//    	Model model = new Model("reporter-dane", null);    // drugim parametrem jest nazwisko_imie
//    													   //  jeżeli jest null to znaczy ze przeglądamy wszystkie pliki
//    	
//    	
//    	
//    	Report4 report = new Report4(model);
//    	
//    	report.printReport();
    }
}