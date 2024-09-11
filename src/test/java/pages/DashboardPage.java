package pages;

import com.fasterxml.jackson.databind.JsonNode;
import elements.LabelElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.helpers.LocatorHelper;

import static utils.constants.TestConfigConstant.DASHBOARD_PAGE;

public class DashboardPage extends PageBase{
    private static final Logger log = LogManager.getLogger(DashboardPage.class.getName());

    private JsonNode locator = LocatorHelper.loadLocators(DASHBOARD_PAGE);

    private LabelElement lblDashboard = new LabelElement(locator.get("lblDashboard").asText());


    public String getDashboardLabel() {
        log.debug("Get dashboard label");
        return lblDashboard.getElement().getText();
    }

}
