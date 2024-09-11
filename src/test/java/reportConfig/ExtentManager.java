package reportConfig;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.model.Media;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestResult;
import utils.helpers.TakeScreenShot;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentManager {
    private static ThreadLocal<ExtentTest> extentTestThreadLocal = new ThreadLocal<>();

    public static synchronized ExtentTest getTest() {
        return extentTestThreadLocal.get();
    }

    public static synchronized void setTest(ExtentTest extentTest) {
        extentTestThreadLocal.set(extentTest);
    }

    public static ExtentReports setUp(String outputPath) {
        ExtentSparkReporter htmlReporter = new ExtentSparkReporter(outputPath);
        htmlReporter.config().setTheme(Theme.DARK);
        htmlReporter.config().setEncoding("utf-8");
        htmlReporter.config().setTimelineEnabled(true);
        htmlReporter.config().setDocumentTitle("Automation Report");
        htmlReporter.config().setReportName("Selenium Test Report");

        ExtentReports extentReports = new ExtentReports();
        extentReports.setSystemInfo("Company", "Agest");
        extentReports.setSystemInfo("Project", "Selenium2");
        extentReports.setSystemInfo("Team", "Group 2");
        extentReports.attachReporter(htmlReporter);
        return extentReports;
    }

    public static ExtentTest createTest(ExtentReports extentReports, String methodName) {
        return extentReports.createTest(methodName);
    }

    public static ExtentTest addTestResult(ExtentTest extentTest, ITestResult result, String extentReportPath) {
        if (result.getStatus() == ITestResult.FAILURE) {
            String fileName = "capture_" + DateTimeGenerate() + ".png";
            TakeScreenShot sc = new TakeScreenShot();

            sc.takeScreenshot(fileName, extentReportPath);
            Media media = MediaEntityBuilder.createScreenCaptureFromPath(fileName).build();

            extentTest.log(Status.FAIL, "Test case failed: " + result.getThrowable(), media);
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            extentTest.log(Status.PASS, "Test case passed");
        } else if (result.getStatus() == ITestResult.SKIP) {
            extentTest.log(Status.SKIP, "Test case skipped: " + result.getThrowable());
        }

        return extentTest;
    }

    public static String DateTimeGenerate() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss");
        return sdf.format(new Date());
    }
}