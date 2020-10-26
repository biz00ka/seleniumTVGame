package actionHelper;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class WebActionHelperMethods {

    private WebDriver driver;

    public WebActionHelperMethods(WebDriver driver) {
        this.driver = driver;
    }

    public void waitForElementClickable(WebDriver driver, int timeInSeconds, WebElement ele)
	{
		try {
			WebDriverWait wait = new WebDriverWait(driver, timeInSeconds );
			wait.until(ExpectedConditions.elementToBeClickable(ele));

		} catch (TimeoutException e) {
			// TODO Auto-generated catch block
			System.out.println("Element "+ele+" is not ckickable.");
		}
	}


	public void waitForElementPresent(WebDriver driver, int timeInSeconds, WebElement ele)
	{
		try {
			WebDriverWait wait = new WebDriverWait(driver, timeInSeconds );
			//wait.until(ExpectedConditions.visibilityOf(ele));
			wait.until(ExpectedConditions.elementToBeClickable(ele));

		} catch (TimeoutException e) {
			// TODO Auto-generated catch block
			System.out.println("Element "+ele+" is not Present.");
		}
	}

	public void waitForAllElementPresent(WebDriver driver, int timeInSeconds, List<WebElement> ele)
	{
		try {
			WebDriverWait wait = new WebDriverWait(driver, timeInSeconds );
			wait.until(ExpectedConditions.visibilityOfAllElements(ele));

		} catch (TimeoutException e) {
			// TODO Auto-generated catch block
			System.out.println("Elements "+ele+" are not Present.");
		}
	}

	public void tapButton(WebElement ele) throws InterruptedException 
	{
		waitForElementPresent(driver,30,ele);
		//Thread.sleep(800);
		ele.click();
		Thread.sleep(800);

	}

	public void browserBackButton() throws InterruptedException 
	{
		Thread.sleep(2000);
		driver.navigate().back();
		Thread.sleep(800);
	}

	public void clickByJavaScript(WebElement ele) throws InterruptedException
	{
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", ele);
		Thread.sleep(800);
	}


	public void dragNadDropByCoordinates(WebElement ele)
	{
		Actions action = new Actions(driver);
		action.clickAndHold(ele).moveByOffset(20, 10).build().perform();
	}

	public void inputValuesSlowly(WebElement ele, String value) throws InterruptedException
	{
		waitForElementPresent(driver,30,ele);
		String val = value; 
		ele.clear();

		for (int i = 0; i < val.length(); i++){
			char c = val.charAt(i);
			String s = new StringBuilder().append(c).toString();
			ele.sendKeys(s);Thread.sleep(250);
		}  
	}

	public void inputValues(WebElement ele, Keys tab) throws Exception {
		// TODO Auto-generated method stub
		waitForElementPresent(driver,30,ele);
		ele.sendKeys(tab);
		Thread.sleep(800);
	}

	public void inputValues(WebElement ele, String value) throws Exception
	{
		waitForElementPresent(driver,30,ele);
		ele.clear();
		ele.sendKeys(value);
		Thread.sleep(800);
	}

	public void inputValuesUsingJS(WebElement ele, String value) throws Exception
	{
		waitForElementPresent(driver,30,ele);
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].setAttribute('value', arguments[1])", ele, value);
		Thread.sleep(800);
	}

	public void setAttributeByJS(WebElement ele, String value) throws Exception
	{
		waitForElementPresent(driver,30,ele);
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].setAttribute('aria-expanded', arguments[1])", ele, value);
		Thread.sleep(800);
	}
    
    
    public void SelectByValue() {

        Select select = new Select(driver.findElement(By.xpath("")));
        select.selectByIndex(1);
    }


    public boolean selectdropdown(String xpath, String value) {

        try {
            List<WebElement> elements = driver.findElements(By.xpath(xpath));

            for (WebElement element : elements) {
                System.out.println(element.getText());
                if (element.getText().contains(value)) {
                    //  System.out.println(element.getText());
                    element.click();
                }
            }
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

}