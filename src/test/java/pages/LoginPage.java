package pages;

import com.fasterxml.jackson.databind.JsonNode;
import dataobjects.Account;
import elements.ButtonElement;
import elements.TextBoxElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.helpers.LocatorHelper;

import static utils.constants.TestConfigConstant.LOGIN_PAGE;

public class LoginPage extends PageBase {
    private static final Logger log = LogManager.getLogger(LoginPage.class.getName());

    private JsonNode locator = LocatorHelper.loadLocators(LOGIN_PAGE);

    private TextBoxElement txtUsername = new TextBoxElement(locator.get("txtInput").asText(), "username");
    private TextBoxElement txtPassword = new TextBoxElement(locator.get("txtInput").asText(), "password");
    private ButtonElement btnLogin = new ButtonElement(locator.get("btnLogin").asText());

    public void login(Account account) {
        log.debug("Login with username {} and password {}", account.getUsername(), account.getPassword());

        txtUsername.sendKeys(account.getUsername());
        txtPassword.sendKeys(account.getPassword());
        btnLogin.click();
    }
}
