package repository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import model.ScanError;
import services.ScanErrorsHolder;

public class FilesFinder {

	private List<File> foundFiles;

	public List<File> findFiles(String path) throws IOException, InvalidFormatException {
		File masterDirectory = new File(path);
		masterDirectory.getCanonicalPath();
		foundFiles = (List<File>) FileUtils.listFiles(masterDirectory, new String[] { "xls", "xlsx" }, true);
		filterFiles();
		return this.foundFiles;
	}

	private void filterFiles() throws InvalidFormatException, IOException {

		List<File> filteredFiles = new ArrayList<File>();
		for (File file : foundFiles) {
			String filename = file.getName().substring(0, file.getName().indexOf("."));
			if (!filename.matches("[A-z]+_[A-z]+")) {
				ScanErrorsHolder.addScanError(new ScanError(file.getPath(), "", "", "zła nazwa pliku!"));
				continue;
			}
			filteredFiles.add(file);
		}
		foundFiles = filteredFiles;
	}

}