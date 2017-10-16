package page.classes;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import AdminPanel.GenericMethods;


public class Overview {
	
	WebDriver driver;
	ExtentTest test;
	public static WebElement element = null ;
	private static GenericMethods gm ;
	private static final Logger log = LogManager.getLogger(Overview.class.getName());
	
	public Overview(WebDriver driver, ExtentTest test){
		this.driver = driver;
		this.test= test;
		PageFactory.initElements(driver, this);
		log.error("Error Message Logged");
		log.debug("debug logged");
	}
	
	@FindBy(xpath="//a[@ng-click='save()']")
	WebElement TeamnameSaveButton;
	
	@FindBy(xpath="//div[@id='team-info']/ul/li[3]/span[3]/button")
	WebElement InviteButton;
	
	@FindBy(id="member_textarea")
	WebElement Enteremail;
	
	@FindBy(id="addButton")
	WebElement Addemail;
	
	@FindBy(xpath="//div[@id='ngdialog2']")
	WebElement Close;
	
	public static WebElement teamNameEditButton (WebDriver driver) {
		gm = new GenericMethods(driver);
		element = gm.getElement("//div[@id='team-info']/ul/li[1]/span[3]/a", "xpath");
		element.click();
	
		return element;
		
	}
	
	public static WebElement teamURLEditButton (WebDriver driver) {
		element = driver.findElement(By.xpath("//div[@id='team-info']/ul/li[2]/span[3]/a[1]"));
		element.click();
		return element;
	}
	
	public void clickSaveButton(){
		TeamnameSaveButton.click();
		test.log(LogStatus.INFO, "Clicked on save button");
		
	}
	
	public void clickInviteButton(){
		InviteButton.click();
		test.log(LogStatus.INFO, "Clicked on Invite Button");
	}
	
	public void Typeemail(String email){
		Enteremail.sendKeys(email);
		test.log(LogStatus.INFO, "Entered email address");
	}
	
	public void clickAddemail(){
		Addemail.click();
		test.log(LogStatus.INFO, "Clicked Add button");
	}
	
	public void clickOverlay(){
		
		if(Close.isDisplayed()){
			System.out.println("Element present");
			Actions action = new Actions(driver);
			action.moveToElement(Close, 50, 50).click().build().perform();
			test.log(LogStatus.INFO, "Team URL changed successfully");
			
		}else{
			System.out.println("Element close button not present");
		}
		
	}

}
