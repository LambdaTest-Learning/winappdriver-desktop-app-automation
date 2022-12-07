package com.windriver;

import java.io.File;
import java.io.IOException;
import java.awt.Desktop;

public class WinDriver
{
    public static void start()
    {
        try
        {
            Desktop desktop = Desktop.getDesktop();

            /* Local Grid */
            /* File file = new File("C:\\Program Files\\Windows Application Driver\\WinAppDriver.exe"); */

            File file = new File("C:\\Program Files (x86)\\Windows Application Driver\\WinAppDriver.exe");

            /* Check if there is support for Desktop or not */
            if(!Desktop.isDesktopSupported())
            {
                System.out.println("not supported");
                return;
            }

            if (file.exists())
            {
                System.out.println("Open WinAppDriver.exe\n");
                desktop.open(file);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
            System.out.println("Encountered Exception\n");
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
