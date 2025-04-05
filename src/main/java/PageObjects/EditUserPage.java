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

    public void updateRole(String role) throws InterruptedException {
        userRoleInput.click();
        Thread.sleep(3000);
        rolesOptions.stream().filter(e -> e.getText().equalsIgnoreCase(role)).findFirst().orElse(null).click();
    }

    public SystemUserPage clickSave() {
        saveBtn.click();
        SystemUserPage systemUserPage = new SystemUserPage(driver);
        return systemUserPage;
    }


}
