package pages;

import com.fasterxml.jackson.databind.JsonNode;
import elements.ButtonElement;
import elements.LabelElement;
import elements.SelectElement;
import elements.TextBoxElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.helpers.LocatorHelper;

import static utils.constants.TestConfigConstant.PIM_REPORT_PAGE;

public class PIM_ReportsPage extends PageBase {
    private static final Logger log = LogManager.getLogger(PIM_ReportsPage.class.getName());

    private JsonNode locator = LocatorHelper.loadLocators(PIM_REPORT_PAGE);

    private ButtonElement tabReports = new ButtonElement(locator.get("PIMHeaderItem").asText(), "Reports");

    private SelectElement btnAddReport = new SelectElement(locator.get("btnAddReport").asText());
    private TextBoxElement txtReportName = new TextBoxElement(locator.get("txtReportName").asText());

    private SelectElement ddlDisplayFieldGroup = new SelectElement(locator.get("btnDisplayField").asText(), "Select Display Field Group", "-- Select --");
    private SelectElement ddlDisplayField = new SelectElement(locator.get("btnDisplayField").asText(), "Select Display Field", "-- Select --");
    private ButtonElement btnPlus = new ButtonElement(locator.get("btnPlus").asText());
    private ButtonElement btnSave = new ButtonElement(locator.get("btnSave").asText());

    public void clickReportsTab() {
        log.debug("Switch to 'Reports' tab");
        tabReports.click();
    }

    public void clickAddReportButton() {
        log.debug("click on 'Add' button");
        btnAddReport.click();
    }

    public void enterReportInfo(String reportName, String displayFieldGroup, String displayField) {
        log.debug("Enter report info");
        txtReportName.waitForElementToBeVisible();
        txtReportName.sendKeys(reportName);

        ddlDisplayFieldGroup.click();
        SelectElement ddlDisplayFieldGroupJS = new SelectElement(locator.get("btnDisplayFieldJS").asText(),
                "Select Display Field Group", displayFieldGroup);
        ddlDisplayFieldGroupJS.click();
        ddlDisplayFieldGroup.setdynamicBy("Select Display Field Group", displayFieldGroup);
        ddlDisplayFieldGroup.getElement().isDisplayed();

        ddlDisplayField.click();
        SelectElement ddlDisplayFieldJS = new SelectElement(locator.get("btnDisplayFieldJS").asText(),
                "Select Display Field", displayField);
        ddlDisplayFieldJS.click();
        ddlDisplayField.setdynamicBy("Select Display Field", displayField);
        ddlDisplayField.getElement().isDisplayed();
    }

    public void clickPlusButton() {
        log.debug("click on '+' button");
        btnPlus.click();
    }

    public void clickBtnSave() {
        log.debug("click on 'Save' button");
        btnSave.click();
    }

    public String getReportName() {
        log.debug("Getting report name after created");
        LabelElement lblReportName = new LabelElement(locator.get("lblReportName").asText());
        return lblReportName.getText();
    }
}
