package PageObjects;

import AbstractComponents.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.*;

import java.io.*;
import java.util.*;

public class LoginPage extends AbstractComponent {

    WebDriver driver;
    DashBoard dashBoard;
    Properties prop;
    public LoginPage(WebDriver driver) {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(css = ".oxd-sheet p:first-child")
    WebElement userNameTxt;

    @FindBy(css = ".oxd-sheet p:nth-of-type(2)")
    WebElement passwordTxt;

    @FindBy(xpath = "//input[@name='username']")
    WebElement usernameInputBox;

    @FindBy(css = "input[type='password']")
    WebElement passwordInputBox;

    @FindBy(css = "button[class*='login-button']")
    WebElement loginBtn;

    @FindBy(css = "p[class*='alert-content-text']")
    WebElement errorMsge;





    public void enterUsername(String username) throws IOException {

        usernameInputBox.sendKeys(username);
    }

    public void enterPassword(String password) throws IOException {
        passwordInputBox.sendKeys(password);
    }

    public DashBoard clickLoginBtn()
    {
        loginBtn.click();
         dashBoard = new DashBoard(driver);
        return dashBoard;
    }

    public DashBoard login(String username,String password) throws IOException {
        enterUsername(username);
        enterPassword(password);
         dashBoard = clickLoginBtn();
        return dashBoard;
    }

    public String getErrorMsge()
    {
        waitForWebElementToAppear(errorMsge);
      return  errorMsge.getText();
    }

}
