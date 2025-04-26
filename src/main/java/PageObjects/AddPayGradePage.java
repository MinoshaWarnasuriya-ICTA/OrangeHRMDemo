package PageObjects;

import AbstractComponents.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.*;

public class AddPayGradePage extends AbstractComponent {
    WebDriver driver;

    public AddPayGradePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".oxd-input--active:nth-child(1)")
    WebElement payGradeNameInput;

    @FindBy(xpath = "//*[text()=' Save ']")
    WebElement saveBtn;

    @FindBy(css = ".oxd-toast-container")
    WebElement toastContainer;

    @FindBy(css = ".oxd-text--toast-message")
    WebElement toastMsg;

    public void typePayGradeName(String payGradeName) {
        payGradeNameInput.sendKeys(payGradeName);
    }

    public EditPayGradePage clickSaveBtn()
    {
        saveBtn.click();
        EditPayGradePage editPayGrade = new EditPayGradePage(driver);
        return editPayGrade;
    }

    public boolean checkVisibilityOfToastMsg()
    {
        waitForWebElementToAppear(toastContainer);
       return toastContainer.isDisplayed();
    }

    public String getToastMsg()
    {
//        waitForWebElementToAppear(toastContainer);
       return toastMsg.getText();
    }



}
