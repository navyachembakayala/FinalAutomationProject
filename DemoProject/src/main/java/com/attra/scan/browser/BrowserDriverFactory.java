package com.attra.scan.browser;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
public class BrowserDriverFactory {
// Make reference variable for WebDriver
static WebDriver driver;
public static WebDriver createChromeDriver(Proxy proxy, String path) {
	FirefoxOptions options = new FirefoxOptions();
	options.setHeadless(true);	
   
	System.setProperty("webdriver.gecko.driver", path);
        return new FirefoxDriver(options);    
//        return new FirefoxDriver();
}
}