package TestScripts;

import java.util.HashMap;
import java.util.List;
import org.testng.Assert;
import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import BaseClass.BaseClass;
import PageObjectModel.CartPageObject;
import PageObjectModel.CheckOutPage;
import PageObjectModel.HomePageObject;

public class ProductOrderAndCheckOut extends BaseClass {

	@Test(dataProvider = "provideData")
	public void testProductisPresentInCart(HashMap<String, String> s) {
//String monthValue, String dateValue, String cvv, String nameOnCardValue,String countryname
		HomePageObject prod = new HomePageObject(driver);
		prod.login("dummy@maveric.com", "Maveric@1234");
		By homeText = By.xpath("//button[text()=' HOME ']");
		prod.explicitlyWaitForElementToBeClickable(homeText);
		String[] products = { "IPHONE", "ZARA" };
		prod.selectProduct(products);
		CartPageObject cart = new CartPageObject(driver);
		cart.verifProductsAreInCart(products);
		CheckOutPage checkout = new CheckOutPage(driver);
		// Assert.fail();
		checkout.placeOrder(s.get("month"), s.get("date"), s.get("cvv"), s.get("cardname"), s.get("country"));
	}

	@DataProvider
	public Object[] provideData() {

		List<HashMap<String, String>> value = jsonReader(
				System.getProperty("user.dir") + "\\src\\test\\resources\\Credentials.json");

		Object[] s = new Object[value.size()];
		for (int i = 0; i < value.size(); i++) {

			s[i] = value.get(i);

		}

		return s;

	}

}
