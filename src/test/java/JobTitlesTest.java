import PageObjects.*;
import TestComponents.*;
import org.testng.*;
import org.testng.annotations.*;

import java.io.*;

public class JobTitlesTest extends BaseTest {
    DashBoard dashBoard;
    AdminPage adminPage;
    JobTitles jobTitles;
    AddJobTitlePage addTitle;
    EditJobTitlePage editJobTitle;

    @Test
    public void checkVisibilityOfJobTitlesList() throws IOException {
        //Select job titles under Job dropdown and check whether the job titles text and title list is visible
        dashBoard = loginPage.login(loginPage.getUsername(), loginPage.getPassword());
        adminPage = dashBoard.goToAdminPage();
        Assert.assertEquals(dashBoard.getPageTitle(), "User Management");
        jobTitles = adminPage.goToJobTitlesPage();
        Assert.assertEquals(jobTitles.getPageTitle(), "Job Titles");
        Assert.assertTrue(jobTitles.checkVisibilityOfTitles());
    }

    @Test
    public void addJobTitle() throws IOException {
        //Test whether the admin can add a new job title and verify it's visible on the titles list
        String jobTitle = "Full Stack Developer";
        String desc = "Senior level position";
        dashBoard = loginPage.login(loginPage.getUsername(), loginPage.getPassword());
        adminPage = dashBoard.goToAdminPage();
        jobTitles = adminPage.goToJobTitlesPage();
        addTitle = jobTitles.clickAddBtn();
        Assert.assertEquals(addTitle.getPageTitle(), "Add Job Title");
        addTitle.enterJobTitle(jobTitle);
        addTitle.enterJobDescription(desc);
        addTitle.clickSaveBtn();
        Assert.assertEquals(jobTitles.getToastMsg(), "Successfully Saved");
        Assert.assertTrue(jobTitles.verifyVisibilityOFNewTitle(jobTitle, desc));
    }

    @Test
    public void editJobTitle() throws IOException, InterruptedException {
        //Test whether the admin can edit an existing job title
        String jobTitle = "Manual QA Analyst";
        String newTitle = "Automation Engineer";
        String newDesc = "Senior level technical position";
        dashBoard = loginPage.login(loginPage.getUsername(), loginPage.getPassword());
        adminPage = dashBoard.goToAdminPage();
        jobTitles = adminPage.goToJobTitlesPage();
        editJobTitle = jobTitles.clickEditButton(jobTitle);
        Assert.assertEquals(editJobTitle.getEditPageTitle(), "Edit Job Title");
        editJobTitle.updateJobTitle(newTitle);
        editJobTitle.updateJobDesc(newDesc);
        editJobTitle.clickSaveBtn();
        Assert.assertEquals(jobTitles.getToastMsg(), "Successfully Updated");
        Assert.assertTrue(jobTitles.verifyUpdatedTitle(newTitle, newDesc));
    }

    @Test
    public void deleteJobTitle() throws IOException {
        //Test whether the admin can delete an existing job title and verify it's deleted
        String titleToDelete = "HR Director";
        dashBoard = loginPage.login(loginPage.getUsername(), loginPage.getPassword());
        adminPage = dashBoard.goToAdminPage();
        jobTitles = adminPage.goToJobTitlesPage();
        jobTitles.clickDeleteBtn(titleToDelete);
        Assert.assertTrue(jobTitles.checkVisibilityOfDeleteConfirmPopup());
        jobTitles.clickDeleteConfirmBtn();
        Assert.assertTrue(jobTitles.checkToastMsgVisibility());
        Assert.assertEquals(jobTitles.getToastMsg(), "Successfully Deleted");
        Assert.assertTrue(jobTitles.verifyDeletedTitle(titleToDelete));
    }

    @Test
    public void deleteMultipleTitles() throws IOException {
//Test whether multiple titles can be selected and deleted at once
        String[] titleList = {"Test Title 1","Test title2"};
        dashBoard = loginPage.login(loginPage.getUsername(), loginPage.getPassword());
        adminPage = dashBoard.goToAdminPage();
        jobTitles = adminPage.goToJobTitlesPage();
        jobTitles.selectMultipleTitles(titleList);
        jobTitles.clickDeleteSelectedBtn();
        Assert.assertTrue(jobTitles.checkVisibilityOfDeleteConfirmPopup());
        jobTitles.clickDeleteConfirmBtn();
        Assert.assertTrue(jobTitles.checkToastMsgVisibility());
        Assert.assertEquals(jobTitles.getToastMsg(), "Successfully Deleted");
        Assert.assertTrue(jobTitles.verifyDeletedTitles(titleList));




    }
}
