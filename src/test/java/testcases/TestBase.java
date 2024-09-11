package testcases;

import drivers.DriverFactory;
import drivers.DriverWrapper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import utils.helpers.PropertiesReader;
import utils.helpers.DataHelper;

import java.lang.reflect.Method;
import java.util.Properties;

import static utils.constants.TestConfigConstant.*;

public class TestBase {
    protected static Logger log4j = LogManager.getLogger();

    @BeforeSuite
    public void BeforeSuite() {
        log4j.info("BeforeSuite - Start");
    }

    @BeforeMethod
    @Parameters({"sectionBrowser"})
    public void beforeMethod(@Optional("chrome.local") String sectionBrowser, Method method){
        log4j.info("beforeMethod - Start");

        Properties browserProperties = PropertiesReader.loadProperties(BROWSER_PROPERTIES, sectionBrowser);
        Properties propertiesAUT = PropertiesReader.loadProperties(AUT_PROPERTIES, "");

        DriverFactory.setWebDriver(browserProperties);
        DriverWrapper.maximizeBrowser();
        DriverWrapper.navigateToURL(propertiesAUT.getProperty("orangeHRMURL"));
        log4j.info("beforeMethod - End");
    }

    @AfterMethod
    public void afterMethod(ITestResult result) {
        log4j.info("afterMethod - Start");
//        DriverWrapper.closeBrowser();
//        DriverFactory.removeDriver();
        log4j.info("afterMethod - End");
    }

    @AfterSuite
    public void AfterSuite() {
        log4j.info("AfterSuite - Start");
    }

    @DataProvider(name = "jsonDataProvider")
    public Object[][] getData(Method method) {
        log4j.info("Loading DataProvider for {} - Start", method.getName());
        String filePath = "datas/" + method.getName() + ".json";
        DataHelper data = new DataHelper(filePath);
        return new Object[][]{{data}};
    }
}
