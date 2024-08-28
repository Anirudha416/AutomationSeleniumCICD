package rahulshettyacademyInterview;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;
import rahulshettyacademyInterview.TestComponents.BaseTest;

public class ErrorValidations extends BaseTest{
    @Test(groups= {"ErrorHandling"})
	public void LoginErrorValidation() throws InterruptedException, IOException {
		// TODO Auto-generated method stub
    	
    	
   String productName = "ZARA COAT 3";
   landing.loginApplication("abcxyz@gmail.com", "Anirudha12@");
   //Assert.assertEquals("Incorrect email or password@12.", landing.getErrorMessage());
 }
    @Test
   	public void productErrorValidation() throws InterruptedException, IOException {
   		// TODO Auto-generated method stub
      String productName = "ZARA COAT 3";
      
      //LandingPage landing=launchApplication();
       ProductCatalogue productCatalogue = landing.loginApplication("abcxyz@gmail.com", "Anirudha12@");
   	List<WebElement>products = productCatalogue.getProductList();
   	productCatalogue.addProductToCard(productName);
   	CartPage cartPage=productCatalogue.gotoCartPage();

   	Boolean match = cartPage.VerifyProductDisplaying(productName);
   	Assert.assertTrue(match);
	
   	}
    

}
