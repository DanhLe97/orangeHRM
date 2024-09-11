package utils.helpers;

import java.io.File;
import java.io.IOException;

import drivers.DriverWrapper;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class TakeScreenShot {
    private static final Logger log = LogManager.getLogger(TakeScreenShot.class.getName());

    public void takeScreenshot(String fileName, String directory) {
        try {
            log.info("Taking screenshot");
            TakesScreenshot ts = (TakesScreenshot) DriverWrapper.getDriver();
            File source = ts.getScreenshotAs(OutputType.FILE);

            String dest = directory + "\\" + fileName;
            File destination = new File(dest);
            FileUtils.copyFile(source, destination);
        } catch (IOException e) {
            log.error("There was an error while taking screenshot!");
            e.printStackTrace();
        }
    }
}
