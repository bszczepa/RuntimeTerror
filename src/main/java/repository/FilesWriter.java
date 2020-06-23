package repository;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.poi.ss.usermodel.Workbook;

public class FilesWriter {
	public static File writeToFile(Workbook wb) throws IOException, FileNotFoundException {
		try (OutputStream fileOut = new FileOutputStream("generated-reports/report.xls")) {
			wb.write(fileOut);
			File file = new File("generated-reports/report.xls");
			if (file.exists())
				return file;
		}
		return null;
	}
}
