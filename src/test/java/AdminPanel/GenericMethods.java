package AdminPanel;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
public class GenericMethods {
	
	
	WebDriver driver;
	
	public GenericMethods(WebDriver driver){
		this.driver = driver;
		EventFiringWebDriver eDriver = new EventFiringWebDriver(driver);
		LogEvent he = new LogEvent();
		eDriver.register(he);
		
	}
	
	public WebElement getElement(String locator, String type){
		EventFiringWebDriver eDriver = new EventFiringWebDriver(driver);
		LogEvent he = new LogEvent();
		eDriver.register(he);
		type = type.toLowerCase();
		if(type.equals("id")){
			
			System.out.println("Element found with id: " + locator);
			return this.driver.findElement(By.id(locator));
		}
		else if (type.equals("xpath")){
			System.out.println("Element found with xpath: " + locator);
			return this.driver.findElement(By.xpath(locator));
			
		}
		else if(type.equals("classname")){
			System.out.println("Element found with className: " + locator);
			return this.driver.findElement(By.className(locator));
		}
		else if (type.equals("css")) {
			System.out.println("Element found with xpath: " + locator);
			return this.driver.findElement(By.cssSelector(locator));
		}
		else if (type.equals("linktext")) {
			System.out.println("Element found with xpath: " + locator);
			return this.driver.findElement(By.linkText(locator));
		}
		else if (type.equals("partiallinktext")) {
			System.out.println("Element found with xpath: " + locator);
			return this.driver.findElement(By.partialLinkText(locator));
		}
		else{
			System.out.println("Locator type not support");
			return null;
		}
	}
	
	public String getElementString(String locator, String type){
		type = type.toLowerCase();
		if(type.equals("id")){
			System.out.println("Element found with id: " + locator);
			String text = this.driver.findElement(By.id(locator)).getText();
			return text;
		}
		else if (type.equals("xpath")){
			System.out.println("Element found with xpath: " + locator);
			String text = this.driver.findElement(By.xpath(locator)).getText();
			return text;
		}
		else if(type.equals("classname")){
			System.out.println("Element found with className: " + locator);
			String text = this.driver.findElement(By.className(locator)).getText();
			return text;
			
		}
		else if (type.equals("css")) {
			System.out.println("Element found with xpath: " + locator);
			String text =  this.driver.findElement(By.cssSelector(locator)).getText();
			return text;
		}
		else if (type.equals("linktext")) {
			System.out.println("Element found with xpath: " + locator);
			String text = this.driver.findElement(By.linkText(locator)).getText();
			return text;
		}
		else if (type.equals("partiallinktext")) {
			System.out.println("Element found with xpath: " + locator);
			String text = this.driver.findElement(By.partialLinkText(locator)).getText();
			return text;
		}
		else{
			System.out.println("Locator type not support");
			return null;
		}
	}
	
	public List<WebElement> getElements(String locator, String type){
		type = type.toLowerCase();
		List<WebElement> elementList = new ArrayList<WebElement>();
		if(type.equals("id")){
			
			elementList =  this.driver.findElements(By.id(locator));
		}
		else if (type.equals("xpath")){
			
			elementList = this.driver.findElements(By.xpath(locator));
		}
		else if(type.equals("classname")){
			
			elementList = this.driver.findElements(By.className(locator));
		}
		else if (type.equals("css")) {
			
			elementList = this.driver.findElements(By.cssSelector(locator));
		}
		else if (type.equals("linktext")) {
			
			elementList = this.driver.findElements(By.linkText(locator));
		}
		else if (type.equals("partiallinktext")) {
			
			elementList = this.driver.findElements(By.partialLinkText(locator));
		}
		else{
			System.out.println("Locator type not support");
			return null;
		}
		if (elementList.isEmpty()){
			System.out.println("Element not found with " + type + ": " + locator);
		} else {
			System.out.println("Element found with " + type + ": " + locator);
		}
		return elementList;
	}

	public boolean isElementPresent(String locator, String type){
		List<WebElement> elementList = getElements(locator, type);
		
		int size = elementList.size();
		
		if (size > 0){
			return true;
		}
		else {
			return false;
		}
	}
	
	public String waitForElement(By locator, int timeout){
		String element = null ;
		try{
			System.out.println("Waiting for max:: " + timeout + " seconds for element to be available");
			WebDriverWait wait = new WebDriverWait(driver, 3); //
			  element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).getText();
			  System.out.println("Element appeared on the web page");
		}catch(Exception e){
			System.out.println("Element not appeared on the web page");
		}
		return element;
	}
	
	public void clickWhenReady(By locator, int timeout){
		
		try{
			WebElement element = null ;
			System.out.println("Waiting for max:: " + timeout + " seconds for element to be available");
			WebDriverWait wait = new WebDriverWait(driver, 3); //
			  element = wait.until(ExpectedConditions.elementToBeClickable(locator));
			  element.click();
			  System.out.println("Element clicked on the web page");
		}catch(Exception e){
			System.out.println("Element not appeared on the web page");
		}
		
	}
	
	public static List<WebElement> clickableLinks(WebDriver driver) {
		List<WebElement> linksToClick = new ArrayList<WebElement>();
		List<WebElement> elements = driver.findElements(By.tagName("a"));
		elements.addAll(driver.findElements(By.tagName("img")));
		
		for (WebElement e : elements) {
			if (e.getAttribute("href") != null) {
				linksToClick.add(e);
			}
		}
		return linksToClick;
	}
	
	public static String linkStatus(URL url) {
		// http://download.java.net/jdk7/archive/b123/docs/api/java/net/HttpURLConnection.html#getResponseMessage%28%29
		try {
			HttpURLConnection http = (HttpURLConnection) url.openConnection();
			http.connect();
			String responseMessage = http.getResponseMessage();
			http.disconnect();
			return responseMessage;
		}
		catch (Exception e) {
			return e.getMessage();
		}
	}
	
}



