package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.ArticlePageObject;

public class AndroidArticlePageObject extends ArticlePageObject
{
    static {
        TITLE = "";
        FOOTER = "";
        OPTIONS_BUTTON = "";
        ADD_TO_LIST_BUTTON = "";
        OK_ONBOARDING_BUTTON = "";
        MY_LIST_NAME_FIELD = "";
        CREATE_MY_LIST_BUTTON = "";
        CLOSE_ARTICLE_BUTTON = "";
    }


    public AndroidArticlePageObject(AppiumDriver driver)
    {
        super(driver);
    }
}
