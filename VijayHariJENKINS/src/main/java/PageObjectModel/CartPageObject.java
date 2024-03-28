package PageObjectModel;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import UtilityClass.UtilityClass;

public class CartPageObject extends UtilityClass {

	WebDriver driver;

	public CartPageObject(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath = "//button[@routerlink='/dashboard/cart']")
	public WebElement cartButton;

	@FindBy(xpath = "//div[@class='cartSection']/h3")
	public List<WebElement> cartProducts;

	public void verifProductsAreInCart(String[] products) {
		cartButton.click();
		for (int i = 0; i < products.length; i++) {
			for (WebElement x : cartProducts) {
				if (x.getText().contains(products[i])) {
					System.out.println(x.getText() + " is present ");
					break;
				}
			}
		}

	}

}
