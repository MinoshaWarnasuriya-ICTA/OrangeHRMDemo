package PageObjects;

import AbstractComponents.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.*;

import java.util.*;

public class AddUserPage extends AbstractComponent {

    WebDriver driver;

    public AddUserPage(WebDriver driver) {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "(//div[@class='oxd-select-wrapper'])[1]")
    WebElement userRoleInput;

    @FindBy(xpath = "(//div[@class='oxd-select-wrapper'])[2]")
    WebElement statusInput;

    @FindBy(css = "div[class*='oxd-autocomplete-text'] input")
    WebElement employeeName;

    @FindBy(xpath = "(//input[@autocomplete='off'])[1]")
    WebElement usernameInput;

    @FindBy(xpath = "(//input[@type='password'])[1]")
    WebElement passwordInput;

    @FindBy(xpath = "(//input[@type='password'])[2]")
    WebElement passwordConfirmation;

    @FindBy(xpath = "//button[text()=' Save ']")
    WebElement saveBtn;

    @FindBy(xpath = "//div[@role='listbox']")
    WebElement rolesList;

    @FindBy(css = "[role='option'] span")
    List<WebElement> roleOptions;

    @FindBy(css = ".oxd-autocomplete-dropdown")
    WebElement empNameAutoCompleteDropdown;

    @FindBy(css = ".oxd-autocomplete-option")
    List<WebElement> empNameAutoCompleteOptions;

    @FindBy(css = "[role='listbox']")
    WebElement statusList;

    @FindBy(css = "[role='option']")
    List<WebElement> statusOptions;

    public void selectUserRole(String role)
    {
        userRoleInput.click();
        waitForWebElementToAppear(rolesList);
       WebElement roleOption = roleOptions.stream().filter(e->e.getText().equalsIgnoreCase(role)).findFirst().orElse(null);
        roleOption.click();
    }

    public void enterEmployeeName(String empNameHint) throws InterruptedException {
     employeeName.sendKeys(empNameHint);
Thread.sleep(3000);
     WebElement selectedEmpName =empNameAutoCompleteOptions.stream().filter(k->k.getText().contains(empNameHint)).
             findFirst().orElse(null);
     selectedEmpName.click();

    }

    public void selectStatus(String status)
    {
        statusInput.click();
        waitForWebElementToAppear(statusList);
        WebElement selectedStatus = statusOptions.stream().filter(r->r.getText().equalsIgnoreCase(status)).findFirst().orElse(null);
        selectedStatus.click();
    }

    public void enterUsername(String username)
    {
        if(username.length()>5) {
            usernameInput.sendKeys(username);
        }
        else {
            System.out.println("Username should be at least 5 characters long");
        }
    }

    public void enterPassword(String password)
    {
        passwordInput.sendKeys(password);
    }

    public void confirmPassword(String password)
    {
        passwordConfirmation.sendKeys(password);
    }

    public SystemUserPage clickSave()
    {
        saveBtn.click();
        SystemUserPage systemUserPage = new SystemUserPage(driver);
        return systemUserPage;
    }

}
