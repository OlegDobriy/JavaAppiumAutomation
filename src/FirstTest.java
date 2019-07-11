import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.MainPageObject;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import java.util.List;
import lib.ui.SearchPageObject;


public class FirstTest extends CoreTestCase
{

    private MainPageObject MainPageObject;
    protected void setUp() throws Exception
    {
        super.setUp();

        MainPageObject = new MainPageObject(driver);
    }


    @Test
    public void testSearchAndVerifyResult()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.fillSearchField("Java");
        SearchPageObject.waitForSearchResult("Java (programming language)");
    }


    @Test
    public void testCloseSearch()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.fillSearchField("Java");
        SearchPageObject.waitForSearchCancelButtonToAppear();
        SearchPageObject.clickSearchCancelButton();  // первый раз очищает введенный в поиск текст
        SearchPageObject.clickSearchCancelButton();  // второй раз выходит из экрана поиска
        SearchPageObject.waitForSearchCancelButtonToDisappear();
    }


  @Test
    public void testSearchAndClose()
    {
        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find the search field on main screen"
        );

        MainPageObject.waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "Python",
                "Cannot find the search field on search screen"
        );

        List elementsAfterSearch = MainPageObject.waitForElementsAndGetList(
                By.id("org.wikipedia:id/page_list_item_title"),
                "There is no results",
                10
        );

        Assert.assertTrue(
    public void testSearchAndClose() throws InterruptedException  // ex8
  {
        String searchRequest = "Python";
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.fillSearchField(searchRequest);
        int quantityOfSearchResults = SearchPageObject.getAmountOfFoundResults();
        assertTrue(
                "There is less than 2 search results",
                elementsAfterSearch.size() > 2
        );

        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_close_btn"),
                "Cannot find the Close button in the search line"
        );

        MainPageObject.waitForElementNotPresent(
                By.id("org.wikipedia:id/page_list_item_title"),
                "There is still search results",
                10
                quantityOfSearchResults > 2
        );
        SearchPageObject.clickSearchCancelButton();
        SearchPageObject.assertThereIsNoResultAfterSearch();
    }


    @Test
    public void testSearchAndValidateResults() {
        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find the search field on main screen"
        );

        MainPageObject.waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "Python",
                "Cannot find the search field on search screen"
        );

        List<WebElement> elementsAfterSearch = MainPageObject.waitForElementsAndGetList(
                By.id("org.wikipedia:id/page_list_item_title"),
                "There is no results",
                10
        );

        List<String> listOfResults = MainPageObject.getTextFromElementsInList(elementsAfterSearch);
        int wrongResultCount = 0;

        for (String listItem : listOfResults) {
            if (listItem.contains("Python")) {
                assert true;
            }
            else {
                System.out.println("The title '" + listItem + "' does not contain the word 'Python'");
                ++wrongResultCount;
            }
        }

        if (wrongResultCount > 0) {
                throw new java.lang.Error("Not all results contain the word 'Python'. Quantity of wrong topics is " + wrongResultCount);
        }
    }


    @Test
    public void testCheckTextBeforeSearch()
    {
        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find the search field on main screen"
        );

        WebElement element = MainPageObject.waitForElementPresent(
                By.id("org.wikipedia:id/search_src_text"),
                "Cannot find the search field on search screen"
        );

        String text_in_the_search_field = element.getAttribute("text");

        Assert.assertEquals(
                "There is no 'Search…' word in the search field!",
                "Search…",
                text_in_the_search_field
        );

        MainPageObject.waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "Java",
                "Cannot find the search field on search screen"
        );
    }


    @Test
    public void testFindArticleAndCheckTitle()
    {

        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.fillSearchField("Java");
        SearchPageObject.waitForSearchResult("Java (programming language)");
        SearchPageObject.waitForSearchResultAndClick("Java (programming language)");

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);

        String articleTitle = ArticlePageObject.getArticleTitle();

        Assert.assertEquals(
                "The title is different!",
                "Java (programming language)",
                articleTitle
        );
    }


    @Test
    public void testSwipeArticle()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.fillSearchField("Appium");
        SearchPageObject.waitForSearchResult("Appium");
        SearchPageObject.waitForSearchResultAndClick("Appium");

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);

        ArticlePageObject.waitForTitleElement();
        ArticlePageObject.swipeToFooter();
    }


        @Test
        public void testAddFirstArticleToListAndDelete() throws InterruptedException
        {
            MainPageObject.waitForElementAndClick(
                    By.id("org.wikipedia:id/search_container"),
                    "Cannot find the search field on main screen"
            );

            String articleName = "Appium";

            MainPageObject.waitForElementAndSendKeys(
                    By.id("org.wikipedia:id/search_src_text"),
                    articleName,
                    "Cannot find the search field on search screen"
            );

            MainPageObject.waitForElementAndClick(
                    By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='Appium']"),
                    "Cannot find the search result"
            );

            MainPageObject.waitForElementPresent(
                    By.id("org.wikipedia:id/view_page_title_text"),
                    "Cannot find the title",
                    10
            );

            MainPageObject.waitForElementAndClick(
                    By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                    "Cannot find the 'Option' button"
            );

            Thread.sleep(1000);  // ПЕРВЫЙ СЛИП, тут тоже без sleep'a иногда мисклик по другим кнопкам

            MainPageObject.waitForElementAndClick(
                    By.xpath("//*[@text='Add to reading list']"),
                    "Cannot find the 'Add to list' button"
            );

            MainPageObject.waitForElementAndClick(
                    By.id("org.wikipedia:id/onboarding_button"),
                    "Cannot find the 'Ok onboarding' button"
            );

            MainPageObject.waitForElementAndClear(
                    By.id("org.wikipedia:id/text_input"),
                    "Cannot find the 'Name for list' field"
            );

            String folderName = "Autotest";

            MainPageObject.waitForElementAndSendKeys(
                    By.id("org.wikipedia:id/text_input"),
                    folderName,
                    "Cannot find the 'Name for list' field"
            );

            MainPageObject.waitForElementAndClick(
                    By.id("android:id/button1"),
                    "Cannot find the 'Create list' button"
            );

            MainPageObject.waitForElementAndClick(
                    By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                    "Cannot find the 'Close' button"
            );

            MainPageObject.waitForElementAndClick(
                    By.xpath("//android.widget.FrameLayout[@content-desc='My lists']"),
                    "Cannot find the 'My lists' button"
            );

            Thread.sleep(1000);  // ВТОРОЙ слип, без этого иногда тапает не по папке, а по кнопке My list

            MainPageObject.waitForElementAndClick(
                    By.xpath("//android.widget.TextView[@text='" + folderName + "']"),
                    "Cannot find the created folder"
            );

            MainPageObject.swipeElementToLeft(
                    By.xpath("//android.widget.TextView[@text='" + articleName + "']"),
                    "Cannot find the saved article"
            );

            MainPageObject.waitForElementNotPresent(
                    By.xpath("//android.widget.TextView[@text='" + articleName + "']"),
                    "Cannot delete the saved article"
            );
        }

    @Test
    public void testAmountOfNonEmptySearch()
    {
        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find the search field on main screen"
        );

        String articleName = "Linkin Park discography";

        MainPageObject.waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                articleName,
                "Cannot find the search field on search screen"
        );

        String searchResultLocator = "//*[@resource-id='org.wikipedia:id/search_results_list']/*[@resource-id='org.wikipedia:id/page_list_item_container']";

        MainPageObject.waitForElementPresent(
                By.xpath(searchResultLocator),
                "Cannot find results for request: " + articleName,
                10
        );

        int amountOfSearchResults = MainPageObject.getAmountOfElements(
                By.xpath(searchResultLocator)
        );

        Assert.assertTrue(
                "There are too few search results",
                amountOfSearchResults > 0
        );
    }

    @Test
    public void testAmountOfEmptySearch() throws InterruptedException
    {
        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find the search field on main screen"
        );

        String searchRequest = "sgsgsgsgsgsg";

        MainPageObject.waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                searchRequest,
                "Cannot find the search field on search screen"
        );

        String searchResultLocator = "//*[@resource-id='org.wikipedia:id/search_results_list']/*[@resource-id='org.wikipedia:id/page_list_item_container']";
        String noResultsFoundLocator = "//*[@text='No results found']";

        MainPageObject.waitForElementPresent(
                By.xpath(noResultsFoundLocator),
                "'No results' label was't found for request: " + searchRequest
        );

        Thread.sleep(1000);

        MainPageObject.assertElementNotPresent(
                By.xpath(searchResultLocator),
                "Search results exist for request: " + searchRequest
        );
    }

    @Test
    public void testRotateAfterSearchAndCheckTitle()
    {
        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find the search field on main screen"
        );

        String searchRequest = "Appium";

        MainPageObject.waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                searchRequest,
                "Cannot find the search field on search screen"
        );

        String searchResult = "//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='" + searchRequest + "']";

        MainPageObject.waitForElementAndClick(
                By.xpath(searchResult),
                "Cannot find the search result for request: " + searchRequest
        );

        String titleBeforeRotation = MainPageObject.waitForElementAndGetAttribute(
                By.id("org.wikipedia:id/view_page_title_text"),
                "text",
                "Cannot find the article title",
                15
        );

        driver.rotate(ScreenOrientation.LANDSCAPE);

        String titleAfterRotation = MainPageObject.waitForElementAndGetAttribute(
                By.id("org.wikipedia:id/view_page_title_text"),
                "text",
                "Cannot find the article title",
                15
        );

        Assert.assertEquals(
                "Article title is different after rotation",
                titleBeforeRotation,
                titleAfterRotation
        );

        driver.rotate(ScreenOrientation.PORTRAIT);

        String titleAfterSecondRotation = MainPageObject.waitForElementAndGetAttribute(
                By.id("org.wikipedia:id/view_page_title_text"),
                "text",
                "Cannot find the article title",
                5
        );

        Assert.assertEquals(
                "Article title is different after rotation",
                titleBeforeRotation,
                titleAfterSecondRotation
        );
    }

    @Test
    public void testTurnToBackgroundAfterSearchAndCheckTitle()
    {
        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find the search field on main screen"
        );

        String searchRequest = "Appium";

        MainPageObject.waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                searchRequest,
                "Cannot find the search field on search screen"
        );

        String searchResult = "//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='" + searchRequest + "']";

        MainPageObject.waitForElementPresent(
                By.xpath(searchResult),
                "Cannot find the search result for request: " + searchRequest
        );

        driver.runAppInBackground(2);

        MainPageObject.waitForElementPresent(
                By.xpath(searchResult),
                "Cannot find the search result after returning from background"
        );

    }

    @Test
    public void testAddTwoArticlesToListAndDeleteOne() throws InterruptedException
    public void testAddTwoArticlesToListAndDeleteOne() throws InterruptedException  // ex8
    {
        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find the search field on main screen"
        );

        String firstArticleName = "Appium";

        MainPageObject.waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                firstArticleName,
                "Cannot find the search field on search screen"
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='" + firstArticleName + "']"),
                "Cannot find the search result"
        );

        MainPageObject.waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot find the title",
                15
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                "Cannot find the 'Option' button"
        );

        Thread.sleep(1000);

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='Add to reading list']"),
                "Cannot find the 'Add to list' button"
        );

        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/onboarding_button"),
                "Cannot find the 'Ok onboarding' button"
        );

        MainPageObject.waitForElementAndClear(
                By.id("org.wikipedia:id/text_input"),
                "Cannot find the 'Name for list' field"
        );

        String folderName = "Autotest";

        MainPageObject.waitForElementAndSendKeys(
                By.id("org.wikipedia:id/text_input"),
                folderName,
                "Cannot find the 'Name for list' field"
        );

        MainPageObject.waitForElementAndClick(
                By.id("android:id/button1"),
                "Cannot find the 'Create list' button"
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot find the 'Close' button after adding the first article " + firstArticleName
        );

        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find the search field on main screen"
        );

        String secondArticleName = "Java";

        MainPageObject.waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                secondArticleName,
                "Cannot find the search field on search screen"
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='" + secondArticleName + "']"),
                "Cannot find the search result"
        );

        MainPageObject.waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot find the title",
                15
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                "Cannot find the 'Option' button"
        );

        Thread.sleep(1000);

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='Add to reading list']"),
                "Cannot find the 'Add to list' button"
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/item_title'][@text='" + folderName +"']"),
                "Cannot find created folder " + folderName
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot find the 'Close' button after adding the second article " + secondArticleName
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.FrameLayout[@content-desc='My lists']"),
                "Cannot find the 'My lists' button"
        );

        Thread.sleep(1000);

        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.TextView[@text='" + folderName + "']"),
                "Cannot find the created folder"
        );

        MainPageObject.swipeElementToLeft(
                By.xpath("//android.widget.TextView[@text='" + firstArticleName + "']"),
                "Cannot find the saved article"
        );

        MainPageObject.waitForElementNotPresent(
                By.xpath("//android.widget.TextView[@text='" + firstArticleName + "']"),
                "Cannot delete the first saved article " + firstArticleName
        );

        MainPageObject.waitForElementPresent(
                By.xpath("//android.widget.TextView[@text='" + secondArticleName + "']"),
                "Cannot find the second saved article " + secondArticleName
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.TextView[@text='" + secondArticleName + "']"),
                "Cannot find the second saved article " + secondArticleName
        );

        MainPageObject.waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/view_page_title_text'][@text='" + secondArticleName + "']"),
                "Cannot find the title for the second article " + secondArticleName,
                15
        );
        String
                firstSearchRequest = "Appium",
                secondSearchRequest = "Method",
                folderName = "testAddTwoArticlesToListAndDeleteOne";
        // first article
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.fillSearchField(firstSearchRequest);
        SearchPageObject.waitForSearchResultByTitle(firstSearchRequest);
        SearchPageObject.waitForSearchResultAndClick(firstSearchRequest);
        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        ArticlePageObject.createListAndAddArticle(folderName);
        ArticlePageObject.closeArticle();
        // second article
        SearchPageObject.initSearchInput();
        SearchPageObject.fillSearchField(secondSearchRequest);
        SearchPageObject.waitForSearchResultByTitle(secondSearchRequest);
        SearchPageObject.waitForSearchResultAndClick(secondSearchRequest);
        ArticlePageObject.addArticleToExistedList(folderName);
        ArticlePageObject.closeArticle();
        NavigationUI NavigationUI = new NavigationUI(driver);
        // my list
        NavigationUI.openMyList();
        MyListsPageObject MyListsPageObject = new MyListsPageObject(driver);
        MyListsPageObject.openMyFolderByName(folderName);
        MyListsPageObject.swipeArticleToDelete(firstSearchRequest);
        MyListsPageObject.waitForArticleToDisappearInMyLists(firstSearchRequest);
        MyListsPageObject.checkArticleTitleByName(secondSearchRequest);
    }

    @Test
    public void testCheckArticleTitleWithoutWait() throws InterruptedException {
        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find the search field on main screen"
        );

    @Test
    public void testCheckArticleTitleWithoutWait()  // ex8
    {
        String searchRequest = "Appium";
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.fillSearchField(searchRequest);
        SearchPageObject.waitForSearchResultByTitle(searchRequest);
        SearchPageObject.waitForSearchResultAndClick(searchRequest);
        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        ArticlePageObject.checkTitleWithoutWait();

        MainPageObject.waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                searchRequest,
                "Cannot find the search field on search screen"
        );
    }

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='" + searchRequest + "']"),
                "Cannot find the search result"
        );

        MainPageObject.assertElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/view_page_title_text'][@text='" + searchRequest + "']"),
                "Cannot find the title for the request: '" + searchRequest + "'"
        );
    @Test
    public void testSearchAndCheckResultByTitleAndDescription()  // ex9*
    {
        String
                title = "Java",
                description = "Island of Indonesia";
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.fillSearchField(title);
        SearchPageObject.waitForSearchResultByTitleAndDescription(title, description);
    }
}