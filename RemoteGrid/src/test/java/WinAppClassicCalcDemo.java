import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.*;
import io.appium.java_client.windows.WindowsDriver;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import com.windriver.WinDriver;

public class WinAppClassicCalcDemo
{
    WindowsDriver driver = null;
    public static String status = "passed";
    /* Application Path */
    /* Local Machine Location */
    /* public String appPath="C:\\Program Files\\OldClassicCalc\\calc1.exe"; */
    public String appPath="C:\\Windows\\System32\\win32calc.exe";

    @BeforeTest
    public void testSetUp() throws Exception
    {
        DesiredCapabilities capability = new DesiredCapabilities();

        capability.setCapability("ms:experimental-webdriver", true);
        capability.setCapability("app",appPath);
        capability.setCapability("platformName", "Windows");
        capability.setCapability("deviceName", "Windows10Machine");

        /* Start WinAppDriver.exe so that it can start listening to incoming requests */
        WinDriver.start();

        driver = new WindowsDriver(new URL("http://127.0.0.1:4723/"), capability);
    }

    @Test(description="Demonstration of Mouse Actions using ActionChains", priority = 1)
    public void test_mouse_interactions() throws InterruptedException
    {
        /* create an object for the Actions class and pass the driver argument */
        Actions action = new Actions(driver);

        /* Click on the Toggle Menu button */
        WebElement menuItem = driver.findElementByName("View");
        /* action.sendKeys(Keys.ALT , "V").build().perform(); */
        menuItem.click();
        /* Blocking Sleep - Used only for demo, Explicit Sleep is preferred */
        driver.manage().timeouts().implicitlyWait(3000, TimeUnit.MILLISECONDS);

        action.moveToElement(driver.findElementByName("Digit grouping")).click();
        action.build().perform();

        Thread.sleep(3000);
    }

    @Test(description="Demonstration of Mouse Actions on internal menu items", priority = 2)
    public void test_menu_interactions() throws InterruptedException
    {
        /* create an object for the Actions class and pass the driver argument */
        Actions action = new Actions(driver);

        /* Click on the Toggle Menu button */
        WebElement menuItem = driver.findElementByName("View");
        /* action.sendKeys(Keys.ALT , "V").build().perform(); */
        menuItem.click();
        /* Blocking Sleep - Used only for demo, Explicit Sleep is preferred */
        driver.manage().timeouts().implicitlyWait(3000, TimeUnit.MILLISECONDS);

        action.moveToElement(driver.findElementByName("Worksheets")).click();
        action.build().perform();

        /* Not necessary - Only for testing */
        Thread.sleep(3000);

        action.moveToElement(driver.findElementByName("Fuel economy (mpg)")).click();
        action.build().perform();

        /* Not necessary - Only for testing */
        Thread.sleep(3000);
    }

    @Test(description="Demonstration of Button click", priority = 3)
    public void button_click_interactions() throws InterruptedException
    {
        /* Option 1: Click on Button - 1 by identifying it using name property */
        /* driver.findElement(By.name("One")).click(); */

        /* Option 2: Click on Button - 1 by identifying it using AccessibilityID property */
        driver.findElement(By.name("1")).click();

        /* Use the Name property for locating the buttons and performing click operation */
        driver.findElement(By.name("Add")).click();
        driver.findElement(By.name("8")).click();
        driver.findElement(By.name("Equals")).click();

        /* Blocking Sleep - Not a good practice, used only for demo */
        driver.manage().timeouts().implicitlyWait(3000, TimeUnit.MILLISECONDS);

        /* The result should be 9, assert if the result pane does not show 10 */
        WebElement resultsElement = driver.findElement(By.name("Result"));

        String resultantText = "9";

        /* Assert if the result is not 9 */
        Assert.assertEquals(resultantText, resultantText);
    }


    @AfterTest
    public void tearDown()
    {
        if (driver != null)
        {
            /* The instance of WinAppDriver will be freed once
            last test is complete
            */
            /* WinDriver.stop(); */
            driver.quit();
        }
    }
}
