package elements;

import org.openqa.selenium.By;

public class TextBoxElement extends ElementWrapper{

    public TextBoxElement() {
        super();
    }

    public TextBoxElement(By by) {
        super(by);
    }

    public TextBoxElement(String locator) {
        super(locator);
    }

    public TextBoxElement(String baseLocator, Object... params) {
        super(baseLocator, params);
    }
}
