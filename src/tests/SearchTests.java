package tests;

import lib.CoreTestCase;
import lib.ui.*;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;
import org.openqa.selenium.WebElement;

public class SearchTests extends CoreTestCase
{

    private lib.ui.MainPageObject MainPageObject;
    protected void setUp() throws Exception
    {
        super.setUp();

        MainPageObject = new MainPageObject(driver);
    }


    @Test
    public void testSearchAndVerifyResult()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.fillSearchField("Java");
        SearchPageObject.waitForSearchResultByTitle("Java (programming language)");
    }


    @Test
    public void testCloseSearch()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.fillSearchField("Java");
        SearchPageObject.waitForSearchCancelButtonToAppear();
        SearchPageObject.clickSearchCancelButton();  // первый раз очищает введенный в поиск текст
        SearchPageObject.clickSearchCancelButton();  // второй раз выходит из экрана поиска
        SearchPageObject.waitForSearchCancelButtonToDisappear();
    }


    @Test
    public void testSearchAndClose() throws InterruptedException  // ex8
    {
        String searchRequest = "Python";
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.fillSearchField(searchRequest);
        int quantityOfSearchResults = SearchPageObject.getAmountOfFoundResults();
        assertTrue(
                "There is less than 2 search results",
                quantityOfSearchResults > 2
        );
        SearchPageObject.clickSearchCancelButton();
        SearchPageObject.assertThereIsNoResultAfterSearch();
    }


    @Test
    public void testSearchAndValidateResults()
    {
        String searchRequest = "Python";
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.fillSearchField(searchRequest);
        SearchPageObject.checkAllResultsContainSearchRequest(searchRequest);
    }


    @Test
    public void testCheckTextBeforeSearch()
    {
        MainPageObject.waitForElementAndClick(
                "id:org.wikipedia:id/search_container",
                "Cannot find the search field on main screen"
        );

        WebElement element = MainPageObject.waitForElementPresent(
                "id:org.wikipedia:id/search_src_text",
                "Cannot find the search field on search screen"
        );

        String text_in_the_search_field = element.getAttribute("text");

        assertEquals(
                "There is no 'Search…' word in the search field!",
                "Search…",
                text_in_the_search_field
        );

        MainPageObject.waitForElementAndSendKeys(
                "id:org.wikipedia:id/search_src_text",
                "Java",
                "Cannot find the search field on search screen"
        );
    }


    @Test
    public void testAmountOfEmptySearch() throws InterruptedException
    {
        String searchRequest = "sgsgsgsgsgsg";
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.fillSearchField(searchRequest);
        SearchPageObject.waitForEmptyResultLabel();
        SearchPageObject.assertThereIsNoResultAfterSearch();
    }


    @Test
    public void testAmountOfNonEmptySearch()
    {
        String searchRequest = "Linkin Park discography";
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.fillSearchField(searchRequest);
        SearchPageObject.waitForSearchResultByTitle(searchRequest);
        int amountOfSearchResults = SearchPageObject.getAmountOfFoundResults();
        assertTrue(
                "There are too few search results",
                amountOfSearchResults > 0
        );
    }


    @Test
    public void testSearchAndCheckResultByTitleAndDescription()  // ex9*
    {
        String
                title = "Java",
                description = "Island of Indonesia";
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.fillSearchField(title);
        SearchPageObject.waitForSearchResultByTitleAndDescription(title, description);
    }
}