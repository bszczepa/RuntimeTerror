package Reader;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class DataReaderTests {
	
	@Before
	public  void cleanScanErrors() {
		ScanErrorsHolder.clearScanErrors();
	}

	@Test
	public void testEachTypeOfScanErrorOneTime() throws InvalidFormatException, IOException {

		FilesScanner filesScanner = new FilesScanner();
		filesScanner.scanFiles("src/test/reporter-dane-z-bledami");
		List<ScanError> myScanErrors = new ArrayList<ScanError>();
		ScanError scanError1 = new ScanError("src\\test\\reporter-dane-z-bledami\\plik-o-zlej-nazwie.xls", "", "",
				"zła nazwa pliku!");
		myScanErrors.add(scanError1);

		ScanError scanError2 = new ScanError("src\\test\\reporter-dane-z-bledami\\ZLY-KATALOG\\Wisniewski_Marek.xls",
				"", "", "zła lokalizacja pliku!");
		myScanErrors.add(scanError2);

		ScanError scanError3 = new ScanError("src\\test\\reporter-dane-z-bledami\\2014\\05\\Kowalski_Jan.xls",
				"Projekt2", "Arkusz nie zawiera odpowiednich kolumn");
		myScanErrors.add(scanError3);

		ScanError scanError4 = new ScanError("src\\test\\reporter-dane-z-bledami\\2012\\03\\Nowak_Piotr.xls",
				"Projekt1", 11, "pusty wiersz!");
		myScanErrors.add(scanError4);
		ScanErrorsHolder.printScanErrors();

		ScanError scanerror5 = new ScanError("src\\test\\reporter-dane-z-bledami\\2012\\03\\Wisniewski_Marek.xls",
				"Projekt1", 11, "OPIS", "pusta komórka!");
		myScanErrors.add(scanerror5);

		ScanError scanerror6 = new ScanError("src\\test\\reporter-dane-z-bledami\\2012\\03\\Wisniewski_Marek.xls",
				"Projekt2", 10, "CZAS", "pusta komórka!");
		myScanErrors.add(scanerror6);

		ScanError scanerror7 = new ScanError("src\\test\\reporter-dane-z-bledami\\2012\\03\\Wisniewski_Marek.xls",
				"Projekt2", 19, "OPIS", "pusty opis w komórce!");
		myScanErrors.add(scanerror7);

		ScanError scanerror8 = new ScanError("src\\test\\reporter-dane-z-bledami\\2014\\05\\Wisniewski_Marek.xls",
				"Projekt2", 18, "DATA", "pusta komórka!");
		myScanErrors.add(scanerror8);

		ScanError scanerror9 = new ScanError("src\\test\\reporter-dane-z-bledami\\2014\\05\\Wisniewski_Marek.xls",
				"Projekt2", 23, "DATA", "komórka nie zawiera wartości numerycznej!");
		myScanErrors.add(scanerror9);

		ScanError scanerror10 = new ScanError("src\\test\\reporter-dane-z-bledami\\2012\\03\\Wojcik_Dawid.xls",
				"Projekt1", 21, "DATA", "zły rok w dacie!");
		myScanErrors.add(scanerror10);

		ScanError scanerror11 = new ScanError("src\\test\\reporter-dane-z-bledami\\2014\\05\\Wisniewski_Marek.xls",
				"Projekt1", 20, "DATA", "komórka nie zawiera wartości numerycznej!");
		myScanErrors.add(scanerror11);

		ScanError scanerror12 = new ScanError("src\\test\\reporter-dane-z-bledami\\2014\\05\\Wisniewski_Marek.xls",
				"Projekt1", 25, "DATA", "zły miesiąc w dacie!");
		myScanErrors.add(scanerror12);

		ScanError scanerror13 = new ScanError("src\\test\\reporter-dane-z-bledami\\2014\\05\\Wisniewski_Marek.xls",
				"Projekt2", 12, "CZAS", "komórka nie zawiera wartości numerycznej!");
		myScanErrors.add(scanerror13);

		ScanError scanerror14 = new ScanError("src\\test\\reporter-dane-z-bledami\\2014\\05\\Wisniewski_Marek.xls",
				"Projekt2", 22, "DATA", "komórka nie zawiera wartości numerycznej!");
		myScanErrors.add(scanerror14);

		Assert.assertEquals(ScanErrorsHolder.getScanErrors().size(), 14);
		Assert.assertTrue(ScanErrorsHolder.getScanErrors().containsAll(myScanErrors));

	}

	@Test
	public void testManyScanErrorsInManyFiles() throws InvalidFormatException, IOException {
		FilesScanner filesScanner = new FilesScanner();
		filesScanner.scanFiles("src/test/reporter-dane-z-wieloma-bledami");
		Assert.assertEquals(ScanErrorsHolder.getScanErrors().size(), 61);
	}

}
