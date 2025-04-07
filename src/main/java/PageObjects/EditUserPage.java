package PageObjects;

import AbstractComponents.*;
import org.openqa.selenium.*;
import org.openqa.selenium.devtools.v132.page.*;
import org.openqa.selenium.support.*;

import java.util.*;

public class EditUserPage extends AbstractComponent {

    WebDriver driver;

    public EditUserPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "(//div[@class='oxd-select-wrapper'])[1]")
    WebElement userRoleInput;

    @FindBy(xpath = "//div[@role='listbox']")
    WebElement rolesList;

    @FindBy(xpath = "//div[@role='option']")
    List<WebElement> rolesOptions;

    @FindBy(xpath = "//button[text()=' Save ']")
    WebElement saveBtn;

    @FindBy(xpath = "//h6[text()='Edit User']")
    WebElement editUserTitle;

    @FindBy(id="oxd-toaster_1")
    WebElement userUpdateToastMsge;

    @FindBy(css=".oxd-text--toast-message")
    WebElement toastText;

    public boolean verifyEditUserPageVisibility()
    {
        waitForWebElementToAppear(editUserTitle);
        boolean visibility=false;
        if(editUserTitle.isDisplayed())
        {
            visibility=true;
        }
        return visibility;
    }

    public void updateRole(String role) throws InterruptedException {
        userRoleInput.click();
        waitForWebElementToAppear(rolesList);
        rolesOptions.stream().filter(e -> e.getText().equalsIgnoreCase(role)).findFirst().orElse(null).click();
    }

    public SystemUserPage clickSave() {
        saveBtn.click();
        SystemUserPage systemUserPage = new SystemUserPage(driver);
        return systemUserPage;
    }
    public boolean verifyToastMsgeDisplay()
    {
        waitForWebElementToAppear(userUpdateToastMsge);
        return userUpdateToastMsge.isDisplayed();
    }

    public String getToastMsgeText()
    {
      return toastText.getText();

    }
    public void waitTillToastMsgeDissapear()
    {
        waitForElementToDissapear(userUpdateToastMsge);
    }


}
