package BaseClass;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class BaseClass {

	public WebDriver driver;

	public void initialiseBrowser() {

		Properties properties = new Properties();
		String propertiesLocation = System.getProperty("user.dir")
				+ "//src//test//resources//GlobalParameters.properties.txt";
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(propertiesLocation);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		try {
			properties.load(fis);
		} catch (IOException e) {
			e.printStackTrace();
		}

		String browserName = System.getProperty("browser")!=null?System.getProperty("browser"):properties.getProperty("browser");

		if (browserName.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
		}

		if (browserName.equalsIgnoreCase("Firefoz")) {
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
		}
		if (browserName.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
		}

	}

	public void openLoginPage() {

		driver.get("https://www.google.com/");
		WebElement textArea = driver.findElement(By.xpath("(//textarea)[1]"));
		textArea.sendKeys("rahulshettyautomation client" + Keys.ENTER);
		// UtilityClass uc = new UtilityClass(driver);
		WebElement letsShop = driver.findElement(By.xpath("//h3[text()=\"Let's Shop\"]"));
		// uc.explicitlyWaitForElementToBeVisible(letsShop);
		letsShop.click();

	}

	public Object[][] readExcel(String path, int sheetIndex) {
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(path);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		Workbook wb = null;
		try {
			wb = WorkbookFactory.create(fis);
		} catch (Exception e) {
			e.printStackTrace();
		}

		Sheet sheet = wb.getSheetAt(sheetIndex);

		int physicalRows = sheet.getPhysicalNumberOfRows();
		int physicalColumns = sheet.getRow(0).getPhysicalNumberOfCells();

		DataFormatter formatter = new DataFormatter();

		Object[][] value = new Object[physicalRows - 1][physicalColumns];
		for (int rowIndex = 1; rowIndex < physicalRows; rowIndex++) {
			for (int columnIndex = 0; columnIndex < physicalColumns; columnIndex++) {
				value[rowIndex - 1][columnIndex] = formatter
						.formatCellValue(sheet.getRow(rowIndex).getCell(columnIndex));
			}
		}
		return value;
	}

	public List<HashMap<String, String>> jsonReader(String path) {

		File file = new File(path);
		String fileToString = null;
		try {
			fileToString = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
		} catch (IOException e) {
			e.printStackTrace();
		}

		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> value = null;
		try {
			value = mapper.readValue(fileToString, new TypeReference<List<HashMap<String, String>>>() {
			});
		} catch (Exception e) {
			e.printStackTrace();
		}

		return value;

	}

	@BeforeMethod
	public void login() {
		initialiseBrowser();
		openLoginPage();
	}

	@AfterMethod
	public void closeDriver() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.close();

	}

	public String getSreenShot(WebDriver driver, String methodName) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss");
		Date date = new Date();
		String format = sdf.format(date);
		String javaScreenshotPath = System.getProperty("user.dir") + "//ScreenShotFolder//" + methodName + format
				+ ".png";

		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File dest = new File(javaScreenshotPath);
		try {
			FileUtils.copyFile(src, dest);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return javaScreenshotPath;

	}

}
