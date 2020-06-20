package App;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import Model.Model;
import Report.Report5;

import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException, InvalidFormatException {
        UserControl userControl = new UserControl();
        userControl.controlLoop();

        /**
         * w UserControl musicie sobie zmienić ścieżke do folderu z danymi zeby ruszylo
         * na sztywno wpisalem gdyby klient chciał zobaczyć przykladowy raport
         * działa 1 i 5
         */

//    	Model model = new Model("TU WPISAC SCIEZKE DO PLIKOW", null);    // drugim parametrem jest nazwisko_imie
//    																	//  jeżeli jest null to znaczy ze przeglądamy wszystkie pliki
//    	
//    	Report5 report = new Report5(model);
//    	
//    	report.printReport();
    }
}