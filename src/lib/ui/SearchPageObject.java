package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class SearchPageObject extends MainPageObject
{
    private static final String
    SEARCH_FIELD_ON_MAIN_SCREEN = "//android.widget.TextView[@text='Search Wikipedia']",
    SEARCH_CANCEL_BUTTON = "org.wikipedia:id/search_close_btn",
    SEARCH_FIELD_ON_SEARCH_SCREEN = "//*[@resource-id='org.wikipedia:id/search_src_text'][@text='Search…']",
    SEARCH_RESULT_BY_SUBSTRING_TPL = "//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='{SUBSTRING}']";
    SEARCH_RESULT_BY_TITLE_AND_DESCRIPTION_TPL = "//*[*[@text='{TITLE}'] and *[@text='{DESCRIPTION}']]";


    public SearchPageObject(AppiumDriver driver)
    {
        super(driver);
    }


    /* TEMPLATE METHODS */
    private static String getSearchResult(String substring)
    {
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
        return SEARCH_RESULT_BY_TITLE_AND_DESCRIPTION_TPL
                .replace("{TITLE}", title)
                .replace("{DESCRIPTION}", description);
    }
    /* TEMPLATE METHODS */


    public void initSearchInput()
    {
        this.waitForElementAndClick(By.xpath(SEARCH_FIELD_ON_MAIN_SCREEN), "Cannot find the search field on main screen");
        this.waitForElementPresent(By.xpath(SEARCH_FIELD_ON_SEARCH_SCREEN), "Cannot find the search field on search screen");
    }


    public void fillSearchField(String searchRequest)
    {
        this.waitForElementAndSendKeys(By.xpath(SEARCH_FIELD_ON_SEARCH_SCREEN), searchRequest, "Cannot find the search field on search screen");
    }

    public void waitForSearchCancelButtonToAppear()
    {
        waitForElementPresent(By.id(SEARCH_CANCEL_BUTTON), "Cannot find the 'Search Cancel' button on search screen");
    }


    public void waitForSearchCancelButtonToDisappear()
    {
        waitForElementNotPresent(By.id(SEARCH_CANCEL_BUTTON), "'Search Cancel' button is still on search screen");
    }


    public void clickSearchCancelButton()
    {
        waitForElementAndClick(By.id(SEARCH_CANCEL_BUTTON), "Cannot click the 'Search Cancel' button on search screen");
    }


    public void waitForSearchResult(String searchResult)
    {
        String searchResultAfterReplace = getSearchResult(searchResult);
        this.waitForElementPresent(By.xpath(searchResultAfterReplace), "Cannot find the search result: " + searchResult);
    }

    public void waitForSearchResultAndClick(String searchResult)
    {
        String searchResultAfterReplace = getSearchResult(searchResult);
        this.waitForElementAndClick(By.xpath(searchResultAfterReplace), "Cannot click to the search result: " + searchResult);
    }
    public void waitForSearchResultByTitleAndDescription(String title, String description)
    {
        String searchResultXpath = getSearchResultByTitleAndSubtitle(title, description);
        this.waitForElementPresent(
                By.xpath(searchResultXpath),
                "Cannot find the search result title: " + title + ". Search result description: " + description,
        15
        );
    }
}
