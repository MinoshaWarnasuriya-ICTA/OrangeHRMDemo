package TestComponents;

import com.aventstack.extentreports.*;
import org.apache.commons.compress.harmony.pack200.*;
import org.openqa.selenium.*;
import org.testng.*;

import java.io.*;

public class TestListener extends BaseTest implements ITestListener  {
    ExtentReports extent = getExtentObject();
    ExtentTest test;

    public void onTestStart(ITestResult result) {

         test = extent.createTest(result.getMethod().getMethodName());
    }

    public void onTestSuccess(ITestResult result) {
        test.log(Status.PASS,result.getTestName());
    }

    public void onTestFailure(ITestResult result) {
        test.log(Status.FAIL,result.getThrowable());
        WebDriver driver = null;

        try {
           driver =  (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
        } catch (IllegalAccessException | NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
        String path=null;
        try {
             path = getScreenshot(driver,result.getMethod().getMethodName());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        test.addScreenCaptureFromPath(path,result.getMethod().getMethodName()+".png");
    }

    public void onTestSkipped(ITestResult result) {
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    }

    public void onTestFailedWithTimeout(ITestResult result) {
        this.onTestFailure(result);
    }

    public void onStart(ITestContext context) {
    }

    public void onFinish(ITestContext context) {
        extent.flush();
    }

}
