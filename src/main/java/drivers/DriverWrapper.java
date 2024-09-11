package drivers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class DriverWrapper {
    private static final Logger log = LogManager.getLogger(DriverWrapper.class.getName());

    public static WebDriver getDriver() {
        return DriverFactory.getWebDriver();
    }

    public static void navigateToURL(String url) {
        log.debug("Navigating to " + url);
        getDriver().navigate().to(url);
    }

    public static void closeBrowser() {
        log.debug("Closing current window");
        getDriver().close();
    }

    public static void quitBrowser() {
        log.debug("Quitting current window");
        getDriver().quit();
    }

    public static void maximizeBrowser() {
        log.debug("Maximize current window");
        getDriver().manage().window().maximize();
    }

    public static Actions getAction(){
        return new Actions(getDriver());
    }

    public static String getPageTitle() {
        return getDriver().getTitle();
    }

    public static void acceptAlert() {
        getDriver().switchTo().alert().accept();
    }

    public static void dismissAlert() {
        getDriver().switchTo().alert().dismiss();
    }

    public static String getAlertText() {
        return getDriver().switchTo().alert().getText();
    }

    public static void backToPreviousPage() {
        getDriver().navigate().back();
    }

    public static void forwardToPage() {
        getDriver().navigate().forward();
    }

    public static void refreshCurrentPage() {
        getDriver().navigate().refresh();
    }

    public static void switchToWindow(String windowHandle) {
        getDriver().switchTo().window(windowHandle);
    }
}
