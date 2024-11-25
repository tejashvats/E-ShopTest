

import java.io.IOException;

import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import base.Hooks;
import pageobject.OrderFormPayment;
import pageobject.ShopContentPanel;
import pageobject.ShopHomepage;
import pageobject.Homepage;
import pageobject.OrderFormDelivery;
import pageobject.OrderFormPersInfo;
import pageobject.OrderFormShippingMethod;
import pageobject.ShopProductPage;
import pageobject.ShoppingCart;
import resources.ExtentManager;

@Listeners(resources.Listener.class)

public class OrderCompleteTest  {
	public WebDriver = driver;

	
	@BeforeMethod
	public void setUp() {
		 System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");

	        // Initialize the WebDriver (e.g., ChromeDriver)
	        driver = new ChromeDriver();

	        // Maximize the browser window
	        driver.manage().window().maximize();

	        // Open the URL
	        driver.get("https://automationtesting.co.uk/popups.html");
	    }
	
	@Test
	public void endToEndTest() throws InterruptedException, IOException {

		ExtentManager.log("Starting OrderCompleteTest...");
		
		// creating an object of the automationtesting.co.uk webpage
		Homepage home = new Homepage();

		

		home.getTestStoreLink().click();
		ExtentManager.pass("Have successfully reached store homepage");

		// creating an object of the test store homepage
		Homepage shopHome = new Homepage();
		shopHome.getProdOne().click();
		ExtentManager.pass("Have successfully clicked on product");

		// creating an object of the shop products page (when a product has been
		// selected)
		ShopProductPage shopProd = new ShopProductPage();
		ExtentManager.pass("Have successfully reached shop product page");
		Select option = new Select(shopProd.getSizeOption());
		option.selectByVisibleText("M");
		ExtentManager.pass("Have successfully selected product size");
		shopProd.getQuantIncrease().click();
		ExtentManager.pass("Have successfully increased quantity");
		shopProd.getAddToCartBtn().click();
		ExtentManager.pass("Have successfully added item to cart");

		
		// creating an object of the shopping cart page (all items selected)
		ShoppingCart cart = new ShoppingCart();
		ExtentManager.pass("Have successfully reached the shopping cart page");
		cart.getHavePromo().click();
		ExtentManager.pass("Have successfully selected the promo button");
		cart.getPromoTextbox().sendKeys("20OFF");
		cart.getPromoAddBtn().click();
		cart.getProceedCheckoutBtn().click();
		ExtentManager.pass("Have successfully selected the check out button");

		// creating an object of the order personal information page
		OrderFormPersInfo pInfo = new OrderFormPersInfo();
		pInfo.getGenderMr().click();
		pInfo.getFirstNameField().sendKeys("John");
		pInfo.getLastnameField().sendKeys("Smith");
		pInfo.getEmailField().sendKeys("johnsmith@test.com");
		pInfo.getTermsConditionsCheckbox().click();
		pInfo.getContinueBtn().click();
		ExtentManager.pass("Have successfully entered customer details");

		// creating an object of the order delivery info page
		OrderFormDelivery orderDelivery = new OrderFormDelivery();
		orderDelivery.getAddressField().sendKeys("123 Main Street");
		orderDelivery.getCityField().sendKeys("Houston");
		Select state = new Select(orderDelivery.getStateDropdown());
		state.selectByVisibleText("Texas");
		orderDelivery.getPostcodeField().sendKeys("77021");
		orderDelivery.getContinueBtn().click();
		ExtentManager.pass("Have successfully entered delivery info");

		// creating an object of the shipping method page
		OrderFormShippingMethod shipMethod = new OrderFormShippingMethod();
		shipMethod.getDeliveryMsgTextbox().sendKeys("If I am not in, please leave my delivery on my porch.");
		shipMethod.getContinueBtn().click();
		ExtentManager.pass("Have successfully selected the shipping method");

		// creating an object of the payment options page
		OrderFormPayment orderPay = new OrderFormPayment();
		orderPay.getPayByCheckRadioBtn().click();
		orderPay.getTermsConditionsCheckbox().click();
		orderPay.getOrderBtn().click();
		ExtentManager.pass("Have successfully placed order");
	}

}
