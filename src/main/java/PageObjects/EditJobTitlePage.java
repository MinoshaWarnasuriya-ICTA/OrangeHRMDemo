package PageObjects;

import AbstractComponents.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.*;

public class EditJobTitlePage extends AbstractComponent {
    WebDriver driver;
    public EditJobTitlePage(WebDriver driver) {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(css = ".orangehrm-main-title")
    WebElement pageTitle;

    @FindBy(css = ".oxd-form-row div div:nth-child(2) input")
    WebElement jobTitleInputBox;

    @FindBy(css = "[placeholder*='Type description']")
    WebElement jobDescBox;

    @FindBy(xpath = "//*[text()=' Save ']")
    WebElement saveBtn;


    public String getEditPageTitle()
    {
      return  pageTitle.getText();
    }

    public void updateJobTitle(String newTitle) throws InterruptedException {
//        updateTextWithAction(jobTitleInputBox,newTitle);
     jobTitleInputBox.sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
      jobTitleInputBox.sendKeys(newTitle);

    }

    public void updateJobDesc(String newDesc) throws InterruptedException {
      // updateTextWithAction(jobDescBox,newDesc);


    }

    public void clickSaveBtn()
    {
        saveBtn.click();
    }
}
