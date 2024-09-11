package elements;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

public class ButtonElement extends ElementWrapper {
    private static final Logger log = LogManager.getLogger(ButtonElement.class.getName());

    public ButtonElement() {
        super();
    }

    public ButtonElement(By by) {
        super(by);
    }

    public ButtonElement(String locator) {
        super(locator);
    }

    public ButtonElement(String baseLocator, Object... params) {
        super(baseLocator, params);
    }

    public void waitToClick() {
        log.debug("Wait and click on element");
        super.waitForElementToBeClickable(Integer.parseInt(propertiesConfig.getProperty("timeOut")));
        super.click();
    }

    public void scrollToClick() {
        log.debug("Scroll and click on element");
        super.scrollToElement();
        super.click();
    }
}
