
import java.io.IOException;

import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import base.ExtentManager;
import base.Hooks;
import pageObjects.Homepage;
import pageObjects.ShopContentPanel;
import pageObjects.ShopHomepage;
import pageObjects.ShopProductPage;
import pageObjects.ShoppingCart;

@Listeners(resources.Listener.class)

@BeforeMethod
public void setUp() {
	 System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");

        // Initialize the WebDriver (e.g., ChromeDriver)
        driver = new ChromeDriver();

        // Maximize the browser window
        driver.manage().window().maximize();

        // Open the URL
        driver.get("https://www.example.com");
    }
}
	@Test
	public void addRemoveItem() throws IOException {
		
		ExtentManager.log("Starting AddRemoveItemBasketTest...");
		
		// creating an object of the automationtesting.co.uk webpage
		Homepage home = new Homepage();

		//handle cookie visibility using JSE - added 20230217
		JavascriptExecutor jse = (JavascriptExecutor)getDriver();
		jse.executeScript("arguments[0].scrollIntoView()", home.getTestStoreLink()); 
		home.getTestStoreLink().click();

		// creating an object of the test store homepage
		ShopHomepage shopHome = new ShopHomepage();
		ExtentManager.pass("Reached the shop homepage");
		shopHome.getProdOne().click();

		// creating an object of the shop products page (when a product has been
		// selected)
		ShopProductPage shopProd = new ShopProductPage();
		ExtentManager.pass("Reached the shop product page");
		Select option = new Select(shopProd.getSizeOption());
		option.selectByVisibleText("M");
		ExtentManager.pass("Have successfully selected product size");
		shopProd.getQuantIncrease().click();
		ExtentManager.pass("Have successfully increased quantity");
		shopProd.getAddToCartBtn().click();
		ExtentManager.pass("Have successfully added product to basket");

		// creating an object of the cart content panel (once an item was added)
		ShopContentPanel cPanel = new ShopContentPanel();
		cPanel.getContinueShopBtn().click();
		shopProd.getHomepageLink().click();
		shopHome.getProdTwo().click();
		shopProd.getAddToCartBtn().click();
		cPanel.getCheckoutBtn().click();

		// creating an object for the shopping cart page and deleting item 2
		ShoppingCart cart = new ShoppingCart();
		cart.getDeleteItemTwo().click();

		// using a wait to ensure the deletion has taken place
		waitForElementInvisible(cart.getDeleteItemTwo(), 10);

		// printing the total amount to console
		System.out.println(cart.getTotalAmount().getText());
		
		try {
			// using an assertion to make sure the total amount is what we are expecting
			Assert.assertEquals(cart.getTotalAmount().getText(), "$45.23");
			ExtentManager.pass("The total amount matches the expected amount.");
		} catch (AssertionError e) {
			Assert.fail("Cart amount did not match the expected amount, it found" + cart.getTotalAmount().getText() +
					" expected $45.23");
			ExtentManager.fail("The total amount did not match the expected amount.");
		}

	}

}