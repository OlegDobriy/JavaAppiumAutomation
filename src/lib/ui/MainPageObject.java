package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class MainPageObject
{
    protected AppiumDriver driver;

    public MainPageObject(AppiumDriver driver)
    {
        this.driver = driver;
    }

    public WebElement waitForElementPresent(By by, String error_message, long timeoutInSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }


    public WebElement waitForElementPresent(By by, String errorMessage)
    {
        return waitForElementPresent(by, errorMessage, 5);
    }


    public WebElement waitForElementAndClick(By by, String errorMessage, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(by, errorMessage, timeoutInSeconds);
        element.click();
        return element;
    }


    public WebElement waitForElementAndClick(By by, String errorMessage)
    {
        return waitForElementAndClick(by, errorMessage, 5);
    }


    public WebElement waitForElementAndSendKeys(By by, String value, String errorMessage, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(by, errorMessage, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }


    public WebElement waitForElementAndSendKeys(By by, String value, String errorMessage)
    {
        return waitForElementAndSendKeys(by, value, errorMessage, 5);
    }


    public boolean waitForElementNotPresent(By by, String errorMessage, long timeoutInSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(errorMessage + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }


    public boolean waitForElementNotPresent(By by, String errorMessage) {
        return waitForElementNotPresent(by, errorMessage,5);
    }


    public WebElement waitForElementAndClear(By by, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.clear();
        return element;
    }


    public WebElement waitForElementAndClear (By by, String errorMessage)
    {
        return waitForElementAndClear(by, errorMessage, 5);
    }

    public List<WebElement> waitForElementsAndGetList(By by, String errorMessage, long timeoutInSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(errorMessage + "\n");
        return wait.until(
                ExpectedConditions.presenceOfAllElementsLocatedBy(by)
        );
    }

    public List<String> getTextFromElementsInList(List<WebElement> listOfWebElements)
    {
        List<String> resultList = new ArrayList();
        for (WebElement element : listOfWebElements) {
            String elementText = element.getAttribute("text");
            resultList.add(elementText);
        }
        return resultList;
    }

    protected void swipeUp (int timeOfSwipe)
    {
        TouchAction action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize(); // получить размеры устройства

        int x = size.width / 2; // середина устройства по оси x
        int y_start = (int) (size.height * 0.8);
        int y_end = (int) (size.height * 0.2);

        action
                .press(x, y_start)
                .waitAction(timeOfSwipe)
                .moveTo(x, y_end)
                .release()
                .perform();

    }

    protected void swipeUpQuick ()
    {
        swipeUp(200);
    }

    public void swipeUpToFindElement(By by, String errorMessage, int maxSwipes)
    {
        int alreadySwiped = 0;
        while (driver.findElements(by).size() == 0) {

            if (alreadySwiped > maxSwipes) {
                waitForElementPresent(by, "Cannot find the element by swiping. \n" + errorMessage);
                return;
            }
            swipeUpQuick();
            ++alreadySwiped;
        }
    }

    public void swipeElementToLeft (By by, String errorMessage)
    {
        WebElement element = waitForElementPresent(by,
                errorMessage,
                5);

        int x_left = element.getLocation().getX(); // координата x эелемента (слева)
        int x_right = x_left + element.getSize().getWidth(); // координата x эелемента (справа)
        int y_top = element.getLocation().getY(); // координата y эелемента (сверху)
        int y_bottom = y_top + element.getSize().getHeight(); // координата y эелемента (снизу)
        int y_middle = (y_top + y_bottom) / 2; // координата y эелемента (середина)

        TouchAction action = new TouchAction(driver);

        action
                .press(x_right, y_middle)
                .waitAction(300)
                .moveTo(x_left, y_middle)
                .release()
                .perform();
    }

    public int getAmountOfElements(By by)
    {
        List elements = driver.findElements(by);
        return elements.size();
    }


    public void assertElementNotPresent(By by, String errorMessage, int timeoutInSeconds)
    {
        int amountOfElements = getAmountOfElements(by);
        if (amountOfElements > 0)
        {
            String defaultMessage = "An element '" + by.toString() + "' should not be present.";
            throw new AssertionError(defaultMessage + " " + errorMessage);
        }
    }

    public String waitForElementAndGetAttribute(By by, String attribute, String errorMessage, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(by, errorMessage, timeoutInSeconds);
        return element.getAttribute(attribute);
    }

    public boolean assertElementPresent(By by, String errorMessage)
    {
        try {
            driver.findElement(by);
            return true;
        }

        catch(NoSuchElementException exception) {
            throw new AssertionError("There is no such element. " + errorMessage);
        }
    }

    public void checkElementIsMoving(By by) throws InterruptedException
    {
        String errorMessage = "Cannot find the element: " + by.toString();
        System.out.println("Checking if the element is moving: " + by.toString());
        Point location1 = waitForElementPresent(by, errorMessage).getLocation();
        Point location2 = waitForElementPresent(by, errorMessage).getLocation();
        while (!(location1.equals(location2)))
        {
            System.out.println("location1 = " + location1 + "\nlocation2 = " + location2 + "\n---");
            location1 = waitForElementPresent(by, errorMessage).getLocation();
            Thread.sleep(2);
            location2 = waitForElementPresent(by, errorMessage).getLocation();
            System.out.println("location1 = " + location1 + "\nlocation2 = " + location2 + "\n---");
        }
    }
}
