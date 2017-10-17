package AdminPanel;

import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
//import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import page.classes.Overview;

//import com.gargoylesoftware.htmlunit.javascript.host.Set;
/*@Listeners(CustomListener1.class)*/
public class OverviewTab {
	// public String baseUrl =
	// "https://admin.flock-staging.com/?config=preprod&guid=cgbblb87cvi5l5g5&token=0qnn0avg0g2380qunn0cavauvvqua00g";
	WebDriver driver;
	private GenericMethods gm;
	ManageTeamTab mtt;
	Overview overview;
	private static final Logger log = LogManager.getLogger(OverviewTab.class.getName());
	ExtentReports report;
	ExtentTest test;
	XSSFWorkbook ExcelWBook;
	XSSFSheet ExcelWSheet;
	XSSFCell Cell;
	String path = "C:\\Users\\idris.t\\Downloads\\emaid id.xls";
	String sheetName = "Sheet1";
	/*
	 * public String nodeURL1 = "http://172.16.181.33:5555/wd/hub"; public
	 * String nodeURL2 = "http://172.16.138.73:5555/wd/hub";
	 */

	/*
	 * @DataProvider(name="email ids") public Object[][] getEmail(){ return new
	 * Object[][]{ {"test50idristinwala" }, {"test-50@idristinwala.com"}
	 * 
	 * 
	 * };
	 * 
	 * }
	 */

	@BeforeClass(alwaysRun = true)
	@Parameters({ "browser" })
	public void setBaseURL(String browser) throws InterruptedException {
		String baseUrl = "https://admin.flock-staging.com/?config=preprod&guid=cgbblb87cvi5l5g5&token=0qnn0avg0g2380qunn0cavauvvqua00g";
		report = ExtentFactory.getInstance();
		test = report.startTest("verify Overview Tab");
		if (browser.equalsIgnoreCase("firefox")) {
			// if(node.equals("172.16.181.33")){
			String driverPath = "C:\\\\Users\\idris.t\\Documents\\Java Docs\\geckodriver.exe";
			System.setProperty("webdriver.gecko.driver", driverPath);
			ProfilesIni profile = new ProfilesIni();
			FirefoxProfile fxProfile = profile.getProfile("seleniumTest");
			driver = new FirefoxDriver(fxProfile);
			/*
			 * DesiredCapabilities caps = DesiredCapabilities.firefox();
			 * caps.setBrowserName("firefox");
			 * caps.setPlatform(Platform.WINDOWS); driver = new
			 * RemoteWebDriver(new URL(nodeURL1), caps);
			 */
			// EventFiringWebDriver eDriver = new EventFiringWebDriver(driver);

			test.log(LogStatus.INFO, "Firefox Browser Started...");
			/*
			 * }else if(node.equalsIgnoreCase("172.16.138.73")){ String
			 * driverPath =
			 * "C:\\\\Users\\idris.t\\Documents\\Java Docs\\geckodriver.exe";
			 * System.setProperty("webdriver.gecko.driver", driverPath);
			 * DesiredCapabilities caps = DesiredCapabilities.firefox();
			 * caps.setBrowserName("firefox");
			 * caps.setPlatform(Platform.WINDOWS); driver = new
			 * RemoteWebDriver(new URL(nodeURL2), caps); }
			 */
		} else if (browser.equalsIgnoreCase("chrome")) {
			String driverPathchrome = "C:\\\\Users\\idris.t\\Documents\\Java Docs\\chromedriver.exe";
			System.setProperty("webdriver.chrome.marionette", driverPathchrome);
			/*
			 * ChromeOptions options = new ChromeOptions();
			 * options.addExtensions(new File(
			 * "C:\\Users\\idris.t\\AppData\\Local\\Google\\Chrome\\User Data\\Default\\Extensions\\eaelpbcbablcdbbocfbfnedfmgjaoicj\\2.1.0.109_0.crx"
			 * ));
			 */
			driver = new ChromeDriver();
			/*
			 * DesiredCapabilities caps = DesiredCapabilities.chrome();
			 * caps.setBrowserName("chrome");
			 * caps.setPlatform(Platform.WINDOWS); driver = new
			 * RemoteWebDriver(new URL(nodeURL2), caps);
			 */
			test.log(LogStatus.INFO, "Chrome Browser Started...");
		}

		/*
		 * String driverPath =
		 * "C:\\\\Users\\idris.t\\Documents\\Java Docs\\chromedriver.exe";
		 * System.setProperty("webdriver.chrome.marionette", driverPath); driver
		 * = new ChromeDriver();
		 */
		gm = new GenericMethods(driver);
		overview = new Overview(driver, test);
		driver.get(baseUrl);
		test.log(LogStatus.INFO, "URL opened");
		driver.manage().window().maximize();
		test.log(LogStatus.INFO, "Browser Maximized");
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		log.error("Error Message Logged");
		log.debug("debug logged");

		Thread.sleep(2000);
	}

