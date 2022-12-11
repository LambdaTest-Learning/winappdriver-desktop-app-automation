Before running the tests, please check the location of the <b>Calculator</b>, <b>Calculator (Classic)</b>, and <b>Notepad</b> applications on your Windows machine. The Calculator (Classic) application was not present on my machine, hence it was downloaded from the internet. You can also download and install the Calculator (Classic) application from [here](https://github.com/microsoft/WinAppDriver/releases/tag/v1.2.99)

In the Notepad automation demo, the *.txt* file is saved in the folder *c:\Himanshu* as *WinAppDriverDemo.txt*. In case you want to change the folder name, please change it [testng.xml](https://github.com/hjsblogger/winappdriver-desktop-app-automation/blob/main/testng.xml)

<img width="600" height="250" alt="FilePath" src="https://user-images.githubusercontent.com/1688653/205455356-29fc038c-1f6c-483f-8740-4b59dca641dc.png">

**Note**: Automation on Calculator, Calculator (Classic), and Notepad applications is tested on Windows 10 machine

## Prequisites ##

 - [**WinAppDriver.exe**](https://github.com/microsoft/WinAppDriver/releases/tag/v1.2.99)
 - **[Inspect.exe for Windows 10](https://go.microsoft.com/fwlink/?linkid=2164145)** or **[Inspect.exe for Windows 11](https://go.microsoft.com/fwlink/p/?linkid=2196241)**

Start WinAppDriver.exe by running the command **./WinAppDriver.exe** after navigating to the directory where it is installed:
![WinAppDriverScreenshot](https://user-images.githubusercontent.com/1688653/205455944-56d79534-d47f-480e-af0b-d463f7a9640a.png)

**Inspect.exe** is installed in the following location:

![Inspect_Screenshot](https://user-images.githubusercontent.com/1688653/205455835-8d3bc4dc-28bf-4be1-9baa-f1de6718d70e.png)

## Execution ##

 - Open the project in IntelliJ IDE
 - Right click on `testng.xml` and select *Run...*

![enter image description here](https://user-images.githubusercontent.com/1688653/205456191-775c3eb7-566b-48aa-bb72-7d9d5ae64480.png)

![Execution Snapshot](https://user-images.githubusercontent.com/1688653/205456192-f5ca3e31-c134-4b61-84e3-2bbcc4848fca.png)

 - You would see the interactions on Calculator and Notepad applications automated using *WinAppDriver.exe*. The final text file is saved in the location that is mentioned in `testng.xml`

Here is a **[video](https://www.youtube.com/watch?v=sXLde_8aGrg)** with the test execution in progress

### Need Assistance? ###
Discuss your queries by writing to me at [himanshu[dot]sheth[at]gmail](mailto:himanshu.sheth@gmail.com) or you can ping me on the following social media sites:

 - **LinkedIn** : [@hjsblogger](https://linkedin.com/in/hjsblogger)
 -  **Twitter** : [@hjsblogger](https://www.twitter.com/hjsblogger)
