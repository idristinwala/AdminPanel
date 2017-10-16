package AdminPanel;

import com.relevantcodes.extentreports.ExtentReports;

public class ExtentFactory {
	public static ExtentReports getInstance(){
		ExtentReports extent;
		String Path = "C:\\Users\\idris.t\\Documents\\Java Docs\\reports\\overviewtab.html";
		extent = new ExtentReports(Path, false);
		extent
		.addSystemInfo("Selenium Version","3.4.0")
		.addSystemInfo("Platform", "Windows");
		return extent;
		
	}

}
