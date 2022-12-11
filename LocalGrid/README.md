Before running the tests, please check the location of the <b>Calculator</b>, <b>Calculator (Classic)</b>, and <b>Notepad</b> applications on your Windows machine. The Calculator (Classic) application was not present on my machine, hence it was downloaded from the internet. You can also download and install the Calculator (Classic) application from [here](https://drive.google.com/file/d/1ctVq6I50jRXL_WE6txbGjDdT5LT-5P3x/view?usp=sharing)

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

![IDE_1](https://user-images.githubusercontent.com/1688653/206884886-2e701e67-39d4-4bf5-950b-251610f80b08.png)                                                                                                                                       
![IDE_2](https://user-images.githubusercontent.com/1688653/206884888-b5ccc383-d246-4818-b101-ea88c3951583.png)

 - You would see the interactions on Calculator, Calculator (Classic), and Notepad applications automated using *WinAppDriver.exe*. The final text file is saved in the location that is mentioned in `testng.xml`

Here is a [video](https://youtu.be/L69UO8ixI6w) with the test execution in progress

### Need Assistance? ###
Discuss your queries by writing to me at [himanshu[dot]sheth[at]gmail][dot]com(mailto:himanshu.sheth@gmail.com) or you can ping me on the following social media sites:

 - **LinkedIn** : [@hjsblogger](https://linkedin.com/in/hjsblogger)
 - **Twitter** : [@hjsblogger](https://www.twitter.com/hjsblogger)
