package app;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import controller.UserControl;

public class App {
	public static void main(String[] args) throws IOException, InvalidFormatException {

		// USTAWCIE SOBIE ARGUMENT URUCHAMIANIA NA ŚCIEŻKĘ
		// src/main/resources/reporter-dane

		String folderPath = args[0];
		if (args[0] == null) {
			System.out.println("Nie wprowadziłeś ścieżki do folderu");
		}
		UserControl userControl = new UserControl(folderPath);
		userControl.controlLoop();

	}
}