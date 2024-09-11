package reportConfig;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CustomLogger {
    private static final Logger logger = LogManager.getLogger(CustomLogger.class);
    private ExtentTest extentTest;

    public CustomLogger() {
        this.setExtentTest(ExtentManager.getTest());
    }

    public CustomLogger(ExtentTest extentTest) {
        this.extentTest = extentTest;
    }

    public void setExtentTest(ExtentTest extentTest) {
        this.extentTest = extentTest;
    }

    public void info(String message) {
        logger.info("--------------------------------------------------");
        logger.info(message);
        extentTest.log(Status.INFO, message);
    }

    public void error(String message) {
        logger.info("--------------------------------------------------");
        logger.error(message);
        extentTest.log(Status.FAIL, message);
    }

    public void warn(String message) {
        logger.info("--------------------------------------------------");
        logger.warn(message);
        extentTest.log(Status.WARNING, message);
    }

    public void debug(String message) {
        logger.info("--------------------------------------------------");
        logger.debug(message);
        extentTest.log(Status.WARNING, message);
    }
}
