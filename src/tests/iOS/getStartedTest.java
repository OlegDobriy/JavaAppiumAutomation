package tests.iOS;
import lib.iOSTestCase;
import lib.ui.WelcomePageObject;
import org.junit.Test;

public class getStartedTest extends iOSTestCase
{
    @Test
    public void testPassThroughWelcome()
    {
        WelcomePageObject WelcomePage = new WelcomePageObject(driver);
        WelcomePage.waitForLearMoreLink();
        WelcomePage.clickNext();
        WelcomePage.waitForNewWaysToExplore();
        WelcomePage.clickNext();
        WelcomePage.waitForAddOrEditPreferredLanguages();
        WelcomePage.clickNext();
        WelcomePage.waitForLearnMoreAboutDataCollected();
        WelcomePage.clickGetStartedButton();
    }
}
