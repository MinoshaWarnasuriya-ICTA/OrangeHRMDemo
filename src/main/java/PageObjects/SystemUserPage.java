package PageObjects;

import AbstractComponents.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.*;

import java.util.*;
import java.util.stream.*;

public class SystemUserPage extends AbstractComponent {

    WebDriver driver;

    public SystemUserPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "input[class*='oxd-input']:nth-child(1)")
    public WebElement usernameInput;

    @FindBy(xpath = "(//div[@class='oxd-select-wrapper'])[1]")
    public WebElement userRoleInput;

    @FindBy(xpath = "//div[@role='listbox']")
    WebElement rolesList;

    @FindBy(css = "div[role='option'] span")
    List<WebElement> rolesOptions;


    @FindBy(css = "input[placeholder='Type for hints...']")
    public WebElement employeeNameInput;

    @FindBy(css = ".oxd-autocomplete-dropdown")
    WebElement autoCompleteOptionSet;

    @FindBy(xpath = "//div[@class='oxd-autocomplete-option']/span")
    List<WebElement> nameSuggestions;

    @FindBy(xpath = "(//div[@class='oxd-select-wrapper'])[2]")
    public WebElement statusInput;

    @FindBy(css = ".oxd-select-dropdown")
    WebElement statusList;

    @FindBy(css = ".oxd-select-option span")
    List<WebElement> statusOptions;

    @FindBy(css = "button[type='submit']")
    WebElement searchBtn;

    @FindBy(css = ".oxd-table-card")
    WebElement filteredUser;

    @FindBy(xpath = "//div[@class='oxd-table-card']")
    WebElement systemUser;

    @FindBy(xpath = "(//div[@class='oxd-table-card']/div/div)[2]")
    WebElement searchResultUsername;

    @FindBy(xpath = "(//div[@class='oxd-table-card']/div/div)[3]")
    WebElement searchResultUserRole;

    @FindBy(xpath = "(//div[@class='oxd-table-card']/div/div)[4]")
    WebElement searchResultEployeeName;

    @FindBy(xpath = "(//div[@class='oxd-table-card']/div/div)[5]")
    WebElement searchResultStatus;

    @FindBy(css = ".oxd-table-card")
    List<WebElement> filteredUserList;

    @FindBy(css = ".orangehrm-container")
    WebElement resultContainer;

    @FindBy(xpath = "//*[text()=' Reset ']")
    WebElement resetBtn;

    @FindBy(xpath = "//*[text()=' Add ']")
    WebElement addBtn;

    @FindBy(css = ".oxd-table-filter")
    WebElement filterSection;

    @FindBy(xpath = "//div[@class='oxd-table-card']/div/div[2]/div")
    List<WebElement> systemUsersNamesList;

    @FindBy(css = ".oxd-table-cell-actions button:nth-of-type(2)")
    List<WebElement> editBtnList;

    @FindBy(xpath = "//*[@class='oxd-table-card']/div/div[3]/div")
    List<WebElement> systemUserRolesList;

    By filteredUserRole = By.xpath("(//div[@class='oxd-table-card']/div/div)[3]");

    By filteredUserStatus = By.xpath("(//div[@class='oxd-table-card']/div/div)[5]");

    By byUsername = By.xpath("//div[@class='oxd-table-card']/div/div[2]/div");


    public void enterUsername(String username) {
        usernameInput.sendKeys(username);
    }

    public void selectUserRole(String role) {
        userRoleInput.click();
        waitForWebElementToAppear(rolesList);
        for (int i = 0; i < rolesOptions.size(); i++) {
            if (rolesOptions.get(i).getText().contains(role)) {
                rolesOptions.get(i).click();
            }
        }
    }

    public void selectEmployeeName(String employeeNameHint) {
        employeeNameInput.sendKeys(employeeNameHint);
        waitForWebElementToAppear(autoCompleteOptionSet);
        WebElement matchedName = nameSuggestions.stream().filter(e -> e.getText().contains(employeeNameHint)).findFirst().orElse(null);
        matchedName.click();
    }

    public void searchEmployeeName(String employeeNameHint) {
        employeeNameInput.sendKeys(employeeNameHint);
        waitForWebElementToAppear(autoCompleteOptionSet);
    }

    public void selectStatus(String status) {
        statusInput.click();
        waitForWebElementToAppear(statusList);
        WebElement statusOption = statusOptions.stream().filter(s -> s.getText().contains(status)).findFirst().orElse(null);
        statusOption.click();
    }

    public void clickSearchBtn() {
        searchBtn.click();
    }


    public boolean verifyUserSearchResult(String username, String role, String employeeName, String status) {
        boolean flag = false;
        scrollToList(filteredUser);
        if (filteredUser.isDisplayed() &&
                searchResultUsername.getText().equalsIgnoreCase(username) &&
                searchResultUserRole.getText().equalsIgnoreCase(role) &&
                searchResultEployeeName.getText().equalsIgnoreCase(employeeName) &&
                searchResultStatus.getText().equalsIgnoreCase(status)) {
            flag = true;
        }
        return flag;
    }

    public boolean verifyUsernameInSearchResult(String username) {
        boolean flag = false;
        scrollToList(filteredUser);
        if (filteredUser.isDisplayed() && searchResultUsername.getText().equalsIgnoreCase(username)) {
            flag = true;
        }
        return flag;

    }

    public boolean verifyUserRoleInSearchResult(String role) {
        scrollToList(resultContainer);
        waitForWebElementToAppear(resultContainer);
        return filteredUserList.stream().anyMatch(s -> s.findElement(filteredUserRole).getText().equalsIgnoreCase(role));
    }

    public boolean verifyEmpNameSuggestions(String employeeNameHint) {
        return nameSuggestions.stream().anyMatch(k -> k.getText().contains(employeeNameHint));
    }

    public boolean verifyStatusInSearchResult(String status) throws InterruptedException {
        scrollToList(resultContainer);
        Thread.sleep(2000);
        boolean flag = false;
        for (WebElement filteredUser : filteredUserList) {
            String searchedStatus = filteredUser.findElement(filteredUserStatus).getText();
            if (searchedStatus.equalsIgnoreCase(status)) {
                flag = true;
            }
        }
        return flag;
    }

    public void clickResetBtn() {
        resetBtn.click();
    }

    public boolean verifyFilterReset() {
        boolean flag = false;

        if (usernameInput.getText().isEmpty() &&
                userRoleInput.getText().equalsIgnoreCase("-- Select --") &&
                employeeNameInput.getText().isEmpty() &&
                statusInput.getText().equalsIgnoreCase("-- Select --")) {
            flag = true;
        }
        return flag;
    }

    public AddUserPage clickAddBtn() {
        addBtn.click();
        AddUserPage addUserPage = new AddUserPage(driver);
        return addUserPage;
    }

    public boolean verifyNewlyAddedUser(String username) {
        waitForWebElementToAppear(filterSection);
        scrollToList(resultContainer);
        waitForWebElementToAppear(resultContainer);
        boolean flag = false;
        flag = systemUsersNamesList.stream().anyMatch(o -> o.getText().equalsIgnoreCase(username));

        return flag;
    }

    public EditUserPage editSystemUser(String username) {
        scrollToList(resultContainer);
        waitForWebElementToAppear(resultContainer);
        for (int i = 0; i < systemUsersNamesList.size(); i++) {
            String editableUsername = systemUsersNamesList.get(i).getText();
            if (editableUsername.equalsIgnoreCase(username)) {
                for (int j = i; j < editBtnList.size(); j++) {
                    editBtnList.get(j).click();

                }
            }

        }
        EditUserPage editUserPage = new EditUserPage(driver);
        return editUserPage;
    }

    public boolean verifyUserUpdate(String username ,String role)
    {
        boolean flag=false;
        scrollToList(resultContainer);
        waitForWebElementToAppear(resultContainer);
        for(int i=0;i<systemUsersNamesList.size();i++)
        {
           String systemUsername =  systemUsersNamesList.get(i).getText();
           if(systemUsername.equalsIgnoreCase(username))
           {
               String updatedRole = systemUserRolesList.get(i).getText();
               if(updatedRole.equalsIgnoreCase(role))
               {
                   flag=true;
               }
               break;
           }
        }
        return flag;
    }


}
