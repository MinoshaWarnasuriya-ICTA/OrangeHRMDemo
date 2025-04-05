package TestComponents;

import AbstractComponents.*;
import PageObjects.*;
import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.*;
import io.github.bonigarcia.wdm.*;
import org.apache.commons.io.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.edge.*;
import org.openqa.selenium.firefox.*;
import org.testng.annotations.*;

import java.io.*;
import java.time.*;
import java.util.*;

public class BaseTest {
    public WebDriver driver;
    public  LoginPage loginPage;
    public  WebDriver driverInitialization() throws IOException {


        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"//src//main//java//GlobalData//globalData.properties");
        prop.load(fis);
        String browserName = prop.getProperty("browser");


        if(browserName.equalsIgnoreCase("chrome"))
        {
            WebDriverManager.chromedriver().setup();
          driver   = new ChromeDriver();

        } else if (browserName.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();
             driver = new EdgeDriver();
        } else if (browserName.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
             driver = new FirefoxDriver();

        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();
        return driver;

    }

    @BeforeMethod
    public LoginPage launchApplication() throws IOException {
        driverInitialization();
        driver.get("http://localhost/orangehrm/web/index.php/auth/login");
         loginPage = new LoginPage(driver);
        return loginPage;

    }

    @AfterMethod
    public void tearDown()
    {
        driver.quit();
    }

    public ExtentReports getExtentObject()
    {
        ExtentSparkReporter reporter = new ExtentSparkReporter(System.getProperty("user.dir")+"//reports//index.html");
        reporter.config().setReportName("Orange HRM Automation Test Results");
        reporter.config().setDocumentTitle("Orange HRM Test Report");

        ExtentReports extent = new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("QA Engineer","Minosha Warnasuriya");
        return extent;

    }

    public String getScreenshot(WebDriver driver,String testCaseName) throws IOException {
        TakesScreenshot ss = (TakesScreenshot) driver;
        File src = ss.getScreenshotAs(OutputType.FILE);
        String  dest = System.getProperty("user.dir")+"//reports//screenshots//"+testCaseName+".png";
        FileUtils.copyFile(src,new File(dest));
        return dest;
    }
}
