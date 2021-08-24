package genericutils;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.*;

import java.io.File;
import java.io.IOException;

public class Listener extends Reporter implements ITestListener, ISuiteListener {


    @Override
    public void onStart(ISuite suite) {
        Reporter.startReport();
    }

    @Override
    public void onFinish(ISuite suite) {
        Reporter.stopReport();
    }

    String testScriptName;
    String testName;

    @Override
    public void onTestStart(ITestResult result) {
        testScriptName = result.getName();
        /*extentTest = reports.createTest(testScriptName).assignCategory(testName);*/
        extentTest.set(reports.createTest(testScriptName).assignCategory(testName));
        extentTest.get().log(Status.INFO, testScriptName + " started successfully");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        extentTest.get().log(Status.PASS, testScriptName + " successfully executed");
    }

    @Override
    public void onTestFailure(ITestResult result) {

        String path = System.getProperty("user.dir") + "/reports/images/" + (long) Math.floor(Math.random() * 900000000L) + 10000000L + ".jpg";
        try {
            FileUtils.copyFile(((RemoteWebDriver) getDriver()).getScreenshotAs(OutputType.FILE), new File(path));
        } catch (WebDriverException | IOException e) {
            e.printStackTrace();
        }
        /*extentTest.addScreenCaptureFromPath(path).log(Status.FAIL, testName + " failed", MediaEntityBuilder.createScreenCaptureFromPath(path).build());*/
        extentTest.get().log(Status.FAIL, result.getThrowable(), MediaEntityBuilder.createScreenCaptureFromPath(path).build());
    }

    @Override
    public void onStart(ITestContext context) {
        testName = context.getName();
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        extentTest.get().log(Status.SKIP, testScriptName + " skipped");
        extentTest.get().log(Status.INFO, result.getThrowable());
    }
}
