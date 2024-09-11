package elements;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

public class SelectElement extends ElementWrapper{
    private static final Logger log = LogManager.getLogger(SelectElement.class.getName());

    public SelectElement() {
        super();
    }

    public SelectElement(By by) {
        super(by);
    }

    public SelectElement(String locator) {
        super(locator);
    }

    public SelectElement(String baseLocator, Object... params) {
        super(baseLocator, params);
    }

    public void selectByText(String text) {
        log.debug("Selecting item by text");
        Select select = new Select(super.getElement());
        select.selectByVisibleText(text);
    }

    public void selectByIndex(int index) {
        log.debug("Selecting item by id");
        Select select = new Select(super.getElement());
        select.selectByIndex(index);
    }
}
