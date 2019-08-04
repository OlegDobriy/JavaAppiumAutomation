package tests;

import lib.CoreTestCase;
import lib.ui.*;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;


public class ArticleTests extends CoreTestCase
{
    protected void setUp() throws Exception
    {
        super.setUp();
    }


    @Test
    public void testFindArticleAndCheckTitle() throws InterruptedException
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.fillSearchField("Java");
        SearchPageObject.waitForSearchResultByTitle("Java (programming language)");
        SearchPageObject.waitForSearchResultAndClick("Java (programming language)");
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        String articleTitle = ArticlePageObject.getArticleTitle();
        assertEquals(
                "The title is different!",
                "Java (programming language)",
                articleTitle
        );
    }


    @Test
    public void testSwipeArticle() throws InterruptedException
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.fillSearchField("Java");
        SearchPageObject.waitForSearchResultByTitle("Java");
        SearchPageObject.waitForSearchResultAndClick("Object-oriented programming language");
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForTitleElement();
        ArticlePageObject.swipeToFooter();
    }


    @Test
    public void testCheckArticleTitleWithoutWait() throws InterruptedException  // ex8
    {
        String searchRequest = "Appium";
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.fillSearchField(searchRequest);
        SearchPageObject.waitForSearchResultByTitle(searchRequest);
        SearchPageObject.waitForSearchResultAndClick(searchRequest);
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.checkTitleWithoutWait();
    }
}