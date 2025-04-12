package PageObjects;

import AbstractComponents.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.*;

import java.util.*;

public class JobTitles extends AbstractComponent {
    WebDriver driver;

    public JobTitles(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".orangehrm-main-title")
    WebElement pageTitle;

    @FindBy(css = ".orangehrm-container")
    WebElement resultsContainer;

    @FindBy(xpath = "//button[text()=' Add ']")
    WebElement addBtn;

    @FindBy(css = ".oxd-table-card")
    List<WebElement> tableRecords;

    @FindBy(css = ".oxd-table-card div div:nth-child(2)")
    List<WebElement> titlesList;

//   @FindBy(css = ".oxd-table-card div div:nth-child(2) div")
//   WebElement title;

    By title = By.cssSelector(".oxd-table-card div div:nth-child(2) div");

    @FindBy(css = ".oxd-table-card div div:nth-child(3)")
    List<WebElement> descriptionList;

    @FindBy(css = ".oxd-toast-container")
    WebElement toastContainer;

    @FindBy(css = ".oxd-text--toast-message")
    WebElement toastMsg;//.oxd-text--toast-message

    @FindBy(css = ".oxd-sheet")
    WebElement deleteConfirmationPopup;

    @FindBy(xpath = "//*[text()=' Yes, Delete ']")
    WebElement deleteConfirmBtn;

    @FindBy(css = ".oxd-toast-container")
    WebElement deleteToastContainer;
    
    @FindBy(css = ".oxd-button--label-danger")
            WebElement deleteSelected;

//   @FindBy(xpath = "//*[@class='oxd-table-card']/div/div[4]/div/button[2]")
//   List<WebElement> editBtns;

    //By editButton = By.xpath("//*[@class='oxd-table-card']/div/div[4]/div/button[2]");
    By editButton = By.cssSelector(".oxd-table-row div:nth-child(4) button:nth-child(2)");

    By jobDesc = By.cssSelector(".oxd-table-card div div:nth-child(3)");

    By deleteBtn = By.cssSelector(".oxd-table-row div:nth-child(4) button:nth-child(1)");

    By checkBox = By.cssSelector(".oxd-checkbox-input");

    public String getPageTitle() {
        return pageTitle.getText();
    }

    public boolean checkVisibilityOfTitles() {
        // scrollDownToElement(resultsContainer);
        waitForWebElementToAppear(resultsContainer);
        return resultsContainer.isDisplayed();
    }

    public AddJobTitlePage clickAddBtn() {
        addBtn.click();
        return new AddJobTitlePage(driver);
    }

    public boolean verifyVisibilityOFNewTitle(String title, String desc) {
        waitForElementToDissapear(toastContainer);
        scrollDownToElement(resultsContainer);
        // waitForWebElementToAppear(resultsContainer);
        boolean flag = false;
        if (titlesList.stream().anyMatch(e -> e.getText().equalsIgnoreCase(title)) &&
                descriptionList.stream().anyMatch(d -> d.getText().equalsIgnoreCase(desc))) {
            flag = true;
        }
        return flag;
    }

    public String getToastMsg() {
        return toastMsg.getText();
    }

    public EditJobTitlePage clickEditButton(String jobTitle) {
        WebElement recordToEdit = tableRecords.stream().filter(r -> r.findElement(title).getText().equalsIgnoreCase(jobTitle))
                .findFirst().orElse(null);
        recordToEdit.findElement(editButton).click();
        return new EditJobTitlePage(driver);
    }

    public boolean verifyUpdatedTitle(String newTitle, String newDesc) {
        waitForElementToDissapear(toastMsg);
        scrollDownToElement(resultsContainer);
        boolean flag = false;
        if (tableRecords.stream().anyMatch(p -> p.findElement(title).getText().equalsIgnoreCase(newTitle)) &&
                tableRecords.stream().anyMatch(o -> o.findElement(jobDesc).getText().equalsIgnoreCase(newDesc))) {
            flag = true;
        }

        return flag;
    }

    public void clickDeleteBtn(String titleToDelete) {
        WebElement titleRecord = tableRecords.stream().filter(r -> r.findElement(title).getText().equalsIgnoreCase(titleToDelete)).findFirst().orElse(null);
        titleRecord.findElement(deleteBtn).click();
    }

    public boolean checkVisibilityOfDeleteConfirmPopup() {
        waitForWebElementToAppear(deleteConfirmationPopup);
        return deleteConfirmationPopup.isDisplayed();
    }

    public void clickDeleteConfirmBtn() {
        waitForWebElementToAppear(deleteConfirmBtn);
        deleteConfirmBtn.click();
    }

    public boolean checkToastMsgVisibility() {
        waitForWebElementToAppear(toastContainer);
        return deleteToastContainer.isDisplayed();
    }

    public boolean verifyDeletedTitle(String titleToDelete)
    {
        waitForElementToDissapear(toastContainer);
        return tableRecords.stream().noneMatch(r->r.findElement(title).getText().equalsIgnoreCase(titleToDelete));
    }

    public void selectMultipleTitles(String[] titlesList)
    {
        scrollDownToElement(resultsContainer);
        for(String jobTitle: titlesList)
        {
           WebElement selectedRec = tableRecords.stream().filter(t->t.findElement(title).getText().equalsIgnoreCase(jobTitle)).findFirst().orElse(null);
       selectedRec.findElement(checkBox).click();
        }
    }
    
    public void clickDeleteSelectedBtn()
    {
        deleteSelected.click();
    }

    public boolean verifyDeletedTitles(String[] titlesList)
    {
        boolean flag=false;
        waitForElementToDissapear(toastContainer);
        for(String jobTitle:titlesList)
        {
          flag=  tableRecords.stream().noneMatch(o->o.findElement(title).getText().equalsIgnoreCase(jobTitle));
        }
        return flag;
    }

}
