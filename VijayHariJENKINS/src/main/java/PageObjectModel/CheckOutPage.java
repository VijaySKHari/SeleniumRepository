package PageObjectModel;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import UtilityClass.UtilityClass;

public class CheckOutPage extends UtilityClass {
	WebDriver driver;

	public CheckOutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//button[text()='Checkout']")
	public WebElement checkoutButton;

	@FindBy(xpath = "(//input[@type='text'])[2]")
	public WebElement cvvCode;

	@FindBy(xpath = "(//select[@class='input ddl'])[1]")
	public WebElement month;

	@FindBy(xpath = "(//select[@class='input ddl'])[2]")
	public WebElement date;

	@FindBy(xpath = "(//input[@class='input txt'])[2]")
	public WebElement nameOnCard;

	@FindBy(xpath = "//input[@placeHolder='Select Country']")
	public WebElement country;

	@FindBy(xpath = "//a[text()='Place Order ']")
	public WebElement placeOrder;

	public void placeOrder(String monthValue, String dateValue, String cvv, String nameOnCardValue,
			String countryname) {
		explicitlyWaitForElementTobeVisibleUsingWebElement(checkoutButton);
		checkoutButton.click();
		selectClassApply(month, monthValue);
		selectClassApply(date, dateValue);
		cvvCode.sendKeys(cvv);
		nameOnCard.sendKeys(nameOnCardValue);
		country.sendKeys(countryname);
		By selectCountry = By.xpath("(//button/span)[2]");

		explicitlyWaitForElementToBeVisibleUsingLocator(selectCountry);
		System.out.println("element found");
		WebElement country = driver.findElement(By.xpath("(//button/span)[2]"));
//		Actions actions = new Actions(driver);
//		actions.click(country);
		country.click();
		placeOrder.click();
		explicitlyWaitForElementToBeVisibleUsingLocator(By.xpath("//h1"));
		WebElement validateText = driver.findElement(By.xpath("//h1"));
		assertEquals(validateText.getText(), "THANKYOU FOR THE ORDER.");
	}

}
