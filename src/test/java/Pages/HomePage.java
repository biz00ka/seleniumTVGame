package Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import reportManager.ExtentManager;

public class HomePage extends BasePageClass {
	public HomePage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//div[@aria-label='searchInput']")
	WebElement searchBtn;
	
	@FindBy(xpath = "//div[@aria-label='searchInput']//input[@id='LocationSearch_input']")
	WebElement searchBtnInput;

	@FindBy(xpath = "//div[@id='LocationSearch_listbox']/button")
	private List<WebElement> searchBtnList;

	@FindBy(xpath = "//*[@name='location']")
	private WebElement searchIcon;

	@FindBy(xpath = "//h1[contains(@class,'CurrentConditions')]")
	private WebElement city;

	@FindBy(xpath = "//span[@data-testid='TemperatureValue' and contains(@class,'CurrentConditions')]")
	private WebElement cityTemp;

	/**
	 * Seafrch for a city name in weather.com home Page.
	 * @param  City name as Strinng
	 * @return  void
	 * @version 1.0
	 * @throws Exception 
	 * @exception NoSuchElementException
	 * 
	 */
	public void searchForCity(String cityName) throws Exception {

		try {
			Thread.sleep(2000);
			//Setting the attribute to enable the element.
			webActionHelperMethods.setAttributeByJS(searchBtn,"true");
			Thread.sleep(1000);
			webActionHelperMethods.tapButton(searchBtn);
			Thread.sleep(1000);
			webActionHelperMethods.inputValuesSlowly(searchBtnInput,cityName);
			Thread.sleep(1000);
			for(WebElement e : searchBtnList) {
				String nameText= e.getText();
				if(nameText.toLowerCase().contains(cityName.toLowerCase()))
				{
					Thread.sleep(1000);
					webActionHelperMethods.clickByJavaScript(e);
					break;
				}
			}
			
		} catch (Exception e) {
			System.out.println("Error ocurred in city name search.");
			e.printStackTrace();
		}
	}
		
		/**
		 * verifies the City Name On Search result Page.
		 * @param  No parameters
		 * @return  void
		 * @version 1.0 
		 * @exception NoSuchElementException, AssertionError
		 * 
		 */
		public void verifyCityNameOnSearchPage()
		{
			try {
				webActionHelperMethods.waitForElementPresent(driver,20,city);
				Assert.assertTrue(city.isDisplayed());
		
		  } catch (Exception e) {
		  System.out.println("City weather could not be loaded."); e.printStackTrace();
		  }
		 
		}
	

		/**
		 * Fetches the City Temperature From Search result Page.
		 * @param  No parameters
		 * @return  String
		 * @version 1.0 
		 * @exception NoSuchElementException, AssertionError
		 * 
		 */
		public String fetchCityTempUI()
		{
			try {
				webActionHelperMethods.waitForElementPresent(driver,20,cityTemp);
				
		
		  } catch (Exception e) {
		  System.out.println("City Temperature could not be Found."); 
		  e.printStackTrace();
		  }
			return cityTemp.getText();
		}
	

	



}
