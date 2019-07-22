package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject
{
    private static final String
    TITLE = "org.wikipedia:id/view_page_title_text",
    FOOTER = "//*[@resource-id='org.wikipedia:id/page_external_link'][@text='View page in browser']",
    OPTIONS_BUTTON = "//android.widget.ImageView[@content-desc='More options']",
    ADD_TO_LIST_BUTTON = "//*[@text='Add to reading list']",
    OK_ONBOARDING_BUTTON = "org.wikipedia:id/onboarding_button",
    MY_LIST_NAME_FIELD = "org.wikipedia:id/text_input",
    CREATE_MY_LIST_BUTTON = "android:id/button1",
    CLOSE_ARTICLE_BUTTON = "//android.widget.ImageButton[@content-desc='Navigate up']";

    public ArticlePageObject(AppiumDriver driver)
    {
        super(driver);
    }


    public WebElement waitForTitleElement()
    {
        return this.waitForElementPresent(By.id(TITLE), "Cannot find the article title");
    }

    public String getArticleTitle()
    {
       return waitForTitleElement().getAttribute("text");
    }


    public void swipeToFooter()
    {
        this.swipeUpToFindElement(By.xpath(FOOTER), "Cannot swipe to the footer using 20 swipes", 20);
    }


    public void createListAndAddArticle(String folderName) throws InterruptedException
    {

        this.checkElementIsMoving(By.xpath(OPTIONS_BUTTON));

        this.waitForElementAndClick(
                By.xpath(OPTIONS_BUTTON),
                "Cannot find the 'Option' button"
        );

        this.checkElementIsMoving(By.xpath(ADD_TO_LIST_BUTTON));

        this.waitForElementAndClick(
                By.xpath(ADD_TO_LIST_BUTTON),
                "Cannot find the 'Add to list' button"
        );


        this.waitForElementAndClick(
                By.id(OK_ONBOARDING_BUTTON),
                "Cannot find the 'Ok onboarding' button"
        );


        this.waitForElementAndClear(
                By.id(MY_LIST_NAME_FIELD),
                "Cannot find the 'Name for list' field"
        );

        this.waitForElementAndSendKeys(
                By.id(MY_LIST_NAME_FIELD),
                folderName,
                "Cannot find the 'Name for list' field"
        );

        this.waitForElementAndClick(
                By.id(CREATE_MY_LIST_BUTTON),
                "Cannot find the 'Create list' button"
        );
    }

    public void addArticleToExistedList(String folderName) throws InterruptedException
    {
        this.checkElementIsMoving(By.xpath(OPTIONS_BUTTON));

        this.waitForElementAndClick(
                By.xpath(OPTIONS_BUTTON),
                "Cannot find the 'Option' button"
        );

        this.checkElementIsMoving(By.xpath(ADD_TO_LIST_BUTTON));

        this.waitForElementAndClick(
                By.xpath(ADD_TO_LIST_BUTTON),
                "Cannot find the 'Add to list' button"
        );

        this.waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/item_title'][@text='" + folderName +"']"),
                "Cannot find created folder " + folderName
        );
    }


    public void closeArticle ()
    {
        this.waitForElementAndClick(
                By.xpath(CLOSE_ARTICLE_BUTTON),
                "Cannot find the 'Close' button"
        );
    }


    public void checkTitleWithoutWait()
    {
        this.assertElementPresent(
                By.id(TITLE),
                "Cannot find the title"
        );
    }



}
