package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import junit.framework.TestCase;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class CoreTestCase extends TestCase
{
    private static final String
            PLATFORM_IOS = "ios",
            PLATFORM_ANDROID = "android";


    protected AppiumDriver driver;
    private static String AppiumURL = "http://127.0.0.1:4723/wd/hub";


    @Override
    protected void setUp() throws Exception
    {
        super.setUp(); // использовать setUp из TestCase
        DesiredCapabilities capabilities = this.getCapabilitiesByPlatformEnv();
        driver = new AndroidDriver(new URL(AppiumURL), capabilities);
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


    public DesiredCapabilities getCapabilitiesByPlatformEnv() throws Exception
    {
        String platform = System.getenv("PLATFORM"); // получить параметр запуска
        DesiredCapabilities capabilities = new DesiredCapabilities();

        if (platform.equals(PLATFORM_ANDROID))
        {
            capabilities.setCapability("platformName","Android");
            capabilities.setCapability("deviceName","AndroidTestDevice");
            capabilities.setCapability("platformVersion","6.0");
            capabilities.setCapability("automationName","Appium");
            capabilities.setCapability("appPackage","org.wikipedia");
            capabilities.setCapability("appActivity",".main.MainActivity");
            capabilities.setCapability("orientation", "PORTRAIT"); // всегда начинать тесты с портретной ориентации
            capabilities.setCapability("app",System.getProperty("user.dir") + "/apks/org.wikipedia.apk");
        }
        else if (platform.equals(PLATFORM_IOS))
        {
            capabilities.setCapability("platformName","iOS");
            capabilities.setCapability("deviceName","iPhone SE");
            capabilities.setCapability("platformVersion","10.3");
            capabilities.setCapability("orientation", "PORTRAIT"); // всегда начинать тесты с портретной ориентации
            capabilities.setCapability("app",System.getProperty("user.dir") + "/apks/Wikipedia.app");
        }
        else
        {
            throw new Exception("Cannot get platform name from env variable: " + platform);
        }
        return capabilities;
    }
}