	@Test(groups = { "non-ngdialog" })
	public void verifyUserDetails() {
		SoftAssert sa = new SoftAssert();
		String profilename = gm.getElementString("profile-name", "classname");
		// profilename.getText();
		// String profilename =
		// driver.findElement(By.className("profile-name")).getText();
		sa.assertEquals(profilename, "Flock.selenium Test");
		String profileemail = driver.findElement(By.className("profile-email")).getText();
		sa.assertEquals(profileemail, "flock.selenium@mailinator.com");
		sa.assertAll();

	}

	@Test(groups = { "non-ngdialog" })
	public void verifyAdmintag() {
		boolean admintag = gm.isElementPresent("admin-tag", "classname");
		// Boolean admintag =
		// driver.findElement(By.className("admin-tag")).isDisplayed();
		if (admintag) {
			WebElement admintags = gm.getElement("admin-tag", "classname");
			boolean at = admintags.isDisplayed();
			if (at) {
				test.log(LogStatus.INFO, "Admin Tag Displayed");
			} else {
				test.log(LogStatus.ERROR, "Admin Tag Absent");
			}
		} else {
			Assert.fail("admin Tag missing");
			test.log(LogStatus.FAIL, "Admin Tag is missing");
		}
	}

	@Test(groups = { "non-ngdialog" })
	public void verifyTeamname() throws InterruptedException {
		Overview.teamNameEditButton(driver); // ------- > Page Object Modal
		// WebElement edit =
		// gm.getElement("//div[@id='team-info']/ul/li[1]/span[3]/a", "xpath");
		// WebElement edit =
		// driver.findElement(By.xpath(".//*[@id='team-info']/ul/li[1]/span[3]/a"));

		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.xpath(".//*[@id='team-info']/ul/li[1]/span[2]/span/input")))
				.doubleClick().build().perform();
		Thread.sleep(1000);
		WebElement teamnamefield = driver.findElement(By.xpath(".//*[@id='team-info']/ul/li[1]/span[2]/span/input"));
		teamnamefield.sendKeys("Webdriver");
		Thread.sleep(2000);
		overview.clickSaveButton(); // ---------> Page Factory

		/*
		 * WebElement save =
		 * driver.findElement(By.xpath("//a[@ng-click='save()']"));
		 * save.click();
		 */

		// WebDriverWait wait = new WebDriverWait(driver, 3);
		// String teamname =
		// wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='team-info']/ul/li[1]/span[2]/span/span[1]"))).getText();
		String teamname = gm.waitForElement(By.xpath(".//*[@id='team-info']/ul/li[1]/span[2]/span/span[1]"), 3);
		Assert.assertEquals(teamname, "Webdriver");
		test.log(LogStatus.PASS, "Teamname changed successfully");
		Overview.teamNameEditButton(driver);
		action.moveToElement(driver.findElement(By.xpath(".//*[@id='team-info']/ul/li[1]/span[2]/span/input")))
				.doubleClick().build().perform();
		Thread.sleep(1000);
		teamnamefield.sendKeys("Idris");
		Thread.sleep(2000);
		overview.clickSaveButton();
		Thread.sleep(2000);

	}

	@Test(groups = { "ngdialog" }, dependsOnMethods = { "zAddusers" })
	public void verifyTeamURL() throws InterruptedException {
		Overview.teamURLEditButton(driver);
		// String parentHandle = driver.getWindowHandle();
		// WebElement editURL =
		// driver.findElement(By.xpath(".//*[@id='team-info']/ul/li[2]/span[3]/a[1]"));
		// editURL.click();
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.xpath(".//*[@id='team-info']/ul/li[2]/span[2]/span/input")))
				.doubleClick().build().perform();
		Thread.sleep(1000);
		WebElement teamURLfield = driver.findElement(By.xpath(".//*[@id='team-info']/ul/li[2]/span[2]/span/input"));
		teamURLfield.sendKeys("Webdriver3");
		Thread.sleep(2000);
		WebElement saveURL = driver.findElement(By.xpath(".//*[@id='team-info']/ul/li[2]/span[2]/span/span[7]/a[1]"));
		saveURL.click();
		Thread.sleep(4000);
		WebElement confirm = driver.findElement(By.xpath("//div[@id='ngdialog4']//button[@id='confirm']"));
		confirm.click();
		Thread.sleep(2000);

		// WebDriverWait wait = new WebDriverWait(driver, 3);
		String teamname = gm.waitForElement(By.xpath(".//*[@id='team-info']/ul/li[2]/span[2]/span/span[1]"), 3);
		Assert.assertEquals(teamname, "webdriver3.flock.com");
		test.log(LogStatus.PASS, "Team URL changed successfully");

		Overview.teamURLEditButton(driver);
		action.moveToElement(driver.findElement(By.xpath(".//*[@id='team-info']/ul/li[2]/span[2]/span/input")))
				.doubleClick().build().perform();
		Thread.sleep(1000);
		teamURLfield.sendKeys("flockadmintest");
		Thread.sleep(2000);
		saveURL.click();
		Thread.sleep(2000);
		WebElement confirm_2 = driver.findElement(By.xpath("//div[@id='ngdialog6']//button[@id='confirm']"));
		confirm_2.click();
		Thread.sleep(2000);
	}

	@Test(priority = 6)
	public void sidebar() throws InterruptedException {
		WebElement sidebar = driver.findElement(By.id("sidebar"));
		List<WebElement> results = sidebar.findElements(By.tagName("li"));
		int size = results.size();
		System.out.println("Size of the list is: " + size);
		for (int i = 0; i < size; i++) {
			System.out.println(results.get(i).getText());
		}
		Thread.sleep(2000);
		for (WebElement result : results) {
			if (result.getText().equals("Enterprise")) {
				System.out.println("Enterprise tab visible");
				test.log(LogStatus.PASS, "Enterprise tab visible");
			} else {
				System.out.println("Enterprise Tab not visible");
				test.log(LogStatus.FAIL, "Enterprise tab not visible");
				break;
			}
		}
	}

	@Test(groups = { "ngdialog" }) // , dataProvider="email ids")
	public void zAddusers() throws InterruptedException {

		/*
		 * try { FileInputStream ExcelFile = new FileInputStream(path);
		 * ExcelWBook = new XSSFWorkbook(ExcelFile); ExcelWSheet =
		 * ExcelWBook.getSheet(sheetName);
		 * 
		 * Cell = ExcelWSheet.getRow(0).getCell(0); String cellData =
		 * Cell.getStringCellValue();
		 */
		overview.clickInviteButton();
		overview.Typeemail("micka3@sigh.info");
		overview.clickAddemail();
		Thread.sleep(3000);
		overview.clickOverlay();
		test.log(LogStatus.INFO, "Closing Add user modal");
		Thread.sleep(2000);
		/*
		 * } catch (Exception e) { e.printStackTrace(); }
		 */
	}

	@Test(groups = { "non-ngdialog" })
	public void OverviewLinks() {
		List<WebElement> linksList = GenericMethods.clickableLinks(driver);
		for (WebElement link : linksList) {
			String href = link.getAttribute("href");
			try {
				System.out.println("URL " + href + " returned " + GenericMethods.linkStatus(new URL(href)));
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}

	/*
	 * @AfterClass(alwaysRun=true) public void endSession() { driver.quit();
	 * report.endTest(test); report.flush(); }
	 */
}
