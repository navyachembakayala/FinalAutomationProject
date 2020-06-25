package com.attra.scan.test;

import org.apache.log4j.Logger;
import org.junit.*;
import org.openqa.selenium.WebDriver;

import com.attra.scan.browser.BrowserDriverFactoryDefectDojo;
import com.attra.scan.util.CustomProgressBar;

public class ZapSecurityTestDefectDojo {

	private WebDriver driver;
	static Logger log = Logger.getLogger(ZapSecurityTestDefectDojo.class.getName());
// Provide Chrome driver path
	private static final String BROWSER_DRIVER_PATH = "/root/Downloads/geckodriver";

	private WebSiteNavigationDefectDojo siteNavigation;
	public CustomProgressBar cpBar;

	@Before
	public void setUp() {
		// Create driver object
		driver = BrowserDriverFactoryDefectDojo.createChromeDriver(BROWSER_DRIVER_PATH);
		siteNavigation = new WebSiteNavigationDefectDojo(driver);
		cpBar=new CustomProgressBar();
	}

	/*
	 * Method to close the driver connection
	 */
	@After
	public void tearDown() {
		driver.quit();
	}

// ******************************************************* TESTS START FROM HERE **********************************
	@Test
	public void testVulnerabilitiesAfterLogin() throws Exception {
		cpBar.setVisible(true);
		cpBar.jb.setString("Uploading Scan report");
		log.info("Url loading started");
		siteNavigation.navigateBeforeLogin();
		log.info("Url loaded successfully");
		siteNavigation.loginAsUser();
		Thread.sleep(15000);
		siteNavigation.SelectProduct();
		siteNavigation.Product_Screen();
		siteNavigation.ImportScan();
		cpBar.setVisible(false);
	}
}
