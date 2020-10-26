package TestCases;

import Pages.HomePage;
import driverManager.DriverManagerType;
import driverManager.WebDrivers;
import io.restassured.path.json.JsonPath;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

import util.utility;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class BaseTest {


    protected HomePage homePage;
    WebDriver driver;
    Properties properties;
    protected String URL;
    protected String baseURI;
    protected String apiID;
    
    
    @BeforeTest
    public void setUp() {
        String propertyPath = System.getProperty("user.dir") + "//src//test//resources//Env.properties";
   
            driver = WebDrivers.getDriver(DriverManagerType.CHROME);
            properties = utility.loadProperties(propertyPath);
            URL = properties.getProperty("URL");
            homePage = new HomePage(driver);
            baseURI = properties.getProperty("apiEndPoint");
            apiID = properties.getProperty("apiId");     
    }
    
    @DataProvider(name = "body")

    public static Object[][] body() {

		
		String filename = System.getProperty("user.dir") + "//src//test//resources//testData.json";
		JsonPath path = JsonPath.from(new File(filename));
		List<HashMap<String, Object>> list = path.getList("City");
		Object[][] data= new Object[list.size()][2];
		String var=path.getString("Variance");
		for(int i=0;i<list.size();i++)
		{		
				data[i][0]=list.get(i);
				data[i][1]=var;
		}
        return  data;
		
    }

    @AfterTest
    public void destroyDriver() {
        driver.quit();
    }
}