package AbstractComponents;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.*;
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

    @FindBy(css = ".orangehrm-main-title")
    WebElement pageTitle;


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

    public void scrollDownToElement(WebElement ele)
    {
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].scrollIntoView();", ele);
    }

    public void waitForElementToDissapear(WebElement element)
    {
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        wait.until(ExpectedConditions.invisibilityOf(element));
    }

    public void moveToTop()
    {
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("window.scrollTo(0,0)");

    }

    public void updateTextWithAction(WebElement inputField,String newText)
    {
        Actions action = new Actions(driver);
        waitForWebElementToAppear(inputField);
        action.click(inputField).keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys(Keys.BACK_SPACE).sendKeys(newText).build().perform();
    }

    public String getVisiblePageTitle()
    {
        waitForWebElementToAppear(pageTitle);
       return pageTitle.getText();
    }
}
