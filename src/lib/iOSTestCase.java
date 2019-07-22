package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import junit.framework.TestCase;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;
import sun.nio.ch.IOStatus;

import java.net.URL;

public class iOSTestCase extends TestCase
{
    protected AppiumDriver driver;
    private static String AppiumURL = "http://127.0.0.1:4723/wd/hub";


    @Override
    protected void setUp() throws Exception
    {
        super.setUp(); // использовать setUp из TestCase
        DesiredCapabilities capabilities = this.getCapabilitiesByPlatformEnv();
        driver = new IOSDriver(new URL(AppiumURL), capabilities);
    }


    @Override
    protected void tearDown() throws Exception
    {
        driver.quit();

        super.tearDown(); // использовать tearDown из TestCase
    }


    protected void rotateToPortrait()
    {
        driver.rotate(ScreenOrientation.PORTRAIT);
    }


    protected void rotateToLandscape()
    {
        driver.rotate(ScreenOrientation.LANDSCAPE);
    }


    protected void backgroundApp(int seconds)
    {
        driver.runAppInBackground(seconds);
    }
}
