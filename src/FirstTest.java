import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.server.handler.FindElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;

public class FirstTest
{

    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception
    {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("deviceName","AndroidTestDevice");
        capabilities.setCapability("platformVersion","6.0");
        capabilities.setCapability("automationName","Appium");
        capabilities.setCapability("appPackage","org.wikipedia");
        capabilities.setCapability("appActivity",".main.MainActivity");
        capabilities.setCapability("app","D:\\JavaAppiumAutomation\\apks\\org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

//    @Test
//    public void FirstTest()
//    {
//        WaitForElementByXpathAndClick(
//                "//*[contains(@text, 'Search Wikipedia')]",
//                "Cannot find the search field on main screen",
//                5
//        );
//
//        WaitForElementByXpathAndSendKeys(
//                "//*[contains(@text, 'Search…')]",
//                "Java",
//                "Cannot find the search field on search screen",
//                5
//        );
//
//        WaitForElementPresentByXpath(
//                "//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']",
//                "Cannot find the search result"
//        );
//    }


    @Test
    public void CloseSearch()
    {

//        driver.findElementByXPath("//*[@resource-id='org.wikipedia:id/voice_search_button']");
        driver.findElementById("org.wikipedia:id/voice_search_button");
//        WaitForElementPresentById(
//                "org.wikipedia:id/search_container",
//                "Cannot find the search field on main screen",
//                5
//        );

//        WaitForElementByIdAndClick(
//                "org.wikipedia:id/search_close_btn",
//                "Cannot find the Close button in the search line",
//                5
//        );
//
//        WaitForElementNotPresentById(
//                "org.wikipedia:id/search_close_btn",
//                "Close button is still on the page",
//                5
//        );
    }

    private WebElement WaitForElementPresentByXpath(String xpath, String error_message, long timeoutInSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        By by = By.xpath(xpath);
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    private WebElement WaitForElementPresentByXpath(String xpath, String error_message)
    {
        return WaitForElementPresentByXpath(xpath, error_message, 5);
    }

    private WebElement WaitForElementByXpathAndClick(String xpath, String error_message, long timeoutInSeconds)
    {
        WebElement element = WaitForElementPresentByXpath(xpath, error_message, timeoutInSeconds);
        element.click();
        return element;
    }

    private WebElement WaitForElementByXpathAndSendKeys(String xpath, String value, String error_message, long timeoutInSeconds)
    {
        WebElement element = WaitForElementPresentByXpath(xpath, error_message, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }

    private WebElement WaitForElementPresentById(String id, String error_message, long timeoutInSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        By by = By.id(id);
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    private WebElement WaitForElementByIdAndClick(String id, String error_message, long timeoutInSeconds)
    {
        WebElement element = WaitForElementPresentById(id, error_message, timeoutInSeconds);
        element.click();
        return element;
    }

    private WebElement WaitForElementByIdAndSendKeys(String id, String value, String error_message, long timeoutInSeconds)
    {
        WebElement element = WaitForElementPresentById(id, error_message, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }

    private boolean WaitForElementNotPresentById(String id, String error_message, long timeoutInSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        By by = By.id(id);
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }
}
