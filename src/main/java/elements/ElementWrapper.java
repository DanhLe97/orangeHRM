package elements;

import drivers.DriverWrapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Properties;

import utils.constants.ConfigConstant;
import utils.helpers.PropertiesReader;

public class ElementWrapper {
    private static final Logger log = LogManager.getLogger(ElementWrapper.class.getName());
    private By _by;
    private String _locator;
    protected ElementWrapper parent;
    protected Properties propertiesConfig= PropertiesReader.loadProperties(ConfigConstant.CONFIGURATION_PROPERTIES,"");

    public ElementWrapper() {
    }

    public ElementWrapper(By by) {
        this._by = by;
    }

    public ElementWrapper(String locator) {
        this._locator = locator;
        this._by = this.by(locator);
    }

    public ElementWrapper(String baseLocator, Object... params) {
        this._locator = baseLocator;
        setdynamicBy(params);
    }

    private By by(String locator) {
        By by;

        if (locator.toLowerCase().startsWith("id=")) {
            by = By.id(locator.substring(3));
        } else if (locator.toLowerCase().startsWith("css=")) {
            by = By.cssSelector(locator.substring(4));
        } else if (locator.toLowerCase().startsWith("xpath=")) {
            by = By.xpath(locator.substring(6));
        } else if (locator.toLowerCase().startsWith("name=")) {
            by = By.name(locator.substring(5));
        } else if (locator.toLowerCase().startsWith("class=")) {
            by = By.className(locator.substring(6));
        } else if (locator.toLowerCase().startsWith("//")) {
            by = By.xpath(locator);
        } else {
            log.error("Locator type is not supported!");
            throw new RuntimeException();
        }
        return by;
    }

    public void setdynamicBy(Object... params){
        this._by = this.by(String.format(this._locator, params));
    }

    public WebElement getElement() {
        log.debug("Get element");
        this.waitForElementToBeVisible(Integer.parseInt(propertiesConfig.getProperty("timeOut")));
        return DriverWrapper.getDriver().findElement(this._by);
    }

    public List<WebElement> getElements() {
        log.debug("Get list of element");
        this.waitForElementToBeVisible(Integer.parseInt(propertiesConfig.getProperty("timeOut")));
        return DriverWrapper.getDriver().findElements(this._by);
    }

    public void click() {
        try {
            log.debug("Trying to click on element");
            this.waitForElementToBeClickable(Integer.parseInt(propertiesConfig.getProperty("timeOut")));
            this.getElement().click();
        } catch (Exception e) {
            log.error("Element is not clickable at the moment.");
            throw new ElementClickInterceptedException(e.getMessage());
        }
    }

    public void clickAndHold() {
        log.debug("Trying to click and hold on element");
        this.waitForElementToBeClickable(Integer.parseInt(propertiesConfig.getProperty("timeOut")));

        Actions actions = new Actions(DriverWrapper.getDriver());
        actions.clickAndHold(this.getElement()).perform();
    }

    public void sendKeys(String keys) {
        log.info("Sending {} to element", keys);
        this.getElement().sendKeys(keys);
    }

    public String getText() {
        log.debug("Get element text");
        this.waitForElementToBeVisible(Integer.parseInt(propertiesConfig.getProperty("timeOut")));
        return this.getElement().getText();
    }

    public void scrollToElement() {
        log.debug("Trying to click and hold on element");
        ((JavascriptExecutor) DriverWrapper.getDriver())
                .executeScript("arguments[0].scrollIntoView(true);", this.getElement());
    }

    public boolean isDisplayed() {
        log.debug("Check if element is displayed");
        return this.getElement().isDisplayed();
    }

    public boolean isSelected() {
        log.debug("Check if element is selected");
        return this.getElement().isSelected();
    }

    public void clearText() {
        log.debug("Clear element text");
        this.waitForElementToBeVisible(Integer.parseInt(propertiesConfig.getProperty("timeOut")));
        this.getElement().clear();
    }

    public void hoverMouseToElement() {
        log.debug("Move cursor to element");
        Actions action = new Actions(DriverWrapper.getDriver());
        action.moveToElement(this.getElement()).perform();
    }

    public void waitForElementToBeVisible() {
        log.info("Wait for element to be visible with wait time from properties");
        WebDriverWait wait = new WebDriverWait(DriverWrapper.getDriver()
                , Duration.ofSeconds(Integer.parseInt(propertiesConfig.getProperty("timeOut"))));
        wait.until(ExpectedConditions.visibilityOfElementLocated(this._by));
    }

    public void waitForElementToBeClickable() {
        log.info("Wait for element to be clickable with wait time from properties");
        WebDriverWait wait = new WebDriverWait(DriverWrapper.getDriver()
                , Duration.ofSeconds(Integer.parseInt(propertiesConfig.getProperty("timeOut"))));
        wait.until(ExpectedConditions.elementToBeClickable(this._by));
    }

    public void waitForElementToBeVisible(int timeoutInSeconds) {
        log.info("Wait for element to be visible in {} second", timeoutInSeconds);
        WebDriverWait wait = new WebDriverWait(DriverWrapper.getDriver(), Duration.ofSeconds(timeoutInSeconds));
        wait.until(ExpectedConditions.elementToBeClickable(this._by));
    }

    public void waitForElementToBeClickable(int timeoutInSeconds) {
        log.info("Wait for element to be clickable in {} second", timeoutInSeconds);
        WebDriverWait wait = new WebDriverWait(DriverWrapper.getDriver(), Duration.ofSeconds(timeoutInSeconds));
        wait.until(ExpectedConditions.elementToBeClickable(this._by));
    }

    public void pressKeyToElement(Keys key) {
        log.debug("Press {} to element", key);
        Actions action = new Actions(DriverWrapper.getDriver());
        action.sendKeys(this.getElement(), key).perform();

    }
    public void selectItemInDropdown(String item) {
    	log.debug("Select item in default dropdown");
        // Create a Select object to interact with the dropdown
        Select dropdown = new Select(getElement());

        // Select the item by visible text
        dropdown.selectByVisibleText(item);
    }
    
    public void clearTextByKey() {
        log.debug("Clear element text by Key");
        this.getElement().sendKeys(Keys.CONTROL + "a");
        this.getElement().sendKeys(Keys.DELETE);

    }

    public void waitForClickableElementAndClick() {
        log.debug("Wait for element to be clickable in max second");
        Wait<WebDriver> wait = new FluentWait<>(DriverWrapper.getDriver())
                .withTimeout(Duration.ofSeconds(Integer.parseInt(propertiesConfig.getProperty("timeOut"))))
                .pollingEvery(Duration.ofSeconds(Integer.parseInt(propertiesConfig.getProperty("pollingEvery"))))
                .ignoring(NoSuchElementException.class);
        wait.until(ExpectedConditions.elementToBeClickable(this._by));
    }
    public void acceptAlert() {
    	Alert alert = DriverWrapper.getDriver().switchTo().alert();
    	alert.accept();
    }
    public void denyAlert() {
    	Alert alert = DriverWrapper.getDriver().switchTo().alert();
    	alert.dismiss();
    }

}
