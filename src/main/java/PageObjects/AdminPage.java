package PageObjects;

import AbstractComponents.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.*;

import java.util.*;

public class AdminPage extends AbstractComponent {

    WebDriver driver;
    public AdminPage(WebDriver driver) {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(css = ".orangehrm-container")
    WebElement list;

    @FindBy(css = ".oxd-table-card")
    List<WebElement> userList;

    @FindBy(xpath = "//span[text()='User Management ']")
    WebElement userManagementDropdown;

    @FindBy(xpath = "//a[text()='Users']/parent::li")
    WebElement usersOption;

    @FindBy(xpath = "//span[text()='Job ']/parent::li")
    WebElement jobDropdown;

    @FindBy(xpath = "//span[text()='Job ']/parent::li/ul")
    WebElement jobDropdownOptionsList;

    @FindBy(xpath = "(//span[text()='Job ']/parent::li/ul/li)[1]")
    WebElement jobTitles;

    @FindBy(css = ".oxd-dropdown-menu li a")
    List<WebElement> jobDropdownOptions;

    public boolean checkVisibilityOfUsersList()
    {
        scrollDownToElement(list);
        boolean flag = false;
        if(list.isDisplayed() && !userList.isEmpty())
        {
            flag = true;
        }
        return flag;
    }

    public SystemUserPage goToSystemUsersPage()
    {
        userManagementDropdown.click();
        waitForWebElementToAppear(usersOption);
        usersOption.click();
        SystemUserPage systemUserPage = new SystemUserPage(driver);
        return systemUserPage;
    }


    public void clickJobDropdown(){
    jobDropdown.click();
    }

    public JobTitles goToJobTitlesPage()
    {
        clickJobDropdown();
        waitForWebElementToAppear(jobDropdownOptionsList);
      jobTitles.click();
        JobTitles jobTitles = new JobTitles(driver);
       return jobTitles;
    }

    public PayGradePage goToPayGradePage()
    {
        clickJobDropdown();
        waitForWebElementToAppear(jobDropdownOptionsList);
       WebElement payGradeOption =jobDropdownOptions.stream().filter(e->e.getText().equalsIgnoreCase("Pay Grades")).
               findFirst().orElse(null);
       payGradeOption.click();
       PayGradePage payGrade = new PayGradePage(driver);
       return payGrade;
    }




}
