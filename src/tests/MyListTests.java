package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.*;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;


public class MyListTests extends CoreTestCase
{
    protected void setUp() throws Exception
    {
        super.setUp();
    }


    @Test
    public void testAddFirstArticleToListAndDelete() throws InterruptedException
    {
        String
                searchRequest = "Java",
                folderName = "test: add to my list and delete";
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.fillSearchField(searchRequest);
        SearchPageObject.waitForSearchResultByTitle(searchRequest);
        SearchPageObject.waitForSearchResultAndClick(searchRequest);
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        if (Platform.getInstance().isAndroid())
        {
            ArticlePageObject.createListAndAddArticle(folderName);
        }
        else
        {
            ArticlePageObject.addFirstArticleToMyList();
        }
        ArticlePageObject.closeArticle();
        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        NavigationUI.openMyList();
        Thread.sleep(1000);  // ВТОРОЙ слип, без этого иногда тапает не по папке, а по кнопке My list
        MyListPageObject MyListPageObject = MyListPageObjectFactory.get(driver);
        if (Platform.getInstance().isAndroid())
        {
            MyListPageObject.openMyFolderByName(folderName);
        }
        MyListPageObject.swipeArticleToDelete(searchRequest);
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
}