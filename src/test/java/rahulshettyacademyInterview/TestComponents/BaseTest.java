package rahulshettyacademyInterview.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.pageobjects.LandingPage;

public class BaseTest {
	public WebDriver driver;
	public LandingPage landing;
   public WebDriver initializer() throws IOException {
	
   Properties prop = new Properties();
  FileInputStream fis = new FileInputStream("E:\\Eclipse\\SeleniumFrameworkDesignInterview\\src\\main\\java\\rahulshettyacademy\\resources\\GlobalData.properties");
	prop.load(fis);
	//String browserName = prop.getProperty("browser");
	
	String browserName=System.getProperty("browser")!=null?System.getProperty("browser"):prop.getProperty("browser");
	//prop.getProperty("browser");
	if(browserName.equalsIgnoreCase("chrome")) {
      WebDriverManager.chromedriver().setup();
	  driver = new ChromeDriver();
}
	else if(browserName.equalsIgnoreCase("firefox")) {
		//firefox
	}
	else if(browserName.equalsIgnoreCase("edge")) {
		//firefox
	}
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	driver.manage().window().maximize();
	return driver;
}
   
	public List<HashMap<String, String>> getJsonMap(String filePath) throws IOException {
		//read json into string
		String jsonContent=FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_16);
		//String To HashMap
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>(){
			
		});
		return data;
	}
   
   @BeforeMethod(alwaysRun=true)
   public  LandingPage launchApplication() throws IOException {
	   driver = initializer();
	   landing = new LandingPage(driver);
	   landing.goTo();
	return landing;
	   
   }
   @AfterMethod(alwaysRun=true)
   public void tearDown() {
	   driver.close();   
	   }
   
}