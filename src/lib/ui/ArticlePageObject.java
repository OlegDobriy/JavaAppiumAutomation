package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject
{

    private static final String
    TITLE = "org.wikipedia:id/view_page_title_text",
    FOOTER = "//*[@resource-id='org.wikipedia:id/page_external_link'][@text='View page in browser']";

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

}
