package com.attra.scan.test;

import java.io.File;
import java.io.FileOutputStream;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.zaproxy.clientapi.core.ApiResponse;
import org.zaproxy.clientapi.core.ApiResponseElement;
import org.zaproxy.clientapi.core.ClientApi;
import org.zaproxy.clientapi.core.ClientApiException;

import com.attra.scan.browser.BrowserDriverFactory;
import com.attra.scan.test.WebSiteNavigation;
import com.attra.scan.util.CustomProgressBar;

public class ZapSecurityTest {
	/*
	 * Provide details about ZAP Proxy
	 */
	static Logger log = Logger.getLogger(ZapSecurityTest.class.getName());
	private static final String ZAP_PROXYHOST = "localhost";
	private static final int ZAP_PROXYPORT = 8098;
	// Provide Chrome driver path
	private static final String BROWSER_DRIVER_PATH = "/root/Downloads/geckodriver";

	private WebDriver driver;
	private ClientApi zapClientAPI;
	private WebSiteNavigation siteNavigation;

	private CustomProgressBar cpBar;

	/**
	 * Create ZAP proxy by specifying proxy host and proxy port
	 * 
	 * @return Proxy
	 */

	private static Proxy createZapProxyConfiguration() {
		Proxy proxy = new Proxy();
		proxy.setHttpProxy(ZAP_PROXYHOST + ":" + ZAP_PROXYPORT);
		proxy.setSslProxy(ZAP_PROXYHOST + ":" + ZAP_PROXYPORT);
		return proxy;
	}

	/*
	 * Method to configure ZAP scanner, API client and perform User Registration
	 */
	@Before
	public void setUp() {

		// Create ZAP API client
		zapClientAPI = new ClientApi(ZAP_PROXYHOST, ZAP_PROXYPORT);
		log.info("Created client to ZAP API");

		// Create driver object
		driver = BrowserDriverFactory.createChromeDriver(createZapProxyConfiguration(), BROWSER_DRIVER_PATH);
		siteNavigation = new WebSiteNavigation(driver);

		// Creating CustomProgressBar object
		cpBar = new CustomProgressBar();
		log.info("Setup conpleted");
	}

	/*
	 * Method to close the driver connection
	 */
	@After
	public void tearDown() {
		driver.quit();
	}

	/*
	 * Method to configure spider settings, execute ZAP spider, log the progress and
	 * found URLs
	 */
	public void spiderWithZap() throws ClientApiException {
		log.info("Spidering started");
		ApiResponse resp = zapClientAPI.spider.scan(WebSiteNavigation.BASE_URL, "", "", "", "");

		String scanid = ((ApiResponseElement) resp).getValue();

		int progressPercent = 0;
		cpBar.jb.setString("Spider Scan started...");
		while (progressPercent < 100) {
			progressPercent = Integer.parseInt(((ApiResponseElement) zapClientAPI.spider.status(scanid)).getValue());

			cpBar.jb.setString("Spider Scan Completed  " + progressPercent + "%");
			cpBar.jb.setValue(progressPercent);
			log.info("Spider is " + progressPercent + "% complete.");

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		log.info("Spidering ended");
	}

	/*
	 * Method to execute scan and log the progress
	 */
	public void scanWithZap() throws ClientApiException {
		log.info("Scanning started");
		// Execute the ZAP scanner
		zapClientAPI.ascan.scan(WebSiteNavigation.BASE_URL, "", "", "", "", "");

		int progressPercent = 0;

		while (progressPercent < 100) {
			progressPercent = Integer.parseInt(((ApiResponseElement) zapClientAPI.ascan.status("")).getValue());

			cpBar.jb.setString("Active Scan Completed  " + progressPercent + "%");
			cpBar.jb.setValue(progressPercent);

			log.info("Scan is " + progressPercent + "% complete.");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
		log.info("Scanning ended");
	}

	@Test
	public void testVulnerabilitiesAfterLogin() throws Exception {

		siteNavigation.loginAsUser();

		log.info("After Login");

		cpBar.setVisible(true);
		log.info("Starting  spidering");

		// Using ZAP Spider
		spiderWithZap();

		log.info("Starting Active scanning");
		// Using ZAP Scanner
		 scanWithZap();

		cpBar.setVisible(false);

		FileOutputStream fout = new FileOutputStream(new File("/root/Pictures/report.xml"));
		fout.write(zapClientAPI.core.xmlreport());
		fout.close();
		// System.out.println(new String(zapClientAPI.core.xmlreport(ZAP_APIKEY))); //To
		// Print the report

	}
}