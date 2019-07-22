package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class WelcomePageObject extends MainPageObject
{
    private static final String
            STEP_LEARM_MORE_ABOUT_WIKIPEDIA_LINK = "Lear more about Wikipedia",
            STEP_NEW_WAYS_TO_EXPLORE_TITLE = "New ways to explore",
            STEP_ADD_OR_EDIT_PREFERRED_LANGUAGES_LINK = "Add or edit preferred languages",
            STEP_LEARN_MORE_ABOUT_DATA_COLLECTED_LINK = "Learn more about data collected",
            NEXT_LINK = "NEXT",
            GET_STARTED_BUTTON = "Get started";


    public WelcomePageObject(AppiumDriver driver)
    {
        super(driver);
    }


    public void waitForLearMoreLink()
    {
        this.waitForElementPresent(
                By.id(STEP_LEARM_MORE_ABOUT_WIKIPEDIA_LINK),
                "Cannot find 'Lear more about Wikipedia' link",
                10);
    }


    public void waitForNewWaysToExplore()
    {
        this.waitForElementPresent(
                By.id(STEP_NEW_WAYS_TO_EXPLORE_TITLE),
                "Cannot find 'New ways to explore' title",
                10);
    }


    public void waitForAddOrEditPreferredLanguages()
    {
        this.waitForElementPresent(
                By.id(STEP_ADD_OR_EDIT_PREFERRED_LANGUAGES_LINK),
                "Cannot find 'Add or edit preferred languages' link",
                10);
    }


    public void waitForLearnMoreAboutDataCollected()
    {
        this.waitForElementPresent(
                By.id(STEP_LEARN_MORE_ABOUT_DATA_COLLECTED_LINK),
                "Cannot find 'Learn more about data collected' link",
                10);
    }


    public void clickNext()
    {
        this.waitForElementPresent(
                By.id(NEXT_LINK),
                "Cannot find and click 'Next' button",
                10);
    }

    public void clickGetStartedButton()
    {
        this.waitForElementPresent(
                By.id(GET_STARTED_BUTTON),
                "Cannot find and click 'Get started' button",
                10);
    }
}
