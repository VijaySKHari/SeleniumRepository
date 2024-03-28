package TestScripts;

import static org.testng.Assert.fail;
import org.testng.Assert;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import BaseClass.BaseClass;
import PageObjectModel.HomePageObject;

public class ProductSelect extends BaseClass {

	@Test
	public void selectProduct() {
		HomePageObject prod = new HomePageObject(driver);
		prod.login("dummy@maveric.com", "Maveric@1234");
		By homeText = By.xpath("//button[text()=' HOME ']");
		prod.explicitlyWaitForElementToBeClickable(homeText);
		String[] products = { "IPHONE", "ZARA" };
		//Assert.fail();
		prod.selectProduct(products);
	}
}
