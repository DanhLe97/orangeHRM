package pages;

import com.fasterxml.jackson.databind.JsonNode;

import drivers.DriverWrapper;
import elements.ButtonElement;
import elements.ElementWrapper;
import elements.LabelElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.helpers.LocatorHelper;

import static utils.constants.TestConfigConstant.BASE_PAGE;

public class PageBase {
    private static final Logger log = LogManager.getLogger(ElementWrapper.class.getName());
    protected JsonNode locator = LocatorHelper.loadLocators(BASE_PAGE);

    private LabelElement lblUserProfile = new LabelElement(locator.get("lblUserProfile").asText());
    private ButtonElement btnLogout = new ButtonElement(locator.get("btnLogout").asText());

    public void selectMenuItem(String item) {
    	DriverWrapper.refreshCurrentPage();
        log.debug("Navigate to menu item {}", item);
        LabelElement lblDashboardItem = new LabelElement(locator.get("lblLeftMenu").asText(), item);
        lblDashboardItem.click();
    }

    public void openUserProfileDropdown() {
        log.debug("Click on user profile");
        lblUserProfile.click();
    }

    public void logOut() {
        log.debug("Logging out");
        lblUserProfile.click();
        btnLogout.click();
    }
}
