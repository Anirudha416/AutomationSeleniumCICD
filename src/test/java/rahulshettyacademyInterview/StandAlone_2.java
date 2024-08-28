package rahulshettyacademyInterview;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.OrderPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;
import rahulshettyacademyInterview.TestComponents.BaseTest;

public class StandAlone_2 extends BaseTest{
	 String productName = "ZARA COAT 3";
    @Test(dataProvider="getData", groups={"Purchase"})
	public void submitOrder(HashMap<String,String> input) throws InterruptedException, IOException {
		// TODO Auto-generated method stub
  
   
   //LandingPage landing=launchApplication();
    ProductCatalogue productCatalogue = landing.loginApplication(input.get("email"), input.get("password"));
	List<WebElement>products = productCatalogue.getProductList();
	productCatalogue.addProductToCard(input.get("product"));
	CartPage cartPage=productCatalogue.gotoCartPage();

	Boolean match = cartPage.VerifyProductDisplaying(input.get("product"));
	Assert.assertTrue(match);
	CheckoutPage chekoutPage=cartPage.goToCheckout();
	chekoutPage.selectCountry("india");
    Thread.sleep(2000);
	ConfirmationPage confirmpage = chekoutPage.submitOrder();
	String confirmMessage  = confirmpage.getConfirmationMessage();
	Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
   // driver.close();	
	}
    //To verify ZARA COAT 3 is displaying in orders page 
    @Test(dependsOnMethods= {"submitOrder"})
    public void OrderHistoryTest() {
    	 ProductCatalogue productCatalogue = landing.loginApplication("abcxyz@gmail.com", "Anirudha12@");
    	 OrderPage ordersPage = productCatalogue.gotoOrderPage();
    	 Assert.assertTrue(ordersPage.VerifyOrderDisplaying(productName));
    }
    
    public String getScreenshot(String testcasename) throws IOException {
    	TakesScreenshot ts = (TakesScreenshot)driver;
    	File source = ts.getScreenshotAs(OutputType.FILE);
    	File file = new File(System.getProperty("user.dir")+"//reports"+testcasename+".png");
    	FileUtils.copyFile(source, file);
    	return System.getProperty("user.dir")+"//reports"+testcasename+".png";
    }
    
    @DataProvider
    public Object[][] getData() throws IOException {
    	HashMap<String, String> map = new HashMap<String, String>();
    	map.put("email", "abcxyz@gmail.com");
    	map.put("password", "Anirudha12@");
    	map.put("product", "ZARA COAT 3");
    	
    	HashMap<String, String> map1 = new HashMap<String, String>();
    	map1.put("email", "shetty@gmail.com");
    	map1.put("password", "Iamking@000");
    	map1.put("product", "ADIDAS ORIGINAL");
//    	List <HashMap<String,String>> data=getJsonMap("E:\\Eclipse\\SeleniumFrameworkDesignInterview\\src\\test\\java\\rahulshettyacademy\\data\\PurchaseOrder.json");
//    	return new Object[][] {{data.get(0)}, {data.get(1)}};
    	return new Object[][] {{map}, {map1}};
    }
//    @DataProvider
//    public Object[][] getData() {
//    	
//    	
//    	return new Object[][] {{"abcxyz@gmail.com", "Anirudha12@", "ZARA COAT 3"}, {"shetty@gmail.com","Iamking@000","ADIDAS ORIGINAL"}};
//    }
  }
