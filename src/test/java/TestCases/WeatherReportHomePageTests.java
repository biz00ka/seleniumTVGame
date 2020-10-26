package TestCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import util.utility;

public class WeatherReportHomePageTests extends BaseTest {
	
    // Here we are calling the Data Provider object with its Name

	 @Test(dataProvider = "body",invocationCount = 1, description="Enter the city name in search box and verify on search result.")
	public void TEST_CASE_ONE(String cityName,String variance) throws Exception {
		driver.get(URL);
		homePage.searchForCity(cityName);
		homePage.verifyCityNameOnSearchPage();
		String tempUI=homePage.fetchCityTempUI();
		tempUI= tempUI.replace("\u00B0", "");
		Double tempUIU= utility.celsiusToKelvin(tempUI);
		Response res=RestAssured.get(baseURI,cityName,apiID);
		String tempAPI=res.body().jsonPath().getMap("main").get("temp").toString();
		System.out.println("API temp for city "+cityName+" is "+tempAPI+" and UI temp is "+tempUIU);
		Assert.assertTrue(utility.tempMatcher(Double.parseDouble(tempAPI),tempUIU,Double.parseDouble(variance)), "The temperature varience exceeds to the given limit.");
	}


}
