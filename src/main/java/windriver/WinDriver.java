package windriver;

import java.io.File;
import java.io.IOException;
import java.awt.Desktop;

public class WinDriver {
    public static void start()
    {
        try
        {
            Desktop d = Desktop.getDesktop();
            d.open(new File("C:\\Program Files\\Windows Application Driver\\WinAppDriver.exe"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static void stop()
    {
        try
        {
            ProcessBuilder processBuilder =new ProcessBuilder("taskkill ","/f","/IM","WinAppDriver.exe");
            processBuilder.start();
        }
        catch (IOException e)
        {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
