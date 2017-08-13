package com.softwaretestinghelp.selenium;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import com.google.common.base.Function;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.junit.Assert.*;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.Select;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import org.apache.commons.io.FileUtils;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import java.io.File;
import java.io.IOException;



public class AutoTraderTesting {

		private WebDriver webDriver;
		private setupSearchCars setupSearchCars;

		private Wait<WebDriver> wait;

		private ExtentReports report;
		private ExtentTest test;
		private String reportFilePath = "test.html";


		@Before
		public void before() {

			report = new ExtentReports();
			ExtentHtmlReporter extentHtmlReporter = new ExtentHtmlReporter(reportFilePath);
			extentHtmlReporter.config().setReportName("ReportName");
			extentHtmlReporter.config().setDocumentTitle("DocumentTitle");
			report.attachReporter(extentHtmlReporter);
			test = report.createTest("TestName");

			webDriver = new ChromeDriver();
			setupSearchCars = PageFactory.initElements(webDriver, setupSearchCars.class);



			wait = new FluentWait<WebDriver>(webDriver).withTimeout(30, SECONDS).pollingEvery(5, SECONDS)
					.ignoring(NoSuchElementException.class);
			


		}

		@Ignore
		public void Test1() throws InterruptedException {
			webDriver.navigate().to("http://www.autotrader.co.uk/");

			webDriver.manage().window().maximize();


			
			WebElement foo = wait.until(new Function<WebDriver, WebElement>() {
				public WebElement apply(WebDriver driver) {
					return driver.findElement(By.cssSelector("#searchVehicles > div > h1"));
				}
			});

			assertEquals("New & used cars", foo.getText());
			


		}

		@Ignore
		public void Test2() {
			webDriver.navigate().to("http://www.autotrader.co.uk/");

			webDriver.manage().window().maximize();

			webDriver
					.findElement(By.cssSelector(
							"#home > header > nav.site-header__other-vehicles.js-peek-nav.is-active > ul > li:nth-child(2) > a"))
					.click();

			WebElement foo = wait.until(new Function<WebDriver, WebElement>() {
				public WebElement apply(WebDriver driver) {
					return driver.findElement(By.cssSelector(
							"body > div.tm-content > div.topmarques-header > div.topmarques-content.topmarques-header-height > h3 > span:nth-child(1)"));
				}
			});

			assertEquals("Top Marques", foo.getText());

		}
		
		

		@Ignore
		public void Test3() {
			webDriver.navigate().to("http://www.autotrader.co.uk/");

			webDriver.manage().window().maximize();

			webDriver
					.findElement(By.cssSelector(
							"#home > header > nav.site-header__other-vehicles.js-peek-nav.is-active > ul > li:nth-child(3) > a"))
					.click();

			WebElement foo3 = wait.until(new Function<WebDriver, WebElement>() {
				public WebElement apply(WebDriver driver) {
					return driver.findElement(By.cssSelector(
							"#main-content > div.homesearchholder.cf > div > div > div > h1"));
				}
			});

			assertEquals("Find used vans", foo3.getText());

		}
		
		

		@Test
		public void searchCar() throws InterruptedException {

			SpreadSheetReader sheetReader = new SpreadSheetReader(
					System.getProperty("user.dir") + "/Autotrader_Spreadsheet.xlsx");

			int noRows = sheetReader.getLastRowNum("Input Data");
			System.out.println(noRows);

			int index = 1;

			while (index <= noRows) {
				List<String> row = sheetReader.readRow(index, "Input Data");
				System.out.println(row);
				Test(row.get(0), row.get(1), row.get(2), row.get(3), row.get(4));
				index++;
			}
		}

		public void Test(String testID, String postcode, String distance, String car_make, String car_model) throws InterruptedException {

			
			webDriver.navigate().to("http://www.autotrader.co.uk/");

			webDriver.manage().window().maximize();
			
			setupSearchCars.userpostcodeInput(postcode);
			
			
			Select oSelect = new Select(webDriver.findElement(By.id("radius")));

			System.out.println(oSelect);
			
			oSelect.selectByValue("5");
			
			Thread.sleep(5000);
			
			

			try {
				test.log(Status.INFO, "Info level" + test.addScreenCaptureFromPath(ScreenShot.take(webDriver, "Rep")));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		@After
		public void aTest() {

			report.flush();
			webDriver.quit();		
		
		
		
	}

}
