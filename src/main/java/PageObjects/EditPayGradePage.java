package PageObjects;

import AbstractComponents.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.*;

import java.sql.*;
import java.util.*;

public class EditPayGradePage extends AbstractComponent {
    WebDriver driver;

    public EditPayGradePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//*[text()= ' Add ']")
    WebElement addBtn;

    @FindBy(xpath = "//*[text()='Add Currency']")
    WebElement addCurrencyTitle;

    @FindBy(css = ".orangehrm-card-container:nth-child(2)")
    WebElement addCurrencyContainer;

    @FindBy(css = ".oxd-select-text")
    WebElement currencyDropdown;

    @FindBy(css = ".oxd-select-dropdown")
    WebElement currencyContainer;

    @FindBy(css = ".oxd-select-option span")
    List<WebElement> currencyList;

    @FindBy(xpath = "(//*[@class='oxd-input oxd-input--active'])[3]")
    WebElement minimumSalaryInput;
//(//*[@class='oxd-input-group oxd-input-field-bottom-space']/div[2]/input)[3]
    @FindBy(xpath = "(//*[@class='oxd-input-group oxd-input-field-bottom-space']/div[2]/input)[3]")
    WebElement maximumSalaryInput;

    @FindBy(xpath = "//*[text()= ' Save ']")
    WebElement saveBtn;

    @FindBy(xpath = "(//*[text()= ' Save '])[2]")
    WebElement currencyContainerSaveBtn;

    @FindBy(css = ".oxd-table-card div .oxd-table-cell:nth-child(2) div")
    List<WebElement> addedCurrencyList;

    @FindBy(css = ".oxd-toast-container")
    WebElement toastContainer;

    @FindBy(css = ".oxd-text--toast-message")
    WebElement toastMsg;

    @FindBy(css = ".orangehrm-container")
    WebElement currencyRecordsContainer;

    @FindBy(css = ".oxd-input-group .oxd-input")
    WebElement payGradeNameInput;

    public void clickAddBtn() {
        addBtn.click();
    }

    public boolean checkVisibilityOfAddCurrencyTitle() {
        //scrollDownToElement(addCurrencyTitle);
        return addCurrencyTitle.isDisplayed();
    }

    public void selectCurrency(String currencyType) {
        //scrollDownToElement(currencyDropdown);
        waitForWebElementToAppear(currencyDropdown);
        currencyDropdown.click();
       // scrollDownToElement(currencyContainer);
        waitForWebElementToAppear(currencyContainer);
        WebElement currency = currencyList.stream().filter(e -> e.getText().equalsIgnoreCase(currencyType)).
                findFirst().orElse(null);
        currency.click();
    }

    public void setMinimumSalary(String minimumSal) {
        minimumSalaryInput.sendKeys(minimumSal);
    }

    public void setMaximumSalary(String maximumSal) {
        maximumSalaryInput.sendKeys(maximumSal);
    }

    public void clickSaveBtn() {
        currencyContainerSaveBtn.click();
    }
    public void clickSave() {
    saveBtn.click();
    }

    public String getToastMsg()
    {
        waitForWebElementToAppear(toastContainer);
       return toastMsg.getText();
    }

    public boolean verifyAddedCurrency(String currency) {
        waitForElementToDissapear(toastContainer);
        scrollDownToElement(currencyRecordsContainer);
        String editedCurrency = currency.split("- ")[1];
        return addedCurrencyList.stream().anyMatch(k -> k.getText().equalsIgnoreCase(editedCurrency));
    }

    public void editPayGradeName(String newPayGradeName)
    {
        waitForWebElementToAppear(payGradeNameInput);
        payGradeNameInput.clear();
        if(payGradeNameInput.getText().equalsIgnoreCase("")) {
            payGradeNameInput.sendKeys(newPayGradeName);
        }
        else {
            System.out.println("Field is not empty");
        }
    }
}
