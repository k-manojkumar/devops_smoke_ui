package selenium;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Protocol;
import com.aventstack.extentreports.reporter.configuration.Theme;



public class UISmokeTest {

	static ExtentTest test;
	static ExtentReports report;

	@Test

	public void uiSmokeTest() throws IOException {

		/*
		 * Setup for local
		 *
		
		System.setProperty("webdriver.gecko.driver", "/Users/cbsadmin/Documents/geckodriver");
		*/


		/*
		 * Setup for Jenkins
		 */
		
		System.setProperty("webdriver.gecko.driver", "/home/manojkumar_ibt/geckodriver");
		File firefoxPathBinary = new File("/snap/firefox/280/firefox-bin");
		System.setProperty("webdriver.firefox.bin", firefoxPathBinary.getAbsolutePath());
		
		

		FirefoxDriver driver = new FirefoxDriver();

		
		test = report.createTest("UI Smoke Test");
		ExtentTest testNode = test.createNode("Application Launch");
		driver.get("http://35.228.146.167:7000/");
		
		WebElement title = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div/div/div[1]/label[2]"));

		System.out.println("********************************************************");

		System.out.println("* " + title.getAttribute("innerHTML") + " *");

		WebElement date = driver.findElement(By.xpath("/html/body/div/div/div[3]/div[1]/div[1]/div[3]/h2"));

		System.out.println("* " + date.getAttribute("innerHTML") + " *");

		System.out.println("********************************************************");

		String screenshoot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BASE64);
		if (null != title && null != title.getAttribute("innerHTML") && "Test Colleague - SS07001".equals(title.getAttribute("innerHTML"))) {
			testNode.pass("Colleague Name Validated");
		}else {
			testNode.fail("Colleague Name Not Validated");
		}
		
		if (null != date && null != date.getAttribute("innerHTML") && "November 15, 2019".equals(date.getAttribute("innerHTML"))) {
			testNode.pass("Date Validated");
		}else {
			testNode.fail("Date Not Validated");
		}
		
		testNode.info("Test Completed", MediaEntityBuilder.createScreenCaptureFromBase64String(screenshoot).build());
		driver.quit();

	}
	
	@BeforeClass

	public static void initateReport() {

		Date date = Calendar.getInstance().getTime();
		SimpleDateFormat dateFormat = new SimpleDateFormat("ddMM_HHmm");
		SimpleDateFormat dateFormatForReport = new SimpleDateFormat("dd MMM yyyy");
		String fileName = System.getProperty("user.dir") +"/test-reports/TestReport.html";

		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(fileName);
		report = new ExtentReports(); 
		htmlReporter.config().setAutoCreateRelativePathMedia(true);
		htmlReporter.config().setCSS("css-string");
		htmlReporter.config().setDocumentTitle("UI Smoke Test Report");
		htmlReporter.config().setEncoding("utf-8");
		htmlReporter.config().setJS("js-string");
		htmlReporter.config().setProtocol(Protocol.HTTPS);
		htmlReporter.config().setReportName("UI Smoke Test Report");
		htmlReporter.config().setTheme(Theme.DARK);
		htmlReporter.config().setTimeStampFormat("MMM dd, yyyy HH:mm:ss");
		report.attachReporter(htmlReporter);
		report.setSystemInfo("Application Name", "DevOps UI");
		report.setSystemInfo("Tool", "Selenium");
		report.setSystemInfo("Report Date", dateFormatForReport.format(date));
		//return fileName;

	}


	@AfterClass()
	public static void finishReport() {
		report.flush();

	}


}

