package elements;

import org.openqa.selenium.By;

public class LabelElement extends ElementWrapper{

    public LabelElement() {
        super();
    }

    public LabelElement(By by) {
        super(by);
    }

    public LabelElement(String locator) {
        super(locator);
    }

    public LabelElement(String baseLocator, Object... params) {
        super(baseLocator, params);
    }
}
