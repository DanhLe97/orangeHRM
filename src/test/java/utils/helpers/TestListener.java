package utils.helpers;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import reportConfig.CustomLogger;
import reportConfig.ExtentManager;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TestListener implements ITestListener {
    protected static ExtentReports extentReports;
    private static final Logger log = LogManager.getLogger(TestListener.class.getName());
    private String reportName = genReportName();
    private String extentReportPath = System.getProperty("user.dir") + "\\reports\\" + reportName;

    public static String genReportName() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss");
        return "report_" + sdf.format(new Date());
    }

    @Override
    public void onStart(ITestContext context) {
        log.info("Test suite started: " + context.getName());
        extentReports = ExtentManager.setUp(extentReportPath + "\\" + reportName + ".html");
    }

    @Override
    public void onTestStart(ITestResult result) {
        log.info("Test started: " + result.getName());
        ExtentTest test = ExtentManager.createTest(extentReports, result.getName());
        ExtentManager.setTest(test);


        CustomLogger logger = new CustomLogger();
        logger.setExtentTest(ExtentManager.getTest());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        log.info("Test passed: " + result.getName());
        ExtentManager.addTestResult(ExtentManager.getTest(), result, extentReportPath);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        log.info("Test failed: " + result.getName());
        ExtentManager.addTestResult(ExtentManager.getTest(), result, extentReportPath);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        log.info("Test skipped: " + result.getName());
        ExtentManager.addTestResult(ExtentManager.getTest(), result, extentReportPath);
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        log.info("Test failed but within success percentage: " + result.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        extentReports.flush();
        log.info("Test suite finished: " + context.getName());
    }
}
