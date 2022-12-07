import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.*;
import io.appium.java_client.windows.WindowsDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import com.windriver.WinDriver;

public class WinAppNotepadDemo
{
    public WindowsDriver driver = null;
    /* Notepad application path */
    public static String appPath="C:\\Windows\\notepad.exe";
    public static String strContent = "This is a demo of WinAppDriver";
    public static String pastedContent = "WinAppDriver Demo WinAppDriver Demo WinAppDriver Demo ";
    public static String emptyString = "";
    public static String saveFilePath;

    WebElement editBox = null;

    /* In ideal setup, the same cap instance can be used. This is only for demo */
    @BeforeTest
    @Parameters(value={"filePath"})
    public void testSetUp(String filePath) throws Exception
    {
        DesiredCapabilities capability = new DesiredCapabilities();

        capability.setCapability("ms:experimental-webdriver", true);
        capability.setCapability("app",appPath);
        capability.setCapability("platformName", "Windows");
        capability.setCapability("deviceName", "Windows10Machine");

        WinDriver.start();

        driver = new WindowsDriver(new URL("http://127.0.0.1:4723/"), capability);

        saveFilePath = String.valueOf(filePath);
    }

    /* Certain tests are derived from the one present on official WinAppDriver website */
    /* https://github.com/microsoft/WinAppDriver/blob/master/Tests/WebDriverAPI/SendKeys.cs */
    @Test(description="Demonstration of entering content in the Edit Box of Notepad", priority = 0)
    public void test_add_content() throws InterruptedException, MalformedURLException
    {
        String windowHandle = driver.getWindowHandle();
        System.out.println(windowHandle);

        /* Locate the Edit button to add content in the TextArea */
        editBox = driver.findElement(By.className("Edit"));
        editBox.click();
        editBox.sendKeys(strContent);
    }

    @Test(description="Using ActionChains to clear content in Notepad", priority = 1)
    public void test_delete_content() throws InterruptedException, MalformedURLException
    {
        Actions action = new Actions(driver);

        driver.manage().timeouts().implicitlyWait(3000, TimeUnit.MILLISECONDS);
        action.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
        action.perform();

        Assert.assertEquals(emptyString, editBox.getText());
    }

    @Test(description="Using sendKeys to copy and paste content", priority = 2)
    public void test_copy_paste() throws InterruptedException, MalformedURLException
    {
        editBox.sendKeys("WinAppDriver Demo ");

        /* Select All */
        editBox.sendKeys(Keys.CONTROL + "a" + Keys.CONTROL);

        /* Copy Content */
        editBox.sendKeys(Keys.CONTROL + "c" + Keys.CONTROL);

        /* Paste Content Thrice, hence vvv :) */
        editBox.sendKeys(Keys.CONTROL + "vvv" + Keys.CONTROL);

        /* Blocking wait, only for demo. Explicit Wait is preferred */
        driver.manage().timeouts().implicitlyWait(3000, TimeUnit.MILLISECONDS);

        Assert.assertEquals(pastedContent, editBox.getText());

        /* Earlier, was closing the Notepad to save the file */
        /* This operation is now done using ActionChains in the next test */
        /* driver.findElement(By.name("Close")).click(); */

        /* Maximize the Notepad Window */
        WebElement maximizeRestoreElement = driver.findElement(By.name("Maximize"));
        maximizeRestoreElement.click();

        driver.manage().timeouts().implicitlyWait(2000, TimeUnit.MILLISECONDS);
    }

    @Test(description="Using sendKeys to save file in the system", priority = 3)
    public void test_save_file() throws InterruptedException, MalformedURLException
    {
        System.out.println("Start Saving File");

        Actions action = new Actions(driver);

        /* Send CONTROL + S --> Save */
        action.sendKeys(Keys.chord(Keys.CONTROL, "s"));

        /* The FileName text area is highlighted by default, simply enter the filename */
        /* File Rewrite is not covered but that can also be replicated by following
        the same steps
        */
        action.sendKeys(saveFilePath);

        /* Send ALT + S --> Shortcut for Save */
        action.sendKeys(Keys.chord(Keys.ALT, "s"));

        /* Perform the action */
        action.perform();

        driver.manage().timeouts().implicitlyWait(2000, TimeUnit.MILLISECONDS);

        System.out.println("Test Complete\n");
    }

    @AfterTest
    public void tearDown()
    {
        if (driver != null)
        {
            /* Instantiated WinAppDriver can be stopped, needs to be started again from terminal */
            driver.quit();
            WinDriver.stop();
        }
    }
}
