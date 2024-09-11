package elements;

import org.openqa.selenium.By;

public class CheckBoxElement extends ElementWrapper {

    public CheckBoxElement() {
        super();
    }

    public CheckBoxElement(By by) {
        super(by);
    }

    public CheckBoxElement(String locator) {
        super(locator);
    }

    public CheckBoxElement(String baseLocator, Object... params) {
        super(baseLocator, params);
    }
}
