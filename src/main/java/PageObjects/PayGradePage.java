package PageObjects;

import AbstractComponents.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.*;

import java.util.*;

public class PayGradePage extends AbstractComponent {
    WebDriver driver;
    public PayGradePage(WebDriver driver) {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//*[text()=' Add ']")
    WebElement addBtn;

    @FindBy(css = ".oxd-table-card")
    List<WebElement> payGradeList;

//    @FindBy(css = "div .oxd-table-cell:nth-child(2)")
//    WebElement payGradeName;

    @FindBy(css = ".orangehrm-container")
            WebElement resultsContainer;

    @FindBy(css = ".orangehrm-dialog-popup")
            WebElement deleteConfirmationPopup;

    @FindBy(xpath = "//*[text()=' Yes, Delete ']")
            WebElement confirmDeleteBtn;

    @FindBy(css = ".oxd-toast--success")
            WebElement toastContainer;

    @FindBy(css = ".oxd-text--toast-message")
            WebElement toastMsg;

    @FindBy(css = ".oxd-table-header .oxd-checkbox-input")
            WebElement selectAllCheckBox;

    @FindBy(xpath = "//*[text()=' Delete Selected ']")
            WebElement deleteSelectedBtn;

    By payGradeName = By.cssSelector("div .oxd-table-cell:nth-child(2)");

    By editBtn = By.cssSelector(".oxd-table-cell-actions button:last-child");

    By deleteBtn = By.cssSelector(".oxd-table-cell-actions button:first-child");

    public AddPayGradePage clickAddBtn()
    {
        addBtn.click();
        AddPayGradePage addPayGrade = new AddPayGradePage(driver);
        return addPayGrade;
    }

    public boolean verifyPayGradeVisibility(String name)
    {
        waitForWebElementToAppear(resultsContainer);
        return payGradeList.stream().anyMatch(e->e.findElement(payGradeName).getText().equalsIgnoreCase(name));
    }

public EditPayGradePage clickEditBtn(String payGradeTitle)
{
 WebElement editableRecord = payGradeList.stream().filter(t->t.findElement(payGradeName).getText().equalsIgnoreCase(payGradeTitle))
         .findFirst().orElse(null);
 editableRecord.findElement(editBtn).click();
 return new EditPayGradePage(driver);

}

public void clickDeleteBtn(String payGrade)
{
   WebElement deletableRecord =  payGradeList.stream().filter(o->o.findElement(payGradeName).getText().equalsIgnoreCase(payGrade))
            .findFirst().orElse(null);
   deletableRecord.findElement(deleteBtn).click();
}

public boolean verifyDeleteConfirmationPopupDisplay()
{
    return deleteConfirmationPopup.isDisplayed();
}

public void confirmDelete()
{
   confirmDeleteBtn.click();
}

public String getToastMsg()
{
    waitForWebElementToAppear(toastContainer);
  return  toastMsg.getText();

}
public boolean verifyDeletedRecordNotVisible(String deletedPayGrade)
{
   return payGradeList.stream().noneMatch(t->t.findElement(payGradeName).getText().equalsIgnoreCase(deletedPayGrade));
}

public void selectAllPayGrades()
{
    selectAllCheckBox.click();
}

public void deleteSelectedPayGrades()
{
    deleteSelectedBtn.click();
    waitForWebElementToAppear(deleteConfirmationPopup);
    confirmDeleteBtn.click();
}








}
