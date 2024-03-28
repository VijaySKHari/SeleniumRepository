package PageObjectModel;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import UtilityClass.UtilityClass;

public class HomePageObject extends UtilityClass {

	WebDriver driver;

	public HomePageObject(WebDriver driver) {
		super(driver);
		this.driver = driver;

		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "(//div[@class='card-body'])/h5")
	public List<WebElement> productName;

	public void selectProduct(String[] product) {

		for (int i = 0; i < product.length; i++) {
			for (WebElement x : productName) {
				if (x.getText().contains(product[i])) {
					x.findElement(By.xpath("./following-sibling::button[2]")).click();
					By elemelementLocator = By.xpath("//div[@role='alert']");
					explicitlyWaitForElementToBeVisibleUsingLocator(elemelementLocator);
					explicitlyWaitForElementToBeInvisible(elemelementLocator);
					break;
				}
			}
		}
	}

}
