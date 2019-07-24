package tests;

import lib.CoreTestCase;
import lib.ui.*;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;
import org.openqa.selenium.WebElement;


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
    public void testFindArticleAndCheckTitle()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.fillSearchField("Java");
        SearchPageObject.waitForSearchResultByTitle("Java (programming language)");
        SearchPageObject.waitForSearchResultAndClick("Java (programming language)");
        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        String articleTitle = ArticlePageObject.getArticleTitle();
        assertEquals(
                "The title is different!",
                "Java (programming language)",
                articleTitle
        );
    }


    @Test
    public void testSwipeArticle()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.fillSearchField("Appium");
        SearchPageObject.waitForSearchResultByTitle("Appium");
        SearchPageObject.waitForSearchResultAndClick("Appium");
        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        ArticlePageObject.waitForTitleElement();
        ArticlePageObject.swipeToFooter();
    }


        @Test
        public void testAddFirstArticleToListAndDelete() throws InterruptedException
        {
            String searchRequest = "Java (programming language)";
            String folderName = "test: add to my list and delete";
            SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
            SearchPageObject.initSearchInput();
            SearchPageObject.fillSearchField(searchRequest);
            SearchPageObject.waitForSearchResultByTitle(searchRequest);
            SearchPageObject.waitForSearchResultAndClick(searchRequest);
            ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
            ArticlePageObject.createListAndAddArticle(folderName);
            ArticlePageObject.closeArticle();
            NavigationUI NavigationUI = new NavigationUI(driver);
            NavigationUI.openMyList();
            Thread.sleep(1000);  // ВТОРОЙ слип, без этого иногда тапает не по папке, а по кнопке My list
            MyListsPageObject MyListsPageObject = new MyListsPageObject(driver);
            MyListsPageObject.openMyFolderByName(folderName);
            MyListsPageObject.swipeArticleToDelete(searchRequest);
            MyListsPageObject.waitForArticleToDisappearInMyLists(searchRequest);
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
    public void testRotateAfterSearchAndCheckTitle()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.fillSearchField("Java");
        SearchPageObject.waitForSearchResultByTitle("Java (programming language)");
        SearchPageObject.waitForSearchResultAndClick("Java (programming language)");
        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        String titleBeforeRotation = ArticlePageObject.getArticleTitle();
        this.rotateToLandscape();
        String titleAfterRotation = ArticlePageObject.getArticleTitle();
        assertEquals(
                "Article title is different after rotation",
                titleBeforeRotation,
                titleAfterRotation
        );
        this.rotateToPortrait();
        String titleAfterSecondRotation = ArticlePageObject.getArticleTitle();
        assertEquals(
                "Article title is different after rotation",
                titleBeforeRotation,
                titleAfterSecondRotation
        );
    }


    @Test
    public void testTurnToBackgroundAfterSearchAndCheckResult()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.fillSearchField("Java");
        SearchPageObject.waitForSearchResultByTitle("Java (programming language)");
        this.backgroundApp(2);
        SearchPageObject.waitForSearchResultByTitle("Java (programming language)");
    }

    @Test
    public void testAddTwoArticlesToListAndDeleteOne() throws InterruptedException  // ex8
    {
        String
                firstSearchRequest = "Appium",
                secondSearchRequest = "Method",
                folderName = "testAddTwoArticlesToListAndDeleteOne";
        // first article
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
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
    public void testCheckArticleTitleWithoutWait()  // ex8
    {
        String searchRequest = "Appium";
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.fillSearchField(searchRequest);
        SearchPageObject.waitForSearchResultByTitle(searchRequest);
        SearchPageObject.waitForSearchResultAndClick(searchRequest);
        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        ArticlePageObject.checkTitleWithoutWait();

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