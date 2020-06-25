package com.attra.scan.test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebSiteNavigationDefectDojo {
	static Logger log = Logger.getLogger(WebSiteNavigationDefectDojo.class.getName());
	WebDriver driver;
	    final static String BASE_URL = "http://localhost:8080/login?next=/";
	    final static String CRT_PRD_URL = "http://localhost:8080/dashboard";
	    final static String ADD_PRD_URL = "http://localhost:8080/product/add";
	    final static String PRODUCT_SCREEN = "http://localhost:8080/product/3";
	    final static String IMPORT_SCAN_RESULT = "http://localhost:8080/product/2/import_scan_results";
	    final static String USERNAME = "admin";
	    final static String PASSWORD = "kSVbFyzIZPz60WRgezcxG4";
	    
	    public WebSiteNavigationDefectDojo(WebDriver driver) {
	        this.driver = driver;
	        this.driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
	        this.driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
	    }
	   
	    /*
	     * User navigates before Login
	     *  - Automate the steps to navigate to pages without performing Login
	     *   - Selenium Webdriver commands are used to identify the elements 
	     */
	    public void navigateBeforeLogin() {
	    	driver.manage().window().maximize();
	        driver.get(BASE_URL);
	    }
	    /*
	     * User performs Login Operation
	     *  - Automate the steps to perform login operation in the application
	     *   - Selenium Webdriver commands are used to identify the elements 
	     */
	    
		public void loginAsUser() throws Exception {    
	        driver.findElement(By.id("id_username")).clear();
	        driver.findElement(By.id("id_username")).sendKeys(USERNAME);
	        driver.findElement(By.id("id_password")).clear();
	        driver.findElement(By.id("id_password")).sendKeys(PASSWORD);
	        driver.findElement(By.cssSelector(".btn-success")).click();
	        
	   }
	    public void SelectProduct() throws Exception {
	    	log.info("before wait");
	    WebDriverWait wait1=new WebDriverWait(driver, 20);
	    WebElement SelectPrd=wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/nav/div[2]/div/ul/li[2]/a/i")));
 	    Actions actions = new Actions(driver);
 	   log.info("before wait 2");
 	    actions.moveToElement(SelectPrd).build().perform();
 	    log.info("before wait 3");
// 	    WebDriverWait wait2=new WebDriverWait(driver, 20);
// 	    WebElement SelectCreatedPrd=wait2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[text()='All Products']")));
//	    Actions actions1=new Actions(driver);
// 	    actions1.moveToElement(SelectCreatedPrd).build().perform();
 	    driver.findElement(By.xpath("/html/body/div[1]/nav/div[2]/div/ul/li[2]/ul/li[2]/a")).click();
 	    Thread.sleep(3000);
	    }
	    public String GetDateinJava() {
	    	DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
	    	Date date1 = new Date();
	    	String date2 = dateFormat.format(date1);
	    	return date2;
	    }
	    
 	    public void Product_Screen() throws Exception{
 	    	//driver.get(PRODUCT_SCREEN);
 	    	//Thread.sleep(5000);
 	    	WebDriverWait wait=new WebDriverWait(driver, 20); 	    	
 	    	WebElement SelectCreatedPrd = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/div/div/div[3]/div/div/div/div[3]/div/table/tbody/tr/td[2]/a/b")));
 	    	Actions actions1 = new Actions(driver);
 	    	actions1.moveToElement(SelectCreatedPrd).click().build().perform();
 	    	log.info("before wait 4");
// 	    	Thread.sleep(5000);
 	    	WebDriverWait wait1=new WebDriverWait(driver, 20);
 	    	WebElement SelectFindings = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/div[1]/div/ul/li[4]/a/span[2]")));
 	    	Actions actions2 = new Actions(driver);
 	    	actions2.moveToElement(SelectFindings).click().build().perform();
 	    	log.info("before wait 5");
 	    	Thread.sleep(5000);
 	        driver.findElement(By.xpath("/html/body/div/div/div/div[1]/div/ul/li[4]/ul/li[10]/a")).click();
 	      
 	       log.info("before wait 6");
 	    Thread.sleep(3000);
 	    }

	    
	    public void ImportScan() throws Exception  {
	    	WebDriverWait wait=new WebDriverWait(driver, 20);
		 	//Select PRTY = new Select(driver.findElement(By.name("minimum_severity")));
	    	Select PRTY = new Select(driver.findElement(By.xpath("//*[@id=\"id_minimum_severity\"]")));
	    	driver.findElement(By.xpath("//*[@id=\"id_minimum_severity\"]")).click();
			//PRTY.selectByVisibleText("Info");
			driver.findElement(By.xpath("//*[@id=\"id_active\"]")).click();
			Select SCAN = new Select(driver.findElement(By.name("scan_type")));
			SCAN.selectByVisibleText("ZAP Scan");
			driver.findElement(By.id("id_file")).sendKeys("/root/Pictures/report.xml");
//			WebDriverWait wait2=new WebDriverWait(driver, 20);
// 	    	WebElement TestResultScreen = wait2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/div/div/div[3]/div/form/div[10]/div/button")));
// 	    	Actions actions2 = new Actions(driver);
// 	    	actions2.moveToElement(TestResultScreen).click().build().perform();
			driver.findElement(By.xpath("/html/body/div[1]/div/div/div[3]/div/form/div[10]/div/button")).click();
			Thread.sleep(3000);
			log.info("import successful");
			WebDriverWait wait3=new WebDriverWait(driver, 20);
		    WebElement TestScreenResult=wait3.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/div[1]/div/ul/li[3]/a/span[2]")));
			Thread.sleep(15000);
			
			

		    }

		
	    }
	