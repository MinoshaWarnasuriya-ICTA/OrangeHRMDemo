package AbstractComponents;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.*;

import java.io.*;
import java.time.*;
import java.util.*;

public class AbstractComponent {
    WebDriver driver;
    Properties prop;
    public AbstractComponent(WebDriver driver)
    {
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }


public Properties getGlobalData() throws IOException {
     prop = new Properties();
    FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"//src//main//java//GlobalData//globalData.properties");
    prop.load(fis);
    return prop;
}

    public String getUsername() throws IOException {
        prop = getGlobalData();
        String username =  prop.getProperty("username");
        return username;
    }

    public String getPassword() throws IOException {
        prop =  getGlobalData();
        String password = prop.getProperty("password");
        return password;

    }
    public void waitForWebElementToAppear(WebElement element)
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void scrollToList(WebElement list)
    {
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].scrollIntoView();", list);
    }


}
