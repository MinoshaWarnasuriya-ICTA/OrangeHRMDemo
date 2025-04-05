import PageObjects.*;
import TestComponents.*;
import org.slf4j.*;
import org.testng.*;
import org.testng.annotations.*;

import java.io.*;

public class LoginTest extends BaseTest {


    @Test
    public void loginWithValidCredentials() throws IOException {
        //Login to admin panel with valid credentials and check whether the dashboard is visible
        loginPage.enterUsername(loginPage.getUsername());
        loginPage.enterPassword(loginPage.getPassword());
     DashBoard dashBoard = loginPage.clickLoginBtn();
        Assert.assertEquals(dashBoard.getDashboardTitle(),"Dashboard");

    }

    @Test
    public void loginWithInvalidCredentials() throws IOException {
        //Login with invalid credentials and check whether the error message is displayed
        loginPage.enterUsername(loginPage.getUsername());
        loginPage.enterPassword("mino123");
        loginPage.clickLoginBtn();
        Assert.assertEquals(loginPage.getErrorMsge(),"Invalid credentials");

    }

}
