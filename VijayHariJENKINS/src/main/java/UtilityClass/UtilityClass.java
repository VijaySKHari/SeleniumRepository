package UtilityClass;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UtilityClass {

	WebDriver driver;
	WebDriverWait wait;

	public UtilityClass(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(10000));

	}

	public void login(String username, String password) {

		WebElement emailField = driver.findElement(By.xpath("//input[@id='userEmail']"));
		emailField.clear();
		emailField.sendKeys(username);
		WebElement passwordField = driver.findElement(By.xpath("//input[@placeholder='enter your passsword']"));
		passwordField.clear();
		passwordField.sendKeys(password);
		WebElement submitButton = driver.findElement(By.xpath("//input[@id='login']"));
		submitButton.click();
	}

	public void explicitlyWaitForElementToBeVisibleUsingLocator(By locator) {

		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

	}

	public void explicitlyWaitForElementToBeClickable(By locator) {

		wait.until(ExpectedConditions.elementToBeClickable(locator));

	}

	public void explicitlyWaitForElementTobeVisibleUsingWebElement(WebElement element) {
		wait.until(ExpectedConditions.visibilityOf(element));

	}

	public void explicitlyWaitForElementToBeInvisible(By locator) {

		wait.until(ExpectedConditions.invisibilityOfElementLocated((locator)));
	}

	public void selectClassApply(WebElement element, String text) {

		Select select = new Select(element);
		select.selectByVisibleText(text);

	}

}
