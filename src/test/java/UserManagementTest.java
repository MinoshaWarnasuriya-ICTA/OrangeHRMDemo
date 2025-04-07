import PageObjects.*;
import TestComponents.*;
import org.testng.*;
import org.testng.annotations.*;

import java.io.*;
import java.sql.*;

public class UserManagementTest extends BaseTest {
    AdminPage adminPage;
    DashBoard dashBoard;
    SystemUserPage systemUserPage;
    AddUserPage addUserPage;
    EditUserPage editUserPage;

    //    String username ="minosha";
//    String password="Mwarnasu@123";
    @Test
    public void checkVisibilityOfSystemUsers() throws IOException {
        //Go to admin tab and check whether the user management text and system users list is visible
        dashBoard = loginPage.login(loginPage.getUsername(), loginPage.getPassword());
        adminPage = dashBoard.goToAdminPage();
        Assert.assertEquals(dashBoard.getPageTitle(), "User Management");
        //System.out.println(dashBoard.getPageTitle());
        Assert.assertTrue(adminPage.checkVisibilityOfUsersList());
    }

    @Test
    public void searchSystemUser() throws IOException {
        String username = "minosha";
        String role = "Admin";
        String employeeNameHint = "Mi";
        String status = "Enabled";
        String employeeName = "Minosha Warnasuriya";
        //Search system users with all the filters and verify that the search results match
        dashBoard = loginPage.login(loginPage.getUsername(), loginPage.getPassword());
        adminPage = dashBoard.goToAdminPage();
        systemUserPage = adminPage.goToSystemUsersPage();
        systemUserPage.enterUsername(username);
        systemUserPage.selectUserRole(role);
        systemUserPage.selectEmployeeName(employeeNameHint);
        systemUserPage.selectStatus(status);
        systemUserPage.clickSearchBtn();
        Assert.assertTrue(systemUserPage.verifyUserSearchResult(username, role, employeeName, status));

    }

    @Test
    public void searchUserByUsername() throws IOException {
        //Search system user by username only and verify the search result
        String username = "minosha";
        dashBoard = loginPage.login(loginPage.getUsername(), loginPage.getPassword());
        adminPage = dashBoard.goToAdminPage();
        systemUserPage = adminPage.goToSystemUsersPage();
        systemUserPage.enterUsername(username);
        Assert.assertTrue(systemUserPage.verifyUsernameInSearchResult(username));
    }

    @Test
    public void searchAdminUsers() throws IOException {
        //Filter all the admin users and verify only admin users are displayed
        String role = "Admin";
        dashBoard = loginPage.login(loginPage.getUsername(), loginPage.getPassword());
        adminPage = dashBoard.goToAdminPage();
        systemUserPage = adminPage.goToSystemUsersPage();
        systemUserPage.selectUserRole(role);
        systemUserPage.clickSearchBtn();
        Assert.assertTrue(systemUserPage.verifyUserRoleInSearchResult(role));
    }

    @Test
    public void searchESSUsers() throws IOException {
        //Filter all the ESS users and verify only the ESS users are displayed
        String role = "ESS";
        dashBoard = loginPage.login(loginPage.getUsername(), loginPage.getPassword());
        adminPage = dashBoard.goToAdminPage();
        systemUserPage = adminPage.goToSystemUsersPage();
        systemUserPage.selectUserRole(role);
        systemUserPage.clickSearchBtn();
        Assert.assertTrue(systemUserPage.verifyUserRoleInSearchResult(role));
    }

    @Test
    public void searchUserByEmpName() throws IOException {
        //Search employee by employee name and check whether the matching suggestions are displayed
        String employeeNameHint = "Min";
        dashBoard = loginPage.login(loginPage.getUsername(), loginPage.getPassword());
        adminPage = dashBoard.goToAdminPage();
        systemUserPage = adminPage.goToSystemUsersPage();
        systemUserPage.searchEmployeeName(employeeNameHint);
        Assert.assertTrue(systemUserPage.verifyEmpNameSuggestions(employeeNameHint));
    }

    @Test
    public void filterEnabledUsers() throws IOException, InterruptedException {
        //Filter all the enabled users and verify that only enabled users are displayed
        String status = "Enabled";
        dashBoard = loginPage.login(loginPage.getUsername(), loginPage.getPassword());
        adminPage = dashBoard.goToAdminPage();
        systemUserPage = adminPage.goToSystemUsersPage();
        systemUserPage.selectStatus(status);
        systemUserPage.clickSearchBtn();
        Assert.assertTrue(systemUserPage.verifyStatusInSearchResult(status));
    }

    @Test
    public void filterDisabledUsers() throws IOException, InterruptedException {
        //Filter all the disabled users and verify that only disabled users are displayed
        String status = "Disabled";
        dashBoard = loginPage.login(loginPage.getUsername(), loginPage.getPassword());
        adminPage = dashBoard.goToAdminPage();
        systemUserPage = adminPage.goToSystemUsersPage();
        systemUserPage.selectStatus(status);
        systemUserPage.clickSearchBtn();
        Assert.assertTrue(systemUserPage.verifyStatusInSearchResult(status));
    }

