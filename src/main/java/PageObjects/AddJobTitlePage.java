package PageObjects;

import AbstractComponents.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.*;

public class AddJobTitlePage extends AbstractComponent {

    WebDriver driver;

    public AddJobTitlePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    @FindBy(css = ".orangehrm-main-title")
    WebElement pageTitle;

    @FindBy(xpath = "(//form[@class='oxd-form']/div/div/div/input)[1]")
    WebElement jobTitleInput;

    @FindBy(xpath = "//*[@placeholder='Type description here']")
    WebElement descriptionBox;

    @FindBy(xpath = "//*[text()=' Save ']")
    WebElement saveBtn;

    public String getPageTitle() {
        waitForWebElementToAppear(pageTitle);
        return pageTitle.getText();
    }

    public void enterJobTitle(String jobTitle){
        jobTitleInput.sendKeys(jobTitle);
    }

    public void enterJobDescription(String description)
    {
        descriptionBox.sendKeys(description);
    }

    public void clickSaveBtn()
    {
        saveBtn.click();
    }
}
