import PageObjects.*;
import TestComponents.*;
import org.testng.*;
import org.testng.annotations.*;

import java.io.*;

public class PayGradeTest extends BaseTest {
    EditPayGradePage editPayGrade;
    PayGradePage payGrade;
    AdminPage adminPage;
    DashBoard dashBoard;
    @Test
    public void testPayGradePageVisibility() throws IOException {
        //Select pay grades from the job dropdown list and test
        // whether the Pay Grade text and pay grade list in visible
         dashBoard = loginPage.login(loginPage.getUsername(), loginPage.getPassword());
         adminPage = dashBoard.goToAdminPage();
         payGrade = adminPage.goToPayGradePage();
        Assert.assertEquals(payGrade.getVisiblePageTitle(), "Pay Grades");
    }

    @Test
    public void addNewPayGrade() throws IOException {
        //Test whether the admin can add a new pay grade and verify that it's visible on the list
        String payGradeName = "Senior Engineer Level";
        String currency = "LKR - Sri Lanka Rupee";
        String minimumSal = "200000";
        String maximumSal = "350000";
        dashBoard = loginPage.login(loginPage.getUsername(), loginPage.getPassword());
         adminPage = dashBoard.goToAdminPage();
          payGrade = adminPage.goToPayGradePage();
        AddPayGradePage addPayGrade = payGrade.clickAddBtn();
        addPayGrade.typePayGradeName(payGradeName);
         editPayGrade = addPayGrade.clickSaveBtn();
        Assert.assertTrue(addPayGrade.checkVisibilityOfToastMsg());
        //Assert.assertEquals(addPayGrade.getToastMsg(), "Successfully Saved");
        Assert.assertEquals(editPayGrade.getVisiblePageTitle(), "Edit Pay Grade");
        editPayGrade.clickAddBtn();
        Assert.assertTrue(editPayGrade.checkVisibilityOfAddCurrencyTitle());
        editPayGrade.selectCurrency(currency);
        editPayGrade.setMinimumSalary(minimumSal);
        editPayGrade.setMaximumSalary(maximumSal);
        editPayGrade.clickSaveBtn();
        //Assert.assertEquals(editPayGrade.getToastMsg(),"Successfully Saved");
        Assert.assertTrue(editPayGrade.verifyAddedCurrency(currency));
        adminPage.goToPayGradePage();
        Assert.assertTrue(payGrade.verifyPayGradeVisibility(payGradeName));
    }

    @Test
    public void editExistingPayGrade() throws IOException {
        //Test whether the admin can edit an existing pay grade name and verify it's updated on the list
        String existingPayGradeName = "Senior Engineer Level";
        String newPayGrade = "Specialist Engineer";
          dashBoard = loginPage.login(loginPage.getUsername(), loginPage.getPassword());
          adminPage = dashBoard.goToAdminPage();
         payGrade = adminPage.goToPayGradePage();
        editPayGrade =payGrade.clickEditBtn(existingPayGradeName);
        editPayGrade.editPayGradeName(newPayGrade);
        editPayGrade.clickSave();
        Assert.assertEquals(editPayGrade.getToastMsg(),"Successfully Updated");
    }

    @Test
    public void deleteSinglePayGrade() throws IOException {
        //Test whether the admin can delete an existing pay grade and verify that it's not visible on the list
        String payGradeName = "Senior Project Manager";
         dashBoard = loginPage.login(loginPage.getUsername(), loginPage.getPassword());
         adminPage = dashBoard.goToAdminPage();
         payGrade = adminPage.goToPayGradePage();
         payGrade.clickDeleteBtn(payGradeName);
         Assert.assertTrue(payGrade.verifyDeleteConfirmationPopupDisplay());
         payGrade.confirmDelete();
         Assert.assertEquals(payGrade.getToastMsg(),"Successfully Deleted");
         Assert.assertTrue(payGrade.verifyDeletedRecordNotVisible(payGradeName));
    }

    @Test
    public void deleteAllPayGrades() throws IOException {
        //Test whether multiple pay grades can be selected and deleted at once
        dashBoard = loginPage.login(loginPage.getUsername(), loginPage.getPassword());
        adminPage = dashBoard.goToAdminPage();
        payGrade = adminPage.goToPayGradePage();
        payGrade.selectAllPayGrades();
        payGrade.deleteSelectedPayGrades();
        Assert.assertEquals(payGrade.getToastMsg(),"Successfully Deleted");

    }



}
