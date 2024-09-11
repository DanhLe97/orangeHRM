package drivers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;


public class DriverFactory {
    private static final Logger log = LogManager.getLogger(DriverFactory.class.getName());
    private static ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();
    private static ThreadLocal<Properties> propertiesBrowser = new ThreadLocal<>();

    public static void setWebDriver(Properties browserProperties) {
        propertiesBrowser.set(browserProperties);
        String browserName=browserProperties.getProperty("browserName");
        String remote=browserProperties.getProperty("remoteUrl");

        if (remote!=null) {
            switch (browserName.trim().toLowerCase()) {
                case "chrome":
                    initRemoteChromeDriver(browserProperties);
                    break;
                default:
                    log.error("Browser type not support " + browserName);
                    throw new IllegalArgumentException();
            }
        } else {
            switch (browserName.trim().toLowerCase()) {
                case "chrome":
                    initChromeDriver(browserProperties);
                    break;
                case "firefox":
                    initFirefoxDriver(browserProperties);
                    break;
                case "edge":
                    initEdgeDriver(browserProperties);
                    break;
                default:
                    log.error("Browser type not support " + browserName);
                    throw new IllegalArgumentException();
            }
        }
    }

    private static void initRemoteChromeDriver(Properties properties) {
        try {
            log.info("Running test on {}", "remote chrome");
            String[] arguments = properties.getProperty("arguments").split(",");
            ChromeOptions options = new ChromeOptions();
            for (String argument : arguments) {
                options.addArguments(argument);
            }

            String remoteUrl = properties.getProperty("remoteUrl");
            driverThreadLocal.set(new RemoteWebDriver(new URL(remoteUrl), options));
        } catch (MalformedURLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private static void initChromeDriver(Properties properties) {
        log.info("Running test on {}", "local chrome");
        String[] arguments = properties.getProperty("arguments").split(",");
        ChromeOptions options = new ChromeOptions();
        for (String argument : arguments) {
            options.addArguments(argument);
        }
        DesiredCapabilities caps = new DesiredCapabilities(options);
        caps.setAcceptInsecureCerts(true);
        driverThreadLocal.set(new ChromeDriver(options));
    }

    private static void initFirefoxDriver(Properties properties) {
        log.info("Running test on {}", "local firefox");
        String[] arguments = properties.getProperty("arguments").split(",");
        FirefoxOptions options = new FirefoxOptions();
        for (String argument : arguments) {
            options.addArguments(argument);
        }
        driverThreadLocal.set(new FirefoxDriver(options));
    }

    private static void initEdgeDriver(Properties properties) {
        log.info("Running test on {}", "local edge");
        String[] arguments = properties.getProperty("arguments").split(",");
        EdgeOptions options = new EdgeOptions();
        for (String argument : arguments) {
            options.addArguments(argument);
        }
        driverThreadLocal.set(new EdgeDriver());
    }

//    private static void initChromeHeadlessDriver(Properties properties) {
//        log.info("Running test on {}", "local chrome headless");
//        String[] arguments = properties.getProperty("arguments").split(",");
//        ChromeOptions options = new ChromeOptions();
//        for (String argument : arguments) {
//            options.addArguments(argument);
//        }
//        driverThreadLocal.set(new ChromeDriver(options));
//    }

    public static WebDriver getWebDriver() {
        return driverThreadLocal.get();
    }

    public static void removeDriver() {
        driverThreadLocal.remove();
    }

    public static String getBrowserNameDriver() {
        //get browserName from properties of sectionBrowser
        return propertiesBrowser.get().getProperty("browserName");
    }
}
