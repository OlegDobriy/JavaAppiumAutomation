package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class MyListsPageObject extends MainPageObject
{

    private static final String
            FOLDER_BY_NAME_TPL = "//android.widget.TextView[@text='{FOLDER_NAME}']",
            ARTICLE_IN_MY_LISTS_BY_NAME_TPL = "//android.widget.TextView[@text='{ARTICLE_TITLE}']",
            ARTICLE_TITLE_ON_ARTICLE_SCREEN_BY_NAME_TPL = "//*[@resource-id='org.wikipedia:id/view_page_title_text'][@text='{ARTICLE_TITLE}']",
            SEARCH_BUTTON = "org.wikipedia:id/menu_search_lists";


    /* TEMPLATE METHODS */
    private static String getFolderXpathInMyLists(String folderName)
    {
        return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}", folderName);
    }


    private static String getArticleXpathInMyLists(String articleTitle)
    {
        return ARTICLE_IN_MY_LISTS_BY_NAME_TPL.replace("{ARTICLE_TITLE}", articleTitle);
    }

    private static String getArticleXpathOnArticleScreen(String articleTitle)
    {
        return ARTICLE_TITLE_ON_ARTICLE_SCREEN_BY_NAME_TPL.replace("{ARTICLE_TITLE}", articleTitle);
    }

    /* TEMPLATE METHODS */


    public MyListsPageObject(AppiumDriver driver)
    {
        super(driver);
    }

    public void openMyFolderByName (String folderName) throws InterruptedException
    {
        this.checkElementIsMoving(By.id(SEARCH_BUTTON));

        String folderXpath = getFolderXpathInMyLists(folderName);
        this.waitForElementAndClick(
                By.xpath(folderXpath),
                "Cannot find the created folder"
        );
    }

    public void swipeArticleToDelete(String articleTitle)  // в папке выводится title фактического результата поиска, а не запрос
    {
        this.waitForArticleToAppearInMyLists(articleTitle);
        String articleXpath = getArticleXpathInMyLists(articleTitle);
        this.swipeElementToLeft(
                By.xpath(articleXpath),
                "Cannot find the saved article"
        );
    }

    public void waitForArticleToAppearInMyLists(String articleTitle)  // в папке выводится title фактического результата поиска, а не запрос
    {
        String articleXpath = getArticleXpathInMyLists(articleTitle);
        this.waitForElementPresent(
                By.xpath(articleXpath),
                "Article doesn't present. Article name is " + articleTitle);
    }

    public void waitForArticleToDisappearInMyLists(String articleName)
    {
        String articleXpath = getArticleXpathInMyLists(articleName);
        this.waitForElementNotPresent(
                By.xpath(articleXpath),
                "Article is still present. Article name is " + articleName);
    }

    public void checkArticleTitleByName(String articleTitle)
    {
        String articleXpath = getArticleXpathInMyLists(articleTitle);
        this.waitForElementAndClick(
                By.xpath(articleXpath),
                "Article doesn't present in the folder. Expected article: " + articleTitle);
        String articleTitleXpath = getArticleXpathOnArticleScreen(articleTitle);
        this.waitForElementPresent(
                By.xpath(articleTitleXpath),
                "Cannot find the article title. Expected article: " + articleTitle,
                15
        );
    }
}
