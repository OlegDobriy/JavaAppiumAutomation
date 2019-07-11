package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SearchPageObject extends MainPageObject
{
    private static final String
    SEARCH_FIELD_ON_MAIN_SCREEN = "//android.widget.TextView[@text='Search Wikipedia']",
    SEARCH_CANCEL_BUTTON = "org.wikipedia:id/search_close_btn",
    SEARCH_FIELD_ON_SEARCH_SCREEN = "//*[@resource-id='org.wikipedia:id/search_src_text'][@text='Search…']",
    SEARCH_RESULT_BY_TITLE_TPL = "//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='{TITLE}']",
    SEARCH_RESULT_ELEMENT = "//android.widget.LinearLayout[@resource-id='org.wikipedia:id/page_list_item_container']",
    SEARCH_RESULT_TITLE_ELEMENT = "//android.widget.TextView[@resource-id='org.wikipedia:id/page_list_item_title']",
    SEARCH_EMPTY_RESULT_ELEMENT = "//*[@text='No results found']",
    SEARCH_RESULT_BY_TITLE_AND_DESCRIPTION_TPL = "//*[*[@text='{TITLE}'] and *[@text='{DESCRIPTION}']]";


    /* TEMPLATE METHODS */
    private static String getSearchResultByTitle(String substring)
    {
        return SEARCH_RESULT_BY_TITLE_TPL.replace("{TITLE}", substring);
    }

    private static String getSearchResultByTitleAndSubtitle(String title, String description)
    {
        return SEARCH_RESULT_BY_TITLE_AND_DESCRIPTION_TPL
                .replace("{TITLE}", title)
                .replace("{DESCRIPTION}", description);
    }
    /* TEMPLATE METHODS */


    public SearchPageObject(AppiumDriver driver)
    {
        super(driver);
    }


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


    public void waitForSearchResultByTitle(String title)
    {
        String searchResultAfterReplace = getSearchResultByTitle(title);
        this.waitForElementPresent(By.xpath(searchResultAfterReplace), "Cannot find the search result: " + title);
    }

    public void waitForSearchResultAndClick(String searchResult)
    {
        String searchResultAfterReplace = getSearchResultByTitle(searchResult);
        this.waitForElementAndClick(By.xpath(searchResultAfterReplace), "Cannot click to the search result: " + searchResult);
    }


    public int getAmountOfFoundResults()
    {
        this.waitForElementPresent(
                By.xpath(SEARCH_RESULT_ELEMENT),
                "Cannot find the search results");
        return this.getAmountOfElements(
                By.xpath(SEARCH_RESULT_ELEMENT));
    }

    public void waitForEmptyResultLabel()
    {
        this.waitForElementPresent(
                By.xpath(SEARCH_EMPTY_RESULT_ELEMENT),
                "'No results' label was't found"
        );
    }


    public void assertThereIsNoResultAfterSearch() throws InterruptedException
    {
        this.checkElementIsMoving(By.xpath(SEARCH_RESULT_ELEMENT));
        this.waitForElementPresent(
                By.xpath(SEARCH_RESULT_ELEMENT),
                "There is still at least one search result for the search"
        );
    }

    public List<WebElement> getListOfSearchResults()
    {
        return this.waitForElementsAndGetList(
                By.xpath(SEARCH_RESULT_TITLE_ELEMENT),
                "Cannot find at least one search result",
                15
        );
    }


    public void checkAllResultsContainSearchRequest(String searchRequest)
    {
        List<WebElement> list = getListOfSearchResults();
        int wrongResultCount = 0;
        List<String> listOfResults = this.getTextFromElementsInList(list);

        for (String listItem : listOfResults) {
            if (listItem.contains(searchRequest)) {
                assert true;
            }
            else {
                System.out.println("The title '" + listItem + "' does not contain the word '" + searchRequest + "'");
                ++wrongResultCount;
            }
        }
        if (wrongResultCount > 0) {
            throw new java.lang.Error("Not all results contain the word '" + searchRequest + "'. Quantity of wrong topics is " + wrongResultCount);
        }
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
