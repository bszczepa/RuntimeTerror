package App;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import Model.Model;
import Report.Report5;

import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException, InvalidFormatException {
//        UserControl userControl = new UserControl();
//        userControl.controlLoop();
    	
    	
    	
    	Model model = new Model("D:/Users/aniam/Desktop/TERROR/RuntimeTerror/src/Resources/reporter-dane", null);
    	
    	Report5 report5 = new Report5();
    	report5.createReport(model.getEmployeeList());
    	
    	report5.printReport();
    }
}