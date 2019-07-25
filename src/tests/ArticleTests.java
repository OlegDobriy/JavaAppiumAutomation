package tests;

import lib.CoreTestCase;
import lib.ui.*;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;


public class ArticleTests extends CoreTestCase
{
    protected void setUp() throws Exception
    {
        super.setUp();
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
}