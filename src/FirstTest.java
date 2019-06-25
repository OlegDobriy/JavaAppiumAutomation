import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


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

    @Test
    public void testSearch()
    {
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find the search field on main screen"
        );

        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "Java",
                "Cannot find the search field on search screen"
        );

        waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Cannot find the search result"
        );
    }


    @Test
    public void testCloseSearch()
    {
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find the search field on main screen"
        );

        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "Java",
                "Cannot find the search field on search screen"
        );

        waitForElementAndClear(
                By.id("org.wikipedia:id/search_src_text"),
                "Cannot find the search field on search screen"
        );

        waitForElementAndClick(
                By.id("org.wikipedia:id/search_close_btn"),
                "Cannot find the Close button in the search line"
        );

        waitForElementNotPresent(
                By.id("org.wikipedia:id/search_close_btn"),
                "Close button is still on the page"
        );
    }


    @Test
    public void testSearchAndClose()
    {
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find the search field on main screen"
        );

        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "Python",
                "Cannot find the search field on search screen"
        );

        List elementsAfterSearch = waitForElementsAndGetList(
                By.id("org.wikipedia:id/page_list_item_title"),
                "There is no results",
                10
        );

        Assert.assertTrue(
                "There is less than 2 search results",
                elementsAfterSearch.size() > 2
        );

        waitForElementAndClick(
                By.id("org.wikipedia:id/search_close_btn"),
                "Cannot find the Close button in the search line"
        );

        waitForElementNotPresent(
                By.id("org.wikipedia:id/page_list_item_title"),
                "There is still search results",
                10
        );
    }


    @Test
    public void testSearchAndValidateResults() {
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find the search field on main screen"
        );

        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "Python",
                "Cannot find the search field on search screen"
        );

        List<WebElement> elementsAfterSearch = waitForElementsAndGetList(
                By.id("org.wikipedia:id/page_list_item_title"),
                "There is no results",
                10
        );

        List<String> listOfResults = getTextFromElementsInList(elementsAfterSearch);

        for (String listItem : listOfResults) {
            if (listItem.contains("Python")) {
                assert true;
            }
            else {
                throw new java.lang.Error("Not all results contain the word 'Python'");
            }
        }
    }


    @Test
    public void testCheckTextBeforeSearch()
    {
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find the search field on main screen"
        );

        WebElement element = waitForElementPresent(
                By.id("org.wikipedia:id/search_src_text"),
                "Cannot find the search field on search screen"
        );

        String text_in_the_search_field = element.getAttribute("text");

        Assert.assertEquals(
                "There is no 'Search…' word in the search field!",
                "Search…",
                text_in_the_search_field
        );

        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "Java",
                "Cannot find the search field on search screen"
        );
    }


    @Test
    public void testFindArticleAndCheckTitle()
    {
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find the search field on main screen"
        );

        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "Java",
                "Cannot find the search field on search screen"
        );

        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Cannot find the search result"
        );

        WebElement element_title = waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot find the title",
                10
        );

        String article_title = element_title.getAttribute("text");

        Assert.assertEquals(
                "The title is different!",
                "Java (programming language)",
                article_title
        );
    }

    private WebElement waitForElementPresent(By by, String error_message, long timeoutInSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }


    private WebElement waitForElementPresent(By by, String error_message)
    {
        return waitForElementPresent(by, error_message, 5);
    }


    private WebElement waitForElementAndClick(By by, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.click();
        return element;
    }


    private WebElement waitForElementAndClick(By by, String error_message)
    {
        return waitForElementAndClick(by, error_message, 5);
    }


    private WebElement waitForElementAndSendKeys(By by, String value, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }


    private WebElement waitForElementAndSendKeys(By by, String value, String error_message)
    {
        return waitForElementAndSendKeys(by, value, error_message, 5);
    }


    private boolean waitForElementNotPresent(By by, String error_message, long timeoutInSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }


    private boolean waitForElementNotPresent(By by, String error_message) {
        return waitForElementNotPresent(by, error_message,5);
    }


    private WebElement waitForElementAndClear(By by, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.clear();
        return element;
    }


    private WebElement waitForElementAndClear (By by, String error_message)
    {
        return waitForElementAndClear(by, error_message, 5);
    }

    private List<WebElement> waitForElementsAndGetList(By by, String error_message, long timeoutInSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.presenceOfAllElementsLocatedBy(by)
        );
    }

    private List<String> getTextFromElementsInList(List<WebElement> listOfWebElements) {
        List<String> resultList = new ArrayList();
        for (WebElement element : listOfWebElements) {
            String elementText = element.getAttribute("text");
            resultList.add(elementText);
        }
        return resultList;
    }
}