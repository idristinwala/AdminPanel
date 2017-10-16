package AdminPanel;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.AfterClass;
//import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
//import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import page.classes.Overview;

public class ManageTeamTab {

	public String baseUrl = "https://admin.flock-staging.com/?config=preprod&guid=cgbblb87cvi5l5g5&token=0qnn0avg0g2380qunn0cavauvvqua00g";
	// public String nodeURL1 = "http://172.16.181.33:5555/wd/hub";
	public String nodeURL2 = "http://172.16.138.73:4444/wd/hub";

	public WebDriver driver;
	private GenericMethods gm;
	Overview overview;
	private static final Logger log = LogManager.getLogger(OverviewTab.class.getName());
	ExtentReports report;
	ExtentTest test;

	@BeforeClass(alwaysRun = true)
	public void setBaseURL() throws InterruptedException, MalformedURLException {

		String driverPath = "C:\\\\Users\\idris.t\\Documents\\Java Docs\\chromedriver.exe";
		report = ExtentFactory.getInstance();
		test = report.startTest("verify Manage Team Tab");
		System.setProperty("webdriver.chrome.marionette", driverPath);
		DesiredCapabilities caps = DesiredCapabilities.chrome();
		caps.setBrowserName("chrome");
		caps.setPlatform(Platform.WINDOWS);
		driver = new RemoteWebDriver(new URL(nodeURL2), caps);
		driver.get(baseUrl);
		EventFiringWebDriver eDriver = new EventFiringWebDriver(driver);
		LogEvent he = new LogEvent();
		eDriver.register(he);
		gm = new GenericMethods(driver);
		overview = new Overview(driver, test);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		log.error("Error Message Logged");
		log.debug("debug logged");
	}

	@Test(groups = { "manage" })
	public void ClickManageTeamTab() {
		WebElement element = gm.getElement("usersTab", "id");
		element.click();
		test.log(LogStatus.PASS, "Clicked on Manage Team Tab");
	}

	@AfterClass(alwaysRun = true)
	public void endSession() {
		driver.quit();
		report.endTest(test);
		report.flush();
	}
}
