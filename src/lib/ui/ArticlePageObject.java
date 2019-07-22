package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject
{
    private static final String
    TITLE = "id:org.wikipedia:id/view_page_title_text",
    FOOTER = "xpath://*[@resource-id='org.wikipedia:id/page_external_link'][@text='View page in browser']",
    OPTIONS_BUTTON = "xpath://android.widget.ImageView[@content-desc='More options']",
    ADD_TO_LIST_BUTTON = "xpath://*[@text='Add to reading list']",
    OK_ONBOARDING_BUTTON = "id:org.wikipedia:id/onboarding_button",
    MY_LIST_NAME_FIELD = "id:org.wikipedia:id/text_input",
    CREATE_MY_LIST_BUTTON = "id:android:id/button1",
    CLOSE_ARTICLE_BUTTON = "xpath://android.widget.ImageButton[@content-desc='Navigate up']";

    public ArticlePageObject(AppiumDriver driver)
    {
        super(driver);
    }


    public WebElement waitForTitleElement()
    {
        return this.waitForElementPresent(TITLE,
                "Cannot find the article title",
                15);
    }

    public String getArticleTitle()
    {
       return waitForTitleElement().getAttribute("text");
    }


    public void swipeToFooter()
    {
        this.swipeUpToFindElement(FOOTER, "Cannot swipe to the footer using 20 swipes", 20);
    }


    public void createListAndAddArticle(String folderName) throws InterruptedException
    {

        this.checkElementIsMoving(OPTIONS_BUTTON);

        this.waitForElementAndClick(
                OPTIONS_BUTTON,
                "Cannot find the 'Option' button"
        );

        this.checkElementIsMoving(ADD_TO_LIST_BUTTON);

        this.waitForElementAndClick(
                ADD_TO_LIST_BUTTON,
                "Cannot find the 'Add to list' button"
        );


        this.waitForElementAndClick(
                OK_ONBOARDING_BUTTON,
                "Cannot find the 'Ok onboarding' button"
        );


        this.waitForElementAndClear(
                MY_LIST_NAME_FIELD,
                "Cannot find the 'Name for list' field"
        );

        this.waitForElementAndSendKeys(
                MY_LIST_NAME_FIELD,
                folderName,
                "Cannot find the 'Name for list' field"
        );

        this.waitForElementAndClick(
                CREATE_MY_LIST_BUTTON,
                "Cannot find the 'Create list' button"
        );
    }

    public void addArticleToExistedList(String folderName) throws InterruptedException
    {
        this.checkElementIsMoving(OPTIONS_BUTTON);

        this.waitForElementAndClick(
                OPTIONS_BUTTON,
                "Cannot find the 'Option' button"
        );

        this.checkElementIsMoving(ADD_TO_LIST_BUTTON);

        this.waitForElementAndClick(
                ADD_TO_LIST_BUTTON,
                "Cannot find the 'Add to list' button"
        );

        this.waitForElementAndClick(
                "//*[@resource-id='org.wikipedia:id/item_title'][@text='" + folderName +"']",
                "Cannot find created folder " + folderName
        );
    }


    public void closeArticle ()
    {
        this.waitForElementAndClick(
                CLOSE_ARTICLE_BUTTON,
                "Cannot find the 'Close' button"
        );
    }


    public void checkTitleWithoutWait()
    {
        this.assertElementPresent(
                TITLE,
                "Cannot find the title"
        );
    }



}
