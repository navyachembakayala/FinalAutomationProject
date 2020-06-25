package com.attra.scan.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class WebSiteNavigation {
	WebDriver driver;
	/*
	 * Necessary information is provided about AUT (Test application - BodgeIt
	 * Store) URL, Logout_URL, Username, Password to be used for registration
	 */
	final static String BASE_URL = Base_Url();
	final static String uname1 = UserName();
	final static String passw1 = Password();

	/**********************************************/

	public static String Base_Url() {
		File file = new File("/root/Downloads/UserInputCredentials.xlsx");

		XSSFWorkbook wkbook;
		XSSFSheet sheet;

		try {
			FileInputStream excel = new FileInputStream(file);
			wkbook = new XSSFWorkbook(excel);
			sheet = wkbook.getSheet("Sheet1");
			String x = sheet.getRow(1).getCell(0).getStringCellValue();
			return x;
		} catch (IOException e) {
			// TODO: handle exception
		}
		return "Nothing";
	}

	public static String UserName() {
		File file = new File("/root/Downloads/UserInputCredentials.xlsx");

		XSSFWorkbook wkbook;
		XSSFSheet sheet;

		try {
			FileInputStream excel = new FileInputStream(file);
			wkbook = new XSSFWorkbook(excel);
			sheet = wkbook.getSheet("Sheet1");

			String z = sheet.getRow(1).getCell(1).getStringCellValue();
			return z;

		} catch (IOException e) {
			System.out.println(e);
		}
		return "Nothing";
	}

	public static String Password() {
		File file = new File("/root/Downloads/UserInputCredentials.xlsx");

		XSSFWorkbook wkbook;
		XSSFSheet sheet;

		try {
			FileInputStream excel = new FileInputStream(file);
			wkbook = new XSSFWorkbook(excel);
			sheet = wkbook.getSheet("Sheet1");
			String y = sheet.getRow(1).getCell(2).getStringCellValue();
			return y;
		} catch (IOException e) {
			System.out.println(e);
		}
		return "Nothing";
	}

	/*********************************************/
	/*
	 * Apply synchronization/wait techniques
	 */
	public WebSiteNavigation(WebDriver driver) {
		this.driver = driver;
		this.driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		this.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	/*
	 * Registration of a new user - Automate the steps to register a new user in the
	 * application - Selenium Webdriver commands are used to identify the elements
	 */
	/*
	 * public void registerNewUser() { --Commented by me
	 * driver.get(BASE_URL+"register.jsp");
	 * driver.findElement(By.id("username")).clear();
	 * driver.findElement(By.id("username")).sendKeys(USERNAME);
	 * driver.findElement(By.id("password1")).clear();
	 * driver.findElement(By.id("password1")).sendKeys(PASSWORD);
	 * driver.findElement(By.id("password2")).clear();
	 * driver.findElement(By.id("password2")).sendKeys(PASSWORD);
	 * driver.findElement(By.id("submit")).click(); } /* User navigates before Login
	 * - Automate the steps to navigate to pages without performing Login - Selenium
	 * Webdriver commands are used to identify the elements
	 */
	/*
	 * public void navigateBeforeLogin() { driver.get(BASE_URL);
	 * driver.findElement(By.linkText("Home")).click();
	 * driver.findElement(By.linkText("Doodahs")).click();
	 * driver.findElement(By.linkText("About Us")).click();
	 * driver.findElement(By.linkText("Your Basket")).click();
	 * driver.findElement(By.linkText("Search")).click();
	 * driver.findElement(By.name("q")).clear();
	 * driver.findElement(By.name("q")).sendKeys("test");
	 * driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
	 * verifyPresenceOfText("Results Found"); }
	 */
	/*
	 * User performs Login Operation - Automate the steps to perform login operation
	 * in the application - Selenium Webdriver commands are used to identify the
	 * elements
	 */
	public void loginAsUser() {
		System.out.println("Inside loginAs User--" + "The User name is--" + uname1 + "The password is " + passw1);
		driver.get(BASE_URL + "login.jsp");
		driver.findElement(By.id("uid")).clear();
		driver.findElement(By.id("uid")).sendKeys(uname1);
		driver.findElement(By.id("passw")).clear();
		driver.findElement(By.id("passw")).sendKeys(passw1);
		driver.findElement(By.name("btnSubmit")).click();
		// verifyPresenceOfText("successfully");
	}

}
