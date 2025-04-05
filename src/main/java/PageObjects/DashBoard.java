package PageObjects;

import AbstractComponents.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.*;

public class DashBoard extends AbstractComponent {
    WebDriver driver;

    public DashBoard(WebDriver driver) {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(css = "span[class*='breadcrumb'] h6")
    WebElement dashboardTitle;

    @FindBy(xpath = "//span[text()='Admin']/parent::a")
    WebElement adminTab;

    @FindBy(xpath = "//span/h6[2]")
    WebElement pageTitle;

    public String getDashboardTitle()
    {
        waitForWebElementToAppear(dashboardTitle);
        return dashboardTitle.getText();
    }

    public AdminPage goToAdminPage()
    {
        waitForWebElementToAppear(adminTab);
        adminTab.click();
        AdminPage adminPage = new AdminPage(driver);
        return adminPage;
    }

    public String getPageTitle()
    {
        return pageTitle.getText();
    }





}
