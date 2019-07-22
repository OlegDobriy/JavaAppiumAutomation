package lib.ui;

import io.appium.java_client.AppiumDriver;


public class NavigationUI extends MainPageObject
{
    private static final String
    MY_LIST_BUTTON = "xpath://android.widget.FrameLayout[@content-desc='My lists']";


    public NavigationUI (AppiumDriver driver)
    {
        super(driver);
    }

    public void openMyList()
    {
        this.waitForElementAndClick(
                MY_LIST_BUTTON,
                "Cannot find the 'My lists' button"
        );
    }

}