    @Test
    public void filterReset() throws IOException {
        //Test whether the reset button works properly
        String username = "Oshan";
        String role = "ESS";
        String nameHint = "Osh";
        String status = "Enabled";
        dashBoard = loginPage.login(loginPage.getUsername(), loginPage.getPassword());
        adminPage = dashBoard.goToAdminPage();
        systemUserPage = adminPage.goToSystemUsersPage();
        systemUserPage.enterUsername(username);
        systemUserPage.selectUserRole(role);
        systemUserPage.selectEmployeeName(nameHint);
        systemUserPage.selectStatus(status);
        systemUserPage.clickResetBtn();
        Assert.assertTrue(systemUserPage.verifyFilterReset());
    }

    @Test
    public void addAdminUser() throws IOException, InterruptedException {
            /*Test whether the admin can add a new system user and verify
            that the new user is visible on the users list*/
        String role = "Admin";
        String empNameHint = "Janv";
        String status = "Enabled";
        String username = "janvic";
        String password = "Emppw@123";
        dashBoard = loginPage.login(loginPage.getUsername(), loginPage.getPassword());
        adminPage = dashBoard.goToAdminPage();
        systemUserPage = adminPage.goToSystemUsersPage();
        addUserPage = systemUserPage.clickAddBtn();
        addUserPage.selectUserRole(role);
        addUserPage.enterEmployeeName(empNameHint);
        addUserPage.selectStatus(status);
        addUserPage.enterUsername(username);
        addUserPage.enterPassword(password);
        addUserPage.confirmPassword(password);
        systemUserPage = addUserPage.clickSave();
        Assert.assertTrue(systemUserPage.verifyNewlyAddedUser(username));
    }

    @Test
    public void addESSUser() throws IOException, InterruptedException {
    /*Test whether the admin can add a new ESS user and
     verify that the new user is visible on the users list*/
        String role = "ESS";
        String empNameHint = "Krish";
        String status = "Enabled";
        String username = "krishr";
        String password = "Emppw@123";
        dashBoard = loginPage.login(loginPage.getUsername(), loginPage.getPassword());
        adminPage = dashBoard.goToAdminPage();
        systemUserPage = adminPage.goToSystemUsersPage();
        addUserPage = systemUserPage.clickAddBtn();
        addUserPage.selectUserRole(role);
        addUserPage.enterEmployeeName(empNameHint);
        addUserPage.selectStatus(status);
        addUserPage.enterUsername(username);
        addUserPage.enterPassword(password);
        addUserPage.confirmPassword(password);
        systemUserPage = addUserPage.clickSave();
        Assert.assertTrue(systemUserPage.verifyNewlyAddedUser(username));
    }

    @Test
    public void editSystemUserRole() throws IOException, InterruptedException {
        //Test whether the admin can edit a user
        String username = "janvic";
        String role = "ESS";
        String toastMsgeText = "Successfully Updated";
        dashBoard = loginPage.login(loginPage.getUsername(), loginPage.getPassword());
        adminPage = dashBoard.goToAdminPage();
        systemUserPage = adminPage.goToSystemUsersPage();
        editUserPage = systemUserPage.editSystemUser(username);
        Assert.assertTrue(editUserPage.verifyEditUserPageVisibility());
        editUserPage.updateRole(role);
        systemUserPage = editUserPage.clickSave();
        Assert.assertTrue(editUserPage.verifyToastMsgeDisplay());
        Assert.assertEquals(editUserPage.getToastMsgeText(), toastMsgeText);
        Assert.assertTrue(systemUserPage.verifyUserUpdate(username, role));
    }

    @Test
    public void deleteSystemUser() throws IOException {
        //Test whether the admin can delete a user
        String username = "ashvi";
        String expectedConfirmationMsge = "The selected record will be permanently deleted. Are you sure you want to continue?";
        String expectedToastMsge = "Successfully Deleted";
        dashBoard = loginPage.login(loginPage.getUsername(), loginPage.getPassword());
        adminPage = dashBoard.goToAdminPage();
        systemUserPage = adminPage.goToSystemUsersPage();
        systemUserPage.clickDeleteUserBtn(username);
        Assert.assertEquals(systemUserPage.getDeleteConfirmationMsgeTxt(), expectedConfirmationMsge);
        systemUserPage.confirmDelete();
        Assert.assertEquals(systemUserPage.getDeleteSuccessToastTest(), expectedToastMsge);
        Assert.assertTrue(systemUserPage.verifyDeletedUserNotVisible(username));
    }

    @Test
    public void deleteMultipleUsers() throws IOException, InterruptedException {
        String[] usernames = {"testu", "ashwind"};
        String expectedConfirmationMsge = "The selected record will be permanently deleted. Are you sure you want to continue?";
        String expectedToastMsge = "Successfully Deleted";
        dashBoard = loginPage.login(loginPage.getUsername(), loginPage.getPassword());
        adminPage = dashBoard.goToAdminPage();
        systemUserPage = adminPage.goToSystemUsersPage();
        systemUserPage.selectMultipleUsers(usernames);
        systemUserPage.clickDeleteSelectedBtn();
        Assert.assertEquals(systemUserPage.getDeleteConfirmationMsgeTxt(), expectedConfirmationMsge);
        systemUserPage.confirmDelete();
        Assert.assertEquals(systemUserPage.getDeleteSuccessToastTest(), expectedToastMsge);
        Assert.assertTrue(systemUserPage.verifyDeletedUsersNotVisible(usernames));

    }

}
