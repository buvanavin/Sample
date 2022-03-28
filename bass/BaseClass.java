package org.bass;

import java.awt.AWTException;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	public static WebDriver driver;
	public static Robot bot;
	public static Actions act;
	public static Select S;

	public static void browserLaunch(String browser) {
		switch (browser) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		default:
			break;
		}
	}

	// to get url
	public static void passUrl(String Url) {
		driver.get(Url);
	}

	// browser window maximize or minimize
	public static void windowMaximize() {
		driver.manage().window().maximize();
	}

	// sendkeys to textbox
	public static void toInput(WebElement element, String input) {
		element.sendKeys(input);
	}

	// to get current browser url
	public static void toPrintCurrentUrl() {
		String printurl = driver.getCurrentUrl();
		System.out.println(printurl);
	}

	// to print current browser title
	public static void toPrintCurrentTitle() {
		String printtitle = driver.getTitle();
		System.out.println(printtitle);
	}

	// to close current browser
	public static void toCloseBrowser() {
		driver.close();
	}

	// to close all open browser
	public static void toQuitBrowser() {
		driver.quit();
	}
	
	public static void toClick(WebElement element) {
		element.click();
	}

	// to perform most used keyboard actions
	public static void multikeys(String button) throws AWTException {
		bot = new Robot();
		if (button == "tab") {
			bot.keyPress(KeyEvent.VK_TAB);
			bot.keyRelease(KeyEvent.VK_TAB);
		}
		if (button == "enter") {
			bot.keyPress(KeyEvent.VK_ENTER);
			bot.keyRelease(KeyEvent.VK_ENTER);
		}
		//to perform keyup or keydown
		if (button == "up") {
			bot.keyPress(KeyEvent.VK_UP);
			bot.keyRelease(KeyEvent.VK_UP);
	}
		if (button == "down") {
			bot.keyPress(KeyEvent.VK_DOWN);
			bot.keyRelease(KeyEvent.VK_DOWN);
		}
	}
	
	// to perform most used cursor actions
	public static void actionsClassClick(WebElement element, String mouseact) {
		act = new Actions(driver);

		if (mouseact == "click") {
			act.click(element).perform();
		}
		if (mouseact == "contextclick") {
			act.contextClick(element).perform();
		}
		if (mouseact == "movetoelement") {
			act.moveToElement(element).perform();
		}
		if (mouseact == "doubleclick") {
			act.doubleClick(element).perform();
		}
		if (mouseact == "keyup") {
			act.keyUp(mouseact).perform();
		}
		if (mouseact == "keydown") {
			act.keyDown(mouseact).perform();
		}
	}

	public static void toDragAndDrop(WebElement element, WebElement element2) {
		act = new Actions(driver);
		act.dragAndDrop(element, element2).perform();
	}

	// to interact webelement/hidden webelement fast javascriptexecutor is used
	public static void jsExecutorInputText(WebElement element, String inputtext) {
		// downcasting
		JavascriptExecutor js = (JavascriptExecutor) driver;
		// javascript executor to send keys
		js.executeAsyncScript("arguments[0].setAttribute('value', inputtext )", element);
	}

	// javascript executor to print value
	public static void jsExecutorToRetrieveText(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("return arguments[0].getAttribute('value')", element);
	}

	// javascript executor to perform click
	public static void jsExecutorToclick() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click");
	}

	// to handle multiple windows
	public static void toHandleMultipleWindows(int i) {
		Set<String> allWindows = driver.getWindowHandles();
		List<String> li = new ArrayList();
		li.addAll(allWindows);
		driver.switchTo().window(li.get(i));

	}

	// to take screenshots
	public static void toTakeScreenShot(String filename) throws IOException {
		TakesScreenshot screenShot = (TakesScreenshot) driver;
		File source = screenShot.getScreenshotAs(OutputType.FILE);
		File destination = new File(
				"C:\\Users\\Win10\\eclipse-workspace\\BaseClass\\screenshots\\" + filename + ".jpg");
		FileUtils.copyFile(source, destination);
	}
	

	
    //pom excel base class 
	public static String excelRead(int row, int cell) throws IOException {
		File f = new File("C:\\Users\\ASUS X555\\LestCode\\datasheets\\Sheet1.xlsx");
		FileInputStream file = new FileInputStream(f);
		Workbook w = new XSSFWorkbook(file);
		Sheet  s   = w.getSheet("Sheet1");
		Row    r   = s.getRow(row);
		Cell   c   = r.getCell(cell);
		int cellType = c.getCellType();
		String Value;
		if (cellType == 1) {
			Value = c.getStringCellValue();

		} else if (DateUtil.isCellDateFormatted(c)) {
			Date d = c.getDateCellValue();
			SimpleDateFormat sim = new SimpleDateFormat("dd/mm/yyyy");
			Value = sim.format(d);

		} else {
			double dd = c.getNumericCellValue();
			long l = (long) dd;
			Value = String.valueOf(l);
		}
		return Value;
	}

}
